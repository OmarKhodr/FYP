<template>
    <div id="app">
        <!-- {{this.skills}}
        {{this.employeeid}} -->
        <!-- {{this.idofthisemployee}} -->
        <div v-for="i in skills" v-bind:key="i.id">  <!--v-bind:key="i.id" -->
            <Skill v-bind:skilldata="i"  ></Skill>  
            

         

        </div>
        <!-- {{this.skills}} -->
        <br>
        <b-button @click="this.addskillfield">Add Skill</b-button>

        <br>
        <br>

        <div v-for="i in skillstoadd" v-bind:key="i.competency">
            <SkillToAdd v-bind:skilldata="i"> </SkillToAdd>
        </div>  
        

        <!-- <br>
        {{this.skills}}  
        {{this.employeeid}} -->
    </div>
</template>

<script>
import Skill from "./Skill";
import SkillToAdd from "./SkillToAdd";
export default {
    name:"Skills",
    components: {
        Skill,
        SkillToAdd
    },

    data () {
        return {
            skillstoadd : [],
            //employeeid : 0  //dummy value
        }
    },
    props: {
        skills : [],
        idofthisemployee : {}
    }
    // ['skills']                     revert to this if it causes any errors
        
    ,

    methods: {

        // extractemployeeidfromskills() {
           
        //    this.employeeid= this.skills[0].id;
           
        // },

        addskillfield() {
            var temp = {};
            temp.emp_id = this.idofthisemployee;//this.employeeid;
            temp.category = "...";                //post authorization
            temp.skill = "...";
            temp.competency = "#";

            this.skillstoadd.push(temp);
           
        }
       
    },

    created() {

       //setTimeout(() => {  this.extractemployeeidfromskills() }, 1500);
    }
    
};
</script>

<style scoped>
</style>