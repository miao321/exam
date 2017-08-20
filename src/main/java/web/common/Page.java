package web.common;

import java.util.List;

public class Page {
	private List data;    //需要显示的数据
	private int initialize;  //从数据库取数据的时候，决定从哪一条开始取
	private int pageSize;  //页面上显示的数据条数
	private int pageCount;  //分页的页数
	private int rowCount;   //数据库表的记录是总行数
	private int pageShow;   //当前页是哪一页
	
	public Page(){
		initialize = 0;  //从第一条开始取数据
		pageSize = 15;  //每页显示的条数默认为15条
		pageCount = 0;
		rowCount = 0;
	}
	
	public void accountPageCount() {
		//计算分页总数
		if(rowCount%pageSize > 0){
			pageCount = (rowCount-(rowCount%pageSize))/pageSize+1;
		}else{
			pageCount = rowCount/pageSize;
		}
	}
	
	public void accountInitialize(){
		//计算数据的初始值
		initialize = pageShow*pageSize-pageSize;
	}
	public Integer PageCount(){
		return new Integer(pageCount);
	}
	public int getPageCount(){
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public int getInitialize() {
		return initialize;
	}

	public void setInitialize(int initialize) {
		this.initialize = initialize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		accountPageCount();
	}

	public int getPageShow() {
		return pageShow;
	}

	public void setPageShow(int pageShow) {
		this.pageShow = pageShow;
		accountInitialize();
	}
	

}
