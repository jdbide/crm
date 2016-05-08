package miage.pds.api.jdatour.controller;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DriveApi {
    @POST("/upload/drive/v3/files?uploadType=media")
    public Call<String> updloadFile (@Header("Authorization") String authorization, @Body byte[] datas);
}
