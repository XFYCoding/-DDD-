package com.wallet.untils.response;

import com.wallet.untils.result.CodeMsgEnum;
import com.wallet.untils.TraceContextUtils;

/**
 * @author 小肥瑜
 */
public class CommonResponse {

    private Integer code;
    private String codeStr;
    private String msg;
    private String traceId;

    public CommonResponse() {
        this.traceId = TraceContextUtils.getTraceId();
    }


    public CommonResponse(CodeMsgEnum codeMsgEnum) {
        this(codeMsgEnum.getCode(), codeMsgEnum.getMsg());
    }

    public CommonResponse(Integer code, String msg) {
        this(code, null, msg);
    }

    public CommonResponse(Integer code, String codeStr, String msg) {
        this();
        this.code = code;
        this.codeStr = codeStr;
        this.msg = msg;
    }

    public CommonResponse(CodeMsgEnum codeMsgEnum, String msg) {
        this(codeMsgEnum.getCode(), msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static CommonResponse buildSuccess() {
        CommonResponse response = new CommonResponse(CodeMsgEnum.SUCCESS);
        return response;
    }

    public static CommonResponse buildFail() {
        return buildFail(CodeMsgEnum.ERROR);
    }

    public static CommonResponse buildFail(CodeMsgEnum codeMsgEnum) {
        return new CommonResponse(codeMsgEnum);
    }

    public static CommonResponse buildFail(int code, String msg) {
        return new CommonResponse(code, msg);
    }

    public static CommonResponse buildFail(CodeMsgEnum codeMsgEnum, String msg) {
        return new CommonResponse(codeMsgEnum, msg);
    }

    public static CommonResponse buildFail(String msg) {
        CommonResponse response = new CommonResponse(CodeMsgEnum.ERROR, msg);
        return response;
    }

    public String getCodeStr() {
        return codeStr;
    }

    public void setCodeStr(String codeStr) {
        this.codeStr = codeStr;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
