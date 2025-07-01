<template>
  <div>
    <h1>查询未清洗数据</h1>
    <querytable1></querytable1>
  </div>
</template>


<script>
import axios from 'axios'
import querytable1 from '@/components/querytable1.vue'
import querytable2 from '@/components/querytable2.vue'
import cleandata from '@/components/cleandata.vue'

export default {
  components: {
  
    querytable1,
    querytable2,
    
    
  },
}
</script>