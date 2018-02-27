import java.util.*;
import javax.jdo.*;
import com.objectdb.Utilities;
/**
 *
 * @author Jinqiu Liu
 */
public class Test {
    public static void main(String[] args) {
        PersistenceManager pm = Utilities.getPersistenceManager( "laptop1.odb" );
        System.out.println( "-- TEST Laptop.groupByCompany --\n" );
        Query q = pm.newQuery("select madeBy, count(this), min(processor.clockSpeed), max(memory.size) "
                + "from Laptop group by madeBy order by madeBy");
        Collection<Object[]> co = (Collection<Object[]>) q.execute();
        Utility.printCollectionOfArrays(co);
        q.closeAll();
	System.out.println();
        
        System.out.println( "-- TEST All Laptops by Company --\n" );
        q = pm.newQuery();
        q.setClass(Laptop.class);
        q.declareVariables("Laptop lt");
        q.setResult("distinct lt");
        q.setOrdering("lt.madeBy, lt");
        Collection<Laptop> cl = (Collection<Laptop>) q.execute();
        Utility.printCollection(cl);
        q.closeAll();
	System.out.println();
        
        System.out.println( "-- TEST All Companies who made both a processor and a laptop --\n" );
        q = pm.newQuery();
        q.setClass(Company.class);
        q.declareVariables("Laptop lt; Processor p; Company c");
        q.setFilter("c.makeLaptops.contains(lt) && c.makeProcessors.contains(p)");
        q.setResult("distinct c, lt, p");
        q.setOrdering("c, lt, p");
        co = (Collection<Object[]>) q.execute();
        Utility.printCollectionOfArrays(co);
        q.closeAll();
	System.out.println();
        
        System.out.println( "-- TEST Laptop with Best CPU --\n" );
        q = pm.newQuery();
        q.setClass(Processor.class);
        q.declareVariables("Processor p");        
        q.setResult("max(p.clockSpeed)");
        float maxClockSpeed = (float) q.execute();
        q = pm.newQuery();
        q.setClass(Laptop.class);
        q.declareVariables("Laptop lt");
        q.declareParameters("float speed");
        q.setFilter("lt.processor.clockSpeed == speed");
        q.setResult("distinct lt");
        cl = (Collection<Laptop>) q.execute(maxClockSpeed);
        Utility.printCollection(cl);
        q.closeAll();
	System.out.println();
    }
}
