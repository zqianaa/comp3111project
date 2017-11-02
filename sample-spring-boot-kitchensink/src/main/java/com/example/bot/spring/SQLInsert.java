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
				int b =(int) (66.47+ (13.75* w) + (5.0*h) - (6.75* a));
				st.executeUpdate("INSERT INTO customertable " + 
						"VALUES ("+ uSERID + "," + w +","+ h +","+ a + ", 'M'," + b +");");
				st.executeUpdate("INSERT INTO customertable VALUES ('2',2,2,2,'M',2);");
			}
			else if (data== "F") {
				int b =(int) (66.47+ (13.75* w) + (5.0*h) - (6.75* a));
				st.executeUpdate("INSERT INTO customertable " + 
						"VALUES ("+ uSERID + "," + w +","+ h +","+ a + ", 'F'," + b +");");
			
			}

			conn.close(); 
		} catch (Exception e) { 
			System.err.println("Got an exception! "); 
			System.err.println(e.getMessage()); 
		} 

	}
}