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

                                              <!-- post authorization -->
    <b-form-group
        label-cols-sm="3"
        label="Category:"
        label-align-sm="right"
        label-for="nested-skill"
      >
       <b-alert show>{{skilldata.category}}</b-alert>
        
      </b-form-group>



      <b-form-group
        label-cols-sm="3"
        label="Skill:"
        label-align-sm="right"
        label-for="nested-skill"
      >
       <b-alert show>{{skilldata.skill}}</b-alert>
        
      </b-form-group>  

            <b-form-group
        label-cols-sm="3"
        label="Competency:"
        label-align-sm="right"
        label-for="nested-competency"
      >
        <b-form-input id="nested-competency" v-model="skilldata.competency"></b-form-input>
      </b-form-group>

      <b-button variant="danger" @click="this.deleteskill">Delete</b-button>
      &nbsp;
      <b-button variant="outline-primary" @click="this.saveskillchanges">Save Changes</b-button>

    </b-form-group>
  </b-card>
</div>
<!-- {{this.skilldata}} -->
</div>

    
    
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from "axios";


export default {
    name: "Skill",
    
    props: {
        skilldata: {}
    },

    methods: {
      
      saveskillchanges(e) {
        e.preventDefault();

        var tempskill = {

        }

        // tempskill.emp_id = this.skilldata.id;
        // tempskill.skill = this.skilldata.skill;
        tempskill.competency = parseFloat(this.skilldata.competency);

        
        
        

         axios.put('http://localhost:8888/skills/'+this.skilldata.id+'/'+this.skilldata.skill+'/'+this.skilldata.category, tempskill)
            .then( )
            .catch( err =>  print(err));

      },

       deleteskill() {
         var div= document.getElementById('skillcard');
        axios
      .delete('http://localhost:8888/skills/'+this.skilldata.id+'/'+this.skilldata.skill)
      .then(
        div.remove()
      )
      
      .catch(err => print(err));
    }
          
    }
};
</script>