package miage.pds.api.jbide.uc.notifypresence.model;


public class Tag {
	  private String id;
	 
	   
	  public Tag() {
	  }
	   
	  public Tag(String idUser) {
	    setId(idUser);
	   
	  }
	   
	  public String getId() {
	    return id;
	  }
	   
	  public void setId(String id) {
	    this.id = id;
	  }
	   
	
	   
	  public String toString() {
	    return "[" + getId() + "]";
	  }
	}