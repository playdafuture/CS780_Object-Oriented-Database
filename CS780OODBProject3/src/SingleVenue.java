import java.util.TreeSet;

/**
 * Single Venue class.
 * A Single Venue holds Events.
 * A Single Venue contains multiple Seats.
 * A single Venue can be a part of a Complex Venue.
 * @author Jinqiu Liu
 */
@javax.jdo.annotations.PersistenceCapable
public class SingleVenue extends Venue{
    /**
     * The events that's held in this Single Venue.
     */
    TreeSet<Event> eventsOfVenue;
    /**
     * The seats that this Venue has.
     */
    TreeSet<Seat> seatsOfVenue;
    /**
     * If this Single Venue is a part of a Complex Venue,
     * the Complex Venue will be the mainVenue, otherwise,
     * mainVenue will be null.
     */
    ComplexVenue mainVenue;
    
    /**
     * Constructor for a Single Venue that does not belong to any other Venue.
     * @param name The name of this Venue.
     * @param location The location of this Venue.
     */
    public SingleVenue(String name, String location) {
        this.name = name;
        this.location = location;
        eventsOfVenue = new TreeSet<>();
        seatsOfVenue = new TreeSet<>();
    }
    
    /**
     * Constructor for a Single Venue that belongs to a Complex Venue.
     * @param name The name of this Venue.
     * @param location The location of this Venue.
     * @param mainVenue The Complex Venue that this Venue belongs to.
     */
    public SingleVenue(String name, String location, ComplexVenue mainVenue) {
        this.name = name;
        this.location = location;
        this.mainVenue = mainVenue;
        eventsOfVenue = new TreeSet<>();
        seatsOfVenue = new TreeSet<>();
    }
    
    /**
     * Adds an Event being held in this Venue.
     * @param event The Event to be added.
     */
    public void addEvent(Event event) {
        eventsOfVenue.add(event);
    }
    
    /**
     * Adds a Seat that belongs in this Venue.
     * @param seat The Seat to be added.
     */
    public void addSeat(Seat seat) {
        seatsOfVenue.add(seat);
    }
    
    /**
     * Set the main venue (if this is a sub-venue that belongs to a complex venue).
     * @param mainVenue The main venue this belongs to.
     */
    public void setMainVenue(ComplexVenue mainVenue) {
        this.mainVenue = mainVenue;
    }
    
    @Override
    public String toString() {
        if (mainVenue == null) {
            return "Single Venue: " + name + " @" + location;
        } else {
            return "Sub-Venue: " + name + " @" + location + " of " + mainVenue;
        }
    }
}
