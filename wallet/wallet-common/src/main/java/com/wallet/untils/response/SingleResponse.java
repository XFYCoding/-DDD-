package com.wallet.untils.response;


import com.wallet.untils.result.CodeMsgEnum;

/**
 * @author 小肥瑜
 */
public class SingleResponse<T> extends CommonResponse {
    private T obj;

    public SingleResponse(T data) {
        super(CodeMsgEnum.SUCCESS);
        this.obj = data;
    }

    private SingleResponse(Integer code, String msg) {
        super(code, msg);
    }

    private SingleResponse(CodeMsgEnum codeMsgEnum, String msg) {
        super(codeMsgEnum, msg);
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }


    public static <T> SingleResponse<T> buildSuccess(T data) {
        return new SingleResponse<>(data);
    }

    public static SingleResponse<?> buildFail() {
        return buildFail(CodeMsgEnum.ERROR);
    }

    public static SingleResponse<?> buildFail(CodeMsgEnum codeMsgEnum) {
        return new SingleResponse(codeMsgEnum.getCode(), codeMsgEnum.getMsg());
    }

    public static SingleResponse<?> buildFail(int code, String msg) {
        return new SingleResponse(code, msg);
    }

    public static SingleResponse<?> buildFail(CodeMsgEnum codeMsgEnum, String msg) {
        return new SingleResponse(codeMsgEnum, msg);
    }

    public static SingleResponse<?> buildFail(String msg) {
        return new SingleResponse(CodeMsgEnum.ERROR, msg);
    }
}
