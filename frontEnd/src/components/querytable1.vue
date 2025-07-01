<template>
<div>
  <el-row>
      <el-input
          placeholder="请输入内容"
          v-model="input"
          clearable
          :style="{width:'200px'}">
      </el-input>
   
      <el-button type="primary" @click="fetchData()">查询</el-button>
  </el-row>

  <el-table
    :data="tableData"
    style="width: 100%"
    height="450">
    <el-table-column
      fixed
      prop="bankName"
      label="银行名"
      width="90">
    </el-table-column>

    <el-table-column
      prop="idNumber"
      label="身份证号"
      width="120">
    </el-table-column>

    <el-table-column
      prop="customerName"
      label="客户姓名"
      width="90">
    </el-table-column>

    <el-table-column
      prop="accountNumber"
      label="账号"
      width="120">
    </el-table-column>

    <el-table-column
      prop="cardNumber"
      label="卡号"
      width="120">
    </el-table-column>

    <el-table-column
      prop="currency"
      label="币种"
      width="60">
    </el-table-column>

    <el-table-column
      prop="transactionDate"
      label="交易日期"
      width="120">
    </el-table-column>

    <el-table-column
      prop="transactionTime"
      label="交易时间"
      width="120">
    </el-table-column>

    <el-table-column
      prop="transactionType"
      label="借贷标志"
      width="80">
    </el-table-column>

    <el-table-column
      prop="amount"
      label="交易金额"
      width="80">
    </el-table-column>

     <el-table-column
      prop="balance"
      label="余额"
      width="80">
    </el-table-column>

    <el-table-column
      prop="counterPartyAccount"
      label="对方账号"
      width="120">
    </el-table-column>
    
    <el-table-column
      prop="counterPartyName"
      label="对方姓名"
      width="80">
    </el-table-column>

    <el-table-column
      prop="counterPartyBank"
      label="对方银行"
      width="90">
    </el-table-column>
  </el-table>
</div>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        input:'',
        tableData: []
      }
    },
    methods: {
      fetchData() {
        const bankName = this.input;
        //Axios发送GET请求到后端API，并传递银行名称参数
        axios.get('http://localhost:8088/getdata1', {
          params: {
            bankName: bankName
          }
        })
        .then(response => {
          // 请求成功后将数据赋值给 tableData
          this.tableData = response.data;
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
      }
    }
  }
</script>