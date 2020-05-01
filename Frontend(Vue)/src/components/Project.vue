<template>
<div id="app">
    <div class="project-object">
         <b-button-group vertical>
            <b-button @click="sendProjdataUsingEventBus"><router-link to="/projects/ProjectPage"> {{project.project_name}} </router-link></b-button>
            <!--b-button>{{project.department}}</b-button-->
         </b-button-group>
    </div>
</div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import {EventBus} from '@/event-bus.js'

export default {
  name: 'Project',
  props: {
    project: {}
  },
  methods: {
    sendProjdataUsingEventBus () {
      EventBus.$emit('eventbusproject', this.project)
    }
  }
}
</script>

<style scoped>
.project-object {
    background: #f4f4f4;
    padding: 10px;
    border-bottom: 1px #ccc dotted;
}

</style>
