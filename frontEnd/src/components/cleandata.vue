<template>
    <el-row>
        <el-button type="primary" @click="submitUpload">数据清洗</el-button>
        <el-button type="primary" @click="deleteData3">删除冗余数据</el-button>
    </el-row>
</template>

<script>
import axios from 'axios'

export default {
    data() {
      return { };
    },
    components:
    {
        
    },
     methods: {
      submitUpload() {
        // 使用 Axios 发送 get 请求到后端 API
        axios.get('http://localhost:8088/cleanBankData')
        .then(Response=>{
            console.log(Response.data);
        })
        .catch(error=>{
            console.error("请求失败",error);
        })
      },
      deleteData3()
      {
        axios.get('http://localhost:8088/deleteData3')
          .then(Response=>{
              console.log(Response);
          })
          .catch(error=>{
              console.error("请求失败",error);
          })
      }
    }
}
</script>