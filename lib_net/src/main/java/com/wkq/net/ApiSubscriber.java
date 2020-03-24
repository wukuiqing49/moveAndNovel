package com.wkq.net;


/**
 * Created by xiansong on 2017/9/8.
 */

public class ApiSubscriber {

    private static final String success_code = "0";
    private static final String success_message = "数据加载成功";

    public static final Subscriber.Config subscriberBuild = Subscriber.newConfig()
            .putInfo(Subscriber.Config.response_success, success_code, success_message) ;

    public static String getSuccessCode(){
        return success_code;
    }
}
