package com.rmaprojects.signupsigninsupabase.model.returnmodel.signin

data class User(
    val app_metadata: AppMetadata,
    val aud: String,
    val confirmation_sent_at: String,
    val confirmed_at: String,
    val created_at: String,
    val email: String,
    val email_confirmed_at: String,
    val id: String,
    val identities: List<Identity>,
    val last_sign_in_at: String,
    val phone: String,
    val role: String,
    val updated_at: String,
    val user_metadata: UserMetadata
)