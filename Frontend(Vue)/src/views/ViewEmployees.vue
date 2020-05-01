<template>
<div id="app">
  <div class="EmployeesBody">
   
   <div>
    <b-navbar type="light" variant="light">
        <b-nav-form>
            <b-form-input class="mr-sm-2" placeholder="Search by Name..." type="text" v-model="searchname"></b-form-input>
            <b-button variant="outline-success" class="my-2 my-sm-0" type="submit" @click="filterbyname(searchname)">Search</b-button>
        </b-nav-form>
    </b-navbar>
   </div>
  </div>
    
 
   <Employees v-bind:employees="employees.content"/>
  
  
   
   
   

  </div>
  </template>

<script>

import Employees from "../components/Employees";


import axios from "axios";

export default {
  name: 'ViewEmployees',
  components: {
    Employees,
    
  },
  data() {
    return {
      employees: [
        // to be imported using axios, GET 
      ],
      
      searchname:""
    }

  },
  props: {            //so far we have no props
    
  },

  methods: {

     
     getemployees() {
      
      axios.get("http://localhost:8888/employees")
      .then(res => (this.employees = res.data))
      .catch(err => print(err));
      
    },

    filterbyname(searchname) {
      const filtered = this.employees.content.filter(function(value) {
        
          return ((value.first_name+' '+value.last_name)==searchname);
        
      });
      this.employees.content=filtered;
      this.searchname="";
    }
  
  },

  created () {
    this.getemployees();
  }
}
</script>


<style>
/* h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
} */
</style>
