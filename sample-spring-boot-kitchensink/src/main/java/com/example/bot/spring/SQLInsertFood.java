package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

@Slf4j
public class SQLInsertFood { 

	public SQLInsertFood (String uSERID ,String a,String b,String c,String d,String r,String f,String g,String h,String i,String j, KitchenSinkController kc ) { 
		try { 
			
			SQLDatabaseEngine engine=new SQLDatabaseEngine(kc) ; 
			
			Connection conn =engine.getConnection() ; 
		
			Statement st = conn.createStatement();
			
				st.executeUpdate("INSERT INTO foodtable " + 
						"VALUES ('"+ uSERID + "','" + a +"','"+ b +"','"+ c + "','"+ d + "','"+ r + "','"+ f + "','"+ g + "','"+ h + "','"+ i + "','"+ j + "')");
			conn.close(); 
		} catch (Exception e) { 
			
			kc.reminder(e.getMessage()); 
			kc.reminder("Got an exception! "); 
		} 
		
	
	}
	
}