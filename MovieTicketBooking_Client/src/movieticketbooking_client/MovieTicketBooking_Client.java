/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieticketbooking_client;

import java.util.Scanner;
import javax.xml.ws.soap.SOAPFaultException;

/**
 *
 * @author sourabh_deshkulkarni
 */
public class MovieTicketBooking_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            System.out.println("Welcome to Movie Booking system. \n Choose one of below operation");

            MovieTicketBooking_Client mc = new MovieTicketBooking_Client();
String movieName="";
String ticketCount="";
            int i = 0;
            while (i != 3 && i < 4) {
                System.out.println("1. Get current movies Information");
                System.out.println("2. Book the ticket.");
                System.out.println("3. Quit");
                Scanner in = new Scanner(System.in);
                i = in.nextInt();
                if (i == 1) {
                    System.out.println("Available Movies: " + mc.getMoviesInfo().movieName + " \n Available Tickets:" + mc.getMoviesInfo().availableTickets);

                } else if (i == 2) {
                    System.out.println("Enter Movie name: ");
                    in = new Scanner(System.in);
                    movieName=in.nextLine();
                    System.out.println("Enter Ticket count: ");
                    in = new Scanner(System.in);
                    ticketCount=in.next();
                    System.out.println(mc.bookTicket(movieName, ticketCount));

                }
            }

            // System.out.println(mc.bookTicket("Titanic", "2"));
            //System.out.println("Available Movies: "+mc.getMoviesInfo().movieName+" \n Available Tickets:"+mc.getMoviesInfo().availableTickets);
        } catch (SOAPFaultException e) {
            System.err.println("Please invoke getMoviesInfo before invoking bookTicket.");
        }
        //System.out.println("Available Movies: "+mc.getMoviesInfo().movieName+" \n Available Tickets:"+mc.getMoviesInfo().availableTickets);
    }

    private static String bookTicket(java.lang.String movieName, java.lang.String ticketCount) {
        movieticketbooking_client.MovieTicketBook_Service service = new movieticketbooking_client.MovieTicketBook_Service();
        movieticketbooking_client.MovieTicketBook port = service.getMovieTicketBookPort();
        return port.bookTicket(movieName, ticketCount);
    }

    private static MovieInfo getMoviesInfo() {
        movieticketbooking_client.MovieTicketBook_Service service = new movieticketbooking_client.MovieTicketBook_Service();
        movieticketbooking_client.MovieTicketBook port = service.getMovieTicketBookPort();
        return port.getMoviesInfo();
    }

}
