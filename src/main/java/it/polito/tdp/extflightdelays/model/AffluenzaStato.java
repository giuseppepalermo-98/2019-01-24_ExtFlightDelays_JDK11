package it.polito.tdp.extflightdelays.model;

public class AffluenzaStato {

	private Stato stato;
	private Integer numTuristi;
	
	public AffluenzaStato(Stato stato, Integer numTuristi) {
		super();
		this.stato = stato;
		this.numTuristi = numTuristi;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Integer getNumTuristi() {
		return numTuristi;
	}

	public void setNumTuristi(Integer numTuristi) {
		this.numTuristi = numTuristi;
	}

	public void decrementaturista() {
		this.numTuristi += 1;
	}

	public void incrementaturista() {
		this.numTuristi -=1;
	}
	
}
