package com.wallet.untils.ddd;

import org.apache.commons.lang3.BooleanUtils;

import java.util.Date;

/**
 * @author 小肥瑜
 */
public abstract class BasePersistentEntity<ID, E extends Entity<ID>> implements EntityMeta<ID>, PersistentObject<E> {
    protected ID id;
    protected Long version;
    protected String createUser;
    protected String updateUser;
    protected Date createTime;
    protected Date updateTime;
    protected Boolean enabled;

    public BasePersistentEntity() {
    }

    @Override
    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean isEnabled() {
        return BooleanUtils.isTrue(this.enabled);
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void fillEntityMeta(E entityMeta) {
        this.id = entityMeta.getId();
        this.version = entityMeta.getVersion();
        this.createTime = entityMeta.getCreateTime();
        this.updateTime = entityMeta.getUpdateTime();
        this.createUser = entityMeta.getCreateUser();
        this.updateUser = entityMeta.getUpdateUser();
        this.enabled = entityMeta.isEnabled();
    }
}
