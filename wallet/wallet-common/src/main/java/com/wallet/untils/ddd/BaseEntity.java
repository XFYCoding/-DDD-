package com.wallet.untils.ddd;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.BooleanUtils;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Supplier;

/**
 * @author 小肥瑜
 */
public abstract class BaseEntity<ID> implements Entity<ID> {
    private static final long INIT_VERSION = 1L;
    private transient Long previousVersion;
    protected final ID id;
    protected Long version;
    protected final String createUser;
    protected String updateUser;
    protected final Date createTime;
    protected Date updateTime;
    protected boolean enabled;
    private final Map<Class<? extends Entity<?>>, BaseEntityChanged<?, ? extends Entity<?>>> subEntityChanges;

    public BaseEntity(ID id, String createUser) {
        this.subEntityChanges = new HashMap(0);
        Date now = new Date();
        this.id = id;
        this.version = 1L;
        this.createUser = createUser;
        this.createTime = now;
        this.updateTime = now;
        this.enabled = true;
    }

    protected BaseEntity(EntityMeta<ID> entityMeta) {
        this(entityMeta.getId(), entityMeta.getVersion(), entityMeta.getCreateUser(), entityMeta.getUpdateUser(), entityMeta.getCreateTime(), entityMeta.getUpdateTime(), entityMeta.isEnabled());
    }

    protected <SID, SE extends Entity<SID>> void beforeSubEntityChange(Class<SE> subClassType, Supplier<Collection<SE>> getCollection, boolean lazyInit) {
        assert !this.subEntityChanges.containsKey(subClassType);

        BaseEntityChanged<SID, SE> beforeChange = new BaseEntityChanged(getCollection, lazyInit);
        this.subEntityChanges.put(subClassType, beforeChange);
    }

    protected <SID, SE extends Entity<SID>> void beforeSubEntityChange(Class<SE> subClassType, Supplier<Collection<SE>> getCollection) {
        this.beforeSubEntityChange(subClassType, getCollection, false);
    }

    public <SID, SE extends Entity<SID>> BaseEntityChanged<SID, SE> changesOf(Class<SE> entityType) {
        BaseEntityChanged<?, ? extends Entity<?>> v = (BaseEntityChanged)this.subEntityChanges.get(entityType);
        return (BaseEntityChanged)Optional.ofNullable(v).map((change) -> {
            change.genericDiffCurrent();
            return change;
        }).orElse(null);
    }

    protected BaseEntity(ID id, Long version, String createUser, String updateUser, Date createTime, Date updateTime, Boolean enabled) {
        this.subEntityChanges = new HashMap(0);
        this.id = id;
        this.version = version;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.enabled = BooleanUtils.isTrue(enabled);
        this.previousVersion = version;
    }

    @Override
    public Long previousVersion() {
        return this.previousVersion;
    }

    @Override
    public void onPersisted() {
        if (!this.subEntityChanges.isEmpty()) {
            Iterator var1 = this.subEntityChanges.values().iterator();

            while(var1.hasNext()) {
                BaseEntityChanged<?, ? extends Entity<?>> change = (BaseEntityChanged)var1.next();
                change.reset();
            }
        }

        this.previousVersion = this.version;
    }

    @Override
    public boolean isPersisted() {
        return this.previousVersion != null;
    }

    @Override
    public boolean isChanged() {
        return !Objects.equals(this.previousVersion, this.version);
    }

    protected void increaseVersion() {
        this.version = this.version + 1L;
    }

    protected void selfValidate() {
    }

    protected void onUpdate(String opUser) {
        this.selfValidate();
        if (this.isPersisted()) {
            if (!this.isChanged()) {
                this.increaseVersion();
            }

            this.updateTime = new Date();
            this.updateUser = opUser;
        }

    }

    protected void disable(String opUser) {
        if (!this.enabled) {
            throw new IllegalStateException("Already disabled.");
        } else {
            this.doUpdate(this.updateUser, () -> {
                this.enabled = false;
            });
        }
    }

    protected void doUpdate(String opUser, Runnable op) {
        assert op != null;

        op.run();
        this.onUpdate(opUser);
    }

    protected void doUpdateWithSubEntities(String opUser, Runnable op) {
        this.subEntityChanges.values().forEach(BaseEntityChanged::ensureInitBeforeUpdate);
        this.doUpdate(opUser, op);
    }

    protected void doUpdateWithSubEntities(String opUser, Runnable op, Class<?> subEntityType) {
        this.doUpdateWithSubEntities(opUser, op, (Set)(subEntityType == null ? null : SetUtils.hashSet(new Class[]{subEntityType})));
    }

    protected void doUpdateWithSubEntities(String opUser, Runnable op, Set<Class<?>> subEntityTypes) {
        if (CollectionUtils.isNotEmpty(subEntityTypes)) {
            this.subEntityChanges.entrySet().stream().filter((i) -> {
                return subEntityTypes.contains(i.getKey());
            }).map(Entry::getValue).forEach(BaseEntityChanged::ensureInitBeforeUpdate);
        }

        this.doUpdate(opUser, op);
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public Long getVersion() {
        return this.version;
    }

    @Override
    public String getCreateUser() {
        return this.createUser;
    }

    @Override
    public String getUpdateUser() {
        return this.updateUser;
    }

    @Override
    public Date getCreateTime() {
        return this.createTime;
    }

    @Override
    public Date getUpdateTime() {
        return this.updateTime;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
