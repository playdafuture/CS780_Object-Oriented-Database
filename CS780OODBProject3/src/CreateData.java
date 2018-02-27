
import com.objectdb.Utilities;
import javax.jdo.PersistenceManager;

/**
 * Create Data class.
 * This class contains the main method that creates some sample data.
 * @author Jinqiu Liu
 */
public class CreateData {
    public static void main(String[] args) {
        //Create Venue
        SingleVenue v1 = new SingleVenue("venue1","location1");        
        //Create Event 1
        Event e1 = new Event("event1", "01/20/2018", v1);
        v1.addEvent(e1);
            //Link Event 1 with Ticket 1
            Price p1 = new Price(20.0f);
            Seat s1 = new Seat(v1, "001");
            v1.addSeat(s1);
                //Link Price 1 with Seat 1
                p1.addSeat(s1);
                s1.addPrice(p1);
            Seat s2 = new Seat(v1, "002");
            v1.addSeat(s2);
                //Link Price 1 with Seat 2
                p1.addSeat(s2);
                s2.addPrice(p1);
            Seat s3 = new Seat(v1, "003");
            v1.addSeat(s3);
                //Link Price 1 with Seat 3
                p1.addSeat(s3);
                s3.addPrice(p1);
            Ticket t1 = new Ticket(e1, p1, s1);
            e1.addTicket(t1);
            p1.addTicket(t1);
            s1.addTicket(t1);
            //Link Event 1 with Ticket 2
            Price p2 = new Price(30.0f);        
            Seat s4 = new Seat(v1, "004");
            v1.addSeat(s4);
                //Link Price 2 with Seat 4
                p2.addSeat(s4);
                s4.addPrice(p2);
            Seat s5 = new Seat(v1, "005");
            v1.addSeat(s5);
                //Link Price 2 with Seat 5
                p2.addSeat(s5);
                s5.addPrice(p2);
            Ticket t2 = new Ticket(e1, p2, s4);
            e1.addTicket(t2);
            p2.addTicket(t2);
            s4.addTicket(t2);
            //Link Event 1 with other Prices
            e1.addPrice(p1);
            p1.addEvent(e1);
            e1.addPrice(p2);
            p2.addEvent(e1);        
        //Create Event 2
        Event e2 = new Event("event2", "02/10/2018", v1);
        v1.addEvent(e2);
            //Link Event 2 with Ticket 3
            Price p3 = new Price(40.0f);
                //Link Price 3 with Seat 1
                p3.addSeat(s1);
                s1.addPrice(p3);
                //Link Price 3 with Seat 2
                p3.addSeat(s2);
                s2.addPrice(p3);
            Ticket t3 = new Ticket(e2, p3, s1);
            e2.addTicket(t3);
            p3.addTicket(t3);
            s1.addTicket(t3);
            //Link Event 2 with other Prices
            e2.addPrice(p2);
            p2.addEvent(e2);
            Price p4 = new Price(50.0f);
                //Link Price 4 with Seat 3
                p4.addSeat(s3);
                s3.addPrice(p4);
                //Link Price 4 with Seat 4
                p4.addSeat(s4);
                s4.addPrice(p4);
                //Link Price 4 with Seat 5
                p4.addSeat(s5);
                s5.addPrice(p4);
            e2.addPrice(p4);
            p4.addEvent(e2);        
        //Create Event 3
        Event e3 = new Event("event3", "03/15/2018", v1);
        v1.addEvent(e3);
            //Link Event 3 with Ticket 4
            Price p5 = new Price(60.0f);
            Ticket t4 = new Ticket(e3, p5);
            e3.addTicket(t4);
            p5.addTicket(t4);
            //Link Event 3 with other Prices
            e3.addPrice(p5);
            p5.addEvent(e3);        
        //Create Event 4
        Event e4 = new Event("event4", "03/16/2018", v1);
        v1.addEvent(e4);
            //Link Event 4 with Ticket 4
            t4.addEvent(e4);
            e4.addTicket(t4);
            //Link Event 4 with other Prices
            e4.addPrice(p5);
            p5.addEvent(e4);
        //Commit to JDO
        PersistenceManager pm = Utilities.getPersistenceManager("ticket.odb");
        pm.currentTransaction().begin();
        pm.makePersistent(v1);
        pm.makePersistent(e1);
        pm.makePersistent(e2);
        pm.makePersistent(e3);
        pm.makePersistent(e4);
        pm.makePersistent(t1);
        pm.makePersistent(t2);
        pm.makePersistent(t3);
        pm.makePersistent(t4);
        pm.makePersistent(p1);
        pm.makePersistent(p2);
        pm.makePersistent(p3);
        pm.makePersistent(p4);
        pm.makePersistent(p5);
        pm.makePersistent(s1);
        pm.makePersistent(s2);
        pm.makePersistent(s3);
        pm.makePersistent(s4);
        pm.makePersistent(s5);        
        pm.currentTransaction().commit();
        System.out.println("DONE");
    }
}
