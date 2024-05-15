package application ;

import java.sql.Time;

public class Trajet {
    private int id;
    private int id_station_arrivee ;
    private int id_station_depart;
    private Time heure_depart;
    private Time heure_arrivee;
	public Trajet(int id, int id_station_arrivee, int id_station_depart, Time heure_depart, Time heure_arrivee) {
		super();
		this.id = id;
		this.id_station_arrivee = id_station_arrivee;
		this.id_station_depart = id_station_depart;
		this.heure_depart = heure_depart;
		this.heure_arrivee = heure_arrivee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_station_arrivee() {
		return id_station_arrivee;
	}
	public void setId_station_arrivee(int id_station_arrivee) {
		this.id_station_arrivee = id_station_arrivee;
	}
	public int getId_station_depart() {
		return id_station_depart;
	}
	public void setId_station_depart(int id_station_depart) {
		this.id_station_depart = id_station_depart;
	}
	public Time getHeure_depart() {
		return heure_depart;
	}
	public void setHeure_depart(Time heure_depart) {
		this.heure_depart = heure_depart;
	}
	public Time getHeure_arrivee() {
		return heure_arrivee;
	}
	public void setHeure_arrivee(Time heure_arrivee) {
		this.heure_arrivee = heure_arrivee;
	}
    
    
}