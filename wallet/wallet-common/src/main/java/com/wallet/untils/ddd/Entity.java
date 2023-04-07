package com.wallet.untils.ddd;

public interface Entity<ID> extends EntityMeta<ID> {
    boolean isChanged();

    boolean isPersisted();

    void onPersisted();

    Long previousVersion();
}
