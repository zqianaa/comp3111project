package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

@Slf4j
public class SQLInsert { 

	public SQLInsert (String uSERID ,int w, int h, int a, String data ) { 
		try { 
			SQLDatabaseEngine engine=new SQLDatabaseEngine() ; 
			Connection conn =engine.getConnection() ; 
			Statement st = conn.createStatement();
			if(data == "M") {
				st.executeUpdate("INSERT INTO customertable " + 
						"VALUES (name,w, h, a,sex, 66.47+ (13.75* w) + (5.0*h) - (6.75* a))");
			}
			else if (data== "F") {
				st.executeUpdate("INSERT INTO customertable " + 
						"VALUES (name,w, h, a,sex, 66.47+ 665.09 + (9.56 * w) + (1.84* h) - (4.67* a)");
			
			}

			conn.close(); 
		} catch (Exception e) { 
			System.err.println("Got an exception! "); 
			System.err.println(e.getMessage()); 
		} 

	}
}