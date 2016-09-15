/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieTicketModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author sourabh_deshkulkarni
 */
@WebService(serviceName = "MovieTicketBook")
@HandlerChain(file = "MovieTicketBook_handler.xml")
public class MovieTicketBook {

    /**
     * This is a sample web service operation
     */
   
   
    @WebMethod(operationName = "bookTicket")
    public String bookTicket(@WebParam(name = "movieName") String movieName, @WebParam(name="ticketCount") String ticketCount) {
         String [] moviesList = {"Titanic", "The Reverent","Catch me if you can", "The Great Gatsby"};
         Connection con =null;
         int availabeTickets=0;
         try {
         con= Utility.getConnection();
            Statement stmt = con.createStatement();
            System.out.println("Created DB Connection....");
            ResultSet rs = stmt.executeQuery("select t.available_tickets from test.moviesInfo as t where t.movie_name='"+movieName+"'");
           while (rs.next()) {
             
             availabeTickets= rs.getInt("t.available_tickets");
               System.out.println("Available tickets: "+availabeTickets);
           }
            if(availabeTickets <=0){
                return "We are out of Tickets for this movie, try other movie.";
            }else{
                if(Integer.parseInt(ticketCount)>0){
                stmt.executeUpdate("UPDATE test.moviesInfo SET available_tickets = (available_tickets -"+ticketCount+") where movie_name='"+movieName+"'");
                return "Ticket Successfully Booked";
                }
                else{
                    return "Ticket count is 0. No Tickets booked.";
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MovieTicketBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Ticket can't be booked, check available shows and tickets";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getMoviesInfo")
    public MovieInfo getMoviesInfo() {
        StringBuffer movieNames= new StringBuffer();
        StringBuffer availableTickets = new StringBuffer();
            Connection con =null;
            MovieInfo mi = new MovieInfo();
        try {
           // String [] moviesList = {"Titanic", "The Reverent","Catch me if you can", "The Great Gatsby"};
            
            con= Utility.getConnection();
            Statement stmt = con.createStatement();
            System.out.println("Created DB Connection....");
            ResultSet rs = stmt.executeQuery("select * from test.moviesInfo as t;");
            while (rs.next()) {
                
                String movieName = rs.getString("t.movie_name");
                String availableTicket = rs.getString("available_tickets");
                
                //System.out.println("userid : " + movieName);
                movieNames.append(movieName+",");
                availableTickets.append(availableTicket+",");
                
                
            }
            mi.setMovieName(movieNames.toString());
            mi.setAvailableTickets(availableTickets.toString());
            
        } catch (SQLException ex) {
            Logger.getLogger(MovieTicketBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mi;
    }
    


}
