package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<String> listTeams() {
		String sql = "SELECT team FROM teams";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("team"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Double getWeight(String s1, String s2) {
		
		String sql = "SELECT COUNT(DISTINCT m.match_id) AS weight " + 
				"FROM matches m " + 
				"WHERE (m.awayTeam = ? AND m.HomeTeam = ?) OR (m.AwayTeam=? AND m.HomeTeam = ?)";
		
		Double result = 0.0;
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, s1);
			st.setString(2, s2);
			st.setString(3, s2);
			st.setString(4, s1);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result = res.getDouble("weight");
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getSquadreStagione(int season) {
		
		String sql = "SELECT DISTINCT m.HomeTeam AS squadra " + 
				"FROM matches m " + 
				"WHERE m.Season = ?";
		
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, season);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("squadra"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Match> getPartiteStagione(int season) {
		
		String sql = "SELECT m.match_id, m.Date, m.HomeTeam, m.AwayTeam, m.FTHG, m.FTAG, m.FTR " + 
				"FROM matches m " + 
				"WHERE m.Season = ? " + 
				"ORDER BY Date";
		
		List<Match> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, season);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Match(res.getInt("m.match_id"), res.getDate("m.Date").toLocalDate(), res.getString("m.HomeTeam"),
						res.getString("m.AwayTeam"), res.getInt("m.FTHG"), res.getInt("m.FTAG"), res.getString("m.FTR")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}

