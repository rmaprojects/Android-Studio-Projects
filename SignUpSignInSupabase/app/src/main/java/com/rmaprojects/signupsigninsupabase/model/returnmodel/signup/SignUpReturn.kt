package com.rmaprojects.signupsigninsupabase.model.returnmodel.signup

data class SignUpReturn(
    val app_metadata: AppMetadata,
    val aud: String,
    val confirmation_sent_at: String,
    val created_at: String,
    val email: String,
    val id: String,
    val identities: List<Identity>,
    val phone: String,
    val role: String,
    val updated_at: String,
    val user_metadata: UserMetadata
)