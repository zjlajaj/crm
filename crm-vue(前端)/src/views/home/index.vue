<template>
  <el-container style="height: 100%; width: 100%; position: fixed">
    <el-header height="50px" class="header"><Header /></el-header>

    <el-container>
      <el-aside width="180px" class="aside"
        ><Aside />
        <div class="logout-button-container" @click="logout()">
          <a href="" class="logout-button">
            <i class="el-icon-switch-button"></i>
            安全退出
          </a>
        </div>
      </el-aside>
      
      <el-main class="mian">
       <el-page-header v-show="this.$route.path!='/home'" class="breadCrumblist-container"  :content="this.$route.meta.title">
      </el-page-header>
        <!-- <el-breadcrumb class="breadCrumblist-container" separator-class="el-icon-arrow-right">
          <el-breadcrumb-item  v-for="Item in breadCrumblist"  :key="Item.path">{{Item.meta.title}}</el-breadcrumb-item>
        </el-breadcrumb> -->
        <div class="router-view-container">
<router-view></router-view>
        </div>
        
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
export default {
  name: "home",
  components: { Aside, Header },


  computed:{
    breadCrumblist(){
      return this.$route.matched;
    }
  }, methods: {
    logout(){
      sessionStorage.clear();
      this.$router.push('/login');
    },
      goBack() {
        this.$router.push(this.$route.matched[this.$route.matched.length - 2].path)
      }
    }
};
</script>

<style>
.el-page-header__content{
  font-weight: Bold;
}
.el-page-header__left{
  display: none;
}
.el-page-header__title{
  display: none;
}
.el-aside{
  background-color: #545c64;
}
.el-main{
  padding: 0;
}
.router-view-container{
  background-color: rgb(252, 252, 252);
  padding-top: 60px;
  padding-left: 20px;
  padding-right: 20px;
}
.breadCrumblist-container{
  position: fixed;
  z-index: 999;
  padding-left: 10px;
  font-size: 18px;
  display: flex;
    align-items: center;
  width: 100%;
  height: 40px;
  background-color: #ffffff;
}
.logout-button-container {
  bottom: 0px;
  margin: auto;
  position: fixed;
  width: 180px;
  background-color: #545c64;
}
.logout-button {
  margin-left: 30%;
  width: 100%;
  height: 34px;
  font-size: 14px;
  text-align: center;
  color: #71747b;
  line-height: 34px;
}
.container {
  height: 100%;
  width: 100%;
}
.header {
  height: 7%;
  width: 100%;
  margin: none;
}
.mian {
  background-color: #f0f1f6;
}
.aside {
  font-size: 20px;
  height: 100%;
}
</style>