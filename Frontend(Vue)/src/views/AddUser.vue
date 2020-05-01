<template>
    <div>
    <div class="card card-body">
  <form>
  <div class="form-group">
    
    <label for="exampleInputEmail1">Username</label>
    <input class="form-control" id="username" aria-describedby="emailHelp" v-model="userdata.userName">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="password" v-model="userdata.password">
  </div>
  <div class="form-group form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">Check me out</label>
  </div>
  <button type="submit" class="btn btn-primary" href='#employees' @click="adduser"><router-link to ="/employees">Submit</router-link></button> 
</form>

</div>
</div>

</template>

<script>

// import {EventBus} from '@/event-bus.js';
import axios from "axios";
// import{v4 as uuidv4} from 'uuid';

export default {
    

    data() {
        return {
           userdata : {
               userName : null,
               password : null,
               
               

           }

            
        }

    },
    components: {
        
    },
    props: {

    },

    methods: {

        

        adduser (){
            

            
            
            

            axios.post('http://localhost:8888/users', this.userdata)
            .then()
            .catch( err => print(err));
       
        }

       
    }
};
</script>



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>