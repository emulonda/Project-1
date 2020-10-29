package edu.jsu.mcis.p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonnelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        CurrencyDatabase db = null;
        
        Connection connection;
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        
        String query;
        String parameter;
        
        StringBuilder table = new StringBuilder();
        
        boolean hasresults;
            
        try {
            
            db = new CurrencyDatabase();
            connection = db.getConnection();

            parameter = request.getParameter("rates_d");
            
            query = "SELECT * from rate WHERE date = '" + parameter + "'";
            
            pstatement = connection.prepareStatement(query);
            
            hasresults = pstatement.execute();
            
            while ( hasresults || pstatement.getUpdateCount() != -1 ) {
                
                if ( hasresults ) {
                    resultset = pstatement.getResultSet();
                    if (resultset.next()) {
                        String code = resultset.getString("code");
                        int date= resultset.getInt("date");
                        int rate = resultset.getInt("rate");
                        String base = resultset.getString("base");
                    }
                    
//                    
                }
                
                else {
                    
                    if ( pstatement.getUpdateCount() == -1 ) {
                        table.append(db.getRequest());
                    }
                    
                }
                String rate = null;
                out.println("it" + resultset );

                hasresults = pstatement.getMoreResults();
            
            }
            
            out.println("<p>Search Parameter: " + parameter + "</p>");
            
            out.println(table.toString());

        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            
            out.close();
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstatement != null) { try { pstatement.close(); pstatement = null; } catch (Exception e) {} }
            
            if (db != null) { db.closeConnection(); }
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doGet(request, response);
        
    }

    @Override
    public String getServletInfo() { return "Database Security Lab Servlet"; }

}