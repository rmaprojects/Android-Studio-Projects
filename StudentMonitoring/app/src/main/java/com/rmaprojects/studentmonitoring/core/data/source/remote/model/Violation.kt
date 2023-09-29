package com.rmaprojects.studentmonitoring.core.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Violation(
    @SerialName("teacher_id")
    val uuid: String,
    @SerialName("student_name")
    val studentName: String,
    @SerialName("student_class")
    val studentClass: String,
    @SerialName("violation_description")
    val description: String,
    @SerialName("point")
    val point: Int,
    @SerialName("teacher_name")
    val teacherName: String? = ""
)