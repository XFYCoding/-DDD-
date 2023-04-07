package com.wallet.untils.Lazy;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 小肥瑜
 */
public interface Lazy<T> {
    T get();

    /**
     * 清空缓存，会导致重新加载
     *
     * @return 如果有缓存则返回true
     */
    boolean clean();

    /**
     * 是否已经初始化
     *
     * @return
     */
    boolean isInit();

    static <T> Lazy<T> build(T target) {
        return new NoneLazy<>(target);
    }

    static <T> Lazy<T> build(Supplier<T> supplier) {
        return new SupplierLazy<>(supplier);
    }

    static <T, R> Lazy<R> build(Function<T, R> function, T arg) {
        return new FunctionLazy<>(function, arg);
    }
}
