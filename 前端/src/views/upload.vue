<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple">
        <h1>数据导入</h1>
      </div></el-col>
    </el-row>
    
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple-dark">
        <uploadv2></uploadv2>
        </div>
        </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple">
        <h1>数据清洗</h1>
        <cleandata2></cleandata2>
      </div></el-col>
    </el-row>

  </div>
</template>

<script>

import cleandata2 from '@/components/cleandata2.vue'
import uploadv2 from '@/components/uploadv2.vue'
export default {
  components: {
    cleandata2,
    uploadv2,

  },
}
</script>


<style>
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #ffffff;
  }
  .bg-purple {
    background: #eef2f7;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>