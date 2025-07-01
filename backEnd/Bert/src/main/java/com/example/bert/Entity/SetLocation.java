package com.example.bert.Entity;

import lombok.Data;

@Data
public class SetLocation {
    private String location;//键，对应数据项列号
    private String n;//键值，对应全局模式字段位置

    //将location转化为数据项在数组中的位置
    public int transform(String str) {
        String numberPart = str.substring("value".length());
        return (Integer.parseInt(numberPart)-1);
    }
}
