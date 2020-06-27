package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private ExtFlightDelaysDAO dao;
	private List<Stato> stati;
	private Map<Integer, Stato> idMapStato;
	private Graph<Stato, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
	}

	public List<Stato> getState(){
		idMapStato= new HashMap<>();
		
		stati = dao.getAllState();
		
		for(Stato s: stati)
			idMapStato.put(s.getId(), s);
		
	
		return stati;
	}
	
	public void creaGrafo() {
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, stati);
		
		List<Adiacenti> archi=dao.getVoliByTratta(idMapStato);
		
		for(Adiacenti a: archi) {
			Graphs.addEdgeWithVertices(grafo, a.getStatopartenza(), a.getStatodestinazione(), a.getNumeroVelivoli());
		}
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
	}
	
	public List<Adiacenti> getVelivoliByStato(Stato partenza){
		List<Stato> raggiungibili = Graphs.successorListOf(grafo, partenza);
		List<Adiacenti>result = new ArrayList<>();
		
		for(Stato s: raggiungibili) {
			result.add(new Adiacenti(partenza, s, (int) grafo.getEdgeWeight(grafo.getEdge(partenza, s))));
		}
		Collections.sort(result);
		return result;
	}
	
	public List<AffluenzaStato> getAffluenza(Integer T, Integer G, Stato partenza){
		Simulatore sim= new Simulatore();
		sim.init(T, G, grafo, partenza);
		sim.run();
		
		List<AffluenzaStato> result= sim.getAffluenza();
		
		return result;
		
	}
}
