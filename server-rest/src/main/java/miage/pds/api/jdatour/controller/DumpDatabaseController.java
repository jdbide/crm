package miage.pds.api.jdatour.controller;

import miage.pds.api.jdatour.Shell;
import miage.pds.api.tlacouque.uc.admin.ref.customer.SpringMongoConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DumpDatabaseController {
    private String MONGODUMP_COMMAND = "mongodump --db " + SpringMongoConfig.DB_NAME;

    @RequestMapping(value = "/database/dump", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    ResponseEntity<DumpDatabaseResponse> dumpDatabase (@RequestBody DumpDatabaseRequest dumpDatabaseRequest) {
        final Integer[] commandExitCode = new Integer[1];

        new Thread(() -> {
            commandExitCode[0] = Shell.executeCommand(MONGODUMP_COMMAND);
        }).run();

        return new ResponseEntity<DumpDatabaseResponse>(HttpStatus.OK);
    }


    public class DumpDatabaseResponse {

    }

    public class DumpDatabaseRequest {


    }
}
