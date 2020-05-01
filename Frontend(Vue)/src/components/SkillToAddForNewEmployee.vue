<template>
    <div id="app">
         <div id="skillcard">
  <b-card bg-variant="light">
    <b-form-group
      label-cols-lg="3"
      label=""
      label-size="lg"
      label-class="font-weight-bold pt-0"
      class="mb-0"
    >
                        <!-- post authorization-->

      <b-form-group
        label-cols-sm="3"
        label="Category:"
        label-align-sm="right"
        label-for="nested-skill"
      >
         <b-form-input id="nested-skill" v-model="skilldata.category"></b-form-input>
        
      </b-form-group>




      <b-form-group
        label-cols-sm="3"
        label="Skill:"
        label-align-sm="right"
        label-for="nested-skill"
      >
         <b-form-input id="nested-skill" v-model="skilldata.skill"></b-form-input>
        
      </b-form-group>

     

            <b-form-group
        label-cols-sm="3"
        label="Competency:"
        label-align-sm="right"
        label-for="nested-competency"
      >
        <b-form-input id="nested-competency" v-model="skilldata.competency"></b-form-input>
      </b-form-group>

     
     

    </b-form-group>
  </b-card>
</div>
<!-- {{this.skilldata}} -->


</div>   
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// import axios from "axios";


export default {
    name: "SkillToAdd",
    
    props: {
        skilldata: {}
    },

    methods: {

        
      
      

      
          
    }
};
</script>

<style scoped>



</style>