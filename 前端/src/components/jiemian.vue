<template>
    <div>
      <el-row class="tac">
        <el-col :span="12">
          <h5>自定义颜色</h5>
          <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">

          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-search"></i>
              <span>数据查询</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="1-1">查询原数据</el-menu-item>
              <el-menu-item index="1-2">查询已清洗数据</el-menu-item>

          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-upload2"></i>
            <span slot="title">数据上传</span>

          </el-menu>
        
        
        </el-col>
      </el-row>
    </div>
</template>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
</style>

<script>
  export default {
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>