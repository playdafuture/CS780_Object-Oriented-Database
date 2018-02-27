import java.util.*;
import javax.jdo.*;

@javax.jdo.annotations.PersistenceCapable

public class Laptop extends Product
{
	int price; // in dollars
	boolean hasHDScreen; // has a high-definition screen?
	int hardDriveCapacity; // in GB

	Processor processor; // the preinstalled processor
	Memory memory; // the preinstalled memory 
	Company madeBy; // the inverse of Company.makeLaptops

	
	public Laptop(String mn, int p, boolean hd, int hdc)
	{
		modelName = mn;
		price = p;
		hasHDScreen = hd;
		hardDriveCapacity = hdc;	
	}

	public String toString()
	{
		return madeBy.name+" "+modelName+"; "+
		       processor.toString()+"; "+
		       memory.toString()+"; "+
		       "harddrive: "+hardDriveCapacity+" GB";
	}
	
	public static Laptop find(String mName, PersistenceManager pm)

	/* Returns the laptop with the given model name "mName"; returns null if no such laptop exists. 
	   The function is applied to the database held by the persistence manager pm. */

	{
                Query q = pm.newQuery();
                q.setClass(Laptop.class);
                q.declareParameters("String mName");
                q.setFilter("this.modelName == mName");
                q.setUnique(true);
                Laptop result = (Laptop) q.execute(mName);
                q.closeAll();
                return result;
	}

	public static Collection<Laptop> HDandHardDrive(int x, Query q)

	/* Returns the collection of all laptops that have an HD screen and at least x GB of harddrive.
	   Sort the result by (hardDriveCapacity, modelName). */

	{
                q.setClass(Laptop.class);
                q.declareParameters("int x");
                q.setFilter("this.hasHDScreen == true && this.hardDriveCapacity >= x");
                q.setOrdering("this.hardDriveCapacity, this.modelName");
                return (Collection<Laptop>) q.execute(x);
	}

	public static Collection<Laptop> speedPrice(float c, int p1, int p2, Query q)

	/* Returns the collection of all laptops that have a processor clock speed of at least "c" GHz
           and a price of at least "p1" and at most "p2" dollars.
	   Sort the result by (processor.clockSpeed, price, modelName). */

	{
                q.setClass(Laptop.class);
                q.declareParameters("float c, int p1, int p2");
                q.setFilter("this.processor.clockSpeed >= c"
                        + " && this.price >= p1"
                        + " && this.price <= p2");
                q.setOrdering("this.processor.clockSpeed, this.price, this.modelName");
                return (Collection<Laptop>) q.execute(c,p1,p2);
	}

	public static Collection<Laptop> hasProcessor(String cName, Query q)

	/* Returns the collection of all laptops that have processors made by
	   the company with the name "cName". Sort the result by (madeBy.name, modelName). */

	{
                q.setClass(Laptop.class);
                q.declareParameters("String cName");
                q.setFilter("this.processor.madeBy.name == cName");
                q.setOrdering("this.processor.madeBy.name, this.modelName");
                return (Collection<Laptop>) q.execute(cName);
	}

	public static Collection<Object[]> laptopProcessorMadeBySameCompany(Query q)

	/* Returns the set of 3-tuples <lt: Laptop, p: Processor, c: Company> such that
	   laptop "lt" is preinstalled with processor "p" and company "c" makes both "lt" and "p". 
	   Sort the result by (c.name, lt.modelName). */

	{
                q.setClass(Laptop.class);
                q.setFilter("this.processor.madeBy == this.madeBy");
                q.setResult("this, this.processor, this.madeBy");
                q.setOrdering("this.madeBy.name, this.modelName");
                return (Collection<Object[]>) q.execute();
	}
	
	public static Collection<Laptop> sameProcessor(Query q)
	
	/* Returns the collection of all laptops each of which has at least one other laptop 
	   preinstalled with the same processor. Sort the result by (madeBy.name, modelName). */
	
	{
                q.setClass(Laptop.class);
                q.declareVariables("Laptop that");
                q.setFilter("this.processor == that.processor && this != that");
                q.setOrdering("this.madeBy.name, this.modelName");
                return (Collection<Laptop>) q.execute();
	}

	public static Collection<Object[]> groupByCompany(Query q)

	/* Group the laptops by the companies that make them.
	   Then return the set of 4-tuples <c: Company, num: int, minSpeed: float, maxSize: int> where:

	   num = the total number of laptops made by c
	   minSpeed = the minimum clock speed of the processors preinstalled on the laptops made by c
	   maxSize = the maximum memory size of the memories preinstalled on the laptops made by c     

	   Sort the result by c.name. */

	{
            /**
             * Query q = pm.newQuery("select madeBy, count(this), min(processor.clockSpeed), max(memory.size) "
                + "from Laptop group by madeBy order by madeBy");
             */
                q.setClass(Laptop.class);
                q.setResult("madeBy, count(this), min(processor.clockSpeed), max(memory.size)");          
                q.setGrouping("madeBy");                      
                q.setOrdering("madeBy");
                return (Collection<Object[]>) q.execute();
	}
}