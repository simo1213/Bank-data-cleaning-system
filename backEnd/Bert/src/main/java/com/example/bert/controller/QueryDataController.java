package com.example.bert.controller;

import com.example.bert.Entity.AfterCleanData;
import com.example.bert.Entity.BankData;
import com.example.bert.service.BankDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class QueryDataController {
    @Autowired
    private BankDataServiceImpl bankDataService;

    @GetMapping("/getdata1")//查询原始数据
    public List<BankData> queryData1(@RequestParam String bankName)
    {
        return bankDataService.queryBankData1List(bankName);
    }
    @GetMapping("/getdata2")//查询清洗数据
    public List<AfterCleanData> queryData2(@RequestParam String bankName)
    {
//        List<AfterCleanData> dataList= bankDataService.queryBankData2List(bankName);
//        for (int i=0;i<dataList.size();i++)
//        {
//            if(dataList.get(i).getCounterPartyBalance())
//        }
        return bankDataService.queryBankData2List(bankName);
    }
    //查询data3，冗余数据
    @GetMapping("/getdata3")
    public List<BankData> getBankDataByBankName(@RequestParam String bankName) {
        return bankDataService.queryBankData1List(bankName);
    }
}
