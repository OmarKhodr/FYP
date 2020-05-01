<template>

    <div id="app">
    <div class="card text-center">
    
       
  <form>
      <div class="card-body">
  <div class="form-group">
    
    <label for="exampleInputEmail1">Username</label>
    <input class="form-control" id="username" aria-describedby="emailHelp" v-model="userdata.username">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="password" v-model="userdata.password">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Repeated Password</label>
    <input type="password" class="form-control" id="password" v-model="userdata.repeatedPassword">
  

  </div>
  <div >

  <button type="button" class="btn btn-dark"  @click="cancel">Cancel</button> 
  
  <button type="submit" class="btn btn-primary" style="margin-left: 10px;" @click="UserSignUp">Sign Up</button> 

  </div>
  <br>
  <label for="exampleInputPassword1" class="text-danger">{{this.msg}}</label>
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
import WelcomePage from './WelcomePage'
//import BootstrapVue from 'bootstrap-vue/dist/bootstrap-vue.esm';
//import VueRouter from 'vue-router'





// import{v4 as uuidv4} from 'uuid';

export default {
    name: 'SignUpPage',
   
    data() {
        return {
           userdata : {
               username : null,
               password : null,
               repeaedPassword : null,

               
               
               

           },
           msg: ''


           

            
        }

    },
    components: {
        
    },
    props: {
      
    },

    methods: {

        

        UserSignUp (){
            
           
            

             axios.post('http://localhost:8888/users/SignUp', this.userdata)
             .then(function (response) {
              if(response.data==true){
                
               new Vue({
              router,
              render: h => h(EmployeesBanner),
              }).$mount('#app')
             }
             
              })
              .catch( );

            this.msg='Please enter information correctly'
              
       
        },

        cancel (){
            new Vue({
              router,
              render: h => h(WelcomePage),
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
  
}

</style>