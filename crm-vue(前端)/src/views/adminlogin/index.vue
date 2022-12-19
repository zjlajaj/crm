<template>
  <div>
    <div class="background">
      <img :src="imgSrc" class="imgSrc" />
    </div>
    <div class="front">
      <div class="welcome-panel">
        <span class="title">CRM客户关系管理系统</span>
        <div class="login-panel">
          <div class="form-container">
            <el-form
              label-position="top"
              label-width="100px"
              ref="form"
              :model="form"
            >
              <el-form-item label="管理员账号">
                <el-input v-model="form.username"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input type="password" v-model="form.password"></el-input>
              </el-form-item>
            </el-form>
          </div>

          <button class="welcome-button" @click="login()">登录</button>
          <el-link class="link-admin" type="info" @click="toAdmin"
            >转到员工登陆</el-link
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      imgSrc: require("@/assets/back.jpg"),
      form: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    toAdmin() {
      this.$router.push("/login");
    },

    async login() {
      this.loading = true;
      setTimeout(() => {
        this.loading = false;
      }, 5000);
  const res = await this.$store.dispatch("AdminLogin", {
        username: this.form.username,
        password: this.form.password,
      });
    },
  },
};
</script>

<style>
.link-admin {
  display: block;
  margin: auto;
  width: 100px;
}
.title {
  width: 40%;
  height: 100%;
  float: left;
  margin: 5%;
  font-size: 25px;
  font-weight: Normal;
  text-align: left;
  color: #ffffff;
  line-height: 0px;
}

.background {
  background-size: cover;
  width: 100%;
  position: absolute;
  background-position: "center top";
}

.imgSrc {
  height: 100%;
  width: 100%;
}

.front {
  width: 100%;
  height: 100%;
  z-index: 1;
  position: absolute;
  text-align: center;
}
.form-container {
  position: relative;
  margin: 0 auto;
  width: 60%;
}
.small-tip {
  margin-left: 50%;
  margin-top: 1%;
  font-size: 14px;
  color: #ffffff;
  line-height: 0px;
}
.login-panel {

  color: #ffffff;
  margin-top: 5%;;
  margin-left: 50%;
  width: 40%;
  height: 400px;
  border-radius: 40px;
  background-color: rgba(0, 0, 0, 0.3);
}
</style> 