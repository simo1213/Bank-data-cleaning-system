<template>
    <div>
      <el-upload
          class="upload-demo"
          ref="upload"
          action="http://localhost:8088/PostCSV"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :on-success="handleSuccess"
          :auto-upload="false">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">模式匹配</el-button>
          <div slot="tip" class="el-upload__tip">请上传csv文件</div>
      </el-upload>

      <div>
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
    </div>
</template>

<script>
export default {
     data() {
      return {
        options: [
        {value: '选项2',label: '证件号码'}, 
        {value: '选项3',label: '客户名称'}, 
        {value: '选项4',label: '账号'}, 
        {value: '选项5',label: '卡号'},
        {value: '选项6',label: '币种'},
        {value: '选项7',label: '交易日期'},
        {value: '选项8',label: '交易时间'},
        {value: '选项9',label: '借贷标志'},
        {value: '选项10',label: '金额数量'},
        {value: '选项11',label: '余额'},
        {value: '选项12',label: '对方账号'},
        {value: '选项13',label: '对方户名'},
        {value: '选项14',label: '对方银行'}
        ],
        value1: [],value2: [],value3: [],value4: [],value5: [],value6: [],value7: [],
        value8: [],value9: [],value10: [],value11: [],value12: [],value13: [],value14: [],
      }
    },
    methods: {
      submitUpload() {
        this.$refs.upload.submit();
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleSuccess(response, file, fileList) {
      console.log('上传成功:', response);
      // 上传成功后不清空 fileList
      this.fileList = fileList; // 更新 fileList
      },
      retriggerUpload() {
      // 重新触发上传操作
      this.fileList.forEach(file => {
        this.$refs.upload.submit(file);
      });
    }
      
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