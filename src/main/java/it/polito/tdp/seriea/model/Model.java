package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {

	SerieADAO dao;
	Graph<String, DefaultWeightedEdge> graph;
	List<String> teams;
	
	public Model() {
		this.dao = new SerieADAO();
	}
	public Set<String> creaGrafo() {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.teams = this.dao.listTeams();
		Graphs.addAllVertices(this.graph, this.teams);
		for(String s1: this.teams) {
			for(String s2: this.teams) {
				if(s1.compareTo(s2)!=0) {
					Double weight = this.dao.getWeight(s1, s2);
					if(weight!=0.0)
						Graphs.addEdgeWithVertices(this.graph, s1, s2, weight);
				}
			}
		}
		System.out.println("Grafo creato con "+this.graph.vertexSet().size()+" vertici e "+this.graph.edgeSet().size()+" archi.");
		return this.graph.vertexSet();
	}
	public List<SquadreVicine> connessioniSquadra(String s){
		List<String> connessioni = Graphs.neighborListOf(this.graph, s);
		List<SquadreVicine> result = new ArrayList<>();
		for(String c: connessioni) {
			Double d = this.graph.getEdgeWeight(this.graph.getEdge(s, c));
			result.add(new SquadreVicine(c,d));
		}
		Collections.sort(result);
		return result;		
	}
	
	public void simulaStagione(Season s) {
		List<String> squadre = this.dao.getSquadreStagione(s.getSeason());
		List<Match> partite = this.dao.getPartiteStagione(s.getSeason());
		Simulator sim = new Simulator();
		sim.init(partite, squadre);
		sim.run();
		sim.stampaClassificaETifosi();
	}

	public List<Season> listSeasons() {
		// TODO Auto-generated method stub
		return this.dao.listSeasons();
	}
	
	
}
