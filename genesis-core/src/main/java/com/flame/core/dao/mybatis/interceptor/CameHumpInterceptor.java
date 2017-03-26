package com.flame.core.dao.mybatis.interceptor;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MyBatis Map类型大写下划线Key转小写驼峰形式
 * 改为拦截ResultSetHandler，更简单的，而且可以避免一级缓存导致重复转换出错
 * Created by sungang on 2016/7/4.
 */
@Intercepts(
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
)
public class CameHumpInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //先执行，后处理
        List<Object> list = (List<Object>) invocation.proceed();
        for (Object object : list) {
            if (object instanceof Map) {
                processMap((Map) object);
            } else {
                break;
            }
        }
        return list;
    }

    /**
     * 处理简单对象
     * @param map
     */
    private void processMap(Map map) {
        Map cameHumpMap = new HashMap();
        Iterator<Map.Entry> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = (String) entry.getKey();
            String cameHumpKey = underlineToCamelhump(key.toLowerCase());
            if (!key.equals(cameHumpKey)) {
                cameHumpMap.put(cameHumpKey, entry.getValue());
                iterator.remove();
            }
        }
        map.putAll(cameHumpMap);
    }

    /**
     * 将下划线风格替换为驼峰风格
     * @param str
     * @return
     */
    public static String underlineToCamelhump(String str) {
        Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
