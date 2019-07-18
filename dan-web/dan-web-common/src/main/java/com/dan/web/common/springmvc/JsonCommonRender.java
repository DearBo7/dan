package com.dan.web.common.springmvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 返回json时使用, 支持json , jsonp, 需配合RequestContext 使用, 注意
 * Created by Dan on 2018/12/4.
 */
public class JsonCommonRender {

    private static Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static Gson nullGson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static final Logger logger = LoggerFactory.getLogger(JsonCommonRender.class);

    private final static String CALLBACK_REGEXP = "[^0-9a-zA-Z_.]";
    private final static int CALLBACK_MAX_LENGTH = 128;
    private final static Pattern PATTERN = Pattern.compile(CALLBACK_REGEXP);
    private final static String JSON_HEADER_APPEND = "\r\n\r\n";
    private final static String CHAR_SET = "UTF-8";
    private final static String dateFormat = "yyyy-MM-dd HH:mm:ss";


    public String getJsonResult(Object obj) {
        String callback = RequestContext.getStr("callback");
        RequestContext.getResponse().setCharacterEncoding(CHAR_SET);
        RequestContext.getResponse().setHeader("Cache-Control", "no-cache");
        String text;
        if (StringUtils.isNotBlank(callback)) {
            callback = StringEscapeUtils.escapeHtml4(callbackFilter(callback));
            RequestContext.getResponse().setHeader("Content-Type", "application/javascript");
            text = JSON_HEADER_APPEND + callback + "(" + getJson(obj) + ");";
        } else {
            RequestContext.getResponse().setHeader("Content-Type", "application/json");
            text = JSON_HEADER_APPEND + getJson(obj);
        }
        return text;
    }

    public String callbackFilter(String callback) {
        if (StringUtils.isEmpty(callback)) {
            return StringUtils.EMPTY;
        }

        String filterCallback = callback;

        if (StringUtils.length(filterCallback) > CALLBACK_MAX_LENGTH) {
            filterCallback = filterCallback.substring(0, CALLBACK_MAX_LENGTH);
        }

        Matcher m = PATTERN.matcher(filterCallback);

        filterCallback = m.replaceAll("");

        if (!StringUtils.equals(callback, filterCallback)) {
            logger.error("callback was filter, callback:" + callback + ",filterCallcack:" + filterCallback);
        }

        return filterCallback;
    }

    public String getJson(Object obj) {
        String result;
        Boolean aBooleanFlag = customizeGetJson(obj);
        if (aBooleanFlag != null) {
            if (aBooleanFlag) {
                result = gson.toJson(obj);
            } else {
                result = nullGson.toJson(obj);
            }
            return result;
        }

        /*if (obj instanceof cn.xtits.xtf.common.web.AjaxResult) {
            if (((cn.xtits.xtf.common.web.AjaxResult) obj).getsNulls()) {
                result = gson.toJson(obj);
            } else {
                result = nullGson.toJson(obj);
            }
        } else*/
        if (obj instanceof com.dan.utils.entity.AjaxResult) {
            if (((com.dan.utils.entity.AjaxResult) obj).getsNulls()) {
                result = gson.toJson(obj);
            } else {
                result = nullGson.toJson(obj);
            }
        } else if (obj instanceof com.dan.utils.entity.AjaxResultGeneric) {
            if (((com.dan.utils.entity.AjaxResultGeneric) obj).getsNulls()) {
                result = gson.toJson(obj);
            } else {
                result = nullGson.toJson(obj);
            }
        } else if (obj instanceof String) {
            result = obj.toString();
        } else {
            result = gson.toJson(obj);
        }
        return result;
    }

    /**
     * 通过继承方式,重写此方法处理json
     *
     * @param obj Object
     * @return 返回null, 使用默认处理,true返回原字段,false去掉字段为null的
     */
    protected Boolean customizeGetJson(Object obj) {

        return null;
    }
}