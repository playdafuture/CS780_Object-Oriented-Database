import java.util.*;
import javax.jdo.*;

@javax.jdo.annotations.PersistenceCapable

public class Company
{
	String name; // key

	HashSet<Laptop> makeLaptops = new HashSet<Laptop>(); 
	  // The set of laptops this company makes

	HashSet<Processor> makeProcessors = new HashSet<Processor>(); 
	  // The set of processors this company makes


	public Company(String s)
	{
		name = s;
	}

	public String toString()
	{
		return name;
	}
	
	public static Collection<Company> memoryProcessor(float c, int s, Query q)

	/* Returns the collection of all companies that make laptops that
	   have a processor clock speed of at least "c" GHz and a memory size of
	   at least "s" GB. Sort the result by name. */

	{
                q.setClass(Company.class);
                q.declareVariables("Laptop t");
                q.setFilter("this.makeLaptops.contains(t) "
                        + "&& t.processor.clockSpeed >= " + c + " "
                        + "&& t.memory.size >= " + s);
                q.setOrdering("name");
                return (Collection<Company>) q.execute();
	}

	public static Collection<Company> differentCompanyProcessor(Query q)

	/* Returns the collection of all companies that make at least two laptops 
	   preinstalled with processors made by different companies. Sort the result by name. */

	{
                q.setClass(Company.class);
                q.declareVariables("Laptop l1, l2");
                q.setFilter("this.makeLaptops.contains(t1)"
                        + "&& this.makeLaptops.contains(t2)"
                        + "&& t1.processor.madeBy != t2.processor.madeBy");
                q.setOrdering("name");
                return (Collection<Company>) q.execute();
	}
}