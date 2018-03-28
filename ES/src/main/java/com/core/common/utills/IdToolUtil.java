package com.core.common.utills;

import java.util.UUID;

/**
 * @author 孙文祥
 * @ClassName: IdToolUtil
 * @Description: TODO(生成32位id)
 * @date 2017年8月11日 下午4:51:24
 */
public class IdToolUtil {

    /**
     * 生成32位UUID 并去掉"-"
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
