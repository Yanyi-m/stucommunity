package com.hnnd.stucommunity.config.dictionary;

/**
 * Created by liuyishan on 15/9/5.
 */
public class   PayType {

//    WX_PAY_JSAPI(1,"WxJsPay"),ALI_PAY_WAP(2,"AliWapPay");
//
//    PayType(int key,String name){
//        this.key = key;
//        this.name = name;
//    }
//
//    private int key;
//    private String name;
//
//    public PayType valueOf(int key){
//        for(PayType tmp:PayType.values()){
//            if(tmp.key == key)
//                return tmp;
//        }
//
//        return null;
//    }
//
//    public String getName() {
//        return name;
//    }

    public static final String HLJ_PAY = "HljPay";

    public static final String WX_PAY_JSAPI = "WxJsPay";

    public static final String WX_PAY_APP = "WxAppPay";

    public static final String ALI_PAY_WAP = "AliWapPay";

    public static final String LIAN_LIAN_WAP_PAY = "LianLianWapPay";

    public static final String DEFAULT = WX_PAY_JSAPI;
}
