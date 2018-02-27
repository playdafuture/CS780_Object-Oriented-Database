import java.util.HashSet;
import java.util.TreeSet;

/**
 * Event class.
 * Implements the 4-ary relation Event(Performer, SingleVenue, Price, Ticket).
 * @author Jinqiu Liu
 */
@javax.jdo.annotations.PersistenceCapable
public class Event implements Comparable<Event> {
    /**
     * Name of the Event.
     */
    String name;
    /**
     * Date of the Event.
     */
    String date;
    /**
     * Set of Performers for the event.
     * Minimum 1 required.
     */
    HashSet<Performer> performersOfEvent;
    /**
     * Venue/Location of the Event.
     */
    SingleVenue venueOfEvent;
    /**
     * Set of Tickets sold for this Event, 
     * including those Tickets that has access to multiple Events.
     */
    TreeSet<Ticket> ticketsOfEvent;
    /**
     * Set of Prices marked for this Event.
     */
    TreeSet<Price> pricesOfEvent;
    
    /**
     * Constructor for an Event.
     * @param name Name of the Event.
     * @param date Date of the Event.
     * @param location Venue/Location of the Event.
     */
    public Event(String name, String date, SingleVenue location) {
        this.name = name;
        this.date = date;
        venueOfEvent = location;
        performersOfEvent = new HashSet<>();
        ticketsOfEvent = new TreeSet<>();
        pricesOfEvent = new TreeSet<>();
    }
    
    /**
     * Add a Performer to this Event.
     * @param performer A performer that performs for this Event.
     */
    public void addPerformer(Performer performer) {
        performersOfEvent.add(performer);
    }
    /**
     * Add a Ticket to this Event.
     * @param ticket A Ticket that was sold for this Event.
     */
    public void addTicket(Ticket ticket) {
        ticketsOfEvent.add(ticket);
    }
    
    /**
     * Add a Price to this Event.
     * @param price A price to be added to this Event.
     */
    public void addPrice(Price price) {
        pricesOfEvent.add(price);
    }
    
    /**
     * Compares this Event with another based on the date and name.
     * An earlier Event would come first, if the date is the same,
     * then the Event name with the smaller lexical order is first.
     * @param that The other Event to compare with.
     * @return -1 if this is less than that, 1 if this is more than that, 
     * and 0 if they are both exactly the same.
     */
    @Override
    public int compareTo(Event that) {
        int dateCompare = Utility.compareDates(this.date, that.date);
        if (dateCompare == 0) { //Date is the same
            return Utility.compareNames(this.name, that.name);
        }
        return dateCompare;
    }
    
    @Override
    public String toString() {
        return "Event: " + name + " on " + date + " @" + venueOfEvent.name;
    }
}
