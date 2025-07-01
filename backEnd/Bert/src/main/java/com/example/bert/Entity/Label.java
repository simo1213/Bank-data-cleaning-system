package com.example.bert.Entity;

import lombok.Data;

@Data
//用于显示模式匹配结果
public class Label {
    private int n;//全局模式字段列号
    private int location;//全局模式列号在数据项中的对应位置
    private String label;//全局模式标签
    private String dataname;//数据项名称
}
