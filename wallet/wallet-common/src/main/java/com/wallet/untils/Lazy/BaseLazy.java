package com.wallet.untils.Lazy;

/**
 * @author 小肥瑜
 */
public abstract class BaseLazy<T> implements Lazy<T> {
    private final Object locker = new Object();
    private T result;
    private volatile boolean initFlag;

    protected BaseLazy() {
        this.initFlag = false;
    }

    protected BaseLazy(T result) {
        this.result = result;
        this.initFlag = true;
    }

    @Override
    public T get() {
        if (!initFlag) {
            synchronized (locker) {
                if (!initFlag) {
                    result = init();
                    initFlag = true;
                }
            }
        }
        return result;
    }

    protected abstract T init();

    @Override
    public boolean isInit() {
        return initFlag;
    }

    @Override
    public boolean clean() {
        if (initFlag) {
            synchronized (locker) {
                if (initFlag) {
                    result = null;
                    initFlag = false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
