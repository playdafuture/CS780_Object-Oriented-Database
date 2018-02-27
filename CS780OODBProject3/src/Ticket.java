
import java.util.TreeSet;
/**
 * Ticket class.
 * Each ticket correspond to a specific Event(s), Seat, and its price.
 * @author Jinqiu Liu
 */
@javax.jdo.annotations.PersistenceCapable
public class Ticket implements Comparable<Ticket> {
    /**
     * Maximum Length for the digits in a Ticket#.
     * For example, length 3 includes 000 to 999.
     */
    final int IDLength = 3;
    
    /**
     * Initial sequence number for tickets.
     * This usually start with 0 or 1, and will be incremented automatically.
     */
    static int sequenceNumber = 1;
    /**
     * The String representation of the Ticket number.
     */
    String IDcode;
    
    /**
     * The set of events this ticket has access to.
     */
    TreeSet<Event> eventsOfTicket;
    /**
     * The price of the ticket.
     */
    Price priceOfTicket;
    /**
     * The seat this ticket has access to.
     */
    Seat seatOfTicket;
    /**
     * The payment method that paid for this ticket.
     */
    Payment paymentOfTicket;
    
    /**
     * Private method for generating a unique ID for a new ticket.
     */
    private void generateID() {
        String newID = "" + sequenceNumber++;
        while (newID.length() < IDLength) {
            newID = "0" + newID;
        }
        IDcode = "T" + newID;
    }
    
    /**
     * Constructor for Ticket without assigned seat. 
     * The sequence number is generated automatically.
     * @param event An event this ticket has access to. Can add more later.
     * @param price The price of the ticket.
     */
    public Ticket(Event event, Price price) {
        generateID();
        eventsOfTicket = new TreeSet<>();
        eventsOfTicket.add(event);
        priceOfTicket = price;
    }
    
    /**
     * Constructor for Ticket with assigned seat. 
     * The sequence number is generated automatically.
     * @param event An event this ticket has access to. Can add more later.
     * @param price The price of the ticket.
     * @param seat The assigned seat for this Ticket.
     */
    public Ticket(Event event, Price price, Seat seat) {
        generateID();
        eventsOfTicket = new TreeSet<>();
        eventsOfTicket.add(event);
        priceOfTicket = price;
        seatOfTicket = seat;
    }
    
    /**
     * Adds an additional event that this ticket has access to.
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        eventsOfTicket.add(event);
    }
    
    /**
     * Sets the payment method that paid for this ticket.
     * Calling this method again will override the previous data.
     * @param payment The payment method that paid for the ticket.
     */
    public void setPayment(Payment payment) {
        paymentOfTicket = payment;
    }

    /**
     * Compares this ticket with another based on its sequence number.
     * @param that The other ticket to be compared with.
     * @return -1 if this ticket has a lower number, 
     * 1 if this ticket has a larger number,
     * and 0 if their numbers are the same (should not happen).
     */
    @Override
    public int compareTo(Ticket that) {
        return Utility.compareNames(this.IDcode,that.IDcode);
    }
    
    @Override
    public String toString() {
        String info = "Ticket [" + IDcode + "]" + "\n"
                + "\t" + paymentOfTicket.ownerAccount.name + "\n"
                + "\t$" + priceOfTicket + "\n";
        Object[] evnts = eventsOfTicket.toArray();
        for (int i = 0; i < evnts.length; i++) {
            info += "\t" + (i+1) + "/" + evnts.length;
            info += "\t" + evnts[i];
            if (i < evnts.length - 1) info += "\n";
        }
        return info;
    }
}
