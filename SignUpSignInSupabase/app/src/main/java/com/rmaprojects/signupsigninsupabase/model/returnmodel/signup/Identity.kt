package com.rmaprojects.signupsigninsupabase.model.returnmodel.signup

data class Identity(
    val created_at: String,
    val id: String,
    val identity_data: IdentityData,
    val last_sign_in_at: String,
    val provider: String,
    val updated_at: String,
    val user_id: String
)