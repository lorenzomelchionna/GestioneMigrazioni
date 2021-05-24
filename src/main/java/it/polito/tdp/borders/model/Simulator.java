package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulator {
	
	//modello
	private Graph<Country,DefaultEdge> grafo;
	
	//tipi di evento
	private PriorityQueue<Event> queue;
	
	//parametri della simulazione
	private int N_MIGRANTI = 1000;
	private Country partenza;
	
	//output
	private int T = -1;
	private Map<Country, Integer> stanziali;
	
	public void init(Country country, Graph<Country,DefaultEdge> grafo) {
		
		this.partenza = country;
		this.grafo = grafo;
		
		//stato inziale
		this.T = 1;
		this.stanziali = new HashMap<>();
		for(Country c : this.grafo.vertexSet()) {
			stanziali.put(c, 0);
		}
		
		//coda
		this.queue = new PriorityQueue<>();
		this.queue.add(new Event(T,partenza,N_MIGRANTI));
		
	}
	
	public void run() {
		
		Event e;
		while((e = this.queue.poll()) != null) {
			
			this.T = e.getT();
			
			int nPersone = e.getN();
			Country stato = e.getCountry();
			
			List<Country> vicini = Graphs.neighborListOf(this.grafo, stato);
			
			//esempio caso limite
			int migrantiPerStato = (nPersone/2)/vicini.size();
			
			if(migrantiPerStato > 0) {
				for(Country confinante : vicini) {
					queue.add(new Event(e.getT()+1, confinante, migrantiPerStato));
				}
			}
			
			int stanziali = nPersone-migrantiPerStato;
			
			this.stanziali.put(stato, this.stanziali.get(stato)+stanziali);
			
		}
		
	}
	
	public Map<Country,Integer> getStanziali() {
		return this.stanziali;
	}
	
	public int getT() {
		return this.T;
	}
	
}
