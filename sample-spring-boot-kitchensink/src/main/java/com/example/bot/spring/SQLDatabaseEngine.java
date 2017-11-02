package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

@Slf4j
public class SQLDatabaseEngine extends DatabaseEngine {
	private KitchenSinkController kc;
	public SQLDatabaseEngine (KitchenSinkController kc) {
		this.kc = kc;
	}
	@Override
	String search(String text) throws Exception {
		//Write your code here
            String result = null;
            
                Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement ("SELECT * FROM respond where input like concat('%', ?, '%')");
                stmt.setString(1, text.toLowerCase());
                ResultSet rs = stmt.executeQuery();
                while (result == null && rs.next()) {
                    if (rs.getString(1).equals(text)) {
                        result = rs.getString(2);
                    }
                }
                if (result == null) {
                    throw new Exception("NOT FOUND");
                }
                rs.close();
                stmt.close();
                    
	    return result;	
       }
	
	
	protected Connection getConnection() throws URISyntaxException, SQLException {
		Connection connection;
		kc.reminder("test");
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		
		String username = dbUri.getUserInfo().split(":")[0];
		kc.reminder(username);
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        kc.reminder(dbUrl);
		log.info("Username: {} Password: {}", username, password);
		log.info ("dbUrl: {}", dbUrl);
		
		connection = DriverManager.getConnection(dbUrl, username, password);

		return connection;
	}

}
