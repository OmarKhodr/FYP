<template>
<div id="app">
  <div class='ProjectsBody'>

    <div>
      <b-navbar type='light' variant='light'>
        <b-nav-form>
          <b-form-input class='mr-sm-2' placeholder='Search by Name...' type='text' v-model="searchname"></b-form-input>
          <b-button variant='outline-success' class='my-2 my-sm-0' type='submit' @click="filterbyname(searchname)">Search</b-button>
        </b-nav-form>
      </b-navbar>
    </div>

    <Projects v-bind:projects="projects.content"/>

  </div>
</div>
</template>

<script>

import Projects from '../components/Projects'
import axios from 'axios'

export default {
  name: 'ViewProjects',
  components: {
    Projects
  },
  data () {
    return {
      projects: [
        // to be imported using axios, GET
      ],
      searchname: ''
    }
  },
  props: { // so far we have no props
  },
  methods: {
    getprojects () {
      axios.get('http://localhost:8888/projects')
        .then(res => (this.projects = res.data))
        .catch(err => print(err))
    },
    filterbyname (searchname) {
      const filtered = this.projects.content.filter(function (value) {
        
        return ((value.name) === searchname)
      })
      
      this.projects.content = filtered
      this.searchname = ''
    }
  },
  created () {
    this.getprojects()
  }
}
</script>

<style>

</style>