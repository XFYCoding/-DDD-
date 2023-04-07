package com.wallet.untils.response;


import com.wallet.untils.result.CodeMsgEnum;

import java.util.List;

/**
 * @author 小肥瑜
 */
public class PageResponse<T> extends ListResponse<T> {
    private static final long serialVersionUID = 1644608856300749235L;

    private PageInfo pageInfo;

    public PageResponse() {
        super();
    }

    public PageResponse(List<T> items, PageInfo pageInfo) {
        super(items);
        this.pageInfo = pageInfo;
    }

    private PageResponse(Integer code, String msg) {
        super(code, msg);
    }

    private PageResponse(CodeMsgEnum codeMsgEnum, String msg) {
        super(codeMsgEnum, msg);
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public static <T> PageResponse<T> buildSuccess(List<T> items, PageInfo pageInfo) {
        return new PageResponse<>(items, pageInfo);
    }

    public static <T> PageResponse<T> buildSuccess(PageResult<T> result) {
        return new PageResponse<>(result.getItems(), result.getPageInfo());
    }

    public static PageResponse<?> buildFail() {
        return buildFail(CodeMsgEnum.ERROR);
    }

    public static PageResponse<?> buildFail(CodeMsgEnum codeMsgEnum) {
        return new PageResponse(codeMsgEnum.getCode(), codeMsgEnum.getMsg());
    }


    public static PageResponse<?> buildFail(int code, String msg) {
        return new PageResponse(code, msg);
    }

    public static PageResponse<?> buildFail(CodeMsgEnum codeMsgEnum, String msg) {
        return new PageResponse(codeMsgEnum, msg);
    }

    public static PageResponse<?> buildFail(String msg) {
        return new PageResponse(CodeMsgEnum.ERROR, msg);
    }

}
