
import java.util.TreeSet;

/**
 * Payment class.
 * Holds the information of tickets purchased by this payment and the owner account.
 * @author Jinqiu Liu
 */
@javax.jdo.annotations.PersistenceCapable
public class Payment {
    /**
     * Amount paid.
     */
    float amount;
    /**
     * Payment method. e.g. Cash, Credit, Debit, Gift Card.
     */
    String method;
    /**
     * Date of Payment.
     */
    String date;
    
    /**
     * The Set of tickets purchased by this payment method.
     */
    TreeSet<Ticket> ticketsPurchased;
    /**
     * The Customer account that owns this Payment.
     */
    CustomerAccount ownerAccount;
    
    /**
     * Constructor for Payment object.
     * Creates a payment object of set amount, method, and date.
     * Additional ticket(s) for this payment can be added later.
     * @param amount The amount paid.
     * @param method The payment method.
     * @param date The payment date.
     * @param ownerAccount The Customer account that owns this Payment.
     * @param ticket A ticket purchased. 
     * This ensures the payment is linked to at least 1 ticket.
     */
    public Payment(float amount, String method, String date, CustomerAccount ownerAccount, Ticket ticket) {
        this.amount = amount;
        this.method = method;
        this.date = date;
        this.ownerAccount = ownerAccount;
        ticketsPurchased = new TreeSet<>();
        ticketsPurchased.add(ticket);
    }
    
    /**
     * Add a ticket to the purchased set of this payment.
     * Repeat this for adding more ticket(s) purchased if needed.
     * @param ticket A ticket purchased by this payment.
     */
    public void addTicketPurchased(Ticket ticket) {
        ticketsPurchased.add(ticket);
    }
    
    @Override
    public String toString() {
        String info = "$" + amount + " " + method + " Payment on " + date + "\n";
        Object[] objs = ticketsPurchased.toArray();
        for (int i = 0; i < objs.length; i++) {
            info += "\t" + (i+1) + "/" + objs.length;
            info += "\t" + objs[i];
            if (i < objs.length - 1) info += "\n";
        }
        return info;
    }
}
