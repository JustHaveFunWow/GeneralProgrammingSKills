package com.knight.common.result;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * Created by geely
 */
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候,如果是null的对象,key也会消失
public class BaseServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private BaseServerResponse(int status){
        this.status = status;
    }
    private BaseServerResponse(int status, T data){
        this.status = status;
        this.data = data;
    }

    private BaseServerResponse(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private BaseServerResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return msg;
    }


    public static <T> BaseServerResponse<T> createBySuccess(){
        return new BaseServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> BaseServerResponse<T> createBySuccessMessage(String msg){
        return new BaseServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }


    public static <T> BaseServerResponse<T> createBySuccess(T data){
        return new BaseServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> BaseServerResponse<T> createBySuccess(String msg, T data){
        return new BaseServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    //特殊情况下
    public static <T> BaseServerResponse<T> createBySuccess(int code, String msg, T data){
        return new BaseServerResponse<T>(code,msg,data);
    }


    public static <T> BaseServerResponse<T> createByError(){
        return new BaseServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }


    public static <T> BaseServerResponse<T> createByErrorMessage(String errorMessage){
        return new BaseServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> BaseServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return new BaseServerResponse<T>(errorCode,errorMessage);
    }

}
