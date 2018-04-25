package com.knight.upms.client.shiro.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RequestParameterUtil {

    /**
     * 移除 url 中的code、username 参数
     * @param request
     * @return
     */
    public static String getParameterWithOutCode(HttpServletRequest request) {
        StringBuffer backUrl = request.getRequestURL();
        String params = "";
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (!"upms_code".equals(entry.getKey()) && !"upms_username".equals(entry.getKey())) {
                if ("".equals(params)) {
                    params = entry.getKey() + "=" + entry.getValue();
                } else {
                    params += "&" + entry.getKey() + "=" + entry.getValue()[0];
                }
            }

        }

        if (!StringUtils.isBlank(params)){
            backUrl = backUrl.append("?").append(params);
        }
        return backUrl.toString();
    }
}
