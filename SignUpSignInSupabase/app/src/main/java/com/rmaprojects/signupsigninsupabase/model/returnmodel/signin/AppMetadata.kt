package com.rmaprojects.signupsigninsupabase.model.returnmodel.signin

data class AppMetadata(
    val provider: String,
    val providers: List<String>
)