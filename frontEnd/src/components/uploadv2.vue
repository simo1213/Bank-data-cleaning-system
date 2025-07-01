<template>
  <div>
    <el-upload
      class="upload-demo"
      ref="upload"
      action="http://localhost:8088/PostCSV"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :on-change="handleChange"
      :file-list="fileList"
      :auto-upload="false">
      <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
      <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">模式匹配</el-button>
      <div slot="tip" class="el-upload__tip">请上传csv文件</div>
    </el-upload>
   
    <el-row justify-content: center>
        <el-table :data="tableData" style="width: 600px" height="200">
            <el-table-column
            fixed
            prop="location"
            label="数据项列号"
            width="90">
            </el-table-column>

            <el-table-column
            fixed
            prop="dataname"
            label="数据项"
            width="90">
            </el-table-column>

            <el-table-column
            prop="label"
            label="全局字段匹配结果"
            width="160">
            </el-table-column>

            <el-table-column
            prop="n"
            label="全局模式字段列号"
            width="160">
            </el-table-column>
        </el-table>
    </el-row>
  
    <div>
            <el-row :gutter="20">
            
            <h6 class="inline">字段0</h6>
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

            <h6 class="inline">字段1</h6>
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

            <h6 class="inline">字段2</h6>
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

            <h6 class="inline">字段3</h6>
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

            <h6 class="inline">字段4</h6>
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

          <h6 class="inline">字段5</h6>
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

          <h6 class="inline">字段6</h6>
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
                <h6 class="inline">字段7</h6>
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
          <h6 class="inline">字段8</h6>
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

          <h6 class="inline">字段9</h6>
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

          <h6 class="inline">字段10</h6>
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

          <h6 class="inline">字段11</h6>
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

          <h6 class="inline">字段12</h6>
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

          <h6 class="inline">字段13</h6>
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
  

    <el-button style="margin-top: 10px;" size="small" type="success" @click="retriggerUpload">文件上传</el-button>
  
  </div>
  
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      fileList: [], // 绑定文件列表
      options: [
        { value: '0', label: '银行名称' },
        { value: '1', label: '证件号码' },
        { value: '2', label: '客户名称' },
        { value: '3', label: '账号' },
        { value: '4', label: '卡号' },
        { value: '5', label: '币种' },
        { value: '6', label: '交易日期' },
        { value: '7', label: '交易时间' },
        { value: '8', label: '借贷标志' },
        { value: '9', label: '金额数量' },
        { value: '10', label: '余额' },
        { value: '11', label: '对方账号' },
        { value: '12', label: '对方户名' },
        { value: '13', label: '对方银行' }
      ],
      value1: null,
      value2: null,
      value3: null,
      value4: null,
      value5: null,
      value6: null,
      value7: null,
      value8: null,
      value9: null,
      value10: null,
      value11: null,
      value12: null,
      value13: null,
      value14: null,
      uploaded: false,//跟踪文件是否已上传
      tableData: [],//模式匹配结果
      userSetLocation:[],//用户设置位置
    };
  },
  created(){
  },
  methods: {
    submitUpload() {
      //this.$refs.upload.submit();
      const formData =new FormData();
      const latestFile = this.fileList[this.fileList.length - 1]; // 获取最新的文件
      formData.append('file', latestFile.raw);
      
      axios.post('http://localhost:8088/PostCSV', formData)
            .then(response => {
              console.log('上传成功:', response.data);
              this.tableData=response.data;//返回数据存入tableData
            })
            .catch(error => {
              console.error('上传失败:', error);
            });

    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleSuccess(response, file, fileList) {
      console.log('上传成功:', response);
    },
    //在文件列表发生变化时被调用
    handleChange(file, fileList) {
      this.uploaded = false; // 重置上传状态
      this.fileList = fileList; // 更新 fileList
      // 打印当前操作的文件和文件列表，方便调试
      console.log('当前操作的文件:', file);
      console.log('更新后的文件列表:', fileList);
      //将选项重置
      this.value1= null,
      this.value2= null,
      this.value3= null,
      this.value4= null,
      this.value5= null,
      this.value6= null,
      this.value7= null,
      this.value8= null,
      this.value9= null,
      this.value10= null,
      this.value11= null,
      this.value12= null,
      this.value13= null,
      this.value14= null
    },
    
    retriggerUpload() {
        const formData =new FormData();
        const latestFile = this.fileList[this.fileList.length - 1]; //获取最新的文件
        formData.append('file', latestFile.raw);
        const tableDataJson=JSON.stringify(this.tableData);//将table转换为JSON字符串
        formData.append('tableData', tableDataJson);

        //创建包含所有字段和值的对象
        const fieldValues = {
            value1: this.value1,
            value2: this.value2,
            value3: this.value3,
            value4: this.value4,
            value5: this.value5,
            value6: this.value6,
            value7: this.value7,
            value8: this.value8,
            value9: this.value9,
            value10: this.value10,
            value11: this.value11,
            value12: this.value12,
            value13: this.value13,
            value14: this.value14,
        };
        
        //定义数组存储对象
        const userSetLocation = []

        //遍历fieldValues，检查哪些字段已被赋值
        for(const [key, value] of Object.entries(fieldValues)) {
            if (value !== null && value !== undefined && value.length > 0) {
            
            //定义对象存储fieldValues非空字段,将已赋值的字段及其值添加到setLocation中
            const setLocation = {};
            setLocation["location"] = key;
            setLocation["n"] = value;
            userSetLocation.push(setLocation);//对象添加到数组
            }
        }
        //数组不为空时，用户执行了位置设置，将其添加到formData中
        if(!this.userSetLocation.length) {
            //转换为JSON字符串并添加到formData
            formData.append('setLocation', JSON.stringify(userSetLocation));

            if(!this.uploaded) {
                axios.post('http://localhost:8088/Upload', formData)
                     
            this.uploaded = true; // 标记文件已上传
            }
        }else if(!this.uploaded){//setLocation为空时说明用户无位置设置，直接使用模式匹配字段位置上传文件
            axios.post('http://localhost:8088/UploadNotSet', formData)
                
            this.uploaded = true; // 标记文件已上传
        }
    }
  }
};
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