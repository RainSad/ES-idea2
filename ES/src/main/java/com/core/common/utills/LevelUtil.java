package com.core.common.utills;


import org.apache.commons.lang.StringUtils;

public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    //0
    //0.1
    //0.1.2
    //0.1.3
    //0.4
    public static String calculateLevel(String parentLevel, String parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return parentLevel + SEPARATOR + parentId;
        }
    }
}
