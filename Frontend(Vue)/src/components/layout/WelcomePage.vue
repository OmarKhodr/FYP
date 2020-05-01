<template>
    <div id="app">
      <div class="card-group">
  <div class="card">
    <img src="./e1.jpg" height="300" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">Explore Employee Details</h5>
      <p class="card-text">Add employees, observe their skills, update their CVs.</p>
      
    </div>
  </div>
  <div class="card">
    <img src="./t2.jpg" height="300" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">Explore Ongoing Prjects</h5>
      <p class="card-text">Add projects, check their requirements, manage deadlines.</p>
      
    </div>
  </div>
  <div class="card">
    <img src="./t1.jpeg" height="300" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">Build the perfect team</h5>
      <p class="card-text">Find the best team combination for a project.</p>
      
    </div>
  </div>
</div>
    <div class="card text-center">
      
        
  <form>
    <div class="card-body">
  <div class="form-group">
    
    <label for="exampleInputEmail1">Username</label>
    
    
    <input class="form-control" id="username" aria-describedby="emailHelp" v-model="userdata.userName">
   
    
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    
    <input type="password" class="form-control" id="password" v-model="userdata.password">
  
  </div>
  <button type="button" class="btn btn-primary"  @click="GoToSignUpPage">Sign Up</button> 
  <button type="submit" class="btn btn-primary" style="margin-left: 10px;" @click="UserLogin">Login</button> 
  <br>
  <br>
  <label for="exampleInputPassword1" class="text-danger" style="margin-left: 10px;">{{this.msg}}</label>
  </div>
  
</form>

</div>


<router-view  class="routerview"/>
</div>

</template>

<script>

// import {EventBus} from '@/event-bus.js';
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Vue from 'vue'
import router from 'C:/Users/PC/Desktop/appp/src/router'
import EmployeesBanner from './EmployeesBanner'
import SignUpPage from './SignUpPage'
//import BootstrapVue from 'bootstrap-vue/dist/bootstrap-vue.esm';
//import VueRouter from 'vue-router'





// import{v4 as uuidv4} from 'uuid';

export default {
    name: 'WelcomePage',
   
    data() {
        return {
           userdata : {
               userName : null,
               password : null,
               
               
               

           },
           msg: ''


           

            
        }

    },
    components: {
        
    },
    props: {
      
    },

    methods: {

        

        UserLogin (){
            
           
            

             axios.post('http://localhost:8888/users/Login', this.userdata)
             .then(function (response) {
              if(response.data==true){
                
               new Vue({
              router,
              render: h => h(EmployeesBanner),
              }).$mount('#app')
             }
             
              })
              .catch( );

            this.msg='wrong password or username'
              
              
             
             
       
        },

        GoToSignUpPage (){
            new Vue({
              router,
              render: h => h(SignUpPage),
              }).$mount('#app')
        }

       
    }
};
</script>



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
input  {
  position: relative;
  width:25%;
  left:37.5%;
  border-color: grey
}

label {
  font-weight: 600;
}











</style>