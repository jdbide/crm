package miage.pds.api.jdatour.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Controller
public class DumpDatabaseController {
    private String MONGODUMP_COMMAND = "mongodump --db crm && tar -cvjf dump dump.tar.bz2";
    private String secret            = "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"esiag-pds\",\n" +
            "  \"private_key_id\": \"2eac60e1262822ff4dc6d07b454b311f1be4c503\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwUX2PJu4gky5H\\n0J8LTYuGtS3hjrAFoNUk87dgOce+je+iD5/r+UwUP4+55rtUMFxNKjM/bq0TKNjk\\n9Azk9ixLTnarr4WBD5wPJ6n8HwTjMstgNwV+cXVmFWW6LQyNjm8cnYBRgqLhpxyD\\nzlJMkMztDO/c60sTBnh2VERSMuZsXLlO4gMQJyzZZGpLbMKmsVL0WwOokgR6FDMh\\nR6NHpcKJ9FA/gJea6xImVo/uDPYyW1yf/sB9VxTmL+jXwfmdkgVTxBrn4/zcAy80\\nU1pCxG3v1EIg9UYmr8OKDYIopLJBAsRBn+MrGjTqNrNotDCTYZ/V0juSSwquE2IZ\\nMrH63RZTAgMBAAECggEAZ+/RzVZMn4VDjfAMIVSYAFPDdShw2RCDNSI6Ig5NkiYH\\ncgg15Jvz3ifN6O971FghwRc0WJs8iYPVv7mh9EKtv8LEL9brIzZ5b3GZQddjQS9L\\nNi+ZHH1zkDbi3cn6kWO6Wir6XpJcxBqHs6cnWp0tQDwVFazad5+j2VU5ZR8+N/e/\\n3VZr4mBzc8RC0mQGckv4CdAp7/T8nx0lLnXJHVR+AE9mQeFSbB+sNyEXeI7ZO6Qh\\nwOpEudqC6d7Iyz74fmJEkJ2/oV0oWlP/VM0ZqwNLcXvZBvbqDrbIcssNqXeOrQLS\\nDRog0QfwMxSN5lVyA91uJxVIG97ERVIMxWEPt17JMQKBgQD1vUMhLhoV+Zpsivve\\n8UWGuSQ1ZM7V5YK8qwz8L0Tpg/dKwdY9jYxoc9JLgIYiSm5xQEK/KPAmi3YLRRxG\\nIDDhi07VM5SJ22OT8Wiot2WxQIpo3fN0yOFwyYOO0FXAqXxsKWKYDnWv2F2Qz0Y1\\nenOD2T/IJ+XeykEl7eJU8qaveQKBgQC3ri3EaOyCGHDGeeIpy2sNq0XWEDBvaAUj\\njd9ekxHfFM8hJChzhyuLS+QCT7SazyPdCLPMIbVFRxtqdTG4TgqmzVpcUCVtFPAX\\n/hkfPPI4GOzxPM4chrScjBLo6wes2hPg1QoQlgBSPO9fqbOwWwGXS4RPB1mv1d0g\\n2iJgCaJFKwKBgAevzePBz2sUjbILfulwXp5iODG7X4TtUvOX3iYdTGiJiji6a3st\\nbFDwHoniEVANR/se6XeVTj3GawDbPpEq/JxxgRvGK0VMqYa9LGrqobGztSCmoLDs\\nf7DJfOYAuk32fz/a44yiFyhS+kpHUYjmtapQJ2CZbuVTKkqHcGb72B7xAoGAbEYY\\nZHcVM3sEv3qJfumSKXSUriLM6l7+6H5w9WbsWfwCPR77WV6MpIgP1+z0IW2JTnoa\\nwnH8UOIhnxeGP4aBcOIMKe50oWB+RyZZ2LgscDUBNxMIzHf7b2yCmRoyZAoJPbdX\\nP3+soM2arWFzsrlyrqStUYibrbL7rqnnKYWEShECgYEAhvBfeZovpUIXRauK5QZH\\n876t6LTi3/wmPtBaoruVwIn+UfoDCKJB36O8TioizJV8Jy2IsXyMg0yl3rhNx0QG\\n0v8dJsESzixy3hx+vviYtjzqfIOb6Y1WAjwAXSG0HCFVnZQy8L6hX7468K3Q0isE\\nyFDYXQDkf1Q3UgvPv0fzlhE=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"esiag-pds-drive@esiag-pds.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"111439602340634995996\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://accounts.google.com/o/oauth2/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/esiag-pds-drive%40esiag-pds.iam.gserviceaccount.com\"\n" +
            "}\n";

    @RequestMapping(value = "/database/dump", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    ResponseEntity<String> dumpDatabase (@RequestBody String dumpDatabaseRequest) {

        /* new Thread(new Runnable() {
            @Override
            public void run () {
                Shell.executeCommand(MONGODUMP_COMMAND);
            }
        }).run(); */

        try {
            InputStream      stream     = new ByteArrayInputStream(secret.getBytes(StandardCharsets.UTF_8));
            GoogleCredential credential = GoogleCredential.fromStream(stream).createScoped(Collections.singleton("https://www.googleapis.com/auth/drive"));
            credential.refreshToken();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.googleapis.com").client(httpClient).addConverterFactory(GsonConverterFactory.create()).build();
            DriveApi driveApi = retrofit.create(DriveApi.class);


            InputStream inputStream = DumpDatabaseController.class.getClassLoader().getResourceAsStream("ESIAG-PDS-2eac60e12628.json");
            byte[]      datas       = IOUtils.readFully(inputStream, -1, true);
            String      token       = credential.getAccessToken();

            Call<String> call = driveApi.updloadFile("Bearer " + token, datas);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse (Call<String> call, Response<String> response) {
                    System.out.println("youpi");
                    System.out.println(response.body());
                }

                @Override
                public void onFailure (Call<String> call, Throwable throwable) {
                    System.out.println("bouh :(");
                }
            });

            System.out.println(credential.getAccessToken());


            return new ResponseEntity<String>(credential.getAccessToken(), HttpStatus.OK);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
