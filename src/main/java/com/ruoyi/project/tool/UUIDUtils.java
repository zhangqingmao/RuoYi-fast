package com.ruoyi.project.tool;

import java.util.UUID;

public class UUIDUtils {

    /***
     * 获取去掉分隔符全大写的uuid
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    /**
     * 获取全数字UUID
     * @return
     */
    public static Integer getUUIDInOrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId;
        return orderId;
    }
}
