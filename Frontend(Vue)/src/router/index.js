import Vue from "vue";
import VueRouter from "vue-router";
import ViewEmployees from "../views/ViewEmployees.vue";
import EmployeePage from "../views/EmployeePage.vue";
import EditEmployeePage from "../views/EditEmployeePage";
import AddEmployee from "../views/AddEmployee";
import AddUser from "../views/AddUser";
import EmployeesBanner from '../components/layout/EmployeesBanner.vue'
import WelcomePage from '../components/layout/WelcomePage.vue'
import SkillRequirementsPage from '../views/SkillRequirementsPage.vue'
import AddProject from '../views/AddProject.vue'
import EditProject from '../views/EditProject'
import ProjectPage from '../views/ProjectPage.vue'
import ViewProjects from '../views/ViewProjects.vue'


Vue.use(VueRouter);

const routes = [
    {
        path: "/employees",
        name: "viewemployees",
        component: ViewEmployees
        },
    
    {
        path: "/employees/employeePage",
        name: "employeepage",
        component: EmployeePage
    },

    {
        path: "/employees/editEmployeePage",
        name: "editemployeepage",
        component: EditEmployeePage
    },

    {
        path: "/employees/addEmployee",
        name: "addemployee",
        component: AddEmployee
    },

    {
        path: "/employees/addUser",
        name: "adduser",
        component: AddUser
    },

    {
        path: "/EmployeesBanner",
        name: "EmployeesBanner",
        component: EmployeesBanner
    },

    {
        path: "/WelcomePage",
        name: "WelcomePage",
        component: WelcomePage
    },

    {
        path: '/projects/projectPage',
        name: 'projectpage',
        component: ProjectPage
      },

      {
        path: '/projects/editProject',
        name: 'editproject',
        component: EditProject
      },

      {
        path: '/projects/addProject',
        name: 'addproject',
        component: AddProject
      },

      {
        path: '/projects',
        name: 'viewproject',
        component: ViewProjects
      },

      {
        path: '/projects/skillrequirements',
        name: 'skillrequirements',
        component: SkillRequirementsPage
      }

]

const router = new VueRouter (
    {routes}
);

export default router;