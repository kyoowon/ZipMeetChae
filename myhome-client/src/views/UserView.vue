<template>
  <div id="login-wrapper">
    <article id="login-content">
      <router-view></router-view>
    </article>
  </div>
</template>

<script>

export default {
  name: "UserView",
};
</script>

<style scoped>
#login-wrapper{
  display: block;
  width: 100%;
  height: 100%;
}

#login-title{
  display:block;
  margin: 35px 0;
  color: #949699;
}

#login-content{
  margin: 30px;
  height: 80vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>