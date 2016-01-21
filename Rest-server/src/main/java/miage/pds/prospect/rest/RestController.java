package miage.pds.prospect.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Truong on 1/20/2016.
 */
@Controller
@RequestMapping("/prospect")
public class RestController {

    @RequestMapping(value = "/prospects", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String helloWorld(){
        return "hello World";
    }
}
