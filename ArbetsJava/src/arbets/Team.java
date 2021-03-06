package arbets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Team {
	int id;
	int sport_id;
	String name;
	public Team(int id, String name, int sport_id) {
		super();
		this.id = id;
		this.name = name;
		this.sport_id = sport_id;
	}
	public Team() {
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSport_id() {
		return sport_id;
	}
	public void setSport_id(int sport_id) {
		this.sport_id = sport_id;
	}
	public List<String> getTeams(DB db) throws Exception {
        
        List<String> teams = new ArrayList<>();

        String sql = "SELECT distinct name FROM team;";
  

        try {
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            
            

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                teams.add(rs.getString("name"));
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
	
}
	
