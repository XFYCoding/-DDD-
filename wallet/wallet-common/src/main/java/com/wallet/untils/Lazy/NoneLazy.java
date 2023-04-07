package com.wallet.untils.Lazy;

/**
 * 为了支持属性类型是lazy，但实际上内容是已经初始化过了的
 *
 * @author 小肥瑜
 */
public final class NoneLazy<T> implements Lazy<T> {
    private final T result;

    public NoneLazy(T result) {
        this.result = result;
    }

    @Override
    public T get() {
        return result;
    }

    @Override
    public boolean clean() {
        return true;
    }

    @Override
    public boolean isInit() {
        return true;
    }
}