/*
 * A Comparator that sorts a List of Entities in desending order by when the
 * Entity was added to the database. In other words, the most recent Entity will
 * be sorted to the front of the List while the first entry into the database 
 * appears at the end of the List. 
 */
package util;

import bean.entity.Entity;
import java.util.Comparator;

/**
 *
 * @author Zach Bolan
 * @param <T>
 */
public class RecentEntityComparator<T extends Entity> implements Comparator<T>{

	@Override
	public int compare(T t1, T t2) {
		return t2.getId() - t1.getId();
	}
	
}
