<template>
    <div id="app">

        <!-- {{this.datatoedit.reports_to}} -->
        <!-- {{this.otheremployeedata[0].emp_id}} -->
        <!-- {{this.skillsdata}} -->
        
    
        <br>
        <br>
          <div>

            <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="First Name:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.first_name" > </b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Last Name:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.last_name"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Position:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.position"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Department:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.department"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Gender:"
            label-for="input-horizontal"
            >
             <!-- <b-form-group label="Individual radios"> -->
             <b-form-radio  name="some-radios" value="M" v-model="datatoedit.gender">Male</b-form-radio>
             <b-form-radio  name="some-radios" value="F" v-model="datatoedit.gender">Female</b-form-radio>
             </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Date Hired:"
            label-for="input-horizontal"
            >
            <b-form-datepicker id="example-datepicker" v-model="datatoedit.hire_date" class="mb-2"></b-form-datepicker>
            </b-form-group>

             <!-- <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Branch ID:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.branch_id"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Reports to:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.reports_to"></b-form-input>
            </b-form-group> -->

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Phone Number:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.phone_num"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Efficiency:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.efficiency"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Effectiveness:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.effectiveness"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Nationality"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="datatoedit.nationality"></b-form-input>
            </b-form-group>

            </div>
            <b-button variant="success" @click="saveemployeedata"><router-link to="/employees"> Update Employee Data</router-link></b-button>
            <br>

            <br>

            <Skills v-bind:skills="skillsdata" v-bind:idofthisemployee="this.otheremployeedata[0].emp_id" ></Skills>
             

         

        
    </div>
</template>

<script>
import Skills from "../components/Skills";
import {EventBus} from '@/event-bus.js';
import axios from "axios";

export default {
    name:"EditEmployeePage",

    data() {
        return {
            skillsdata : [],

            otheremployeedata : []
            ,

            datatoedit : {},

            
        }

    },
    components: {
        Skills
    },
    props: {

    },

    methods: {

        saveemployeedata () {
            
            if ( typeof this.datatoedit.effectiveness == 'string') {
                this.datatoedit.effectiveness = parseFloat(this.datatoedit.effectiveness);
            }

            if ( typeof this.datatoedit.efficiency == 'string') {
                this.datatoedit.effeciency = parseFloat(this.datatoedit.efficiency);
            }
            
            //what to do with hire_date

            // if ( this.datatoedit.branch_id != null) {
            //     this.datatoedit.branch_id = { branch_id :parseInt(this.datatoedit.branch_id)}
            // }

            //  if ( this.datatoedit.reports_to != null) {
            //     this.datatoedit.reports_to = { emp_id : parseInt(this.datatoedit.reports_to)}
            // }
            
            

            axios.put('http://localhost:8888/employees/'+this.datatoedit.emp_id, this.datatoedit)
            .then( )
            .catch( err =>  print(err));
        }
       
    },

    created () {
        EventBus.$on('eventbusskills',(value)=> {
            this.skillsdata = value;
            
        });

        EventBus.$on('eventbusemployeedetailsw/oskills',(value)=> {
            this.otheremployeedata = value;
        });     //added

        setTimeout(() => {  this.datatoedit = JSON.parse(JSON.stringify(this.otheremployeedata[0]))
                         
                            
                            }, 500);


    }
    
};
</script>

<style scoped>
</style>