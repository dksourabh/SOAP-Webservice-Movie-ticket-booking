/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieTicketModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import org.w3c.dom.NodeList;

/**
 *
 * @author sourabh_deshkulkarni
 */
public class myMessageHandler implements SOAPHandler<SOAPMessageContext> {

    //private static boolean operationFlag = false;

    public boolean handleMessage(SOAPMessageContext messageContext) {
        String outProperty = SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY;
        boolean outgoing = (Boolean) messageContext.get(outProperty);

        SOAPMessage msg = messageContext.getMessage();
        Connection con =null;
        String username = "";
        String operationName="";
        try {
           con= Utility.getConnection();
           Statement stmt = con.createStatement();
           HttpSession session =    ((javax.servlet.http.HttpServletRequest)messageContext.get(MessageContext.SERVLET_REQUEST)).getSession();
           System.out.println("Session id: "+session.getId());
            //System.out.println("Created DB Connection....");
            if (outgoing) {
                 SOAPHeader header = msg.getSOAPHeader();
                Iterator it = header.examineAllHeaderElements();
                while (it.hasNext()) {
                    SOAPHeaderElement e = (SOAPHeaderElement) it.next();
                    NodeList nl = e.getElementsByTagName("wsse:Username");
                    for (int i = 0; i < nl.getLength(); i++) {
                        username += nl.item(i).getTextContent();
                    }
                }
                //System.out.println("Outgoing username:"+header);
                   
                
                    msg.writeTo(new FileOutputStream("/Users/sourabh_deshkulkarni/Downloads/responseMessage.txt"));
                   
                
            } else {
                SOAPHeader header = msg.getSOAPHeader();
                Iterator it = header.examineAllHeaderElements();
                while (it.hasNext()) {
                    SOAPHeaderElement e = (SOAPHeaderElement) it.next();
                    NodeList nl = e.getElementsByTagName("wsse:Username");
                    for (int i = 0; i < nl.getLength(); i++) {
                        username += nl.item(i).getTextContent();
                    }
                }
               //  System.out.println("Incoming username:"+header);
//                SOAPEnvelope msgEnvelope = messageContext.getMessage().getSOAPPart().getEnvelope(); //get the SOAP Message envelope
//            SOAPBody body = msgEnvelope.getBody();
//            String operationName = body.getChildNodes().item(1).getLocalName();//.item(1).getLocalName();
                if (msg.getSOAPBody().getLastChild().getNodeName().contains("getMoviesInfo")) {
                    //operationFlag = true;
                     operationName= "getMoviesInfo";
                }else{
                     operationName="bookTicket";
                }


                 String queryTwo= "SELECT * FROM test.login ORDER BY ID DESC LIMIT 1";
               String sessionId=Utility.generateSessionId(username);
                
                 ResultSet rs = stmt.executeQuery(queryTwo);
                 String currentOperation="";
                 String currentUsername="";
                 String currentSession="";
                 String tempString="";
                 String [] tempArr = new String [2];
           while (rs.next()) {
             tempString=rs.getString("username_session");
             currentOperation= rs.getString("operation");
             tempArr= tempString.split("_");
             currentUsername=tempArr[0];
             currentSession=tempArr[1];
              // System.out.println("Available tickets: "+availabeTickets);
           }
           String query = "INSERT INTO test.login ( username_Session,operation)" +
"                       VALUES " 
                        +
"        ( '"+username+"_"+sessionId+"', '"+operationName+"' )";
            stmt.execute(query);
           System.out.print("Current username: "+currentSession+"// LAST username :"+sessionId);
           if((currentUsername.equalsIgnoreCase(""))|| !(sessionId.equalsIgnoreCase(currentSession))||!(currentUsername.equalsIgnoreCase(username))|| ((currentOperation.equalsIgnoreCase("bookTicket"))&&(operationName.equalsIgnoreCase("bookTicket"))) ){
               SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
               System.out.println("We are in fault");
                        SOAPFault soapFault = soapBody.addFault();

                        soapFault.setFaultString("Please invoke getMoviesInfo before invoking bookTicket.");

                       //System.out.println(msg.toString());
                        throw new SOAPFaultException(soapFault);
           }
                FileOutputStream f = new FileOutputStream("/Users/sourabh_deshkulkarni/Downloads/username.txt");
                byte data[] = username.getBytes();
                f.write(data);
                msg.writeTo(new FileOutputStream("/Users/sourabh_deshkulkarni/Downloads/inputMessage.txt"));

                
            }
        } catch (IOException e) {
            System.out.println("IO Error!!!!");
            throw new RuntimeException(e);

        } catch (SOAPException e) {
            System.out.println("SOAP	IO	Error!!!!");
            throw new RuntimeException(e);

        } catch (SQLException ex) {
            Logger.getLogger(myMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }

    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }

    public void close(MessageContext context) {
    }

}
