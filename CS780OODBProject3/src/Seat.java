import java.util.TreeSet;

/**
 * Seat class.
 * @author Jinqiu Liu
 */
@javax.jdo.annotations.PersistenceCapable
public class Seat implements Comparable<Seat> {
    /**
     * Label of the Seat.
     */
    String label;
    
    /**
     * The Venue this seat belongs to.
     */
    SingleVenue venue;
    /**
     * The Set of Prices for this seat (for different Events).
     */
    TreeSet<Price> pricesOfSeat;
    /**
     * The Set of Tickets for this seat.
     */
    TreeSet<Ticket> ticketsOfSeat;
    
    /**
     * Constructor for the Seat.
     * @param venue The Venue this seat belongs to.
     * @param label The label of the Seat.
     */
    public Seat(SingleVenue venue, String label) {
        this.venue = venue;
        this.label = label;
        pricesOfSeat = new TreeSet<>();
        ticketsOfSeat = new TreeSet<>();
    }
    
    /**
     * Add another Price for this seat.
     * @param price The additional price for the seat.
     */
    public void addPrice(Price price) {
        pricesOfSeat.add(price);
    }
    
    /**
     * Add another Ticket for this seat.
     * @param ticket The additional ticket for the seat.
     */
    public void addTicket(Ticket ticket) {
        ticketsOfSeat.add(ticket);
    }
    
    /**
     * Compare the seat with another seat based on its label.
     * @param that The other seat to be compared with.
     * @return -1 if this seat comes first, 1 if the other seat is first,
     * and 0 if two seats have the same label.
     */
    @Override
    public int compareTo(Seat that) {
        return this.label.compareTo(that.label);
    }
    
    @Override
    public String toString() {
        return "Seat " + label;
    }
}
