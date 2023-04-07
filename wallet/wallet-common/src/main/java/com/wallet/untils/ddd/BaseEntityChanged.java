package com.wallet.untils.ddd;

import com.wallet.untils.Lazy.Lazy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author 小肥瑜
 */
public class BaseEntityChanged<ID, E extends Entity<ID>> {
    private final boolean lazyInit;
    private final Supplier<? extends Collection<E>> collGetter;
    private Lazy<Map<ID, Long>> lazyPreviousIdWithVersion;
    private final Set<ID> toRemoveIds;

    public BaseEntityChanged(Supplier<? extends Collection<E>> collGetter) {
        this(collGetter, false);
    }

    public BaseEntityChanged(Supplier<? extends Collection<E>> collGetter, boolean lazyInit) {
        this.toRemoveIds = new HashSet(3);
        this.lazyInit = lazyInit;
        this.collGetter = collGetter;
        this.reset();
    }

    public void reset() {
        this.lazyPreviousIdWithVersion = Lazy.build(() -> CollectionUtils.emptyIfNull(this.collGetter.get()).stream().collect(Collectors.toMap(EntityMeta::getId, EntityMeta::getVersion)));

        if (!this.lazyInit) {
            this.lazyPreviousIdWithVersion.get();
        }

        this.toRemoveIds.clear();
    }

    void ensureInitBeforeUpdate() {
        if (!this.lazyPreviousIdWithVersion.isInit()) {
            this.lazyPreviousIdWithVersion.get();
        }

    }

    public void diffCurrent() {
        this.genericDiffCurrent();
    }

    void genericDiffCurrent() {
        Collection<? extends Entity<?>> entities = (Collection) this.collGetter.get();
        this.toRemoveIds.clear();
        Map<ID, Long> previousIdWithVersion = (Map) this.lazyPreviousIdWithVersion.get();
        if (MapUtils.isNotEmpty(previousIdWithVersion)) {
            Set<ID> currentIds = (Set) CollectionUtils.emptyIfNull(entities).stream().map((e) -> {
                return e.getId();
            }).collect(Collectors.toSet());
            Iterator var4 = previousIdWithVersion.entrySet().iterator();

            while (var4.hasNext()) {
                Map.Entry<ID, Long> entry = (Map.Entry) var4.next();
                if (!currentIds.contains(entry.getKey())) {
                    this.toRemoveIds.add(entry.getKey());
                }
            }
        }

    }

    public boolean hasChanged() {
        return CollectionUtils.isNotEmpty(this.toRemoveIds);
    }

    public Map<ID, Long> getPreviousIdWithVersion() {
        return (Map) this.lazyPreviousIdWithVersion.get();
    }

    public Long getPreviousVersion(ID id) {
        return (Long) MapUtils.getObject((Map) this.lazyPreviousIdWithVersion.get(), id);
    }

    public Set<ID> getToRemoveIds() {
        return this.toRemoveIds;
    }
}
