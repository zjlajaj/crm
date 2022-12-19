<template>
  <div>
    <el-select v-model="selection" @change="getTableData" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>
    <div id="main" style="width: 800px; height: 400px"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
        names:[],
        counts:[],
        selection:'industry',
        options:[
            {
          value: 'industry',
          label: '客户所属行业统计'
        },  {
          value: 'from',
          label: '客户来源信息统计'
        },  {
          value: 'achievement',
          label: '员工业绩统计'
        },{
          value: 'sale',
          label: '库存销售情况统计'
        },
        ]
    };
  },
  async mounted() {
    await  this.$store.dispatch('getTableData','industry');
    this.counts = this.$store.getters.Counts;
    this.names = this.$store.getters.Names;
     this.drawChart();
  },


  methods: {
       
async getTableData(){
    await  this.$store.dispatch('getTableData',this.selection);
    this.counts = this.$store.getters.Counts;
    this.names = this.$store.getters.Names;
     this.drawChart();
},
    drawChart() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      let option = {

        tooltip: {},
        xAxis: {
          data: this.names,
        },
        yAxis: {},
        series: [
          {
            type: "bar",
            data: this.counts,
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
  },
};
</script>

<style>
</style>