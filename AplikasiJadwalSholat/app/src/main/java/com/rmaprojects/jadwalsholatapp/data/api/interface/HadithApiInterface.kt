package com.rmaprojects.jadwalsholatapp.data.api.`interface`

import com.rmaprojects.jadwalsholatapp.data.api.model.hadits.HadithModel
import retrofit2.http.GET
import retrofit2.http.Path

interface HadithApiInterface {
    @GET("{slug}/{hadithNumber}")
    suspend fun getHadith(
        @Path("slug") hadithSlug:String,
        @Path("hadithNumber") hadithNumber:Int
    ) : HadithModel
}