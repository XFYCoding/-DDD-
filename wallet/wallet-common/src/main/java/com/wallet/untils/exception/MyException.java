package com.wallet.untils.exception;

import com.wallet.untils.result.CodeMsgEnum;
import com.wallet.untils.result.Result;

/**
 * 自定义异常类
 *
 * @author 小肥瑜
 */
public class MyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private int errorCode;


    /**
     * 构造一个基本异常.
     *
     * @param message 信息描述
     */
    public MyException(String message) {
        super(message);
        this.errorCode = CodeMsgEnum.ERROR.getCode();
    }

    public MyException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum.getMsg());
        this.errorCode = codeMsgEnum.getCode();
    }

    public MyException(Result result) {
        this(result.getCode(), result.getMsg());
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message   信息描述
     */
    public MyException(int errorCode, String message) {
        this(errorCode, message, null);
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message   信息描述
     */
    public MyException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.setErrorCode(errorCode);
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}