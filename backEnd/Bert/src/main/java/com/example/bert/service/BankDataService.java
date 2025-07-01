package com.example.bert.service;

import com.example.bert.Entity.AfterCleanData;
import com.example.bert.Entity.BankData;

import java.util.List;

public interface BankDataService { //创建接口，规范服务层的功能
    public void save(BankData bankData);//数据插入
    public void savedata3(BankData bankData);//数据插入
    public List<BankData> queryBankData1List(String bankName);//data1数据查询
    public List<AfterCleanData> queryBankData2List(String bankName);//data2数据查询
    public List<BankData> queryBankData3List();//data3数据查询
    public List<String> findAllBankName();

    //查询相似银行数据
    public List<BankData> findBankBDataInRange(BankData bankDataA,String bankBName);



    //将已合并数据插入data2
    public void saveToData2Merge(AfterCleanData afterCleanData);
    //将未合并数据插入data2
    public void saveToData2NotMerge(BankData afterCleanData);

    //删除data3中冗余数据
    public void deleteData3AllData();
}
