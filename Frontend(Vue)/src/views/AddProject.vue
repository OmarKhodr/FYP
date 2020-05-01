<template>
  <div id="app">
    <br>
    <br>

    <div v-if="stage === 1" id="stage_one" >
      <div>
        <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Project Name:' label-for='input-horizontal'>
          <b-form-input id='input-horizontal' v-model="projectdata.project_name" > </b-form-input>
        </b-form-group>

        <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Project Leader:' label-for='input-horizontal'>
          <EmployeeToAddForNewProject  @empSelected="projectdata.project_leader = $event"></EmployeeToAddForNewProject>
        </b-form-group>

        <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Start Date:' label-for='input-horizontal'>
          <b-form-datepicker id='example-datepicker'  class='mb-2' v-model="blueprint.start_date" ></b-form-datepicker>
        </b-form-group>

        <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Deadline:' label-for='input-horizontal'>
          <b-form-datepicker id='example-datepicker'  class='mb-2' v-model="blueprint.deadline"></b-form-datepicker>
        </b-form-group>

        <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Severity:' label-for='input-horizontal'>
          <b-form-input id='input-horizontal' v-model="projectdata.severity"></b-form-input>
        </b-form-group>

      </div>
      <b-button variant='success' class='routerlinkview' v-on:click="stage = 2">Choose Skill Requirements</b-button>
      <b-button  @click="this.addManually" v-on:click="stage = 4">Choose Manually</b-button>
      <!--br>
      Or
      <br>
      <b-button @click="this.addemployeefield">Add Employees Manually</b-button>
      <br>
      <br-->
    </div>
    <div v-if="stage === 2" id="stage_two">
      <div v-for="i in demands" :key="i.numOfEmployees">
        <DemandBox v-bind:skilldata="i"></DemandBox>
      </div>
      <br />
        <b-button @click="this.addskillfield">Add Skill Requirement</b-button>
        <b-button id="algo-id" @click="this.algo">Get Optimal Team</b-button>
    </div>
    <!-- THIS IS FOR WHEN WE GET OPTIMAL TEAMS -->
    <div v-if="stage === 3" id="stage_three">
      <h1>Optimal Teams</h1>
        <TeamBox :members = 'this.teamResult'></TeamBox>
        <b-button  @click="this.addManually" v-on:click="stage = 4">Choose Manually</b-button>
        <b-button variant='success' class='routerlinkview' @click="chooseTeam('optimal')"><router-link to='/projects'>Add Employees</router-link></b-button>
    </div>
    <div v-if="stage === 4" id="stage_four">
      <h1>Manual Teams</h1>
      <div v-for="i in employees" v-bind:key="i.id">
        <EmployeeToAddForNewProject v-bind:employeedata="i"></EmployeeToAddForNewProject>
      </div>
      <br />
      <b-button @click="this.addemployeefield">Add Employee Field</b-button>
      <b-button variant='success' class='routerlinkview' @click="chooseTeam('manual')"><router-link to='/projects'>Add Employees</router-link></b-button>
    </div>
  </div>
</template>

<script>

import axios from 'axios'
import EmployeeToAddForNewProject from '../components/EmployeeToAddForNewProject'
import TeamBox from './TeamBox.vue'
import DemandBox from './DemandBox.vue'

export default {
  name: 'AddProjectPage',

  data () {
    return {
      stage: 1,
      demands: [
      ],
      blueprint: {
        start_date: null,
        deadline: null
      },
      projectdata: {
        project_id: this.generaterandomidnumber(),
        start_date: null,
        effectiveness: null,
        efficiency: null,
        project_name: null,
        end_date: null,
        deadline: null,
        completed: 0,
        project_leader: null,
        severity: null
      },
      employees: [],
      optimalTeams: [],
      teamResult: [],
      chosenTeam: []
    }
  },
  components: {
    EmployeeToAddForNewProject,
    DemandBox,
    TeamBox
  },
  methods: {
    addManually () {
    },
    chooseTeam (choice) {
      // add without employees first
      this.addproject()

      // now add employees
      if (choice === 'optimal') {
        for (var i = 0; i < this.teamResult.length; i++) {
          axios.post('http://localhost:8888/workingon/' + this.teamResult[i].id + '/' + this.projectdata.project_id)
            .then()
            .catch()
        }
      }
      if (choice === 'manual') {
        for (var j = 0; j < this.employees.length; j++) {
          this.employees[j].id = parseInt(this.employees[j].id)
          axios.post('http://localhost:8888/workingon/' + this.employee[j].id + '/' + this.projectdata.project_id)
            .then()
            .catch()
        }
      }

      alert('Project Added!')
    },

    addemployeefield () {
      var temp = {}
      temp.employee = '...'
      this.employees.push(temp)
    },

    generaterandomidnumber () {
      return Math.floor(Math.random() * 100000) + 1
    },
    addproject () {
      this.projectdata.start_date = this.blueprint.start_date
      this.projectdata.deadline = this.blueprint.deadline

      if (typeof this.projectdata.effectiveness === 'string') {
        this.projectdata.effectiveness = parseFloat(this.projectdata.effectiveness)
      }
      if (typeof this.projectdata.efficiency === 'string') {
        this.projectdata.effeciency = parseFloat(this.projectdata.efficiency)
      }

      axios.post('http://localhost:8888/projects', this.projectdata)
        .then()
        .catch()

      // now add employees one by one
    },
    async algo () {
      const temp = {
        demands: this.demands,
        blueprint: this.blueprint
      }
      JSON.stringify(temp)
      var config = {
        /* headers: {'Access-Control-Allow-Origin': '*'} */
        headers: {'Access-Control-Allow-Origin': '*', 'Content-Type': 'application/json', 'Cache-Control': 'no-cache', 'Accept': '*/*'}
      }
      axios.post('http://localhost:8888/projects/findteam', JSON.stringify(temp), config)
        .then(function (response) {
          this.optimalTeams = response.data
          
        })
        .catch()
        /*
      try {
        let response = await axios.post('http://localhost:8888/projects/findteam', temp)
        this.optimalTeams = response.data
        console.log(response)
      } catch (err) {
        console.log(err)
      } */

      for (var i = 0; i < this.optimalTeams.length; i++) {
        for (var j = 0; j < this.demands[i].numOfEmployees; j++) {
          this.teamResult.push(this.optimalTeams[i][j])
        }
      }
      this.stage = 3
    },
    addskillfield () {
      var temp2 = {}
      temp2.skillName = ''
      temp2.skillCategory = ''
      temp2.numOfEmployees = null
      this.demands.push(temp2)
    }
  }
}
</script>

<style scoped>
#algo-id{
  background-color: green;
}
</style>