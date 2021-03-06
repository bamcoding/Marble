package net.ktds.drink.boards.vo;

public class SearchBoardVO {

	private int pageNo;
	private int startRowNumber;
	private int endRowNumber;
	private String categoryId;
	
	private int searchType;
	private String searchKeyword;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
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
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
}
