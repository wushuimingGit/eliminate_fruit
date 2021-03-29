package cn.wsm.fruit.utils;

import java.util.UUID;

public class UserIdUtil {

    public static String getUid(){
       return UUID.randomUUID().toString();
    }

}
