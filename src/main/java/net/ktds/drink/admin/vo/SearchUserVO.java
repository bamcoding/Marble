package net.ktds.drink.admin.vo;

public class SearchUserVO {
	
	private int pageNumber;
	private int startRowNumber;
	private int endRowNumber;
	private int searchType;
	private String searchKeyword;
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getStartRowNumber() {
		return startRowNumber;
	}
	public void setStartRowNumber(int startRowNumber) {
		this.startRowNumber = startRowNumber;
	}
	public int getEndRowNumber() {
		return endRowNumber;
	}
	public void setEndRowNumber(int endRowNumber) {
		this.endRowNumber = endRowNumber;
	}
	public int getSearchType() {
		return searchType;
	}
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
}
