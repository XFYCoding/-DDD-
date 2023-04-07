package com.wallet.untils.response;

import com.wallet.untils.result.CodeMsgEnum;

import java.util.List;

/**
 * @author 小肥瑜
 */
public class ListResponse<T> extends CommonResponse {
    private List<T> items;

    public ListResponse() {
        super();
    }

    public ListResponse(List<T> items) {
        super(CodeMsgEnum.SUCCESS);
        this.items = items;
    }

    protected ListResponse(Integer code, String msg) {
        super(code, msg);
    }

    protected ListResponse(CodeMsgEnum codeMsgEnum, String msg) {
        super(codeMsgEnum, msg);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public static <T> ListResponse<T> buildSuccess(List<T> items) {
        return new ListResponse<>(items);
    }

    public static ListResponse<?> buildFail() {
        return buildFail(CodeMsgEnum.ERROR);
    }

    public static ListResponse<?> buildFail(CodeMsgEnum codeMsgEnum) {
        return new ListResponse(codeMsgEnum.getCode(), codeMsgEnum.getMsg());
    }


    public static ListResponse<?> buildFail(int code, String msg) {
        return new ListResponse(code, msg);
    }

    public static ListResponse<?> buildFail(CodeMsgEnum codeMsgEnum, String msg) {
        return new ListResponse(codeMsgEnum, msg);
    }

    public static ListResponse<?> buildFail(String msg) {
        return new ListResponse(CodeMsgEnum.ERROR, msg);
    }
}
