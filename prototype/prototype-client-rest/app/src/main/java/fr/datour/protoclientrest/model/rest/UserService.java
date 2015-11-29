package fr.datour.protoclientrest.model.rest;

import java.util.ArrayList;

import fr.datour.protoclientrest.model.entities.CreateOrUpdateResponse;
import fr.datour.protoclientrest.model.entities.User;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface UserService {
    String BASE_URL = "http://192.168.20.3:8082";

    @POST("/SpringRESTapi/user/addUser/{lastName}/{firstName}")
    Call<CreateOrUpdateResponse> createUser(@Path("lastName") String lastName, @Path("firstName") String firstName);

    @GET("/SpringRESTapi/users")
    Call<ArrayList<User>> getUsers();

    @POST("/SpringRESTapi/user/updateUser/{id}/{lastName}/{firstName}")
    Call<CreateOrUpdateResponse> updateUser(@Path("id") Integer id, @Path("lastName") String lastName, @Path("firstName") String firstName);

    @GET("/SpringRESTapi/user/deleteUser/{id}")
    Call<CreateOrUpdateResponse> deleteUser(@Path("id") Integer id);
}

