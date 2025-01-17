package com.example.bert.Entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class BankData implements Serializable {

    private String bankName;
    private String idNumber;
    private String customerName;
    private String accountNumber;
    private String cardNumber;
    private String currency;//币种
    private String transactionDate;//日期
    private String transactionTime;//时分秒
    private String transactionType; // 借贷标志
    private double amount; // 金额数量
    private double balance; // 余额
    private String counterPartyAccount; // 对方账号
    private String counterPartyName; // 对方户名
    private String counterPartyBank;//对方银行
}



