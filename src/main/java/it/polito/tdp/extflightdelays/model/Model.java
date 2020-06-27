package it.polito.tdp.extflightdelays.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private ExtFlightDelaysDAO dao;
	private List<String> stati;
	private List<Adiacenti> archi;
	
	private Graph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
	}
	
	public void creaGrafo() {
		stati = dao.getAllState();
		archi = dao.getVoliByTratta();
		
		grafo = new SimpleDirectedWeightedGraph <>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, stati);
		System.out.println(grafo.vertexSet().size());
		
		for(Adiacenti a: archi) {
			if(grafo.containsVertex(a.getStatoPartenza()) && grafo.containsVertex(a.getStatoDestinazione()) && 
					grafo.getEdge(a.getStatoPartenza(), a.getStatoDestinazione()) == null)
					Graphs.addEdgeWithVertices(grafo, a.getStatoPartenza(), a.getStatoDestinazione(), a.getPeso());
		}
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
	}
	
	public List<String> getStati(){
		return stati;
	}
	
	public List<Vicino> getVicini(String selezionato){
		List<Vicino> result = new ArrayList<>();
		List<String> vicini = Graphs.successorListOf(this.grafo, selezionato);
		
		for(String s: vicini) 
			result.add(new Vicino(s, (int) grafo.getEdgeWeight(grafo.getEdge(selezionato, s))));
		
		Collections.sort(result);
			
		return result;
		
	}
	
	public List<AffluenzaStato> simula(Integer G, Integer T, String partenza){
		Simulatore sim = new Simulatore();
		sim.init(T, G, partenza);
		sim.run();
		
		return sim.getTuristi();
	}
}
