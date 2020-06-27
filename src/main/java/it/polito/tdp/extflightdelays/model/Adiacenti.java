package it.polito.tdp.extflightdelays.model;

public class Adiacenti implements Comparable <Adiacenti> {

	private Stato statopartenza;
	private Stato statodestinazione;
	private Integer numeroVelivoli;
	
	public Adiacenti(Stato statopartenza, Stato statodestinazione, Integer numeroVelivoli) {
		super();
		this.statopartenza = statopartenza;
		this.statodestinazione = statodestinazione;
		this.numeroVelivoli = numeroVelivoli;
	}

	public Stato getStatopartenza() {
		return statopartenza;
	}

	public void setStatopartenza(Stato statopartenza) {
		this.statopartenza = statopartenza;
	}

	public Stato getStatodestinazione() {
		return statodestinazione;
	}

	public void setStatodestinazione(Stato statodestinazione) {
		this.statodestinazione = statodestinazione;
	}

	public Integer getNumeroVelivoli() {
		return numeroVelivoli;
	}

	public void setNumeroVelivoli(Integer numeroVelivoli) {
		this.numeroVelivoli = numeroVelivoli;
	}

	@Override
	public int compareTo(Adiacenti o) {
		return -(this.numeroVelivoli-o.getNumeroVelivoli());
	}
	
	
}
