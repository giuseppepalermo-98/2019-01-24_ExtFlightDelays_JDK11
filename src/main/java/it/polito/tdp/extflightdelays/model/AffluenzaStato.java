package it.polito.tdp.extflightdelays.model;

public class AffluenzaStato implements Comparable <AffluenzaStato> {

	private String stato;
	private Integer numTuristi;

	public AffluenzaStato(String stato, Integer numTuristi) {
		super();
		this.stato = stato;
		this.numTuristi = numTuristi;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Integer getNumTuristi() {
		return numTuristi;
	}
	public void setNumTuristi(Integer numTuristi) {
		this.numTuristi = numTuristi;
	}
	
	public void incrementaTurista() {
		this.numTuristi += 1;
	}
	
	public void decrementaTurista() {
		this.numTuristi -= 1;
	}
	@Override
	public int compareTo(AffluenzaStato o) {
		return this.stato.compareTo(o.getStato());
	}
	
}
