package miage.pds.api.jdatour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DumpDatabaseController {

    @RequestMapping(value = "/database/dump", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    DumpDatabaseResponse dumpDatabase (@RequestBody DumpDatabaseRequest dumpDatabaseRequest) {
        return null;
    }


    public class DumpDatabaseResponse {

    }

    public class DumpDatabaseRequest {


    }
}
