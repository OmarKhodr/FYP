<template>
  <div id="app">
    <br>
    <br>
    <br>
    <div v-for="i in demands" :key="i.numOfEmployees">
      <DemandBox v-bind:skilldata="i"></DemandBox>
    </div>
    <br />
      <b-button @click="this.addskillfield">Add Skill Requirement</b-button>
      <b-button id="algo-id" @click="algo">Get Optimal Team</b-button>
  </div>
</template>

<script>

import axios from 'axios'
import DemandBox from './DemandBox.vue'

export default {
  name: 'SkillRequirementsPage',

  data () {
    return {
      demands: [
      ]
    }
  },
  components: {
    DemandBox
  },
  methods: {
    algo () {
      const temp = {
        demands: this.demands
      }
      
      axios.get('localhost:8888/projects/findteam', temp)
        .then()
        .catch()
    },
    addskillfield () {
      var temp = {}
      temp.skillName = ''
      temp.skillCategory = ''
      temp.numOfEmployees = null
      this.demands.push(temp)
    }
  }
}
</script>
<style scoped>
#algo-id{
  background-color: green;
}
</style>