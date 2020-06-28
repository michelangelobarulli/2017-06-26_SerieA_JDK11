package it.polito.tdp.seriea.model;

public class SquadreVicine implements Comparable<SquadreVicine>{
	
	private String nome;
	private Double peso;
	public SquadreVicine(String nome, Double peso) {
		super();
		this.nome = nome;
		this.peso = peso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(SquadreVicine o) {
		// TODO Auto-generated method stub
		return -this.peso.compareTo(o.getPeso());
	}
	@Override
	public String toString() {
		return nome + ", partite giocate contro: " + peso;
	}
	
	
}
