package com.rmaprojects.studentmonitoring.core.domain.model

data class ViolationHistoryData(
    val studentName: String,
    val teacherName: String,
    val description: String,
    val point: Int
)