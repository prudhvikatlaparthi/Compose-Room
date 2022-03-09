package com.pru.composeroom.utils

object Enumerator {
    sealed class Gender(val name: String, val value: String) {
        object None : Gender("", "None")
        object Male : Gender("Male", "male")
        object Female : Gender("Female", "female")
        object Other : Gender("Other", "Other")
    }

    sealed class MaritalStatus(val name: String, val value: String) {
        object None : MaritalStatus("", "None")
        object Single : MaritalStatus("Single", "single")
        object Married : MaritalStatus("Married", "married")
        object Divorced : MaritalStatus("Divorced", "divorced")
        object LegallySeparated : MaritalStatus("Legally separated", "legally_separated")
        object Widowed : MaritalStatus("Widowed", "widowed")
    }
}
