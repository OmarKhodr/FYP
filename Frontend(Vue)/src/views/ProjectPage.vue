<template>
<div id="app">
  <div class='project-page' >

    {{this.extractedprojectemployees}}
    <div >
      <b-table stacked :items="items"  ></b-table>
    </div>
    <div>
      <b-button v-b-toggle="'collapse-2'" class='m-1' @click="this.extractProjectEmployeesIntoextractedprojectemployees">Show Employees</b-button>
      <b-collapse id='collapse-2'>
        <!-- <TeamBox :members = 'this.extractedprojectemployees'></TeamBox> -->
        <b-card>
          <b-table stacked :items=this.extractedprojectemployees  ></b-table>
        </b-card>
      </b-collapse>
    </div>
    <br>
    <br>
    <div>
      <b-button @click="this.extractandsend"><router-link to='/projects/editProject'>Edit Project Data</router-link></b-button>
      &ensp;
      <b-button variant='danger' @click="this.deleteproject"> <router-link to="/projects">Delete Project Data</router-link></b-button>
    </div>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import {EventBus} from '@/event-bus.js'
//import TeamBox from './TeamBox.vue'
// import Employee from '../components/Employee.vue' // eventually i wanna make the leader clickable

export default {
  data () {
    return {
      project: {},
      items: [ {
        project_id: null,
        complete: null,
        deadline: null,
        effectiveness: null,
        efficiency: null,
        end_date: null,
        project_name: null,
        severity: null,
        start_date: null,
        project_leader: null
      }],

      extractedprojectemployees: [],

      projectemployees: []
    }
  },

  methods: {
    getprojectemployees () {
      const id = this.project.project_id

      axios.get('http://localhost:8888/workingon/' + id)
        .then(res => (this.projectemployees = res.data))
        .catch()
    },

    deleteproject () {
      const id = this.project.project_id

      axios
        .delete('http://localhost:8888/projects/' + id)
        .then()
        .catch()
    },

    extractProjectEmployeesIntoextractedprojectemployees () {
      if (this.extractedprojectemployees.length === 0) {
        for (var i = 0; i < this.projectemployees.length; i++) {
        /*  const tempemployee = {
            id: this.project.project_id,
            employee: this.projectemployees[i].emp_id
          } */

          const tempemployee = this.projectemployees[i].emp_id

          this.extractedprojectemployees.push(tempemployee)
        }
      } 
    },

    mountProjectDataAndEmployees () {
      EventBus.$on('eventbusproject', (value) => {
        this.project = value
        this.items = [ {
          project_id: this.project.project_id,
          completed: this.project.completed,
          deadline: this.project.deadline,
          effectiveness: this.project.effectiveness,
          efficiency: this.project.efficiency,
          end_date: this.project.end_date,
          project_name: this.project.project_name,
          severity: this.project.severity,
          start_date: this.project.start_date,
          project_leader: this.project.project_leader
        }]
      })


      setTimeout(() => { this.getprojectemployees() }, 1000)
    },

    sendEmployeesDataUsingEventBus () {
      EventBus.$emit('eventbusemployees', this.extractedprojectemployees)
    },

    extractandsend () {
      this.extractProjectEmployeesIntoextractedprojectemployees()

      

      setTimeout(() => { this.sendEmployeesDataUsingEventBus() }, 1000)

      

      EventBus.$emit('eventbusprojectdetails', this.items) // added
    }

  },

  created () {
    this.mountProjectDataAndEmployees()
  },

  name: 'Project',
  props: {
  },
  components: {
    //TeamBox
  }
}
</script>

<style scoped>

</style>
