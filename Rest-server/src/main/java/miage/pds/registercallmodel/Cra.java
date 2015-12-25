package miage.pds.registercallmodel;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Cra {

    private long iduser;
    private long idcontact;
    private String clientname;
    private String contactname;
    private String comments;
    private String subject;
    private String date;
    private int duration;

    
	public long getIduser() {
		return iduser;
	}
	public void setIduser(long iduser) {
		this.iduser = iduser;
	}
	public long getIdcontact() {
		return idcontact;
	}
	public void setIdcontact(long idcontact) {
		this.idcontact = idcontact;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}


}