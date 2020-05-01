package com.example.postgresdemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TeamBlueprint {

    private Demand[] demands;
    private ProjectBlueprint blueprint;

    public Demand[] getDemands() {
        return demands;
    }

    public void setDemands(Demand[] demands) {
        this.demands = demands;
    }

    public ProjectBlueprint getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(ProjectBlueprint blueprint) {
        this.blueprint = blueprint;
    }
}

//{
//    "demands": [
//        {
//            "numOfEmployees": 2,
//            "skillCategory": "Frontend",
//            "skillName": "VueJS"
//        },
//        {
//            "numOfEmployees": 2,
//            "skillCategory": "Frontend",
//            "skillName": "HTML/CSS"
//        },
//        {
//            "numOfEmployees": 2,
//            "skillCategory": "Backend",
//            "skillName": "Java"
//        }
//    ],
//    "blueprint": {
//        "start_date": "2020-06-23",
//        "deadline": "2020-12-30"
//    }
//}
