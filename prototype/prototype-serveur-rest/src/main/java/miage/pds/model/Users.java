package miage.pds.model;

public class Users {
	  private String id;
	  private String nom;
	  private String prenom;
	   
	  public Users() {
	  }
	   
	  public Users(String idUser, String nomUser, String prenomUser) {
	    setId(idUser);
	    setNom(nomUser);
	    setPrenom(prenomUser);
	  }
	   
	  public String getId() {
	    return id;
	  }
	   
	  public void setId(String id) {
	    this.id = id;
	  }
	   
	  public String getNom() {
	    return nom;
	  }
	   
	  public void setNom(String userName) {
	    this.nom = userName;
	  }
	   
	  public String getPrenom() {
	    return prenom;
	  }
	   
	  public void setPrenom(String userPrenom) {
	    this.prenom = userPrenom;
	  }
	 
	 
	}