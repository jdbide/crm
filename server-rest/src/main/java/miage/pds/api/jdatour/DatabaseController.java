package miage.pds.api.jdatour;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.prefs.Preferences;

@Controller
public class DatabaseController {
    private String PREF_KEY_DUMP_FILE_ID = "dumpFileId";

    @RequestMapping(value = "/database/dump", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    ResponseEntity<String> dumpDatabase (@RequestBody String dumpDatabaseRequest) {
        String command = "mongodump --db crm --path /home && tar -cvjf /home/dump /home/dump.tar.bz2";
        //String           command      = "cmd /c dir";
        Integer          returnCode   = Shell.executeCommand(command);
        InputStream      secretStream = DatabaseController.class.getClassLoader().getResourceAsStream("ESIAG-PDS-2eac60e12628.json");
        GoogleCredential credential   = null;

        if (returnCode != null && returnCode != 0) {
            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        try {
            credential = GoogleCredential.fromStream(secretStream).createScoped(Collections.singleton("https://www.googleapis.com/auth/drive"));
            credential.refreshToken();

            //TODO : Remplacer par le dump
            InputStream inputStream = DatabaseController.class.getClassLoader().getResourceAsStream("ESIAG-PDS-2eac60e12628.json");

            byte[] datas = IOUtils.readFully(inputStream, -1, true);
            String token = credential.getAccessToken();

            Call<UploadFileResponse>     call     = RetrofitHandlerSingleton.getInstance().getDriveApi().updloadFile("Bearer " + token, datas);
            Response<UploadFileResponse> response = call.execute();

            Preferences.userRoot().node(this.getClass().getName()).put(PREF_KEY_DUMP_FILE_ID, response.body().id);
        }
        catch (IOException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/database/restore", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    ResponseEntity<String> restoreDatabase (@RequestBody String restoreDatabaseRequest) {
        InputStream      secretStream = DatabaseController.class.getClassLoader().getResourceAsStream("ESIAG-PDS-2eac60e12628.json");
        GoogleCredential credential   = null;

        try {
            credential = GoogleCredential.fromStream(secretStream).createScoped(Collections.singleton("https://www.googleapis.com/auth/drive"));
            credential.refreshToken();

            String       fileId        = Preferences.userRoot().node(this.getClass().getName()).get(PREF_KEY_DUMP_FILE_ID, "");
            String       authorization = "Bearer " + credential.getAccessToken();
            Call<byte[]> call          = RetrofitHandlerSingleton.getInstance().getDriveApi().downloadFile(authorization, fileId);

            Response<byte[]> response = call.execute();

            if (response != null && response.body() != null) {
                String path = "C:" + File.separator + "dump" + File.separator + "secret.json";
                File   f    = new File(path);

                f.getParentFile().mkdirs();
                f.createNewFile();

                FileOutputStream fos = new FileOutputStream(path);
                fos.write(response.body());
                fos.close();
            }
        }
        catch (IOException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String command = "tar -xvjf /home/dump.tar.bz2 /home/dump && mongorestore --db crm /home/dump";

        if (Shell.executeCommand(command) != 0) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
