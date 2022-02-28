package com.yanxu.book.settingEnum;

import lombok.Data;


 public enum ParameterCodeEnum {

    LOGININ_HISTORY_TASK("1");


    private String parameterCode;

    ParameterCodeEnum(String ParameterCode){
        this.parameterCode=ParameterCode;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String ParameterCode){
        this.parameterCode=ParameterCode;
    }
}
