package it.polito.tdp.borders.model;

public class Event implements Comparable<Event>{
	
	private Integer t;
	private Country country;
	private int n;
	
	public Event(Integer t, Country country, int n) {
		super();
		this.t = t;
		this.country = country;
		this.n = n;
	}

	public int getT() {
		return t;
	}

	public void setT(Integer t) {
		this.t = t;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public int compareTo(Event other) {
		return this.t.compareTo(other.t);
	}
	
}
