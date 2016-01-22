package fr.pds.isintheair.crmtab.registercall.Rest;

import android.util.Base64;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import fr.pds.isintheair.crmtab.registercall.Objects.Constants;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by bide2 on 19/01/2016.
 */
public class ServiceGenerator {



    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Constants.getInstance().getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()));




    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, 0, null);
    }

    public static <S> S createService(Class<S> serviceClass, long username, String password) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient();
        // add logging as last interceptor
        httpClient.interceptors().add(logging);


        if ( password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.interceptors().clear();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic);
                    original.newBuilder().header("Accept", "applicaton/json");
                    //original.newBuilder().method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
