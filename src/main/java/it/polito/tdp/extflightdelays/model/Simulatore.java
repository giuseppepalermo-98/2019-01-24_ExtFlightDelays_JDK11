package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Simulatore {

	//VARIBILI INPUT 
	private Integer T_TURISTI;
	private Integer G_GIORNI;
	
	//VARIABILI OUTPUT(in questo caso è anche una variabile dello stato)
	private Map<String, AffluenzaStato> mappaTuristi;
	//private Graph<String, DefaultWeightedEdge> grafo;
	private ExtFlightDelaysDAO dao;
	private List<Vicino> vicini;
	private List<Adiacenti> tuttiVoli;
	
	//Eventi
	private PriorityQueue<Event> queue;
	
	public void init(Integer T, Integer G, String partenza) {
		this.T_TURISTI= T;
		this.G_GIORNI = G;
		
		dao = new ExtFlightDelaysDAO();
		
		this.mappaTuristi = new HashMap<>();
		
		for(String s: dao.getAllState()) {
			mappaTuristi.put(s, new AffluenzaStato(s, 0));
		}
		
		mappaTuristi.get(partenza).setNumTuristi(T_TURISTI);
		
		this.tuttiVoli = dao.geAllVoli();
		
		this.queue = new PriorityQueue<>();
		
		for(int i=0; i<T_TURISTI; i++) {
			queue.add(new Event(0, partenza));
		}	
	}
	
	public void run () {
		while(!queue.isEmpty()) {
			
			Event e = queue.poll();
			if(e.getGiorno()<G_GIORNI) {
				vicini = new ArrayList<>();
				
				//PRENDO TUTTI I VICINI IN MANIERA ALTERNATIVA AL GRAFO ESSEDO CHE I VALORI SONO DIVERSI 
				//DA QUELLI DEL PUNTO PRECEDENTE
				Integer pesoTot = 0;
				
				
				for(Adiacenti a: this.tuttiVoli) {
					if(a.getStatoPartenza().equals(e.getStato())) {
						vicini.add(new Vicino(a.getStatoDestinazione(), a.getPeso()));
						pesoTot += a.getPeso();
					}
				}
				Random ran = new Random();
				Float probabilita = ran.nextFloat();
				boolean partito = false;
				
				
				for(Vicino prossimo: vicini) {
					if(!partito && probabilita< prossimo.getPeso() / pesoTot*100000) {
						queue.add(new Event(e.getGiorno()+1, prossimo.getStato()));
						mappaTuristi.get(e.getStato()).decrementaTurista();
						mappaTuristi.get(prossimo.getStato()).incrementaTurista();
					}
				}
			}
		}
	}
	
	public List<AffluenzaStato> getTuristi(){
		List<AffluenzaStato> result = new ArrayList<>(mappaTuristi.values());
		Collections.sort(result);
		return result;
	}
	
}
