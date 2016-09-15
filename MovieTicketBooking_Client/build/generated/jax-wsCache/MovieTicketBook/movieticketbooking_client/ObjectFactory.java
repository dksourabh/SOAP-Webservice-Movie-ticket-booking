
package movieticketbooking_client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the movieticketbooking_client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetMoviesInfo_QNAME = new QName("http://MovieTicketModel/", "getMoviesInfo");
    private final static QName _BookTicket_QNAME = new QName("http://MovieTicketModel/", "bookTicket");
    private final static QName _BookTicketResponse_QNAME = new QName("http://MovieTicketModel/", "bookTicketResponse");
    private final static QName _GetMoviesInfoResponse_QNAME = new QName("http://MovieTicketModel/", "getMoviesInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: movieticketbooking_client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMoviesInfo }
     * 
     */
    public GetMoviesInfo createGetMoviesInfo() {
        return new GetMoviesInfo();
    }

    /**
     * Create an instance of {@link GetMoviesInfoResponse }
     * 
     */
    public GetMoviesInfoResponse createGetMoviesInfoResponse() {
        return new GetMoviesInfoResponse();
    }

    /**
     * Create an instance of {@link BookTicketResponse }
     * 
     */
    public BookTicketResponse createBookTicketResponse() {
        return new BookTicketResponse();
    }

    /**
     * Create an instance of {@link BookTicket }
     * 
     */
    public BookTicket createBookTicket() {
        return new BookTicket();
    }

    /**
     * Create an instance of {@link MovieInfo }
     * 
     */
    public MovieInfo createMovieInfo() {
        return new MovieInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMoviesInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MovieTicketModel/", name = "getMoviesInfo")
    public JAXBElement<GetMoviesInfo> createGetMoviesInfo(GetMoviesInfo value) {
        return new JAXBElement<GetMoviesInfo>(_GetMoviesInfo_QNAME, GetMoviesInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MovieTicketModel/", name = "bookTicket")
    public JAXBElement<BookTicket> createBookTicket(BookTicket value) {
        return new JAXBElement<BookTicket>(_BookTicket_QNAME, BookTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MovieTicketModel/", name = "bookTicketResponse")
    public JAXBElement<BookTicketResponse> createBookTicketResponse(BookTicketResponse value) {
        return new JAXBElement<BookTicketResponse>(_BookTicketResponse_QNAME, BookTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMoviesInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MovieTicketModel/", name = "getMoviesInfoResponse")
    public JAXBElement<GetMoviesInfoResponse> createGetMoviesInfoResponse(GetMoviesInfoResponse value) {
        return new JAXBElement<GetMoviesInfoResponse>(_GetMoviesInfoResponse_QNAME, GetMoviesInfoResponse.class, null, value);
    }

}
