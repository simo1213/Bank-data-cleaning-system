package com.example.bert.controller;

import com.example.bert.Entity.BankData;
import com.example.bert.Entity.Label;
import com.example.bert.Entity.SetLocation;
import com.example.bert.demos.web.User;
import com.example.bert.service.BankDataService;
import com.example.bert.service.BankDataServiceImpl;
import com.example.bert.service.BertService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController//注解，意味着类已经变成一个控制器，可以接收客户端请求，使用类中公开的方法接收
@CrossOrigin//允许当前控制器中的方法可以跨域
public class UploadController {
    @Autowired
    private BankDataServiceImpl bankDataService;
    @Autowired
    private BertService bertService;
    // spring自带的jackson工具类，可以使用它进行序列化实现格式转换
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String[] label = {"银行名称", "证件号码", "客户名称", "账号", "卡号", "币种", "交易日期", "交易时间", "借贷标志", "金额数量", "余额", "对方账号", "对方户名","对方银行"};

    //http://localhost:8088
    @GetMapping("/hello")//注解,代表浏览器发送Get请求访问Hello()方法
    public String Hello()
    {
        return "aa";
    }

    @PostMapping("/PostCSV")//进行模式匹配
    public List<Label> PostCSV(@RequestParam("file") MultipartFile file) throws IOException {

        System.out.println("上传成功");
        // 使用 BufferedReader 读取文件内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        // 定义 List 存储所有银行流水数据
        List<String[]> all_data = new ArrayList<>();

        // 重新定位 BufferedReader 到文件头部
        reader.close();
        reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        // 读取文件内容并保存到 all_data 中
        while ((line = reader.readLine()) != null) {
            // 将每一行数据存储到一个数组中
            String[] data = line.split(","); // 假设CSV文件是以逗号分隔的数据
            // 将数组添加到 all_data 列表中
            all_data.add(data);
        }
        // 打印 all_data 内容
//        for (String[] data : all_data) {
//            System.out.println("Bank Record: " + String.join(",", data));
//        }

        //数据存入数据库
        BankData bankData =new BankData();
        ObjectMapper mapper = new ObjectMapper();
        //获得数据规模
        //System.out.println("all_data_size:"+(all_data.size()-1));
        int[] location;
        location = new int[label.length];

        List<Label> PipeiResult=new ArrayList<>();//存储返回结果

        //获得目标匹配字段所在location
        for(int i=0;i<all_data.get(0).length; i++) //（i为字段所在列号）对于每个字段执行如下操作
        {
            JsonNode root = mapper.readTree(bertService.callFlaskApi(all_data.get(0)[i]));
            String result=root.get("result").asText();//获得输出结果
            //扫描label数组找到结果对应下标
            for(int n=0;n<label.length;n++)
            {
                if(label[n].equals(result))
                {
                    location[n]=i;
                    Label label1=new Label();
                    label1.setN(n);//存全局模式列号
                    label1.setLocation(location[n]);//存全局模式字段在数据中对应列号
                    label1.setLabel(label[n]);//存全局模式字段
                    label1.setDataname(all_data.get(0)[i]);//数据项字段名称
                    PipeiResult.add(label1);
                    //System.out.println("label第"+label1.getLocation()+"个是："+label1.getLabel());
                    System.out.println("第"+location[n]+"个是："+label[n]);
                    break;
                }
            }
        }
        return PipeiResult;
    }

    @PostMapping("/Upload")
    public String Upload(@RequestParam("file") MultipartFile file,@RequestParam("tableData")String tableData,@RequestParam("setLocation")String setLocation)throws IOException
    {
        System.out.println("setlocation:"+setLocation);
        System.out.println("tableData::"+tableData);
        System.out.println("含用户设置,模式匹配上传成功");

        List<Label>PipeiResult =objectMapper.readValue(tableData, new TypeReference<List<Label>>() {});
        List<SetLocation> setLocations=objectMapper.readValue(setLocation, new TypeReference<List<SetLocation>>() {});



        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        // 定义 List 存储所有银行流水数据
        List<String[]> all_data = new ArrayList<>();

        // 重新定位 BufferedReader 到文件头部
        reader.close();
        reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        // 读取文件内容并保存到 all_data 中
        while ((line = reader.readLine()) != null) {
            // 将每一行数据存储到一个数组中
            String[] data = line.split(","); // 假设CSV文件是以逗号分隔的数据
            // 将数组添加到 all_data 列表中
            all_data.add(data);
        }
//        // 打印 all_data 内容
//        for (String[] data : all_data) {
//            System.out.println("Bank Record: " + String.join(",", data));
//        }

        //数据存入数据库
        BankData bankData =new BankData();
        ObjectMapper mapper = new ObjectMapper();
        //获得数据规模
        //System.out.println("all_data_size:"+(all_data.size()-1));
        int[] location;
        location = new int[label.length];


        //找到全局模式对应的数据项的位置，即数据项列号
        for(int i=0;i<PipeiResult.size();i++)
        {
            if(PipeiResult.get(i).getLabel().equals(label[0]))
            {
                location[0]=PipeiResult.get(i).getLocation();
            } else if (PipeiResult.get(i).getLabel().equals(label[1])) {
                location[1]=PipeiResult.get(i).getLocation();
            } else if (PipeiResult.get(i).getLabel().equals(label[2])) {
                location[2]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[3])) {
                location[3]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[4])) {
                location[4]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[5])) {
                location[5]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[6])) {
                location[6]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[7])) {
                location[7]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[8])) {
                location[8]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[9])) {
                location[9]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[10])) {
                location[10]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[11])) {
                location[11]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[12])) {
                location[12]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[13])) {
                location[13]=PipeiResult.get(i).getLocation();
            }
        }

        //获得用户输入位置
        for(int i=0;i<setLocations.size();i++)
        {
            int num=Integer.parseInt(setLocations.get(i).getN());
            location[num]=setLocations.get(i).transform(setLocations.get(i).getLocation());
            System.out.println("location["+num+"]: "+location[num]);
        }

        //数据存入数据库
        for(int i=1;i<all_data.size();i++)
        {
            bankData.setBankName(all_data.get(i)[location[0]]);
            bankData.setIdNumber(all_data.get(i)[location[1]]);
            bankData.setCustomerName(all_data.get(i)[location[2]]);
            bankData.setAccountNumber(all_data.get(i)[location[3]]);
            bankData.setCardNumber(all_data.get(i)[location[4]]);
            bankData.setCurrency((all_data.get(i)[location[5]]));
            bankData.setTransactionDate(all_data.get(i)[location[6]]);//存日期
            bankData.setTransactionTime(all_data.get(i)[location[7]]);//存时间
            bankData.setTransactionType(all_data.get(i)[location[8]]);
            bankData.setAmount(Double.parseDouble(all_data.get(i)[location[9]]));
            bankData.setBalance(Double.parseDouble(all_data.get(i)[location[10]]));
            bankData.setCounterPartyAccount(all_data.get(i)[location[11]]);
            bankData.setCounterPartyName(all_data.get(i)[location[12]]);
            bankData.setCounterPartyBank(all_data.get(i)[location[13]]);
            bankDataService.save(bankData);//存入数据库
        }
        return "上传成功";
    }

    @PostMapping("/UploadNotSet")
    public String UploadNotSet(@RequestParam("file") MultipartFile file,@RequestParam("tableData")String tableData)throws IOException
    {
        System.out.println("tableData::"+tableData);
        System.out.println("无用户设置，模式匹配上传成功");

        List<Label>PipeiResult =objectMapper.readValue(tableData, new TypeReference<List<Label>>() {});

        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        // 定义 List 存储所有银行流水数据
        List<String[]> all_data = new ArrayList<>();

        // 重新定位 BufferedReader 到文件头部
        reader.close();
        reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        // 读取文件内容并保存到 all_data 中
        while ((line = reader.readLine()) != null) {
            // 将每一行数据存储到一个数组中
            String[] data = line.split(","); // 假设CSV文件是以逗号分隔的数据
            // 将数组添加到 all_data 列表中
            all_data.add(data);
        }

        BankData bankData =new BankData();
        ObjectMapper mapper = new ObjectMapper();

        int[] location;
        location = new int[label.length];

        //找到全局模式对应的数据项的位置，即数据项列号
        for(int i=0;i<PipeiResult.size();i++)
        {
            if(PipeiResult.get(i).getLabel().equals(label[0]))
            {
                location[0]=PipeiResult.get(i).getLocation();
            } else if (PipeiResult.get(i).getLabel().equals(label[1])) {
                location[1]=PipeiResult.get(i).getLocation();
            } else if (PipeiResult.get(i).getLabel().equals(label[2])) {
                location[2]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[3])) {
                location[3]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[4])) {
                location[4]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[5])) {
                location[5]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[6])) {
                location[6]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[7])) {
                location[7]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[8])) {
                location[8]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[9])) {
                location[9]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[10])) {
                location[10]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[11])) {
                location[11]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[12])) {
                location[12]=PipeiResult.get(i).getLocation();
            }else if (PipeiResult.get(i).getLabel().equals(label[13])) {
                location[13]=PipeiResult.get(i).getLocation();
            }
        }
        //数据存入数据库
        for(int i=1;i<all_data.size();i++)
        {
            bankData.setBankName(all_data.get(i)[location[0]]);
            bankData.setIdNumber(all_data.get(i)[location[1]]);
            bankData.setCustomerName(all_data.get(i)[location[2]]);
            bankData.setAccountNumber(all_data.get(i)[location[3]]);
            bankData.setCardNumber(all_data.get(i)[location[4]]);
            bankData.setCurrency((all_data.get(i)[location[5]]));
            bankData.setTransactionDate(all_data.get(i)[location[6]]);//存日期
            bankData.setTransactionTime(all_data.get(i)[location[7]]);//存时间
            bankData.setTransactionType(all_data.get(i)[location[8]]);
            bankData.setAmount(Double.parseDouble(all_data.get(i)[location[9]]));
            bankData.setBalance(Double.parseDouble(all_data.get(i)[location[10]]));
            bankData.setCounterPartyAccount(all_data.get(i)[location[11]]);
            bankData.setCounterPartyName(all_data.get(i)[location[12]]);
            bankData.setCounterPartyBank(all_data.get(i)[location[13]]);
            bankDataService.save(bankData);//存入数据库
        }

        return "上传成功";
    }

}
