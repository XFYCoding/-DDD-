package com.wallet.untils.ddd;

import java.util.*;

/**
 * @author oopsliu
 */
public abstract class BasePojoSaveOperation<ID, P> {
    private List<P> newPos;
    private List<P> changedPos;
    private Map<ID, Long> idWithPreVersion;
    private Set<ID> toRemoveIds;
    private String updateUser;

    public BasePojoSaveOperation() {
        this.init();
    }

    private void init() {
        this.newPos = new ArrayList<>();
        this.changedPos = new ArrayList<>();
        this.idWithPreVersion = new HashMap<>(16);
        this.toRemoveIds = new HashSet<>();
    }

    public List<P> getNewPos() {
        return newPos;
    }

    public void setNewPos(List<P> newPos) {
        this.newPos = newPos;
    }

    public List<P> getChangedPos() {
        return changedPos;
    }

    public void setChangedPos(List<P> changedPos) {
        this.changedPos = changedPos;
    }

    public Map<ID, Long> getIdWithPreVersion() {
        return idWithPreVersion;
    }

    public void setIdWithPreVersion(Map<ID, Long> idWithPreVersion) {
        this.idWithPreVersion = idWithPreVersion;
    }

    public Set<ID> getToRemoveIds() {
        return toRemoveIds;
    }

    public void setToRemoveIds(Set<ID> toRemoveIds) {
        this.toRemoveIds = toRemoveIds;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}