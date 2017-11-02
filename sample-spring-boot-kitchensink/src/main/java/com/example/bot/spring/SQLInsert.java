package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

@Slf4j
public class SQLInsert { 

	public SQLInsert (String uSERID ,int w, int h, int a, String data, KitchenSinkController kc ) { 
		try { 
			
			SQLDatabaseEngine engine=new SQLDatabaseEngine(kc) ; 
			
			Connection conn =engine.getConnection() ; 
			kc.reminder("connection");
			Statement st = conn.createStatement();
			if(data == "M") {
				
				int b =(int) (66.47+ (13.75* w) + (5.0*h) - (6.75* a));
				st.executeUpdate("INSERT INTO customertable " + 
						"VALUES ('"+ uSERID + "'," + w +","+ h +","+ a + ", 'M'," + b +");");
				st.executeUpdate("INSERT INTO customertable VALUES ('2',2,2,2,'M',2);");
				kc.reminder("test");
				kc.reminder(uSERID+ w+h+a+data);
			}
			else if (data== "F") {
				int b =(int) (66.47+ (13.75* w) + (5.0*h) - (6.75* a));
				st.executeUpdate("INSERT INTO customertable " + 
						"VALUES ("+ uSERID + "," + w +","+ h +","+ a + ", 'F'," + b +");");
			
			}

			conn.close(); 
		} catch (Exception e) { 
			kc.reminder("Got an exception! "); 
			kc.reminder(e.getMessage()); 
		} 

	}
}