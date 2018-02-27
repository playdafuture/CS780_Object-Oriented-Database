import java.util.HashSet;

/**
 * Customer Account class.
 * Each customer account contains the customer's basic information,
 * also multiple payments that purchase tickets.
 * @author Jinqiu Liu
 */
@javax.jdo.annotations.PersistenceCapable
public class CustomerAccount {
    /**
     * Name of customer.
     */
    String name;
    /**
     * Email of customer.
     */
    String email;
    /**
     * The set of payments of the customer.
     */
    HashSet<Payment> payment;
    
    /**
     * Constructor for a Customer Account.
     * Name and Email are both required. 
     * Payments are empty and must be filled in later.
     * @param name Name of customer.
     * @param email Email of customer.
     */
    public CustomerAccount(String name, String email) {
        this.name = name;
        this.email = email;
        this.payment = new HashSet<>();
    }
    
    /**
     * Adds a payment into this account.
     * Repeat this if more payment(s) need to be added.
     * @param payment A payment object.
     */
    public void addPayment(Payment payment) {
        this.payment.add(payment);
    }
    
    @Override
    public String toString() {
        if (payment.isEmpty()) {
            return "Customer: " + name + ", " + email;
        } else {
            String info = "Customer: " + name + ", " + email + "\n";
            info += "\tPayments:\n";
            Object[] pays = payment.toArray();
            for (int i = 0; i < pays.length; i++) {
                info += "\t" + (i+1) + "/" + pays.length;
                info += "\t" + pays[i];
                if (i < pays.length - 1) info += "\n";
            }            
            return info;
        }
    }
}
