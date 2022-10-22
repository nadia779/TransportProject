package models;

import java.sql.Timestamp;


public class Utilisateurs {
   private int id ; 
   private String nom ;
   private String prenom; 
   private String email;
   private String telephone ;
   private String ville ; 
   private String password ; 
   private Timestamp date_inscription ; 
   public String role;
   
   public Utilisateurs(int id , String prenom, String nom, String email, String telephone, 
		   String ville, String password,Timestamp date_inscription,String role) {
	super();
	this.id=id;
	this.prenom = prenom;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.telephone = telephone;
	this.ville = ville;
	this.password = password;
	this.date_inscription = date_inscription;
	this.role=role ; 
    }

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public int getId() {
	return id;
}

public String getNom() {
	return nom;
}

public String getPrenom() {
	return prenom;
}

public String getEmail() {
	return email;
}

public String getTelephone() {
	return telephone;
}

public String getVille() {
	return ville;
}

public String getPassword() {
	return password;
}

public Timestamp getDate_inscription() {
	return date_inscription;
}

@Override
public String toString() {
	return "Utilisateurs [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", telephone=" + telephone
			+ ", ville=" + ville + ", password=" + password + ", date_inscription=" + date_inscription + "]";
} 
   
   
      
   
   
    
    
   
    
}
