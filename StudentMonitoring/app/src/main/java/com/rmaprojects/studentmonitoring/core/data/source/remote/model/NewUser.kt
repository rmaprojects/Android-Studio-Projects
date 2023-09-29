package com.rmaprojects.studentmonitoring.core.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewUser(
    @SerialName("teacher_name")
    val name: String,
    @SerialName("id")
    val uuid: String
)