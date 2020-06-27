package it.polito.tdp.extflightdelays.model;

public class Vicino implements Comparable <Vicino> {

	private String stato;
	private Integer peso;
	
	public Vicino(String stato, Integer peso) {
		super();
		this.stato = stato;
		this.peso = peso;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Vicino o) {
		return this.peso-o.getPeso();
	}
	
	
}
