package it.polito.tdp.extflightdelays.model;

public class Stato implements Comparable <Stato> {

	private String stato;
	private Integer id;
	
	public Stato(String stato, Integer id) {
		super();
		this.stato = stato;
		this.id = id;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int compareTo(Stato o) {
		return this.stato.compareTo(o.getStato());
	}

	@Override
	public String toString() {
		return stato;
	}
	
	
	
}
