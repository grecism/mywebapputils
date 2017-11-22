package util;

import java.lang.reflect.Array;
import java.util.List;

public class ListUtil {

	private ListUtil() {
		super();
	}
	/**
	 * 
	 * @Description : List是否为空
	 * @param list
	 * @return list
	 */
	public static <E> boolean isEmpty(List<E> list) {
		if ((list == null) || (list.size() == 0)) {
			return true;
		}
		return false;

	}

	/**
	 * 
	 * @Description : list转化为数组
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> E[] toArray(List<E> list) {
		if (ListUtil.isEmpty(list)) {
			return null;
		}
		E notNullItem = null;
		boolean find = false;
		for (E e : list) {
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
			result = (E[]) Array.newInstance(notNullItem.getClass(), list.size());
			result = list.toArray(result);
		}
		return result;
	}
}
