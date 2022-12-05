import java.sql.Connection;
import java.sql.DriverManager;

public class DBDriver {

	static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/bakeroo";
    static final String USER = "root";
    static final String PASS = "";
    
    private static Connection con;
    
    static {
    	// singleton
    	try {
    		
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static Connection connect() {
    	return con;
    }
    
}

