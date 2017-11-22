package util;

import java.util.List;

/**
 * 
 *<p>Title	: Pager</p>
 * @Description	: 分页类
 * @author	: admin
 * @date	: 2017年11月22日上午9:38:24
 * @param <T>
 */
public class Pager<T> {
	private int page = 1;//默认起始页
	private int pageSize = 10;//默认每页条数
	@SuppressWarnings("unused")
	private int rowStartIndex;//起始行
	private int totalCount;//总条数
	@SuppressWarnings("unused")
	private int totalPage;//总页数
	private List<T> records;//记录
	
	public Pager(){
		
	}
	
	public Pager(int page, int pageSize){
	    this.page = page;
	    this.pageSize = pageSize;
	}
	
	public int getRowStartIndex() {
	    return (this.page - 1) * this.pageSize;
	}

	public void setRowStartIndex(int rowStartIndex) {
	    this.rowStartIndex = rowStartIndex;
	}


	
	public void setPage(int page) {
	    if (page > 0){
	    	this.page = page;
	    }
	}
	
	public int getPage() {
	    return this.page;
	}
	
	public int getPageSize(){
	    return this.pageSize;
	}
	
	public void setPageSize(int pageSize) {
	    if (pageSize > 0)
	      this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getTotalPage() {
		return (totalCount % pageSize)>0 ? totalCount/pageSize+1:totalCount/pageSize;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public List<T> getRecords() {
		return this.records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
}
