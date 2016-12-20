package com.lxz.xxx.common.util;

import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import java.util.Map;

/**
 * 表达式引擎
 *
 * Created by xiaolezheng on 16/9/1.
 */
@Slf4j
public final class ExpressionEngine {
    private static final ParserContext ctx = new ParserContext();

    static {
        ctx.addPackageImport("java.util");
        ctx.addImport(org.apache.commons.lang3.StringUtils.class);
    }

    public static <T> T eval(String expression, Map<String, Object> params, Class<T> type) {
        try {
            Object result = MVEL.eval(expression, ctx, params, type);
            if (result != null) {
                return type.cast(result);
            }
        } catch (Exception e) {
            log.error("表达式执行异常, params: {}", params, e);
        }

        return null;
    }
}
