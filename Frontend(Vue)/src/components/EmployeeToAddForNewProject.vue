<template>
  <div id="app">
    <div id='employeecard'>
      <b-card bg-variant='light'>
        <b-form-group
        label-cols-lg='3'
        label=''
        label-size='lg'
        label-class='font-weight-bold pt-0'
        class='mb-0'
        >
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelect01">Employee: </label>
          </div>
          <select v-model="temp">
            <option v-for="i in employees" v-bind:key="i.emp_id" v-on:selected='empSelected()'>{{i.first_name}} {{i.last_name}}</option>
          </select>
        </div>
      </b-form-group>
    </b-card>
  </div>

</div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Employees from './Employees.vue'
import axios from 'axios'

export default {
  name: 'EmployeeToAdd',
  data () {
    return {
      employees: [
      ],
      temp: []
    }
  },
  components: {
    //Employees
  },
  props: {
  },
  methods: {
    getemployees () {
      axios.get('http://localhost:8888/employees')
        .then(res => (this.employees = res.data.content))
        .catch(err => print(err))
    },
    empSelected () {
      this.$emit('empSelected')
    }
  },
  computed: {
    employeeList: () => Employees
  },
  created () {
    this.getemployees()
  }
}
</script>

<style scoped>

</style>