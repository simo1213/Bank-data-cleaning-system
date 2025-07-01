package com.example.bert.dao;

import com.example.bert.Entity.AfterCleanData;
import com.example.bert.Entity.BankData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//交给springbootboot管理的注解
public class BankDataDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //插入银行数据data1
    public void save(BankData bankData)
    {
        String sql = "INSERT INTO data1 (bank_name, id_number, customer_name, account_number, card_number, currency, " +
                "transaction_date, transaction_time, transaction_type, amount, balance, counter_party_account, counter_party_name,counter_party_bank) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        jdbcTemplate.update(sql, bankData.getBankName(), bankData.getIdNumber(), bankData.getCustomerName(),
                bankData.getAccountNumber(), bankData.getCardNumber(), bankData.getCurrency(),
                bankData.getTransactionDate(), bankData.getTransactionTime(), bankData.getTransactionType(),
                bankData.getAmount(), bankData.getBalance(), bankData.getCounterPartyAccount(),
                bankData.getCounterPartyName(),bankData.getCounterPartyBank());
    }
    public void savedata3(BankData bankData)
    {
        String sql = "INSERT INTO data3 (bank_name, id_number, customer_name, account_number, card_number, currency, " +
                "transaction_date, transaction_time, transaction_type, amount, balance, counter_party_account, counter_party_name,counter_party_bank) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        jdbcTemplate.update(sql, bankData.getBankName(), bankData.getIdNumber(), bankData.getCustomerName(),
                bankData.getAccountNumber(), bankData.getCardNumber(), bankData.getCurrency(),
                bankData.getTransactionDate(), bankData.getTransactionTime(), bankData.getTransactionType(),
                bankData.getAmount(), bankData.getBalance(), bankData.getCounterPartyAccount(),
                bankData.getCounterPartyName(),bankData.getCounterPartyBank());
    }
    //按银行名字查询data1
    public List<BankData> findByBankName(String bankName)
    {
        String sql = "SELECT bank_name, id_number, customer_name, account_number, card_number, currency, " +
                "transaction_date, transaction_time, transaction_type, amount, balance, counter_party_account, counter_party_name ,counter_party_bank" +
                " FROM data1 WHERE bank_name = ?";
        return jdbcTemplate.query(sql, new Object[]{bankName}, (rs, rowNum) -> {
            BankData bankData = new BankData();
            bankData.setBankName(rs.getString("bank_name"));
            bankData.setIdNumber(rs.getString("id_number"));
            bankData.setCustomerName(rs.getString("customer_name"));
            bankData.setAccountNumber(rs.getString("account_number"));
            bankData.setCardNumber(rs.getString("card_number"));
            bankData.setCurrency(rs.getString("currency"));
            bankData.setTransactionDate(rs.getString("transaction_date"));
            bankData.setTransactionTime(rs.getString("transaction_time"));
            bankData.setTransactionType(rs.getString("transaction_type"));
            bankData.setAmount(rs.getDouble("amount"));
            bankData.setBalance(rs.getDouble("balance"));
            bankData.setCounterPartyAccount(rs.getString("counter_party_account"));
            bankData.setCounterPartyName(rs.getString("counter_party_name"));
            bankData.setCounterPartyBank(rs.getString("counter_party_bank"));
            return bankData;
        });
    }
    //查询data3
    public List<BankData> findAllData3BankData() {
        String sql = "SELECT bank_name, id_number, customer_name, account_number, card_number, currency, " +
                "transaction_date, transaction_time, transaction_type, amount, balance, counter_party_account, counter_party_name, counter_party_bank " +
                "FROM data3";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            BankData bankData = new BankData();
            bankData.setBankName(rs.getString("bank_name"));
            bankData.setIdNumber(rs.getString("id_number"));
            bankData.setCustomerName(rs.getString("customer_name"));
            bankData.setAccountNumber(rs.getString("account_number"));
            bankData.setCardNumber(rs.getString("card_number"));
            bankData.setCurrency(rs.getString("currency"));
            bankData.setTransactionDate(rs.getString("transaction_date"));
            bankData.setTransactionTime(rs.getString("transaction_time"));
            bankData.setTransactionType(rs.getString("transaction_type"));
            bankData.setAmount(rs.getDouble("amount"));
            bankData.setBalance(rs.getDouble("balance"));
            bankData.setCounterPartyAccount(rs.getString("counter_party_account"));
            bankData.setCounterPartyName(rs.getString("counter_party_name"));
            bankData.setCounterPartyBank(rs.getString("counter_party_bank"));
            return bankData;
        });
    }

    //按银行名字查询data2
    public List<AfterCleanData> queryBankData2ListByName(String bankName)
    {
        String sql = "SELECT * FROM data2 WHERE bank_name = ?";
        return jdbcTemplate.query(sql, new Object[]{bankName}, new BeanPropertyRowMapper<>(AfterCleanData.class));
    }

    //查询全部银行名称
    public List<String> findAllBankName()
    {
        String sql = "SELECT DISTINCT bank_name FROM data1";
        return jdbcTemplate.queryForList(sql,String.class);
    }

    //查询Bank?的相似数据
    public List<BankData> findBankBDataInRange(BankData bankDataA,String bankBName)
    {
        double amount=bankDataA.getAmount();//获得金额，保证金额相等
        String accountNumber =bankDataA.getCounterPartyAccount();//获得A银行的、对方账号，保证账号相同
        String transactionDate=bankDataA.getTransactionDate();//获得交易日期
        String transactionType=bankDataA.getTransactionType();//判断交易类型
        String customer_name=bankDataA.getCounterPartyName();//A银行的对方户名为B银行的客户名
        String counterPartyName=bankDataA.getCustomerName();//A银行的客户名为B银行的对方户名

        String sql = "SELECT * FROM data1 WHERE bank_name = ? AND customer_name=? AND amount = ? AND transaction_date = ? AND account_number=? AND transaction_type !=? AND counter_party_name=?";
        return jdbcTemplate.query(sql, new Object[]{bankBName,customer_name,amount,transactionDate,accountNumber,transactionType,counterPartyName},
                new BeanPropertyRowMapper<>(BankData.class));
    }

    //将已合并数据插入data2
    public void saveToData2Merge(AfterCleanData afterCleanData)
    {
        String sql = "INSERT INTO data2(bank_name, id_number, customer_name, account_number, card_number, currency, " +
                "transaction_date, transaction_time, transaction_type, amount, balance, counter_party_account, counter_party_name, " +
                "counter_party_bank, counter_party_card_number, counter_party_id_number, counter_party_balance) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, afterCleanData.getBankName(), afterCleanData.getIdNumber(), afterCleanData.getCustomerName(),
                afterCleanData.getAccountNumber(), afterCleanData.getCardNumber(), afterCleanData.getCurrency(),
                afterCleanData.getTransactionDate(), afterCleanData.getTransactionTime(), afterCleanData.getTransactionType(),
                afterCleanData.getAmount(), afterCleanData.getBalance(), afterCleanData.getCounterPartyAccount(),
                afterCleanData.getCounterPartyName(), afterCleanData.getCounterPartyBank(), afterCleanData.getCounterPartyCardNumber(),
                afterCleanData.getCounterPartyIdNumber(), afterCleanData.getCounterPartyBalance());
    }

    //将未合并数据插入data2
    public void saveToData2NotMerge(BankData afterCleanData)
    {
        String sql = "INSERT INTO data2(bank_name, id_number, customer_name, account_number, card_number, currency, " +
                "transaction_date, transaction_time, transaction_type, amount, balance, counter_party_account, counter_party_name, " +
                "counter_party_bank, counter_party_card_number, counter_party_id_number, counter_party_balance) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, afterCleanData.getBankName(), afterCleanData.getIdNumber(), afterCleanData.getCustomerName(),
                afterCleanData.getAccountNumber(), afterCleanData.getCardNumber(), afterCleanData.getCurrency(),
                afterCleanData.getTransactionDate(), afterCleanData.getTransactionTime(), afterCleanData.getTransactionType(),
                afterCleanData.getAmount(), afterCleanData.getBalance(), afterCleanData.getCounterPartyAccount(),
                afterCleanData.getCounterPartyName(), afterCleanData.getCounterPartyBank(), null, null, null);
    }

    //删除冗余数据
    public void deleteData3AllData()
    {
        String sql="delete from data3;";
        jdbcTemplate.update(sql);
    }
}
