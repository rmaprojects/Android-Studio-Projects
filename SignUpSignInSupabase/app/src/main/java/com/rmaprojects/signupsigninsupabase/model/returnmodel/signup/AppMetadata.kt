package com.rmaprojects.signupsigninsupabase.model.returnmodel.signup

data class AppMetadata(
    val provider: String,
    val providers: List<String>
)