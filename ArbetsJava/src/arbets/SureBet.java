package arbets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SureBet {
	private static int counter = 0;
	private int id;
	private Bet bet1;
	private Bet bet2;
	private double homeTeamBet1Rate;
	private double visitingTeamBet2Rate;

	public SureBet(Bet bet1, Bet bet2) {
		this.id = counter;
		counter++;
		this.bet1 = bet1;
		this.bet2 = bet2;
		this.homeTeamBet1Rate = bet1.getHomeTeamRate();
		this.visitingTeamBet2Rate = bet2.getHomeTeamRate();

	}

	public Bet getBet1() {
		return bet1;
	}

	public void setBet1(Bet bet1) {
		this.bet1 = bet1;
	}

	public Bet getBet2() {
		return bet2;
	}

	public void setBet2(Bet bet2) {
		this.bet2 = bet2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<String> findTeams() throws Exception {
		ArrayList<String> teams = new ArrayList<String>();
	  
        String sql = "SELECT team.name\r\n"
        		+ "FROM  (SELECT team_game.team_id  FROM team_game WHERE team_game.game_id=?) as team_ids \r\n"
        		+ "INNER JOIN team ON team.team_id=team_ids.team_id;";
        DB db = new DB();
        try {
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,this.bet1.getGameId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                teams.add( rs.getString("team.name"));
            }   

            rs.close();
            stmt.close();
            db.close();
            return teams;
           
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        } finally{

            try {
                db.close();
            } catch (Exception e) {
                
            }
            
        }
		
	}

	public String findSport() throws Exception  {
		
		 String sql = "select s.name\r\n"
		 		+ "FROM sport as s\r\n"
		 		+ "where s.sport_id = (SELECT distinct team.sport_id\r\n"
		 		+ "FROM  (SELECT team_game.team_id  FROM team_game WHERE team_game.game_id=?) as team_ids\r\n"
		 		+ "INNER JOIN team ON team.team_id=team_ids.team_id);";
		 
	        DB db = new DB();
	        
	        try {
	            Connection con = db.getConnection();
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setInt(1,this.bet1.getGameId());
	            ResultSet rs = stmt.executeQuery();

	            if (!rs.next()){
	                
	                rs.close();
		            stmt.close();
	                throw new Exception("No team found!");
	                
	            }   
	            String sportName= rs.getString("s.name");
	            rs.close();
	            stmt.close();
	            db.close();
	            return sportName;
	           
	        } catch(Exception e) {
	            throw new Exception(e.getMessage());
	        } finally{

	            try {
	                db.close();
	            } catch (Exception e) {
	                
	            }
	            
	        }
			
	}

	

	public double getHomeTeamBet1Rate() {
		return homeTeamBet1Rate;
	}

	public void setHomeTeamBet1Rate(double homeTeamBet1Rate) {
		this.homeTeamBet1Rate = homeTeamBet1Rate;
	}

	public double getVisitingTeamBet2Rate() {
		return visitingTeamBet2Rate;
	}

	public void setVisitingTeamBet2Rate(double visitingTeamBet2Rate) {
		this.visitingTeamBet2Rate = visitingTeamBet2Rate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bet1, bet2, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SureBet other = (SureBet) obj;
		// den tsekarei an einai idio me antistrofa bets
		return (bet1.equals( other.bet1) && bet2.equals(other.bet2) ) || (bet1.equals( other.bet2) && bet2.equals(other.bet1));
	}

	@Override
	public String toString() {
		return "SureBet [id=" + id + ", bet1=" + bet1 + ", bet2=" + bet2 + ", homeTeamBet1Rate=" + homeTeamBet1Rate
				+ ", visitingTeamBet2Rate=" + visitingTeamBet2Rate + "]";
	}
}