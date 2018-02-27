/**
 * Abstract class Venue.
 * A Venue can be a Single Venue or a Complex Venue. 
 * A Complex Venue contains 2 or more Single Venues.
 * @author Jinqiu Liu
 */

@javax.jdo.annotations.PersistenceCapable
public abstract class Venue {
    /**
     * Name of the Venue.
     */
    String name;
    /**
     * Location of the Venue.
     */
    String location;
}
