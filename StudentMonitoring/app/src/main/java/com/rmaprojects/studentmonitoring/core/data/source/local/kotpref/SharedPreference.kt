package com.rmaprojects.studentmonitoring.core.data.source.local.kotpref

import com.chibatching.kotpref.KotprefModel

object SharedPreference: KotprefModel() {
    var savedUuid by nullableStringPref()
    var savedName by nullableStringPref()
}