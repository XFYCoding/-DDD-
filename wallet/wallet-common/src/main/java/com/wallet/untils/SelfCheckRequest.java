package com.wallet.untils;

/**
 * @author 小肥瑜
 */
public interface SelfCheckRequest {
    /**
     * 校验，如果不通过则抛出异常
     * @param trimValue 对String类型入参进行trim处理
     */
    void verifyAndThrow(boolean trimValue);
    /**
     * 校验并对String类型入参进行trim处理
     */
    default void verifyAndThrow() {
        verifyAndThrow(true);
    }
}
