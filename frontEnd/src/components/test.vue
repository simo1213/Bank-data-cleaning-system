<script>
import axios from 'axios';
export default {
  data() {
    return {
      input: '', // 添加 input 属性，用于存储输入的值
      tableData: []
    }
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      const bankName = this.input; // 使用 this.input 获取输入的值

      axios.get('http://localhost:8088/getdata2', {
        params: {
          bankName: bankName
        }
      })
      .then(response => {
        this.tableData = response.data;
        this.handleEmptyValues();
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
    },
    handleEmptyValues() {
      this.tableData = this.tableData.map(item => {
        for (let key in item) {
          if (!item[key]) { item[key] = "无"; }
        }
        return item;
      });
    },
  }
}
</script>