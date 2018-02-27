/**
 * Test class.
 * Contains tests on different components.
 * @author Jinqiu Liu
 */
public class Test {
    public static void main(String[] args) {
        int[] cases = {0, 1, 2, 3};
        runTests(cases);
    }
    
    private static void runTests(int[] testCases) {
        for (int i = 0; i < testCases.length; i++) {
            switch (testCases[i]) {
                case 3:
                    testComplexVenue();
                    break;
                case 2:
                    testCustomerAccount();
                    break;
                case 1:
                    testPaymentTickets();
                    break;
                case 0:
                    testPerformer();
                    break;
                default:
                    break;
            }
        }
        
    }
    
    private static void testComplexVenue() {
        SingleVenue msgLv1 = new SingleVenue("Rotunda", "Level 1");
        SingleVenue msgLv2 = new SingleVenue("Orchestra", "Level 2");
        SingleVenue msgLv3 = new SingleVenue("First Promenade", "Level 3");
        ComplexVenue msg = new ComplexVenue("Madison Square Garden", "4 Pennsylvania Plaza, New York, NY 10001", msgLv2, msgLv1);
        msg.addSubVenue(msgLv3);
        msgLv1.setMainVenue(msg);
        msgLv2.setMainVenue(msg);
        msgLv3.setMainVenue(msg);
        System.out.println(msg.fullInfo());
        System.out.println("End of testComplexVenue");
    }
    
    private static void testCustomerAccount() {
        CustomerAccount c1 = new CustomerAccount("John Doe", "jd@email.com");
        System.out.println(c1);
        CustomerAccount c2 = new CustomerAccount("Jane Doe", "msjd@email.com");
        Event event1 = new Event("Fake Event", "01/01/2000", new SingleVenue("Fake Venue", "Fake Location"));
        Event event2 = new Event("Fake Event", "01/02/2000", new SingleVenue("Fake Venue", "Fake Location"));
        Ticket ticket1 = new Ticket(event1, new Price(10f));
        Ticket ticket2 = new Ticket(event2, new Price(20f));
        Payment p1 = new Payment(10f, "Cash", "12/12/2012", c2, ticket1);
        c2.addPayment(p1);
        ticket1.setPayment(p1);
        Payment p2 = new Payment(20f, "Cash", "12/12/2012", c2, ticket2);
        ticket2.setPayment(p2);
        c2.addPayment(p2);
        System.out.println(c2);
        System.out.println("End of testCustomerAccount");
    }
    
    private static void testPaymentTickets() {
        CustomerAccount c2 = new CustomerAccount("Jane Doe", "msjd@email.com");
        Event event1 = new Event("Fake Event", "01/01/2000", new SingleVenue("Fake Venue", "Fake Location"));
        Event event2 = new Event("Fake Event", "01/02/2000", new SingleVenue("Fake Venue", "Fake Location"));
        Ticket ticket1 = new Ticket(event1, new Price(10f));
        Ticket ticket2 = new Ticket(event2, new Price(20f));
        Payment p1 = new Payment(10f, "Cash", "12/12/2012", c2, ticket1);
        ticket1.setPayment(p1);
        p1.addTicketPurchased(ticket2);
        ticket2.setPayment(p1);
        System.out.println(p1);
        System.out.println("End of testPaymentTickets");
    }
    
    private static void testPerformer() {
        Event event = new Event("Movie Event", "01/01/2000", new SingleVenue("Movie Theatre", "Some Location"));
        Performer p1 = new Performer("Movie",event);
        System.out.println(p1);
        System.out.println("End of testPerformer");
    }
}
