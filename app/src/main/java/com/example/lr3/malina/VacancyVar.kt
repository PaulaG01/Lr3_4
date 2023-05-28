package com.example.lr3.malina

import com.fasterxml.jackson.annotation.JsonIgnore

class VacancyVar {
    @JsonIgnore
    private val cities: Map<String, Int> = mapOf("Minsk" to 0, "Krakow" to 1, "Moscow" to 2)
    @JsonIgnore
    private val positions: Map<String, Int> = mapOf("Junior" to 0, "Middle" to 1, "Senior" to 2)

    @JsonIgnore
    private val positionImages: Map<String, Int> = mapOf(
        "Junior" to 0,
        "Middle" to 1,
        "Senior" to 2
    )

    var id: Int = 1
    var title: String = ""
    var position: String = ""
    var city: String = ""
    var days: Any = 0
    var skills: List<Skill> = listOf()

    fun getCities():Map<String, Int>{
        return cities;
    }

    fun getPositions():Map<String, Int>{
        return positions;
    }

    fun getPositionsImages(): Map<String, Int> {
        return positionImages;
    }

    fun resetData() {
        title = ""
        days = 0
        city = ""
        position = ""
        skills = listOf()
    }
}