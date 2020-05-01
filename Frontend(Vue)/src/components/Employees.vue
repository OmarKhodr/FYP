<template>
    <div id="app">
        <div v-for="i in employees" v-bind:key="i.id">
            <Employee v-bind:employee="i"  ></Employee>  

            
        </div>
    </div>
</template>

<script>
import Employee from "./Employee";
export default {
    name:"Employees",
    components: {
        Employee
    },
    props: ["employees"],
    methods: {
        
    }
    
};
</script>

<style scoped>
</style>