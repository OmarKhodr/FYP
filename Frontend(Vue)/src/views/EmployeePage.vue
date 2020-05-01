<template>
<div id="app">
    <div class="employee-page" >
       
        <!-- {{this.employee.emp_id}} -->
        <!-- {{this.employee}} -->
        <!-- {{this.employeeskills}} -->
        <!-- {{this.extractedemployeeskills}} -->

        <div >
            <b-table stacked :items="items"  ></b-table>
        </div>


       <div>
            <b-button v-b-toggle="'collapse-2'" class="m-1" @click="this.extractEmployeeSkillsIntoextractedemployeeskills">Show Skills</b-button>

            <b-collapse id="collapse-2">
                <b-card>
                    
                   <b-table stacked :items=extractedemployeeskills  ></b-table>
                </b-card>
            </b-collapse>
        </div>

        <br>
        <br>
        
        <div>
            <b-button @click="this.extractandsend"><router-link to='/employees/editEmployeePage'>Edit Employee Data</router-link></b-button>
            &ensp;
            <b-button variant="danger" @click="this.deleteemployee"> <router-link to="/employees">Delete Employee Data</router-link></b-button>
 
        </div>
           
    <router-view  class="routerview"/>
    </div>
</div>
</template>

<script>
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import {EventBus} from '@/event-bus.js';

export default {
    data () {
        return {
          
            

            employee: {},
    
            items: [ {
                 employee_id : null,
                 first_name : null,
                 last_name :null,
                 position : null,
                 department : null,
                 gender : null,
                 hire_date : null,
                 //branch_id : null,
                 //reports_to : null,
                 phone_numer : null,
                 efficiency : null,
                 effectiveness : null,
                 nationality : null
               }],

               extractedemployeeskills: [],
            
            employeeskills : [],

           

        }
   

    },

    methods : {
           getemployeeskills() {
     
      
      const id= this.employee.emp_id; 
      
      

      axios.get("http://localhost:8888/employees/getemployeeskills/"+id)
      .then(res => (this.employeeskills = res.data))
      .catch(err => print(err));

      
      
     
     
    
    },

        deleteemployee() {
            const id = this.employee.emp_id;

        axios
        .delete('http://localhost:8888/employees/'+id)
        .catch(err => print(err));
        }
    ,

    extractEmployeeSkillsIntoextractedemployeeskills(){
    if (this.extractedemployeeskills.length==0){
        for (var i =0; i<this.employeeskills.length; i++) {
          const tempskill= {
              id : this.employee.emp_id,
              category : this.employeeskills[i].category,                      //post-authorization
              skill : this.employeeskills[i].skill,
              competency : this.employeeskills[i].competency

          }
          

          this.extractedemployeeskills.push(tempskill);
        }

      }
      
    },

    mountEmployeeDataAndSkills(){
         EventBus.$on('eventbusemployee', (value) =>
            {
                this.employee = value; 
                this.items=[ {
                    emp_id : this.employee.emp_id,
                    first_name : this.employee.first_name,
                    last_name : this.employee.last_name,
                    position : this.employee.position,
                    department : this.employee.department,
                    gender : this.employee.gender,
                    hire_date : this.employee.hire_date,
                    //branch_id : this.employee.branch_id.branch_id,  //.branch_id,  //added this
                    //reports_to : this.employee.reports_to, //.emp_id, //added this
                    phone_num : this.employee.phone_num,  //was phone_number (wrong)
                    efficiency : this.employee.efficiency,
                    effectiveness : this.employee.effectiveness,
                    nationality : this.employee.nationality
                }];
                
    
            });


            // this.getemployeeskills();

            setTimeout(() => {  this.getemployeeskills() }, 1000);


    },

    sendSkillsDataUsingEventBus() {
        EventBus.$emit('eventbusskills',this.extractedemployeeskills);
    },

    extractandsend(){
        this.extractEmployeeSkillsIntoextractedemployeeskills();


         setTimeout(() => {  this.sendSkillsDataUsingEventBus(); }, 1000);


         EventBus.$emit('eventbusemployeedetailsw/oskills',this.items);    //added


    }
       

    

    },

    created () {
            // EventBus.$on('eventbusemployee', (value) =>
            // {
            //     this.employee = value; 
            //     this.items=[ {
            //         employee_id : this.employee.emp_id,
            //         first_name : this.employee.first_name,
            //         last_name : this.employee.last_name,
            //         position : this.employee.position,
            //         department : this.employee.department,
            //         gender : this.employee.gender,
            //         hire_date : this.employee.hire_date,
            //         branch_id : this.employee.branch_id,
            //         reports_to : this.employee.reports_to,
            //         phone_number : this.employee.phone_num,
            //         efficiency : this.employee.efficiency,
            //         effectiveness : this.employee.effectiveness,
            //         nationality : this.employee.nationality
            //     }];
    
            // }),
            
            // setTimeout(() => {  this.getemployeeskills }, 11000);
            
           this.mountEmployeeDataAndSkills();
           

          
            
       

            
        },

    name: "Employee",
    // props: ["employee"],     no need for it to be an array since it is an object
    props: {
        // employee: {} not a prop here
    }
};
</script>

<style scoped>



</style>