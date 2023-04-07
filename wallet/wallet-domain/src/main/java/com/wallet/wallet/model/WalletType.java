package com.wallet.wallet.model;

import org.apache.commons.lang3.EnumUtils;

import java.util.List;

/**
 * @author 小肥瑜
 */

public enum WalletType {
    Fee("Fee", "消费"),
    ReFund("Fee", "消费");

    private String code;
    private String desc;

    WalletType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static WalletType findByCode(String code) {
        List<WalletType> values = EnumUtils.getEnumList(WalletType.class);
        for (WalletType e : values) {
            if (code.equals(e.getCode())) {
                return e;
            }
        }
        return null;
    }
}
