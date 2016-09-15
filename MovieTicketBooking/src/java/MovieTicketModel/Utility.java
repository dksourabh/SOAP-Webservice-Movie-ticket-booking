/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieTicketModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sourabh_deshkulkarni
 */
public class Utility {
    public static Connection getConnection(){
         Connection con = null; 
    try {
            Class.forName("com.mysql.jdbc.Driver");
            
              try {
                   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "root");

              } catch (SQLException ex) {
                  Logger.getLogger(MovieTicketBook.class.getName()).log(Level.SEVERE, null, ex);
              }
           
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    return con;
    }
    public static String generateSessionId(String userName){
        StringBuffer sessionId= new StringBuffer();
        Date d1 = new Date();
        sessionId.append(userName+"-"+userName);
        sessionId.append(d1.getMonth());
        
        return sessionId.toString();
    }
}
