package it.polito.tdp.extflightdelays.model;

public class Adiacenti {

	private String statoPartenza;
	private String statoDestinazione;
	private Integer peso;
	
	public Adiacenti(String statoPartenza, String statoDestinazione, Integer peso) {
		super();
		this.statoPartenza = statoPartenza;
		this.statoDestinazione = statoDestinazione;
		this.peso = peso;
	}

	public String getStatoPartenza() {
		return statoPartenza;
	}

	public void setStatoPartenza(String statoPartenza) {
		this.statoPartenza = statoPartenza;
	}

	public String getStatoDestinazione() {
		return statoDestinazione;
	}

	public void setStatoDestinazione(String statoDestinazione) {
		this.statoDestinazione = statoDestinazione;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	
}
