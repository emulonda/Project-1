package edu.jsu.mcis.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class CurrencyDatabase {
    
    Context envContext = null, initContext = null;
    DataSource ds = null;
    Connection conn = null;

    public CurrencyDatabase() throws NamingException {
        
        try {
            
            envContext = new InitialContext();
            initContext  = (Context)envContext.lookup("java:/comp/env");
            ds = (DataSource)initContext.lookup("jdbc/db_pool");
            conn = ds.getConnection();
            
        }
        
        catch (SQLException e) {}

    }
    

    public JSONObject getRequest() {
        String uri= "https://api.exchangeratesapi.io/latest?base=USD";
        JSONObject jsonResponse= null;
        try {
            URL url= new URL(uri);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json; charset=UTF-8");
            connection.setRequestMethod("GET");
            int responseCode= connection.getResponseCode();
            if (responseCode== HttpURLConnection.HTTP_OK) {
            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(in);
            String response = reader.readLine();
            JSONParser parser = new JSONParser();
            jsonResponse= (JSONObject)parser.parse(response);
            }
            connection.disconnect();
        }
        catch (Exception e) { e.printStackTrace(); }
        return jsonResponse;
        }        
    public void closeConnection() {
        
        if (conn != null) {
            
            try {
                conn.close();
            }
            
            catch (SQLException e) {}
            
        }
    
    } // End closeConnection()
    
    public Connection getConnection() { return conn; }
    
}