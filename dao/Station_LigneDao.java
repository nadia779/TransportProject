package dao;

import java.util.List;

import models.Station_Ligne;


public interface Station_LigneDao {
//	boolean addStation_Ligne(Station_Ligne c);
//	boolean updateStation_Ligne(Station_Ligne c);
//	boolean deleteStation_Ligne(String code_abonne);
//	Station_Ligne getStation_LigneByCode(String code_abonne);
//	Station_Ligne getStation_LigneByUsername(String username);
//	Station_Ligne getStation_Ligne(String email , String password);
//	List<Station_Ligne> getStation_Lignes();
	List<Station_Ligne> getStation_Lignes(int ligne);
}
