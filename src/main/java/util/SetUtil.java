package util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUtil {
	private SetUtil() {
		super();
	}

	/**
	 * 
	 * @Description : Set是否为空
	 * @param set
	 * @return
	 */
	public static <E> boolean isEmpty(Set<E> set) {
		if ((set == null) || (set.size() == 0)) {
			return true;
		}
		return false;

	}
	
	/**
	 * 
	 * @Description : Set转为List
	 * @param set
	 * @return
	 */
	public static <E> List<E> toList(Set<E> set) {
		if (SetUtil.isEmpty(set)) {
			return null;
		}
		List<E> list = new ArrayList<E>();
		for(E item : set){
			list.add(item);
		}
		return list;
	}

	/**
	 * 
	 * @Description : set转化为数组
	 * @param set
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> E[] toArray(Set<E> set) {
		if (SetUtil.isEmpty(set)) {
			return null;
		}
		E notNullItem = null;
		boolean find = false;
		for (E e : set) {
			if (find) {
				break;
			}
			if (e != null) {
				notNullItem = e;
				find = true;
			}
		}

		E[] result = null;
		if (notNullItem != null) {
			result = (E[]) Array.newInstance(notNullItem.getClass(), set.size());
			result = set.toArray(result);
		}
		return result;
	}
	
	/**
	 * 
	 * @Description : 方法功能描述：数组转化为set, 如果数组中有null值，转化后会丢失null值
	 * @param array
	 * @return
	 */
	public static <E> Set<E> toSet(E[] array){
		if(array == null || array.length == 0) {
			return null;
		}
		
		Set<E> set = new HashSet<E>();
		for(E e : array) {
			if(e == null) {
				continue;
			}
			set.add(e);
		}
		return set;
	}
}
