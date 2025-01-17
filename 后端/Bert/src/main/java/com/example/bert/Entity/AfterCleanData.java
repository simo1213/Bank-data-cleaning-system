package com.example.bert.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AfterCleanData implements Serializable {
    private String bankName;
    private String idNumber;
    private String customerName;
    private String accountNumber;
    private String cardNumber;
    private String currency;
    private String transactionDate;
    private String transactionTime;
    private String transactionType; // 借贷标志
    private double amount; // 金额数量
    private double balance; // 余额
    private String counterPartyAccount; // 对方账号
    private String counterPartyName; // 对方户名
    private String counterPartyBank;//对方银行
    private String counterPartyCardNumber;//对方卡号
    private String counterPartyIdNumber; //对方身份证
    private String counterPartyBalance; // 对方余额
}
