package com.imoosen.util;



import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class EntityUtils {
    public EntityUtils() {
    }

    public static void reflect(Object model) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field[] field = model.getClass().getDeclaredFields();
        String[] modelName = new String[field.length];
        String[] modelType = new String[field.length];

        for(int i = 0; i < field.length; ++i) {
            String name = field[i].getName();
            modelName[i] = name;
            String type = field[i].getGenericType().toString();
            modelType[i] = type;
            field[i].setAccessible(true);
            name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
            if(type.equals("class java.lang.String")) {
                Method m = model.getClass().getMethod("get" + name, new Class[0]);
                String value = null;

                try {
                    value = (String)m.invoke(model, new Object[0]);
                } catch (Exception var10) {
                    return;
                }

                if(value != null) {
                    field[i].set(model, field[i].getType().getConstructor(new Class[]{field[i].getType()}).newInstance(new Object[]{toHtmlOnly(value)}));
                }
            }
        }

    }

    public static String toHtmlOnly(String strSource) {
        if(StringUtils.isBlank(strSource)) {
            return strSource;
        } else {
            if(strSource.toLowerCase().indexOf("<br") != -1) {
                strSource = strSource.replaceAll("<br>", "\r\n");
                strSource = strSource.replaceAll("<br/>", "\r\n");
                strSource = strSource.replaceAll("<br />", "\r\n");
                strSource = strSource.replaceAll("<BR>", "\r\n");
                strSource = strSource.replaceAll("<BR />", "\r\n");
                strSource = strSource.replaceAll("<BR/>", "\r\n");
            }

            byte charLt = 60;
            byte charGt = 62;
            StringBuffer StrBufReturn = new StringBuffer();

            for(int i = 0; i < strSource.length(); ++i) {
                if(strSource.charAt(i) == charLt) {
                    StrBufReturn.append("&lt;");
                } else if(strSource.charAt(i) == charGt) {
                    StrBufReturn.append("&gt;");
                } else {
                    StrBufReturn.append(strSource.charAt(i));
                }
            }

            return StrBufReturn.toString().replaceAll("&lt;p&gt;", "<p>").replaceAll("&lt;/p&gt;", "</p>");
        }
    }
}
