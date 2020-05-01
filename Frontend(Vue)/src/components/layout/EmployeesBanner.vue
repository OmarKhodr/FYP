<template>
   <div id="app" >
        
        
   <div  class="accordion">
    <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
  
     <a  class="nav-item" > <router-link to="/employees" >View Employees </router-link> </a>
     <a  class="nav-item" > <router-link to ="/employees/addEmployee">Add Employee</router-link> </a>
     <a  class="nav-item" > <router-link to ="/projects">View Projects</router-link> </a>
     <a  class="nav-item" > <router-link to ="/projects/addProject">Add Project</router-link> </a>
     <button class="btn btn-link" type="submit" @click="UserLogout"> <router-link to ="/">Logout</router-link> </button>

    </nav>
        <router-view  class="routerview"/>
   </div>
   </div>
  
    
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Vue from 'vue'
import router from 'C:/Users/PC/Desktop/appp/src/router'
import WelcomePage from './WelcomePage'

export default {
  name: 'EmployeesBanner',
  props: {
   
  },
  methods: {

        

        UserLogout (){
             
               new Vue({
              router,
              render: h => h(WelcomePage),
              }).$mount('#app')
             
       
        }

       
    }
}
</script>


<style scoped>

</style>
