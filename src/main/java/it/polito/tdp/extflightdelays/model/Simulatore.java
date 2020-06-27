package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.model.Event.EventType;

public class Simulatore {

	//Vaariabili inserite in fase di simulazione
	private Integer T_turisti;
	private Integer G_giorni;
	
	//Varibili dello stato 
	private Graph<Stato, DefaultWeightedEdge> grafo;
	private PriorityQueue<Event> queue;
	
	//Varibili da restituire
	private Map<String, AffluenzaStato> affluenza; //la chiave è la sigla dello stato 
	
	public void init(Integer T, Integer G, Graph<Stato, DefaultWeightedEdge> grafo, Stato partenza) {
		this.T_turisti=T;
		this.G_giorni=G;
		this.grafo=grafo;
		
		
		this.affluenza= new HashMap<>();
		
		for(Stato s: grafo.vertexSet()) {
			affluenza.put(s.getStato(), new AffluenzaStato(s, 0));
		}
		
		affluenza.put(partenza.getStato(), new AffluenzaStato(partenza, T_turisti));
		
		
		this.queue = new PriorityQueue<>();
		
		queue.add(new Event(EventType.PARTENZA, 1, partenza));
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Event e= queue.poll();
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		//Allora GENERO NUOVI EVENTI 
		
		if(e.getGiorno()<this.G_giorni) {
			Integer numeroTuristi=affluenza.get(e.getStato().getStato()).getNumTuristi();
			List<Stato>possibili = Graphs.successorListOf(grafo, e.getStato());
			Integer sommaUscenti=0;
			
			
			for(Stato s: possibili) {
				sommaUscenti += (int) grafo.getEdgeWeight(grafo.addEdge(e.getStato(), s));
			}
			
			
			for(int i=0; i<numeroTuristi; i++) {
				boolean partito=false;
				for(Stato s:possibili) {
					Integer pesoArco= (int) grafo.getEdgeWeight(grafo.getEdge(e.getStato(), s));
					if(Math.random()<pesoArco/sommaUscenti && !partito) {
						partito=true;
						
						queue.add(new Event(EventType.PARTENZA, e.getGiorno()+1, s));
						
						affluenza.get(e.getStato().getStato()).decrementaturista();
						
						affluenza.get(s.getStato()).incrementaturista();
					}
				}
			}
		}
	}
	
	public List<AffluenzaStato> getAffluenza(){
		List<AffluenzaStato> result= new ArrayList<>();
		
		for(AffluenzaStato af: affluenza.values())
			result.add(af);
		
		return result;
	}
}
