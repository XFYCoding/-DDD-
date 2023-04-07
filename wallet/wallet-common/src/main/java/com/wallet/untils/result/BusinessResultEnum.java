package com.wallet.untils.result;

/**
 * 系统通用异常
 *
 * @author 小肥瑜
 */

public enum BusinessResultEnum implements BusinessFailResult {

    /**
     * 缺少参数
     */
    MISSING_PARAM("缺少参数：%s"),
    /**
     * 参数有误
     */
    BAD_PARAM("参数有误：%s"),
    /**
     * 数据状态已改变
     */
    DATA_STATUS_CHANGED("数据状态已改变"),
    /**
     * 数据不存在
     */
    DATA_NOT_FOUND("数据不存在!"),
    /**
     * 暂无访问权限，请联系管理员
     */
    OPERATION_NOT_AUTHORIZED("暂无访问权限，请联系管理员"),
    /**
     * 数据已存在
     */
    DATA_ALREADY_EXIST("数据已存在"),
    ;

    /**
     * 描述信息
     */
    private final String msg;

    BusinessResultEnum(String msg) {
        this.msg = msg;
    }

    @Override
    public String getCodeStr() {
        return this.name();
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public BusinessFailResult withMsgParams(Object... params) {
        String finalMsg = String.format(msg, params);
        return withMsg(finalMsg);
    }
}
