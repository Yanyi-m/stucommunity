package com.hnnd.stucommunity.common.base;

import com.alibaba.fastjson.JSONObject;


public final class ResultModel {

    public static final int RETURN_CODE_SUCCESS = 0;
    public static final int RETURN_CODE_FAIL = 1;

    private ResultModel(int code, Object body) {
        this.code = code;
        this.body = body;
    }

    private int code = 0;
    private Object body;


    public int getCode() {
        return code;
    }
    public Object getBody() {
        return body;
    }

    public static ResultModel successModel(){
        return successModel("");
    }
    public static ResultModel successModel(Object body){
        return new ResultModel(RETURN_CODE_SUCCESS,body);
    }
    public static ResultModel failModel(){
        return failModel("");
    }
    public static ResultModel failModel(Object body){
        return new ResultModel(RETURN_CODE_FAIL,body);
    }
    
    public boolean isSuccess(){
        return getCode() == RETURN_CODE_SUCCESS;
    }
    public boolean isFail(){
        return getCode() == RETURN_CODE_FAIL;
    }
   /*
    public static void main(String[] args) {
    	ResultModel resultModel=ResultModel.successModel();	
		System.out.println(JSONObject.toJSONString((resultModel)));
	}*/
}