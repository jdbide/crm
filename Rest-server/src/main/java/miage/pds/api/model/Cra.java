package miage.pds.api.model;

import java.util.HashMap;
import java.util.Map;

public class Cra {
	private Integer iduser;
    private Integer idcontact;
    private Integer contactnumber;
    private Integer duration;
    private String calldate;
    private String subject;
    private String comment;
    

    public Cra(Integer iduser, Integer idcontact, Integer contactnumber,Integer duration, String subject, String calldate, String comment, Map<String, Object> additionalProperties) {
        this.iduser = iduser;
        this.idcontact = idcontact;
        this.contactnumber = contactnumber;
        this.duration = duration;
        this.subject = subject;
        this.calldate = calldate;
        this.comment = comment;
        
    }


}
