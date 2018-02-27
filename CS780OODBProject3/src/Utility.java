import java.util.Collection;

/**
 * Utility class is not an object.
 * However, it holds a few methods shared by multiply classes 
 * for String comparisons.
 * @author Jinqiu Liu
 */
public abstract class Utility {
    public static <T> void printCollection(Collection<T> c) {
        for ( T x: c )
            System.out.println( x );
    }

    /**
     * Returns the last element of collection c, "last" according to the order of
     * the iteration of the for-loop. In particular, if c contains only one element,
     * that element is returned. If c is empty, null is returned.	   
     * @param <T>
     * @param c
     * @return 
     */
    public static <T> T extract(Collection<T> c) {
        T x = null;
        for ( T e: c )
            x = e;
        return x;
    }
    /**
     * Compare two date Strings and decide which is earlier than the other.
     * Assumes MM/DD/YYYY format.
     * @param d1 First date.
     * @param d2 Second date.
     * @return -1 if d1 is earlier than d2, 0 if they are the same, and 1 if d2 is earlier than d1.
     */
    public static int compareDates(String d1, String d2) {
        if(yearOf(d1) < yearOf(d2)) {
            return -1;
        } else if (yearOf(d1) > yearOf(d2)) {
            return 1;
        } else {
            if(monthOf(d1) < monthOf(d2)) {
                return -1;
            } else if (monthOf(d1) > monthOf(d2)) {
                return 1;
            } else {
                if(dayOf(d1) < dayOf(d2)) {
                    return -1;
                } else if (dayOf(d1) > dayOf(d2)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
    
    /**
     * Compare two Strings lexicographically.
     * @param s1 First string.
     * @param s2 Second string.
     * @return -1 if the first string comes first, 1 if the second string comes first, or 0 if two strings are the same.
     */
    public static int compareNames(String s1, String s2) {
        if (s1.equalsIgnoreCase(s2)) {
            return 0;
        } else {
            return s1.compareToIgnoreCase(s2)%2;
        }
    }
    
    /**
     * Returns the year value as integer of a date String.
     * Assumes MM/DD/YYYY format.
     * @param date A date String.
     * @return Value of year as integer.
     */
    private static int yearOf(String date) {
        String year = date.substring(6, 10);
        return Integer.parseInt(year);
    }
    
    /**
     * Returns the month value as integer of a date String.
     * Assumes MM/DD/YYYY format.
     * @param date A date String.
     * @return Value of month as integer.
     */
    private static int monthOf(String date) {
        String year = date.substring(3, 5);
        return Integer.parseInt(year);
    }
    
    /**
     * Returns the day value as integer of a date String.
     * Assumes MM/DD/YYYY format.
     * @param date A date String.
     * @return Value of day as integer.
     */
    private static int dayOf(String date) {
        String year = date.substring(0, 2);
        return Integer.parseInt(year);
    }
}
