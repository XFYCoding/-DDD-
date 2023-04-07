package com.wallet.untils;

import java.util.function.Supplier;

/**
 * 链路追踪上下文工具
 *
 * @author 小肥瑜
 */
public class TraceContextUtils {

    private static Supplier<String> traceIdSupplier;

    /**
     * 注册一个可用的链路上下文 traceId 提供者
     *
     * @param traceIdSupplier traceId 提供者
     */
    public static void registerSupplier(Supplier<String> traceIdSupplier) {
        TraceContextUtils.traceIdSupplier = traceIdSupplier;
    }

    /**
     * 获取当前链路上下文的 traceId
     *
     * @return traceId
     */
    public static String getTraceId() {
        if (traceIdSupplier != null) {
            return traceIdSupplier.get();
        }
        return null;
    }
}
