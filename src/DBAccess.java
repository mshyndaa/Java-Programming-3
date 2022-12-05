import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DBAccess {
	static Connection con = DBDriver.connect();
	
	public void runScript (String fileName) throws SQLException {
		ScriptRunner sr = new ScriptRunner(con);
		Reader read = null;
		try {
			read = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sr.runScript(read);
		System.out.println("Done!!");
	}
	
	// insert cupcake
	public int insertCupcake (Cupcake c) throws SQLException {
		String query = "INSERT INTO cup_cake(PastryID, PastryName, PastryType, PastryPrice, ToppingName) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, c.getPastryID());
		ps.setString(2, c.getPastryName());
		ps.setString(3, c.getPastryType());
		ps.setInt(4, c.getPastryPrice());
		ps.setString(5, c.getToppingName());
		int n = ps.executeUpdate();
		return n;
	}
	
	// insert rollcake
	public int insertRollcake (Rollcake r) throws SQLException {
		String query = "INSERT INTO cup_cake(PastryID, PastryName, PastryType, PastryPrice, FillingName) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, r.getPastryID());
		ps.setString(2, r.getPastryName());
		ps.setString(3, r.getPastryType());
		ps.setInt(4, r.getPastryPrice());
		ps.setString(5, r.getFillingName());
		int n = ps.executeUpdate();
		return n;
	}
	
	// insert transaction
		public int insertTransaction (String id, String pastryId, int qty) throws SQLException {
			String query = "INSERT INTO transaction(TransactionID, PastryID, Quantity) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, pastryId);
			ps.setInt(3, qty);
			int n = ps.executeUpdate();
			return n;
		}
	
    public void delete (String id) throws SQLException {
        String query = "DELETE FROM transaction where TransactionID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }
  

    public Vector<Cupcake> selectCupcake() throws SQLException { 
  
        String query = "SELECT * FROM cup_cake";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        Vector<Cupcake> listCupcake = new Vector<Cupcake>();
  
        while (rs.next()) {
         Cupcake c = new Cupcake(rs.getString("PastryID"), rs.getString("PastryName"), rs.getString("PastryType"), rs.getInt("PastryPrice"), rs.getString("ToppingName")); 
         listCupcake.add(c);
        }
        
        return listCupcake;
    }
    
    public Vector<Rollcake> selectRollcake() throws SQLException { 
    	  
        String query = "SELECT * FROM roll_cake";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        Vector<Rollcake> listRollcake = new Vector<Rollcake>();
  
        while (rs.next()) {
         Rollcake r = new Rollcake(rs.getString("PastryID"), rs.getString("PastryName"), rs.getString("PastryType"), rs.getInt("PastryPrice"), rs.getString("FillingName")); 
         listRollcake.add(r);
        }
        
        return listRollcake;
    }
  

    public Vector<Transaction> selectTransaction() throws SQLException { 
    	  
        String query = "SELECT * FROM transaction";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        Vector<Transaction> listTransaction = new Vector<Transaction>();
  
        while (rs.next()) {
         Transaction t = new Transaction(rs.getString("TransactionID"), rs.getString("PastryID"), rs.getInt("Quantity")); 
         listTransaction.add(t);
        }
        
        return listTransaction;
    }
  
}

