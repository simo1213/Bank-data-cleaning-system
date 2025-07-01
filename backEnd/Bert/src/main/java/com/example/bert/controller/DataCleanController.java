package com.example.bert.controller;

import com.example.bert.Entity.*;
import com.example.bert.service.BankDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class DataCleanController {
    @Autowired
    private BankDataServiceImpl bankDataService;
//    private List<BankData> afterCleanDataNotMerge= new ArrayList<>();//清洗后未合并bankdata数据
//    private List<AfterCleanData> afterCleanDataMerge= new ArrayList<>();//清洗后合并bankdata数据
//    private List<BankData> redundancyData= new ArrayList<>();//冗余bankdata数据

    //查询冗余数据
    @GetMapping("/querydata3")
    public List<BankData> getBankDataByBankName()
    {
        return bankDataService.queryBankData3List();
    }
    //数据清洗port=8088
    @GetMapping("/cleanBankData")
    public String cleanBankData()
    {
        List<BankData> afterCleanDataNotMerge= new ArrayList<>();//清洗后未合并bankdata数据
        List<AfterCleanData> afterCleanDataMerge= new ArrayList<>();//清洗后合并bankdata数据
        List<BankData> redundancyData= new ArrayList<>();//冗余bankdata数据
        //获得所有银行的名字
        List<String> allbanname=bankDataService.findAllBankName();

        System.out.println("全部银行名称"+allbanname.get(0)+allbanname.get(1));

        //根据名字获取银行A数据
        List<BankData> bankdataA= bankDataService.queryBankData1List(allbanname.get(0));
        System.out.println("bankdataA.size():"+bankdataA.size());
        for(int i=0;i<bankdataA.size();i++)
        {
            //A-A或者A-C(非B)
            if(bankdataA.get(i).getBankName().equals(bankdataA.get(i).getCounterPartyBank())||!(bankdataA.get(i).getCounterPartyBank().equals(allbanname.get(1))))
            {
                System.out.println("未合并的插入数据，客户姓名："+bankdataA.get(i).getCustomerName());
                afterCleanDataNotMerge.add(bankdataA.get(i));continue;//该数据不冗余，添加至清洗后
            }
            //分块查询银行b数据
            List<BankData> bankdataB=bankDataService.findBankBDataInRange(bankdataA.get(i),allbanname.get(1));

            if(!bankdataB.isEmpty())//有冗余
            {
                System.out.println("针对"+bankdataA.get(i).getCustomerName()+"进行分块查询bankdataB.size():"+bankdataB.size());
                List<SimBankData> simBankDataList = new ArrayList<>();//存相似度和对应银行数据
                for(int y=0;y<bankdataB.size();y++)//对每一条B银行流水
                {
                    DataCleanController dataCleanController=new DataCleanController();
                    SimBankData simBankData1=new SimBankData();
                    simBankData1.setBankData(bankdataB.get(y));//获得b银行数据
                    //获得相似度
                    simBankData1.setSim(dataCleanController.calculateTimeSimilarity(bankdataB.get(y).getTransactionTime(),bankdataA.get(i).getTransactionTime()));
                    simBankDataList.add(simBankData1);//添加至列表
                }
                //获得相似度最大值
                double maxSim = simBankDataList.get(0).getSim();
                BankData maxSimBankData = simBankDataList.get(0).getBankData();
                //循环遍历列表，比较每个元素的 sim 属性与当前最大相似度，更新最大相似度和对应的 BankData
                for (SimBankData simBankData : simBankDataList) {
                    if (simBankData.getSim() > maxSim) {
                        maxSim = simBankData.getSim();
                        maxSimBankData = simBankData.getBankData();
                    }
                }
                // 输出最大相似度对应的 BankData
                //System.out.println("最大相似度对应的 BankData：" + maxSimBankData);
                //数据合并
                //获得合并数据
                AfterCleanData afterCleanData1=new AfterCleanData();
                afterCleanData1.setBankName(bankdataA.get(i).getBankName());
                afterCleanData1.setIdNumber(bankdataA.get(i).getIdNumber());
                afterCleanData1.setCustomerName(bankdataA.get(i).getCustomerName());
                afterCleanData1.setAccountNumber(bankdataA.get(i).getAccountNumber());
                afterCleanData1.setCardNumber(bankdataA.get(i).getCardNumber());
                afterCleanData1.setCurrency(bankdataA.get(i).getCurrency());
                afterCleanData1.setTransactionDate(bankdataA.get(i).getTransactionDate());
                afterCleanData1.setTransactionTime(bankdataA.get(i).getTransactionTime());
                afterCleanData1.setTransactionType(bankdataA.get(i).getTransactionType());
                afterCleanData1.setAmount(bankdataA.get(i).getAmount());
                afterCleanData1.setBalance(bankdataA.get(i).getBalance());
                afterCleanData1.setCounterPartyAccount(maxSimBankData.getAccountNumber());
                afterCleanData1.setCounterPartyName(maxSimBankData.getCustomerName());
                afterCleanData1.setCounterPartyBank(maxSimBankData.getBankName());
                afterCleanData1.setCounterPartyCardNumber(maxSimBankData.getCardNumber());
                afterCleanData1.setCounterPartyIdNumber(maxSimBankData.getIdNumber());
                afterCleanData1.setCounterPartyBalance(Double.toString(maxSimBankData.getBalance()));//Double转String
                //合并数据插入
                afterCleanDataMerge.add(afterCleanData1);
                //记录冗余数据
                redundancyData.add(maxSimBankData);
            }else {
                System.out.println(bankdataA.get(i).getCustomerName()+"无相似数据");
                afterCleanDataNotMerge.add(bankdataA.get(i));//无冗余，添加至清洗后
            }
        }
        //清洗数据insert到表data2中
        for(int x=0;x<afterCleanDataNotMerge.size();x++)
        {
            bankDataService.saveToData2NotMerge(afterCleanDataNotMerge.get(x));

        }
        for(int x=0;x< afterCleanDataMerge.size();x++)
        {
            bankDataService.saveToData2Merge(afterCleanDataMerge.get(x));
        }
        //冗余数据存入data3
        for(int x=0;x<redundancyData.size();x++)
        {
            bankDataService.savedata3(redundancyData.get(x));
        }
        System.out.println("清洗结束");
        return "数据清洗结束";
    }

    @GetMapping("/deleteData3")
    public String deleteData3()
    {
        bankDataService.deleteData3AllData();
        return "data3数据删除成功";
    }

    //定义时间相似性计算方法
    public double calculateTimeSimilarity(String time1, String time2) {
        LocalTime lt1 = LocalTime.parse(time1, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime lt2 = LocalTime.parse(time2, DateTimeFormatter.ofPattern("HH:mm:ss"));
        long secondsDifference = Math.abs(lt1.toSecondOfDay() - lt2.toSecondOfDay());
        return 1.0 / (1.0 + secondsDifference); // 返回相似性得分，值越接近1表示越相似
    }
}
