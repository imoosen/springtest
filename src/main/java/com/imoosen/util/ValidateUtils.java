package com.imoosen.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class ValidateUtils {
    ValidateUtils() {
    }

    public static List<String> validReadMethodPatterns(List<String> readMethodPatterns) {
        if(readMethodPatterns == null) {
            throw new IllegalArgumentException("readMethodPatterns arugment must not be null");
        } else {
            ArrayList compiledPattern = new ArrayList(readMethodPatterns.size());
            Iterator var3 = readMethodPatterns.iterator();

            while(var3.hasNext()) {
                String readMethodPattern = (String)var3.next();
                if(StringUtils.countOccurrencesOf(readMethodPattern, "*") > 2) {
                    throw new IllegalArgumentException("readMethodPatterns only suppoer follows pattern style: \"xxx*\", \"*xxx\", \"*xxx*\" and \"xxx*yyy\"  must not be null");
                }

                int first = readMethodPattern.indexOf(42);
                int last = readMethodPattern.lastIndexOf(42);
                if(first > 0 && last > 0 && first + 1 == last) {
                    throw new IllegalArgumentException("readMethodPatterns only suppoer follows pattern style: \"xxx*\", \"*xxx\", \"*xxx*\" and \"xxx*yyy\"  must not be null");
                }

                String tmp = readMethodPattern.trim();
                compiledPattern.add(tmp);
            }

            return compiledPattern;
        }
    }
}