import java.util.HashSet;

/**
 * Performer class.
 * A Performer has 1..* relationship with Event
 * @author Jinqiu Liu
 */

@javax.jdo.annotations.PersistenceCapable
public class Performer {
    /**
     * Name of the performer.
     */
    String name;
    /**
     * Set of events this performer is performing.
     */
    HashSet<Event> performances;
    
    /**
     * Constructor for Performer.
     * At least 1 event is required.
     * Additional events can be added later if needed.
     * @param name Name of the performer.
     * @param event An event where this performer is performing.
     */
    public Performer(String name, Event event) {
        this.name = name;
        performances = new HashSet<>();
        performances.add(event);
    }
    
    /**
     * Add another event to this performer list of events.
     * More events can be added if needed.
     * @param event An event this performer is performing.
     */
    public void addPerformance(Event event) {
        performances.add(event);
    }
    
    @Override
    public String toString() {
        return "Performer: " + name;
    }
}
