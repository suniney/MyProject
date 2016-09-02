package com.example.yanxu.myproject.Base.callBack;

/**
 * Created by yanxu 2016/4/1.
 */
//实体类
public class Common {

    private  String errcode;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private  String msg;
//    private List errors;
//    private boolean isSuccess;
//
//    public boolean isSuccess() {
//        return isSuccess;
//    }
//
//    public void setIsSuccess(boolean isSuccess) {
//        this.isSuccess = isSuccess;
//    }
//    public List getErrors() {
//        return errors;
//    }
//
//    public void setErrors(ArrayList errors) {
//        this.errors = errors;
//    }
//
//
//    @Override
//    public String toString() {
//        return this.isSuccess + " " + errors;
//    }
}
