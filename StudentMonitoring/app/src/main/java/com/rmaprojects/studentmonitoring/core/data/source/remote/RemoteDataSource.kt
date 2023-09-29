package com.rmaprojects.studentmonitoring.core.data.source.remote

import com.rmaprojects.studentmonitoring.core.data.source.local.kotpref.SharedPreference
import com.rmaprojects.studentmonitoring.core.data.source.remote.model.NewUser
import com.rmaprojects.studentmonitoring.core.data.source.remote.model.Violation
import com.rmaprojects.studentmonitoring.core.data.source.remote.table.Table
import com.rmaprojects.studentmonitoring.core.data.source.remote.table.View
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.LogoutScope
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.selects.whileSelect
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val client: SupabaseClient
) {
    suspend fun register(
        newEmail: String,
        newPassword: String,
        name: String
    ) {
        client.gotrue.signUpWith(Email) {
            email = newEmail
            password = newPassword
        }

        val currentSession = client.gotrue.currentSessionOrNull()?.user

        currentSession?.let { user ->
            client.postgrest[Table.TABLE_USER]
                .insert(
                    NewUser(
                        name = name,
                        uuid = user.id
                    )
                )
            SharedPreference.savedUuid = user.id
            SharedPreference.savedName = name
        }
    }

    suspend fun login(
        loginEmail: String,
        loginPassword: String
    ) {
        client.gotrue.loginWith(Email) {
            email = loginEmail
            password = loginPassword
        }

        val user = client.gotrue.currentUserOrNull()

        val userName = client.postgrest[Table.TABLE_USER]
            .select(
                columns = Columns.list("teacher_name"),
                single = true
            ) {
                eq("id", "${user?.id}")
            }.decodeSingle<String>()

        SharedPreference.savedUuid = client.gotrue.currentUserOrNull()?.id ?: ""
        SharedPreference.savedName = userName
    }

    suspend fun logOut() {
        return client.gotrue.logout(LogoutScope.GLOBAL)
    }

    suspend fun inputViolationData(
        studentName: String,
        studentClass: String,
        description: String,
        point: Int,
        uuid: String = SharedPreference.savedUuid!!,
    ) {
        client.postgrest[Table.TABLE_VIOLATION]
            .insert(
                Violation(
                    uuid = uuid,
                    studentName = studentName,
                    studentClass = studentClass,
                    description = description,
                    point = point
                )
            )
    }

    suspend fun getAllViolationHistory(): List<Violation> {
        return client.postgrest[View.VIOLATION_HISTORY].select().decodeList()
    }
}