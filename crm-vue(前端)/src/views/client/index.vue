// 管理员对于员工的管理，增删账号、查询员工信息
<template>
  <div class="client-container">
    <div>
      <el-button type="primary" class="add-button" @click="addWorker"
      >新增客户</el-button
      >
      <div class="search-container">
        <el-select
            class="search-select"
            v-model="selection"
            placeholder="请选择查询方式"
        >
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
        <el-input
            v-model="selectionInput"
            class="search-input"
            :disabled="disabled"
            :placeholder="searchPlaceholder"
        ></el-input>
        <el-button type="primary" class="search-button" @click="handleSearch()"
        >立即查询</el-button
        >
      </div>
    </div>
    <el-table :data="ClientList" border style="width: 100%">
      <el-table-column fixed prop="customName" label="姓名" width="150">
      </el-table-column>
      <el-table-column prop="customTel" label="电话" width="120">
      </el-table-column>
      <el-table-column prop="customAddr" label="地址" width="120">
      </el-table-column>
      <el-table-column prop="customEmail" label="邮箱" width="200">
      </el-table-column>
      <el-table-column prop="customFrom" label="来源" width="120">
      </el-table-column>
      <el-table-column prop="customWork" label="工作" width="120">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="handleMail(scope.row)" type="text" size="small"
          >发送邮件</el-button
          >
          <el-button @click="handleEdit(scope.row)" type="text" size="small"
          >编辑</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-drawer
        title="编辑"
        :modal-append-to-body="false"
        :visible.sync="drawer_edit"
        direction="rtl"
    >
      <el-form label-width="80px" :model="editClientInfo">
        <el-form-item label="姓名">
          <el-input v-model="editClientInfo.customName"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="editClientInfo.customTel"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="editClientInfo.customAddr"></el-input>
        </el-form-item>
        <el-form-item label="客户邮箱">
          <el-input v-model="editClientInfo.customEmail"></el-input>
        </el-form-item>
        <el-form-item label="客户来源">
          <el-input v-model="editClientInfo.customFrom"></el-input>
        </el-form-item>
        <el-form-item label="客户工作">
          <el-input v-model="editClientInfo.customWork"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" class="commit-button" @click="commitEditCustom"
      >修改</el-button
      >
    </el-drawer>
    <el-drawer
        title="发送"
        :modal-append-to-body="false"
        :visible.sync="drawer_send"
        direction="rtl"
    >
      <el-form label-width="80px" :model="sendMailInfo">
        <el-form-item label="收件人">
          <el-input v-model="sendMailInfo.reciver"></el-input>
        </el-form-item>
        <el-form-item label="主题">
          <el-input v-model="sendMailInfo.title"></el-input>
        </el-form-item>
        <el-form-item label="消息">
          <el-input v-model="sendMailInfo.xiaoxi"></el-input>
        </el-form-item>>
      </el-form>
      <el-button type="primary" class="commit-button" @click="commitSendMail"
      >发送</el-button
      >
    </el-drawer>
    <el-drawer
        title="新增"
        :modal-append-to-body="false"
        :visible.sync="drawer_add"
        direction="rtl"
    >
      <el-form label-width="80px" :model="newClientInfo">
        <el-form-item label="姓名">
          <el-input v-model="newClientInfo.customName"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="newClientInfo.customTel"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="newClientInfo.customAddr"></el-input>
        </el-form-item>
        <el-form-item label="客户邮箱">
          <el-input v-model="newClientInfo.customEmail"></el-input>
        </el-form-item>
        <el-form-item label="客户来源">
          <el-input v-model="newClientInfo.customFrom"></el-input>
        </el-form-item>
        <el-form-item label="客户工作">
          <el-input v-model="newClientInfo.customWork"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" class="commit-button" @click="commitAddCustom"
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
    await this.$store.dispatch("getClientList", 1);
    this.ClientList = this.$store.getters.ClientList;
    this.page = this.$store.getters.ClientListPage;
    this.current = this.current;
  },
  computed: {
    searchPlaceholder() {
      let res = "";
      if (this.selection == "name") {
        this.disabled = false;
        res = "客户名";
      } else if (this.selection == "addr") {
        this.disabled = false;
        res = "地址";
      } else if (this.selection == "tel") {
        this.disabled = false;
        res = "电话";
      } else {
        this.disabled = true;
      }
      return "请输入" + res + "查询";
    },
  },
  data() {
    return {
      disabled: false,
      current: 0,
      page: {},
      ClientList: [],
      options: [
        {
          value: "name",
          label: "客户名",
        },
        {
          value: "addr",
          label: "地址",
        },
        {
          value: "tel",
          label: "电话",
        },
        {
          value: "all",
          label: "查询全部",
        },
      ],
      editClientInfo: {
        pkId: 0,
        customAddr: "",
        customEmail: "",
        customFrom: "",
        customName: "",
        customTel: "",
        customWork: "",
      },
      sendMailInfo: {
        reciver: "",
        title: "",
        xiaoxi: "",
      },
      newClientInfo: {
        customAddr: "",
        customEmail: "",
        customFrom: "",
        customName: "",
        customTel: "",
        customWork: "",
      },
      selection: "all",
      selectionInput: "",
      currentPageData: [], //当前页显示内容
      drawer_detail: false,
      drawer_edit: false,
      drawer_add: false,
      drawer_send: false
    };
  },
  methods: {
    async handleSearch(pageNum) {
      if (this.selection == "all") {
        this.getClientList(1);
      } else {
        if (pageNum == null) {
          pageNum = 1;
        }
        const res = await this.$store.dispatch("searchClientList", {
          keyword: this.selectionInput,
          pageNum: pageNum,
          type: this.selection,
        });
      }

      this.ClientList = await this.$store.getters.ClientList;
      this.page = await this.$store.getters.ClientListPage;
    },
    async commitEditCustom() {
      const res = await this.$store.dispatch("editClient", {
        pkId: this.editClientInfo.pkId,
        customName: this.editClientInfo.customName,
        customAddr: this.editClientInfo.customAddr,
        customEmail: this.editClientInfo.customEmail,
        customTel: this.editClientInfo.customTel,
        customWork: this.editClientInfo.customWork,
        customFrom: this.editClientInfo.customFrom,
      });
      this.getClientList();
    },
    async commitSendMail() {
      const res = await this.$store.dispatch("sendMail", {
        reciver: this.sendMailInfo.reciver,
        title: this.sendMailInfo.title,
        xiaoxi: this.sendMailInfo.xiaoxi
      });
    },
    async commitAddCustom() {
      const res = await this.$store.dispatch("addClient", {
        customName: this.newClientInfo.customName,
        customAddr: this.newClientInfo.customAddr,
        customEmail: this.newClientInfo.customEmail,
        customTel: this.newClientInfo.customTel,
        customWork: this.newClientInfo.customWork,
        customFrom: this.newClientInfo.customFrom,
      });
      this.getClientList();
    },
    handleCurrentChange(e) {
      if (this.selection == "all") {
        this.getClientList(e);
      } else {
        this.handleSearch(e);
      }
    },
    async getClientList(pageNum) {
      if (pageNum == null) {
        pageNum = 1;
      }
      await this.$store.dispatch("getClientList", pageNum);
      this.ClientList = this.$store.getters.ClientList;
      this.page = this.$store.getters.ClientListPage;
    },
    addWorker() {
      this.drawer_add = true;
    },
    handleMail(row){
      var s1={
        reciver: row.customEmail
      }
      //this.sendMailInfo.reciver=row.customEmail
      this.sendMailInfo=Object.assign({},s1)
      this.drawer_send=true;
    },
    handleEdit(row) {
      this.editClientInfo = Object.assign({}, row);
      this.drawer_edit = true;
    },
  },
};
</script>

<style>
.search-button {
  margin-left: 50px;
}
.add-button {
  margin-bottom: 10px;
}
.client-container {
  height: 700px;
}
.search-container {
  float: right;
}
.search-select {
  margin-right: 50px;
}
.search-input {
  width: 200px;
}
</style>
