
<template>
  <div class="client-container">
    <div>
      <el-button type="primary" class="add-button" @click="addStock"
        >添加库存</el-button
      >
    </div>
    <el-table :data="stockList" border style="width: 100%">
      <el-table-column fixed prop="id" label="货物编号" width="120">
      </el-table-column>

      <el-table-column prop="stockName" label="名称" width="150">
      </el-table-column>
      <el-table-column prop="stockOut" label="已售" width="150">
      </el-table-column>
      <el-table-column prop="stockLeft" label="剩余" width="150">
      </el-table-column>
      <el-table-column prop="stockPrice" label="单价" width="150">
      </el-table-column>
      <el-table-column prop="stockUnit" label="单位" width="150">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)" type="text" size="small"
            >更新</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-drawer
      title="新增"
      :modal-append-to-body="false"
      :visible.sync="drawer_edit"
      direction="rtl"
    >
      <el-form label-width="80px" :model="editStock">
        <el-form-item label="名称">
          <el-input v-model="editStock.stockName"></el-input>
        </el-form-item>
        <el-form-item label="已售">
          <el-input v-model="editStock.stockOut"></el-input>
        </el-form-item>
        <el-form-item label="剩余">
          <el-input v-model="editStock.stockLeft"></el-input>
        </el-form-item>
        <el-form-item label="单价">
          <el-input v-model="editStock.stockPrice"></el-input>
        </el-form-item>
        <el-form-item label="单位">
          <el-input v-model="editStock.stockUnit"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" class="commit-button" @click="commitEditStock"
        >提交</el-button
      >
    </el-drawer>
    <el-drawer
      title="新增"
      :modal-append-to-body="false"
      :visible.sync="drawer_add"
      direction="rtl"
    >
      <el-form label-width="80px" :model="newStock">
        <el-form-item label="名称">
          <el-input v-model="newStock.stockName"></el-input>
        </el-form-item>
        <el-form-item label="已售">
          <el-input v-model="newStock.stockOut"></el-input>
        </el-form-item>
        <el-form-item label="剩余">
          <el-input v-model="newStock.stockLeft"></el-input>
        </el-form-item>
        <el-form-item label="单价">
          <el-input v-model="newStock.stockPrice"></el-input>
        </el-form-item>
        <el-form-item label="单位">
          <el-input v-model="newStock.stockUnit"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" class="commit-button" @click="commitAddStock"
        >提交</el-button
      >
    </el-drawer>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="page.current"
        :pag-sizes="page.size"
        layout="prev, pager, next"
        :page-count="page.pages"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  async created() {
    await this.$store.dispatch("getStockList", 1);
    this.stockList = await this.$store.getters.StockList;
    this.page = this.$store.getters.StockListPage;
  },
  methods: {
    handleCurrentChange(e) {
      this.getStockList(e);
    },
    async commitEditStock() {
      await this.$store.dispatch("editStock", this.editStock);
      this.getStockList(1);
    },
    async commitAddStock() {
      await this.$store.dispatch("newStock", this.newStock);
      this.getStockList(1);
    },
    async getStockList(pageNum) {
      if (pageNum == null) {
        pageNum = 1;
      }
      await this.$store.dispatch("getStockList", pageNum);
      this.stockList = await this.$store.getters.StockList;
    },
    addStock() {
      this.drawer_add = true;
    },
    handleEdit(row) {
      this.editStock = Object.assign({}, row);;
      this.drawer_edit = true;
    },
  },

  data() {
    return {
      page:{},
      editStock: {
        id: 0,
        stockLeft: 0,
        stockName: "",
        stockOut: 0,
        stockPrice: 0,
        stockUnit: "",
      },
      newStock: {
        id: 0,
        stockLeft: 0,
        stockName: "",
        stockOut: 0,
        stockPrice: 0,
        stockUnit: "",
      },
      drawer_detail: false,
      drawer_edit: false,
      drawer_add: false,
      stockList: [],
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