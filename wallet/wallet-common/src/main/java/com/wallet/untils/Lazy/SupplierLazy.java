package com.wallet.untils.Lazy;

import java.util.function.Supplier;

/**
 * @author 小肥瑜
 */
public final class SupplierLazy<T> extends BaseLazy<T> {
    private final Supplier<T> supplier;

    SupplierLazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    protected T init() {
        return supplier.get();
    }
}