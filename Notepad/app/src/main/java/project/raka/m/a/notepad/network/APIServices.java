package project.raka.m.a.notepad.network;

import project.raka.m.a.notepad.model.listDataNote.ResponseListNotes;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIServices {
    //Sebagai pen-generate
    //Untuk menampilkan list data
    //Komunikasi Client dengan service
    @FormUrlEncoded
    @POST("module/list_notes.php")
    Call<ResponseListNotes> requestListNotes(
        @Field("username") String username,
        @Field("password") String password,
        @Field("hash_useraccess") String hash_useraccess,
        @Field("level") String level,
        @Field("category") String category
    );
}
