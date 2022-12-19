<template>
   <div class="container">
       <div class="header-title">
           CRM
       </div>
   </div>
</template>

<script>
export default {
    name:'Header',
}
</script>

<style scoped>
.container{
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 100%;
    background-color: #545c64;
    margin: none;
}
.el-header header{
    margin: none;
    background-color: #545c64;
}
.header-title{
    font-size: 24px;
    margin: none;
}

</style>