
import java.util.TreeSet;

/**
 * Price object.
 * This implements the 3-ary relationship of price(Ticket, Event, Seat).
 * @author Jinqiu Liu
 */
@javax.jdo.annotations.PersistenceCapable
public class Price implements Comparable<Price> {
    /**
     * The price amount.
     */
    float amount;
    
    /**
     * The set of Events that uses this Price.
     */
    TreeSet<Event> eventsOfPrice;
    /**
     * The set of Tickets that uses this Price.
     */
    TreeSet<Ticket> ticketsOfPrice;
    /**
     * The set of Seats that uses this Price.
     */
    TreeSet<Seat> seatsOfPrice;
    
    /**
     * Constructor for Price.
     * @param amount The amount of Price.
     */
    public Price(float amount) {
        this.amount = amount;
        eventsOfPrice = new TreeSet<>();
        ticketsOfPrice = new TreeSet<>();
        seatsOfPrice = new TreeSet<>();
    }
    
    /**
     * Adds an addition event that uses this Price.
     * Multiple events can and should use the same Price.
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        eventsOfPrice.add(event);
    }
    
    /**
     * Adds an additional ticket that uses this Price.
     * Multiple tickets can and should use the same Price.
     * @param ticket The ticket to be added.
     */
    public void addTicket(Ticket ticket) {
        ticketsOfPrice.add(ticket);
    }
    
    /**
     * Adds an additional seat that uses this Price.
     * Multiple seats can and should use the same Price.
     * @param seat The seat to be added.
     */
    public void addSeat(Seat seat) {
        seatsOfPrice.add(seat);
    }

    /**
     * Compares this Price with another Price.
     * @param that The other Price to be compared with.
     * @return -1 if this Price is cheaper, 1 if more expensive,
     * and 0 if it's the same.
     */
    @Override
    public int compareTo(Price that) {
        if (this.amount < that.amount) {
            return -1;
        } else if (this.amount > that.amount) {
            return 1;            
        } else {
            return 0;
        }
    }
    
    @Override
    public String toString() {
        return "" + amount;
    }
}
