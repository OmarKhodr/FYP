<template>
    <div id="app">
      <!--ul class="list-group">
        <li v-for="i in members" :key="i.first_name">
          {{ i.employee.first_name }} {{i.employee.last_name}}
        </li>
      </ul-->
      <Employees v-bind:employees="members"/>
    </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Employees from '../components/Employees.vue'

export default {
  name: 'TeamBox',
  data () {
    return {

    }
  },
  props: ['members'],
  methods: {
  },
  components: {
    Employees
  }
}
</script>

<style scoped>

</style>
