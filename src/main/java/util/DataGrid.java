package util;

import java.util.List;

/**
 * 
 *<p>Title	: DataGrid</p>
 * @Description	: 分页数据传输封装类
 * @author	: admin
 * @date	: 2017年11月22日上午9:36:48
 * @param <T>
 */
public class DataGrid<T> {
	private int totalCount;//总条数
	private List<T> data;//数据

	public DataGrid() {
	}

	public DataGrid(int totalCount, List<T> data) {
		this.totalCount = totalCount;
		this.data = data;
	}

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

}
