package it.polito.tdp.extflightdelays.model;

public class Event implements Comparable <Event>{

	private Integer giorno;
	private String stato;
	
	public Event(Integer giorno, String stato) {
		super();
		this.giorno = giorno;
		this.stato = stato;
	}

	public Integer getGiorno() {
		return giorno;
	}

	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	@Override
	public int compareTo(Event o) {
		return this.giorno-o.getGiorno();
	}
	
	
}
