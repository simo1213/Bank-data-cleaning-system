<template>
  <div id="app">
      <div>
      <el-container style="height: 500px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-row class="tac">
        
          <el-menu
          width="100px"
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">

          <el-submenu index="/">
            <template slot="title">
              <i class="el-icon-search"></i>
              <span>数据查询</span>
              
            </template>
            <el-menu-item-group>
               <el-menu 
                default-active="2"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b"
                :router="true"
                >
                
                <el-menu-item index="/queryData1">查询未清洗数据</el-menu-item>
                <el-menu-item index="/queryData2">查询已清洗数据</el-menu-item>
                </el-menu>
             </el-menu-item-group>
          </el-submenu>

          <el-menu-item index="/">
            <i class="el-icon-upload2"></i>
            <router-link to="/" class="custom-link">数据上传</router-link>

          </el-menu-item>
          </el-menu>
        
      </el-row>
      </el-aside>

        <el-container>
           <el-main>
           <router-view></router-view><!-- 路由匹配到的组件将渲染在这里 -->
           </el-main>
        </el-container>
      </el-container>
    </div>




    <!-- <upload></upload>
    <cleandata></cleandata> -->
    <!-- <querytable1></querytable1> -->
    <!-- <querytable2></querytable2> -->
    
  </div>
</template>

<script>

import axios from 'axios'
import upload from './components/upload.vue'
import * as XLSX from 'xlsx'
import querytable1 from './components/querytable1.vue'
import querytable2 from './components/querytable2.vue'
import cleandata from './components/cleandata.vue'
import selects from './components/selects.vue'

export default {
  name: 'App',
  data:function()
  {
    return{
      
    }
  },
  created() {
    // axios.get("http:localhost:8088/hello").then(function (response) {
    //   console.log(response)
    // })
  },
  components: {
    upload,
    querytable1,
    querytable2,
    cleandata,
    selects,
  },
  methods: {
      handleOpen(key, keyPath) {
        // console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        // console.log(key, keyPath);
      }
    }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.custom-link {
    text-decoration: none;
    color: white;
}
</style>
