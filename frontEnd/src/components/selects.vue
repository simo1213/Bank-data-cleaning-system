<template>
  <div>
  <!-- 为el-select设置multiple属性即可启用多选，
        此时v-model的值为当前选中值所组成的数组。
        默认情况下选中值会以 Tag 的形式展现，
        也可以设置collapse-tags属性将它们合并为一段文字 -->
  <el-row :gutter="20">
    
    <h6 class="inline">字段1</h6>
    <el-select 
    v-model="value1" 
    size="mini"
    class="option inline"
    placeholder="请选择">
        <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
        </el-option>
    </el-select>

    <h6 class="inline">字段2</h6>
    <el-select
        v-model="value2"
        class="option"
        collapse-tags
        size="mini"
        placeholder="请选择">
        <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
        </el-option>
    </el-select>

    <h6 class="inline">字段3</h6>
    <el-select
    v-model="value3"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
    </el-select>

    <h6 class="inline">字段4</h6>
    <el-select
    v-model="value4"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
    </el-select>

    <h6 class="inline">字段5</h6>
    <el-select
    v-model="value5"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
    </el-select>

  <h6 class="inline">字段6</h6>
    <el-select
    v-model="value6"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>

  <h6 class="inline">字段7</h6>
        <el-select
            v-model="value7"
            collapse-tags
            size="mini"
            class="option"
            placeholder="请选择">
            <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
    </el-row>

    <el-row>
        <h6 class="inline">字段8</h6>
        <el-select
        v-model="value8"
        collapse-tags
        size="mini"
        class="option"
        placeholder="请选择">
        <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
        </el-option>
        </el-select>
  <h6 class="inline">字段9</h6>
  <el-select
    v-model="value9"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>

  <h6 class="inline">字段10</h6>
  <el-select
    v-model="value10"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>

  <h6 class="inline">字段11</h6>
  <el-select
    v-model="value11"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>

  <h6 class="inline">字段12</h6>
  <el-select
    v-model="value12"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>

  <h6 class="inline">字段13</h6>
  <el-select
    v-model="value13"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>

  <h6 class="inline">字段14</h6>
  <el-select
    v-model="value14"
    collapse-tags
    size="mini"
    class="option"
    placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
  </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        options: [
         {value: '选项2',
          label: '证件号码'
        }, {
          value: '选项3',
          label: '客户名称'
        }, {
          value: '选项4',
          label: '账号'
        }, {
          value: '选项5',
          label: '卡号'
        },{
          value: '选项6',
          label: '币种'
        },{
          value: '选项7',
          label: '交易日期'
        },{
          value: '选项8',
          label: '交易时间'
        },{
          value: '选项9',
          label: '借贷标志'
        },{
          value: '选项10',
          label: '金额数量'
        },{
          value: '选项11',
          label: '余额'
        },{
          value: '选项12',
          label: '对方账号'
        },{
          value: '选项13',
          label: '对方户名'
        },{
          value: '选项14',
          label: '对方银行'
        }
        ],
        value1: [],
        value2: [],
        value3: [],
        value4: [],
        value5: [],
        value6: [],
        value7: [],
        value8: [],
        value9: [],
        value10: [],
        value11: [],
        value12: [],
        value13: [],
        value14: [],
      }
    },
    methods:{

    }
  }
</script>

<style scoped>
.option{
    width: 100px;
}
.inline {
  display: inline-block;
  vertical-align: middle; /* 使内容垂直居中对齐 */
}
</style>