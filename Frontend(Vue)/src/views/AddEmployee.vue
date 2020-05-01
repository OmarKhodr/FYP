<template>
    <div id="app">
        <!-- {{this.employeedata}} -->
        <br>
        <!-- {{this.skills}} -->
        <br>
        <br>
       
          <div>

              <!-- <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Employee ID:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.emp_id"> </b-form-input>
            </b-form-group> -->

            <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="First Name:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.first_name" > </b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Last Name:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.last_name" ></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Position:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.position" ></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Department:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.department" ></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Gender:"
            label-for="input-horizontal"
            >
             <!-- <b-form-group label="Individual radios"> -->
             <b-form-radio  name="some-radios" value="M" v-model="employeedata.gender">Male</b-form-radio>
             <b-form-radio  name="some-radios" value="F" v-model="employeedata.gender">Female</b-form-radio>
             </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Date Hired:"
            label-for="input-horizontal"
            >
            <b-form-datepicker id="example-datepicker"  class="mb-2" v-model="employeedata.hire_date"></b-form-datepicker>
            </b-form-group>

             <!-- <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Branch ID:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.branch_id"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Reports to:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.reports_to"></b-form-input>
            </b-form-group> -->

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Phone Number:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.phone_num"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Efficiency:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.efficiency"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Effectiveness:"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.effectiveness"></b-form-input>
            </b-form-group>

             <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Nationality"
            label-for="input-horizontal"
            >
            <b-form-input id="input-horizontal" v-model="employeedata.nationality"></b-form-input>
            </b-form-group>

            </div>
            <b-button variant="success" @click="addemployee">Save Employee</b-button>
            <br>
            
            <br>
            <b-button @click="this.addskillfield">Add Skill</b-button>
            <br>
            <br>

            <div v-for="i in skills" v-bind:key="i.competency">
            <SkillToAddForNewEmployee v-bind:skilldata="i"> </SkillToAddForNewEmployee>
            </div>  

            
             

         

        
    </div>
</template>

<script>

// import {EventBus} from '@/event-bus.js';
import axios from "axios";
import SkillToAddForNewEmployee from '../components/SkillToAddForNewEmployee';
// import{v4 as uuidv4} from 'uuid';

export default {
    name:"EditEmployeePage",

    data() {
        return {
           employeedata : {
               //emp_id :  this.generaterandomidnumber(),
               department : null,
               effectiveness : null,
               efficiency : null,
               first_name : null,
               gender : null,
               hire_date : null,
               last_name : null,
               nationality : null,
               phone_num : null,
               position : null,
            //    branch_id : null,
            //    reports_to : null

           },

           skills : []
            
        }

    },
    components: {
        SkillToAddForNewEmployee
    },
    props: {

    },

    methods: {

        addskillfield() {
            var temp = {};
            // temp.emp_id = this.employeedata.emp_id; according to api this is not necessary (optional)
            temp.category = "...";                  //post authorization
            temp.skill = "...";
            temp.competency = "#";

            this.skills.push(temp);
           
        },

        generaterandomidnumber() {
            return Math.floor(Math.random() * 100000) + 1;  
        },

        addemployee (){
            


            if ( typeof this.employeedata.effectiveness == 'string') {
                this.employeedata.effectiveness = parseFloat(this.employeedata.effectiveness);
            }

            if ( typeof this.employeedata.efficiency == 'string') {
                this.employeedata.effeciency = parseFloat(this.employeedata.efficiency);
            }
            
            //what to do with hire_date

            // if ( this.employeedata.branch_id != null) {
            //     this.employeedata.branch_id = { branch_id :parseInt(this.employeedata.branch_id)};
            // }

            //  if ( this.employeedata.reports_to != null) {
            //     this.employeedata.reports_to = {emp_id :parseInt(this.employeedata.reports_to)};
            // }

            if (this.skills.length == 0){
                axios.post()
            }

            if (this.skills.length ==0) {
                axios.post('http://localhost:8888/employees', this.employeedata)
                .then( )
                .catch( err => print(err));
            }

            else if ( this.skills.length != 0 ) {
                for ( var i=0; i<this.skills.length; i++){
                    this.skills[i].competency = parseInt(this.skills[i].competency);
                }

                const temp = {
                    employeedata : this.employeedata,
                    skills : this.skills
                }

                

                axios.post('http://localhost:8888/employees/withskills', temp)
                .then( )
                .catch( err => print(err));
            }

            


        }

       
    }
};
</script>

<style scoped>
</style>