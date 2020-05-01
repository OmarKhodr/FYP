<template>
  <div id="app" >
    
    <WelcomePage></WelcomePage>
    <router-view  class="routerview"/>
    


  </div>
</template>

<script>
import WelcomePage from './components/layout/WelcomePage.vue'




export default {
  name: 'App',
  components: {
   //EmployeesBanner
   WelcomePage
  },

  
}
</script>

<style scoped>

</style>
