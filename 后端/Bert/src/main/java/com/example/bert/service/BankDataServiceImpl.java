package com.example.bert.service;

import com.example.bert.Entity.AfterCleanData;
import com.example.bert.Entity.BankData;
import com.example.bert.dao.BankDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankDataServiceImpl implements BankDataService{
    @Autowired
    private BankDataDao dao;

    @Override
    public void save (BankData bankData)
    {
        dao.save(bankData);
    }
    @Override
    public void savedata3(BankData bankData){
        dao.savedata3(bankData);
    }//数据插入
    @Override
    public List<BankData> queryBankData1List(String bankName)
    {
        return dao.findByBankName(bankName);
    }
    @Override
    public List<AfterCleanData> queryBankData2List(String bankName)
    {
        return dao.queryBankData2ListByName(bankName);
    }
    @Override
    public List<BankData> queryBankData3List()//data3数据查询
    {
        return dao.findAllData3BankData();
    }
    @Override
    public List<String> findAllBankName()
    {
        return dao.findAllBankName();
    }

    @Override
    public List<BankData> findBankBDataInRange(BankData bankDataA,String bankBName)
    {
        return dao.findBankBDataInRange(bankDataA,bankBName);
    }

    //将已合并数据插入data2
    public void saveToData2Merge(AfterCleanData afterCleanData)
    {
        dao.saveToData2Merge(afterCleanData);
    }
    //将未合并数据插入data2
    public void saveToData2NotMerge(BankData afterCleanData)
    {
        dao.saveToData2NotMerge(afterCleanData);
    }
    public void deleteData3AllData()
    {
        dao.deleteData3AllData();
    }
}
