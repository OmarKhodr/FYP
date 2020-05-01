<template>
  <div id="app">
    <br />
    <br />
    <br />
    <div class="demand-box">
      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Skill Name:' label-for='input-horizontal'>
        <b-col sm="3">
          <b-form-input id='input-horizontal' v-model="skilldata.skillName" placeholder="Java, Visual Studio..."> </b-form-input>
        </b-col>
      </b-form-group>
      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Skill Category:' label-for='input-horizontal'>
        <b-col sm="3">
          <b-form-input id='input-horizontal' v-model="skilldata.skillCategory" placeholder="Backend, Compilers..."> </b-form-input>
        </b-col>
      </b-form-group>
      <b-form-group id='fieldset-horizontal' label-cols-sm='4' label-cols-lg='3' label='Number of Employees:' label-for='input-horizontal'>
        <b-col sm="1">
          <b-form-input type="number" value="1" min="1" max="50" step="1" v-model="skilldata.numOfEmployees"/>
        </b-col>
      </b-form-group>

    </div>
</div>
</template>
<script>

export default {
  name: 'Demand',
  props: {
    skilldata: {}
  }

}
</script>
<style scoped>
.demand-box{
  background: lightgrey;
  padding: 10px;
  border-radius: 24px;
}
/*
.demand-box{
  margin: 0;
  background: lightgrey;
  width: 700px;
  padding: 40px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  text-align: left;
  border-radius: 24px;
}
b-form-group{
  text-align: left;
}*/
</style>
