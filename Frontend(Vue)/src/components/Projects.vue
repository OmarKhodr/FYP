<template>
    <div id="app">
        <div v-for="i in projects" v-bind:key="i.id">
            <Project v-bind:project="i"  ></Project>

        </div>
    </div>
</template>

<script>
import Project from './Project'
export default {
  name: 'Projects',
  components: {
    Project
  },
  props: ['projects'],

  methods: {

  }

}
</script>

<style scoped>
</style>