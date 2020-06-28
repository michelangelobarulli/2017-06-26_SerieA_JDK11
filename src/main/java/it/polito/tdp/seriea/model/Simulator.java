package it.polito.tdp.seriea.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Simulator {

	List<Match> partite;
	List<String> squadre;
	Map<String, Double> squadreTifosi;
	Map<String, Integer> classifica;
	Integer P = 10;
	
	public void init(List<Match> p, List<String> s) {
		this.partite = p;
		this.squadre = s;
		this.squadreTifosi = new TreeMap<>();
		this.classifica = new TreeMap<>();
		for(String str: this.squadre) {
			this.squadreTifosi.put(str, 1000.0);
			this.classifica.put(str, 0);
		}
	}
	
	public void run() {
		for(Match m: partite) {
			
			String squadraCasa = m.getHomeTeam();
			String squadraTrasferta = m.getAwayTeam();
			
			Integer goalCasaTraccia = m.getFthg();
			Integer goalTrasfertaTraccia = m.getFtag();
			
			Double tifosiCasa = this.squadreTifosi.get(squadraCasa);
			Double tifosiTrasferta = this.squadreTifosi.get(squadraTrasferta);
			Double rapporto = tifosiCasa/tifosiTrasferta;
			
			if(rapporto.compareTo(1.0)==0) {
				System.out.println("Le squadre hanno lo stesso numero di tifosi: il risultato non è cambiato.");
			} else if(rapporto > 0 && rapporto <1) {
				Double p = Math.random();
				if(p<1-rapporto) {
					if(goalCasaTraccia!=0) {
						goalCasaTraccia = goalCasaTraccia-1;
						System.out.println("La squadra "+squadraCasa+" fa un goal in meno poiché ha meno tifosi della squadra "+squadraTrasferta);
					}
				} else {
					System.out.println("La squadra "+squadraCasa+" fa gli stessi goal di prima anche se ha meno tifosi della squadra "+squadraTrasferta);
				}
			} else {
				Double p = Math.random();
				if(p<rapporto) {
					if(goalTrasfertaTraccia!=0) {
						goalTrasfertaTraccia = goalTrasfertaTraccia-1;
						System.out.println("La squadra "+squadraTrasferta+" fa un goal in meno poiché ha meno tifosi della squadra "+squadraCasa);
					} else {
						System.out.println("La squadra "+squadraCasa+" fa gli stessi goal di prima ma ha meno tifosi della squadra "+squadraTrasferta);
					}	
				}
			}
			System.out.println("Partita: "+squadraCasa+" vs "+squadraTrasferta+" "+goalCasaTraccia+"-"+goalTrasfertaTraccia);
			this.inserisciPuntiInClassifica(squadraCasa, squadraTrasferta, goalCasaTraccia, goalTrasfertaTraccia);
			this.cambiaTifosi(squadraCasa, squadraTrasferta, goalCasaTraccia, goalTrasfertaTraccia);
			
		}
	}

	private void cambiaTifosi(String squadraCasa, String squadraTrasferta, Integer goalCasa,
			Integer goalTrasferta) {
		Double tifosiVecchiC = this.squadreTifosi.get(squadraCasa);
		Double tifosiVecchiT = this.squadreTifosi.get(squadraCasa);
		if(goalCasa > goalTrasferta) {
			this.squadreTifosi.remove(squadraCasa);
			this.squadreTifosi.remove(squadraTrasferta);
			
			Integer scarto = goalCasa - goalTrasferta;
			Integer tifosiSwitch = (int)(this.P*scarto*tifosiVecchiT/100);
			
			this.squadreTifosi.put(squadraCasa, tifosiVecchiC+tifosiSwitch);
			this.squadreTifosi.put(squadraTrasferta, tifosiVecchiT-tifosiSwitch);
			
			System.err.println(tifosiSwitch+"tifosi della squadra "+squadraTrasferta+" passano alla squadra "+squadraCasa);
			
		}  else if (goalCasa < goalTrasferta){
			this.squadreTifosi.remove(squadraCasa);
			this.squadreTifosi.remove(squadraTrasferta);
			
			Integer scarto = goalTrasferta - goalCasa;
			Integer tifosiSwitch = (int)(this.P*scarto*tifosiVecchiC/100);
			
			this.squadreTifosi.put(squadraCasa, tifosiVecchiC-tifosiSwitch);
			this.squadreTifosi.put(squadraTrasferta, tifosiVecchiT+tifosiSwitch);
			
			System.err.println(tifosiSwitch+"tifosi della squadra "+squadraCasa+" passano alla squadra "+squadraTrasferta);
		
		}
		
	}

	private void inserisciPuntiInClassifica(String squadraCasa, String squadraTrasferta, Integer goalCasa,
			Integer goalTrasferta) {
		
		if(goalCasa > goalTrasferta) {
			Integer puntiVecchi = this.classifica.get(squadraCasa);
			this.classifica.remove(squadraCasa);
			this.classifica.put(squadraCasa,puntiVecchi+3);
		} else if (goalCasa == goalTrasferta) {
			Integer puntiVecchiCasa = this.classifica.get(squadraCasa);
			this.classifica.remove(squadraCasa);
			this.classifica.put(squadraCasa, puntiVecchiCasa+1);
			Integer puntiVecchiTrasferta = this.classifica.get(squadraTrasferta);
			this.classifica.remove(squadraTrasferta);
			this.classifica.put(squadraTrasferta, puntiVecchiTrasferta+1);
		} else {
			Integer puntiVecchi = this.classifica.get(squadraTrasferta);
			this.classifica.remove(squadraTrasferta);
			this.classifica.put(squadraTrasferta, puntiVecchi+3);
		}
		
	}
	
	public void stampaClassificaETifosi() {
		System.out.println("CLASSIFICA: ");
		for(String s: this.classifica.keySet()) {
			System.out.println(s+" "+this.classifica.get(s));
		}
		System.out.println("TIFOSI: ");
		for(String s: this.squadreTifosi.keySet()) {
			System.out.println(s+" "+this.squadreTifosi.get(s));
		}
	}
}
