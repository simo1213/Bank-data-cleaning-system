package com.example.bert.Entity;

import lombok.Data;

@Data
public class SimBankData {
    private BankData bankData;//冗余流水
    private double sim;//相似度
}
