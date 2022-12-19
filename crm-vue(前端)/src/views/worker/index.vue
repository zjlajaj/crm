// 管理员对于员工的管理，增删账号、查询员工信息
<template>
  <div>
    <div>
      <el-button type="primary" class="add-button" @click="addWorker"
        >新增员工</el-button
      >
    </div>
    <el-table :data="WorkerList" border style="width: 100%">
      <el-table-column fixed prop="workName" label="姓名" width="170">
      </el-table-column>

      <el-table-column prop="id" label="id" width="170"> </el-table-column>
      <el-table-column prop="workAccount" label="账号" width="170">
      </el-table-column>
      <el-table-column prop="workAddr" label="地址" width="170">
      </el-table-column>
      <el-table-column prop="workTel" label="电话" width="170">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="170">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)" type="text" size="small"
            >编辑</el-button
          >
          <el-button
            @click="handlePwdReset(scope.row)"
            type="text"
            style="color: orange"
            size="small"
            >重置密码</el-button
          >
          <el-button
            @click="handleDelete(scope.row)"
            type="text"
            style="color: red"
            size="small"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-drawer
      title="新增员工"
      :modal-append-to-body="false"
      :visible.sync="drawer"
      direction="rtl"
    >
      <el-form label-width="80px" :model="newWorkerInfo">
        <el-form-item label="姓名">
          <el-input v-model="newWorkerInfo.workName"></el-input>
        </el-form-item>

        <el-form-item label="电话">
          <el-input v-model="newWorkerInfo.workTel"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="newWorkerInfo.workAddr"></el-input>
        </el-form-item>
        <el-form-item label="员工账号">
          <el-input v-model="newWorkerInfo.workAccount"></el-input>
        </el-form-item>
        <el-form-item label="账号密码">
          <el-input v-model="newWorkerInfo.workPwd"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" class="commit-button" @click="commitNewWorker"
        >提交</el-button
      >
    </el-drawer>
    <el-drawer
      title="编辑员工"
      :modal-append-to-body="false"
      :visible.sync="drawerEdit"
      direction="rtl"
    >
      <el-form label-width="80px" :model="newWorkerInfo">
        <el-form-item label="姓名">
          <el-input v-model="editWorkerInfo.workName"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="editWorkerInfo.workTel"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="editWorkerInfo.workAddr"></el-input>
        </el-form-item>
        <el-form-item label="员工账号">
          <el-input v-model="editWorkerInfo.workAccount"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" class="commit-button" @click="commitEditWorker"
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
    await this.$store.dispatch("QueryWorkers", 1);
    this.WorkerList = this.$store.getters.WorkerList;
    this.page = this.$store.getters.WorkerListPage;
  },
  methods: {
    handleCurrentChange(e){
     this.getWorkerList(e);
    },
    async commitEditWorker() {
      const res = await this.$store.dispatch("EditWorker", {
        id: this.editWorkerInfo.id,
        workAccount: this.editWorkerInfo.workAccount,
        workAddr: this.editWorkerInfo.workAddr,
        workName: this.editWorkerInfo.workName,
        workPwd: this.editWorkerInfo.workPwd,
        workTel: this.editWorkerInfo.workTel,
      });
      await this.getWorkerList();
    },
    async getWorkerList(pageNum) {
      if(pageNum == null){
        pageNum = 1;
      }
      await this.$store.dispatch("QueryWorkers", pageNum);
      this.WorkerList = this.$store.getters.WorkerList;
      this.page = this.$store.getters.WorkerListPage;
    },
    async commitNewWorker() {
      const res = await this.$store.dispatch("AddWorker", {
        workAccount: this.newWorkerInfo.workAccount,
        workAddr: this.newWorkerInfo.workAddr,
        workName: this.newWorkerInfo.workName,
        workPwd: this.newWorkerInfo.workPwd,
        workTel: this.newWorkerInfo.workTel,
      });
      this.getWorkerList();
    },
    addWorker() {
      this.drawer = true;
    },
    async handleDelete(row) {
      let id = row.id;
      await this.$store.dispatch("DeleteWorker", id);
      await this.getWorkerList();
    },
    handlePwdReset(row) {
      let id = row.id;
      this.$store.dispatch("ResetWorkerPwd", id);
    },
    handleEdit(row) {
      this.editWorkerInfo = Object.assign({}, row);
      this.drawerEdit = true;
    },
  },

  data() {
    return {
      drawer: false,
      drawerEdit: false,
      page: { pages: 0, current: 0, size: 0, total: 0 },
      WorkerList: [],
      editWorkerInfo: {
        id: 0,
        workAccount: "",
        workAddr: "",
        workName: "",
        workPwd: "",
        workTel: "",
      },
      newWorkerInfo: {
        id: 0,
        workAccount: "",
        workAddr: "",
        workName: "",
        workPwd: "",
        workTel: "",
      },
    };
  },
};
</script>

<style>
</style>