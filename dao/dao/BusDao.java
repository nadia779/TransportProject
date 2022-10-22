package dao;

import java.util.List;

import models.Bus;


public interface BusDao {
//	boolean addBus(Bus c);
//	boolean updateBus(Bus c);
//	boolean deleteBus(String code_abonne);
//	Bus getBusByCode(String code_abonne);
//	Bus getBusByUsername(String username);
//	Bus getBus(String email , String password);
	List<Bus> getBuses();
	List<Bus> getBuses(int numLigne);
	boolean updatePosition(int id, double latitude, double longitude);
}
