package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

@Slf4j
public class SQLInsert { 

	public SQLInsert (String name ,int w, int h, int a, String sex ) { 
		try { 
			SQLDatabaseEngine engine=new SQLDatabaseEngine() ; 
			Connection conn =engine.getConnection() ; 
			Statement st = conn.createStatement();
			if(sex == "M") {
				st.executeUpdate("INSERT INTO customertable " + 
						"VALUES (name,w, , h, a,sex, 66.47+ (13.75 x w) + (5.0 x h) - (6.75 x a))");
			}
			else if (sex== "F") {
				st.executeUpdate("INSERT INTO customertable " + 
						"VALUES (name,w, , h, a,sex, 66.47+ 665.09 + (9.56 x w) + (1.84 x h) - (4.67 x a)");
			
			}

			conn.close(); 
		} catch (Exception e) { 
			System.err.println("Got an exception! "); 
			System.err.println(e.getMessage()); 
		} 

	}
}