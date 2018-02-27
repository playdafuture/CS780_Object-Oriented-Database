import java.util.*;
import javax.jdo.*;

@javax.jdo.annotations.PersistenceCapable

public class Processor extends Product
{
	float clockSpeed; // in gigahertz (GHz)

	Company madeBy; // the inverse of Company.makeProcessors


	public Processor(String mn, float cs)
	{
		modelName = mn;
		clockSpeed = cs;	
	}

	public String toString()
	{
		return madeBy.name+" "+modelName+" "+clockSpeed+" GHz";
	}
	
	public Collection<Laptop> installedOn(Query q)

	/* Returns the collection of all laptops on which the target processor is preinstalled. 
	   Represents the inverse of Laptop.processor. Sort the result by (madeBy.name, modelName). */

	{
                q.setClass(Laptop.class);
                q.declareVariables("Laptop lt");
                q.declareParameters("Processor p");
                q.setFilter("lt.processor == p");
                q.setResult("distinct lt");
                q.setOrdering("lt.madeBy.name, lt.modelName");
                return (Collection<Laptop>) q.execute(this);
	}
}