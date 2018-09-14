import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrainDAO {
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "hr";
	final String PASSWORD = "hr" ;
	
	public Train findTrain(int trainNumber) throws Exception {
		Class.forName(DRIVER_NAME);
		Connection con = DriverManager.getConnection(DB_URL,USERNAME, PASSWORD);
		PreparedStatement ps = con.prepareStatement("Select * from TRAINS " + " where train_no = ? ");
		Train train = new Train();
		ps.setInt(1,trainNumber);
		 ResultSet rs = ps.executeQuery();
		 while (rs.next()) {
			train = new Train(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5) );
			 }
		 
		
		
		return train;
	}

}
