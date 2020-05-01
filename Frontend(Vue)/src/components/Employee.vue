<template>
<div id="app">
    <div class="employee-object">
         <b-button-group vertical>
            <b-button @click="sendEmpdataUsingEventBus"><router-link to="/employees/employeePage">{{employee.first_name}} {{employee.last_name}}</router-link></b-button>
            <b-button>{{employee.department}}</b-button>
            <b-button>{{employee.position}}</b-button>
         </b-button-group>
         
    </div>
    </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import {EventBus} from '@/event-bus.js';
export default {
    name: "Employee",
    // props: ["employee"],     no need for it to be an array since it is an object
    props: {
        employee: {}
    },
    methods: {
       sendEmpdataUsingEventBus(){
           EventBus.$emit('eventbusemployee',this.employee);
       }
          
    }
};
</script>

<style scoped>
.employee-object {
    background: #f4f4f4;
    padding: 10px;
    border-bottom: 1px #ccc dotted;
}
</style>