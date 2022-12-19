<template>
  <div>
    <el-menu :default-active="activeIndex" router  background-color="#545c64" text-color="#fff"  active-text-color="#ffd04b" class="el-menu-vertical-demo">
      <div v-for="route in routes" :key="route.id" >
        <el-menu-item :index="route.path">
          <i :class="route.meta.icon"></i>
          <span slot="title">{{ route.meta.title }}</span>
        </el-menu-item>
      </div>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      activeIndex: "",
      routes: [],
    };
  },
  computed: {

  },
  mounted() {
    this.routes = [];
    for(let i in this.$router.options.routes[0].children){
      let permission = this.$router.options.routes[0].children[i].meta.permission;
      if(permission.indexOf(sessionStorage.getItem('role')) !== -1){
        this.routes.push(this.$router.options.routes[0].children[i]);
      }
    }
    

    this.activeIndex = this.$route.fullPath;
  },
  methods: {

  },
};
</script>

<style>
</style>