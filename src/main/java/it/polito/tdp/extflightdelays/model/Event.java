package it.polito.tdp.extflightdelays.model;

public class Event implements Comparable <Event> {

	public enum EventType{
		PARTENZA
	}
	
	private EventType type;
	private Integer giorno;
	private Stato stato;
	
	public Event(EventType type, Integer giorno, Stato stato) {
		super();
		this.type = type;
		this.giorno = giorno;
		this.stato = stato;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Integer getGiorno() {
		return giorno;
	}

	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
	
	
	@Override
	public int compareTo(Event o) {
		return this.giorno-o.getGiorno();
	}
	
	
}
