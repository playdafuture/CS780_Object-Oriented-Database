import java.util.HashSet;

/**
 * Complex Venue class.
 * A Complex Venue contains 2 or more Single Venues.
 * @author Jinqiu Liu
 */
@javax.jdo.annotations.PersistenceCapable
public class ComplexVenue extends Venue {
    /**
     * The set of Sub-Venues that belongs to this Complex Venue.
     */
    HashSet<SingleVenue> subVenues;
    
    /**
     * Constructor for the Complex Venue.
     * Two single venues must be included in the constructor.
     * Additional sub-venues can be included later if needed.
     * @param name Name of the Venue.
     * @param location Location of the Venue.
     * @param v1 The 1st sub-venue that belongs to this complex.
     * @param v2 The 2nd sub-venue that belongs to this complex.
     */
    public ComplexVenue(String name, String location, SingleVenue v1, SingleVenue v2) {
        this.name = name;
        this.location = location;
        subVenues = new HashSet<>();
        //ensures there are at least 2 sub-Venues
        subVenues.add(v1); 
        subVenues.add(v2);
    }
    
    /**
     * Adds an additional sub-venue to this complex.
     * Repeat this to add additional single venue(s) to this complex.
     * @param subVenue Another single venue that belongs to this complex.
     */
    public void addSubVenue(SingleVenue subVenue) {
        subVenues.add(subVenue);
    }
    
    @Override
    public String toString() {
        return "Complex Venue: " + name + " @" + location;
    }
    
    /**
     * Returns the full info of a complex venue, the venue itself and all it's sub-venues.
     * @return Full information of the Venue as a Multi-line String.
     */
    public String fullInfo() {
        String info = "Complex Venue: " + name + " @" + location + "\n";
        info += "\tSubVenues:\n";
        Object[] subs = subVenues.toArray();
        for (int i = 0; i < subs.length; i++) {
            info += "\t" + (i+1) + "/" + subs.length;
            info += "\t" + subs[i];
            if (i < subs.length - 1) info += "\n";
        }
        return info;
    }
}
