// 管理员对于员工的管理，增删账号、查询员工信息
<template>
  <div class="client-container">
    <div>
      <el-button type="primary" class="add-button" @click="addOrder"
        >新增订单</el-button
      >
      <el-button
        type="primary"
        class="export"
        icon="el-icon-document"
        circle
        @click="exportExcel"
      ></el-button>
    </div>
    <el-table :data="orderList" border style="width: 100%">
      <el-table-column fixed prop="pkId" label="订单号" width="80">
      </el-table-column>
      <el-table-column prop="dealName" label="订单名" width="120">
      </el-table-column>
      <el-table-column prop="dealWorker" label="下单员" width="120">
      </el-table-column>
      <el-table-column prop="dealStock" label="货物名称" width="120">
      </el-table-column>
      <el-table-column prop="dealNum" label="购买数量" width="120">
      </el-table-column>
      <el-table-column prop="dealAddr" label="送货地址" width="120">
      </el-table-column>
      <el-table-column prop="dealCustom" label="客户" width="120">
      </el-table-column>
      <el-table-column prop="dealTime" label="时间" width="120">
      </el-table-column>
      <el-table-column prop="dealTel" label="联系电话" width="120">
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)" type="text" size="small"
            >编辑</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-drawer
      title="修改"
      :modal-append-to-body="false"
      :visible.sync="drawer_edit"
      direction="rtl"
    >
      <el-form label-width="80px" :model="editOrder">
        <el-form-item label="订单名">
          <el-input v-model="editOrder.dealName"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="editOrder.dealAddr"></el-input>
        </el-form-item>
        <el-form-item label="客户">
          <el-input v-model="editOrder.dealCustom"></el-input>
        </el-form-item>
        <el-form-item label="收货人">
          <el-input v-model="editOrder.dealPeople"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="editOrder.dealTel"></el-input>
        </el-form-item>
        <el-form-item label="数量">
          <el-input v-model="editOrder.dealNum"></el-input>
        </el-form-item>
        <el-form-item label="订单状态">
          <el-input v-model="editOrder.dealState"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="editOrder.dealDetail"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" class="commit-button" @click="commitEditOrder"
        >提交</el-button
      >
    </el-drawer>
    <el-drawer
      title="新增"
      :modal-append-to-body="false"
      :visible.sync="drawer_add"
      direction="rtl"
    >
      <el-form label-width="80px" :model="newOrder">
        <el-form-item label="订单名">
          <el-input v-model="newOrder.dealName"></el-input>
        </el-form-item>
        <el-form-item label="货物ID">
          <el-input v-model="newOrder.dealStockId"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="newOrder.dealAddr"></el-input>
        </el-form-item>
        <el-form-item label="客户">
          <el-input v-model="newOrder.dealCustom"></el-input>
        </el-form-item>
        <el-form-item label="收货人">
          <el-input v-model="newOrder.dealPeople"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="newOrder.dealTel"></el-input>
        </el-form-item>
        <el-form-item label="数量">
          <el-input v-model="newOrder.dealNum"></el-input>
        </el-form-item>
        <el-form-item label="订单状态">
          <el-input v-model="newOrder.dealState"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="newOrder.dealDetail"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" class="commit-button" @click="commitAddOrder"
        >提交</el-button
      >
    </el-drawer>
  </div>
</template>

<script>
export default {
  async created() {
    await this.$store.dispatch("getOrderList", 1);
    this.orderList = await this.$store.getters.OrderList;
  },
  methods: {
    async exportExcel() {
      await this.$store.dispatch("exportExcel");
    },
    async commitAddOrder() {
      await this.$store.dispatch("newOrder", this.newOrder);
      this.getOrderList();
    },
    async commitEditOrder() {
      await this.$store.dispatch("editOrder", this.editOrder);
      this.getOrderList();
    },
    async getOrderList() {
      await this.$store.dispatch("getOrderList", 1);
      this.orderList = await this.$store.getters.OrderList;
    },
    addOrder() {
      this.drawer_add = true;
    },
    handleEdit(row) {
      this.editOrder = Object.assign({}, row);;
      this.drawer_edit = true;
      console.log(row);
    },
  },

  data() {
    return {
      editOrder: {
        pkId:0,
        dealAddr: "",
        dealCustom: "",
        dealDetail: "",
        dealName: "",
        dealNum: 0,
        dealPeople: "",
        dealState: "",
        dealStockId: 0,
        dealTel: "",
      },
      newOrder: {
        pkId:0,
        dealAddr: "",
        dealCustom: "",
        dealDetail: "",
        dealName: "",
        dealNum: 0,
        dealPeople: "",
        dealState: "",
        dealStockId: 0,
        dealTel: "",
      },
      drawer_detail: false,
      drawer_edit: false,
      drawer_add: false,
      orderList: [],
    };
  },
};
</script>

<style>
.add-button {
  margin-bottom: 10px;
}
.order-container {
  height: 700px;
}
</style>