package com.wallet.untils;

import com.wallet.untils.exception.BusinessException;
import com.wallet.untils.result.BusinessFailResult;
import com.wallet.untils.exception.MyException;
import com.wallet.untils.response.CommonResponse;
import com.wallet.untils.result.CodeMsgEnum;
import com.wallet.untils.result.DefaultResult;
import com.wallet.untils.result.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author 小肥瑜
 */
public final class AssertUtils {
    public static boolean isSuccess(CommonResponse response) {
        return response != null && response.getCode() != null && response.getCode() == 0;
    }

    private static void throwEx(Result result) {
        if (result instanceof BusinessFailResult) {
            throw new BusinessException((BusinessFailResult) result);
        } else {
            throw new MyException(result);
        }
    }

    public static void isNull(Object object, Result result) {
        if (object != null) {
            throwEx(result);
        }
    }

    public static void notNull(Object object, Result result) {
        if (object == null) {
            throwEx(result);
        }
    }

    public static void notNull(Object object, int code, String msg) {
        notNull(object, new DefaultResult(code, msg));
    }

    public static void notEmpty(String text, Result result) {
        if (StringUtils.isEmpty(text)) {
            throwEx(result);
        }
    }

    public static void notEmpty(String text, int code, String msg) {
        notEmpty(text, new DefaultResult(code, msg));
    }

    public static void notBlank(String text, Result result) {
        if (StringUtils.isBlank(text)) {
            throwEx(result);
        }
    }

    public static void notBlank(String text, int code, String msg) {
        notBlank(text, new DefaultResult(code, msg));
    }

    public static <T> void isEmpty(T[] array, Result result) {
        if (ArrayUtils.isNotEmpty(array)) {
            throwEx(result);
        }
    }

    public static <T> void notEmpty(T[] array, Result result) {
        if (ArrayUtils.isEmpty(array)) {
            throwEx(result);
        }
    }

    public static void isEmpty(Collection collection, Result result) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throwEx(result);
        }
    }

    public static void isEmpty(CharSequence charSequence, Result result) {
        if (StringUtils.isNotEmpty(charSequence)) {
            throwEx(result);
        }
    }

    public static void notEmpty(Collection collection, Result result) {
        if (CollectionUtils.isEmpty(collection)) {
            throwEx(result);
        }
    }

    public static void notEmpty(Collection collection, int code, String msg) {
        notEmpty(collection, new DefaultResult(code, msg));
    }


    public static void notEmpty(Map map, Result result) {
        if (MapUtils.isEmpty(map)) {
            throwEx(result);
        }
    }

    public static void notEmpty(Map map, int code, String msg) {
        notEmpty(map, new DefaultResult(code, msg));
    }

    public static void isTrue(Boolean value, Result result) {
        notNull(value, result);
        if (!value) {
            throwEx(result);
        }
    }

    public static void isTrue(Boolean value, int code, String msg) {
        isTrue(value, new DefaultResult(code, msg));
    }

    public static void isFalse(Boolean value, Result result) {
        notNull(value, result);
        if (value) {
            throwEx(result);
        }
    }

    public static void isFalse(Boolean value, int code, String msg) {
        isFalse(value, new DefaultResult(code, msg));
    }

    public static void anyNotNull(Collection collection, Result result) {
        notNull(collection, result);
        isTrue(collection.stream().anyMatch(Objects::nonNull), result);
    }

    public static void anyNotNull(Collection collection, int code, String msg) {
        anyNotNull(collection, new DefaultResult(code, msg));
    }

    public static void allNotNull(Collection collection, Result result) {
        notNull(collection, result);
        isTrue(collection.stream().allMatch(Objects::nonNull), result);
    }

    public static void allNotNull(Collection collection, int code, String msg) {
        allNotNull(collection, new DefaultResult(code, msg));
    }

    public static void equals(Object a, Object b, Result result) {
        if (!Objects.equals(a, b)) {
            throwEx(result);
        }
    }

    public static void equals(Object a, Object b, int code, String msg) {
        equals(a, b, new DefaultResult(code, msg));
    }

    public static void notEquals(Object a, Object b, Result result) {
        if (Objects.equals(a, b)) {
            throwEx(result);
        }
    }

    public static void notEquals(Object a, Object b, int code, String msg) {
        notEquals(a, b, new DefaultResult(code, msg));
    }

    public static void verify(SelfCheckRequest request) {
        AssertUtils.notNull(request, CodeMsgEnum.ERROR_MISSING_PARAMETER);
        request.verifyAndThrow();
    }

    public static void verify(SelfCheckRequest request, boolean trimValue) {
        AssertUtils.notNull(request, CodeMsgEnum.ERROR_MISSING_PARAMETER);
        request.verifyAndThrow(trimValue);
    }

    public static void maxLength(CharSequence sequence, int max, Result result) {
        isTrue(StringUtils.length(sequence) <= max, result);
    }

    public static void minLength(CharSequence sequence, int min, Result result) {
        isTrue(StringUtils.length(sequence) >= min, result);
    }

    public static void betweenLength(CharSequence sequence, int min, int max, Result result) {
        isTrue(StringUtils.length(sequence) <= max && StringUtils.length(sequence) >= min, result);
    }

    public static void maxByteLength(String sequence, int max, Result result) {
        maxByteLength(sequence, null, max, result);
    }

    public static void maxByteLength(String sequence, Charset charset, int max, Result result) {
        betweenByteLength(sequence, charset, Integer.MIN_VALUE, max, result);
    }

    public static void minByteLength(String sequence, int min, Result result) {
        minByteLength(sequence, null, min, result);
    }

    public static void minByteLength(String sequence, Charset charset, int min, Result result) {
        betweenByteLength(sequence, charset, min, Integer.MAX_VALUE, result);
    }

    public static void betweenByteLength(String sequence, int min, int max, Result result) {
        betweenByteLength(sequence, null, min, max, result);
    }

    public static void betweenByteLength(String sequence, Charset charset, int min, int max, Result result) {
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        final int length = StringUtils.length(sequence);
        final int halfMax = max / 2;
        if (min <= length && length <= halfMax) {
            return;
        }
        final int byteLength = sequence.getBytes(charset).length;
        isTrue(min <= byteLength && byteLength <= max, result);
    }

    public static void maxSize(Collection collection, int max, Result result) {
        isTrue(CollectionUtils.size(collection) <= max, result);
    }

    public static void minSize(Collection collection, int min, Result result) {
        isTrue(CollectionUtils.size(collection) >= min, result);
    }

    public static void betweenSize(Collection collection, int min, int max, Result result) {
        isTrue(CollectionUtils.size(collection) >= min && collection.size() <= max, result);
    }

    public static <T> void isPresent(Optional<T> opt, Result result) {
        if (!opt.isPresent()) {
            throwEx(result);
        }
    }
}
