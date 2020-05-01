<template>
  <div id="app">
    <br>
    <br>
    <div v-if="boolComplete === 0">
      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Project Name:' label-for='input-horizontal'>
        <b-form-input id="input-horizontal" v-model="datatoedit.project_name" > </b-form-input>
      </b-form-group>

      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Project Leader:' label-for='input-horizontal'>
        <b-form-input id="input-horizontal" v-model="datatoedit.project_leader"></b-form-input>
      </b-form-group>

      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Start Date:' label-for='input-horizontal'>
        <b-form-datepicker id='example-datepicker' v-model="datatoedit.start_date"></b-form-datepicker>
      </b-form-group>

      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Deadline:' label-for='input-horizontal'>
        <b-form-datepicker id='example-datepicker' v-model="datatoedit.deadline"></b-form-datepicker>
      </b-form-group>

      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Severity:' label-for='input-horizontal'>
        <b-form-input id="input-horizontal" v-model="datatoedit.severity"></b-form-input>
      </b-form-group>

    <b-button variant="success" @click="saveprojectdata"><router-link to="/projects"> Update Project Data</router-link></b-button>
    <b-button variant="success" @click="boolComplete = 1" v-if="this.datatoedit.completed === 0">Project Completed</b-button>
    <br>

    <br>
    <TeamBox></TeamBox>
    <!--Employees v-bind:employees="employeesdata" v-bind:idofthisproject="this.otherprojectdata[0].project_id" ></Employees-->
    </div>
    <div v-if="boolComplete === 1">
      <h1>{{ datatoedit.project_name }}</h1>

      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Rate the effectiveness of the project:' label-for='input-horizontal'>
        <b-col sm="1">
          <b-form-input type="number" value="1" min="1" max="3" step="0.1" v-model="datatoedit.effectiveness"/> <!-- use this to set range -->
        </b-col>
      </b-form-group>

      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Rate the efficiency of the project:' label-for='input-horizontal'>
        <b-col sm="1">
          <b-form-input type="number" value="1" min="1" max="3" step="0.1" v-model="datatoedit.efficiency"/> <!-- use this to set range -->
        </b-col>
      </b-form-group>
      <b-button variant="success" @click="saveprojectdata"><router-link to="/projects"> Confirm </router-link></b-button>
    </div>
  </div>
</template>

<script>
//import Employees from '../components/Employees'
import {EventBus} from '@/event-bus.js'
import TeamBox from './TeamBox.vue'
import axios from 'axios'

export default {
  name: 'EditProject',
  data () {
    return {
      employeesdata: [],
      otherprojectdata: [],
      datatoedit: {},
      boolComplete: 0
    }
  },
  components: {
    //Employees,
    TeamBox
  },
  props: {
  },
  methods: {
    saveprojectdata () {
      if (typeof this.datatoedit.effectiveness === 'string') {
        this.datatoedit.effectiveness = parseFloat(this.datatoedit.effectiveness)
      }

      if (typeof this.datatoedit.efficiency === 'string') {
        this.datatoedit.effeciency = parseFloat(this.datatoedit.efficiency)
      }

      this.datatoedit.completed = 1
      this.datatoedit.end_date = new Date()
      this.datatoedit

      axios.put('http://localhost:8888/projects/' + this.datatoedit.project_id, this.datatoedit)
        .then()
        .catch()
    }
  },
  created () {
    EventBus.$on('eventbusprojectdetails', (value) => {
      this.otherprojectdata = value
    })
    setTimeout(() => { this.datatoedit = JSON.parse(JSON.stringify(this.otherprojectdata[0])) }, 500)
  }
}
</script>

<style scoped>
</style>