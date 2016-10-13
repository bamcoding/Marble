package net.ktds.drink.support.pager;

public abstract class Pager {
	
	public static final boolean ORACLE = true;
	public static final boolean OTHER = false;
	
	private int totalArticleCount;

	protected int printArticle;
	int printPage;

	protected int startArticleNumber;
	protected int endArticleNumber;

	int totalPage;
	int totalGroup;

	int nowGroupNumber;

	int groupStartPage;

	int nextGroupPageNumber;
	int prevGroupPageNumber;

	protected int pageNo;
	
	/**
	 * Paging ê°ì²´ë¥? ?–»?–´?˜¨?‹¤.
	 * ?•œ ?˜?´ì§??‹¹ ë³´ì—¬ì§??Š” ê²Œì‹œê¸? ?ˆ˜ 10ê°?
	 * ?•œ ?˜?´ì§??‹¹ ë³´ì—¬ì§??Š” ?˜?´ì§? ?ˆ˜ 10ê°?
	 * ë¡? ê¸°ë³¸ ?„¤? •?¨.
	 */
	public Pager() {
		this.printArticle = 10;
		this.printPage = 10;
	}
	
	public Pager(int printArticle, int printPage) {
		this.printArticle = printArticle;
		this.printPage = printPage;
	}
	
	public void setPageNumber(int pageNumber) {
		setPageNumber(pageNumber + "");
	}
	
	/**
	 * ?š”ì²??œ ?˜?´ì§??˜ ë²ˆí˜¸ë¥? ?–»?–´?˜¨?‹¤.
	 * 1 ?˜?´ì§??˜ ê²½ìš° 0?´ ?…? ¥?œ?‹¤.
	 * ?•„ë¬´ê²ƒ?„ ?…? ¥?•˜ì§? ?•Š?•˜?‹¤ë©? 0?œ¼ë¡? ê¸°ë³¸ ?„¤? •?œ?‹¤.
	 * @param pageNumber
	 */
	public void setPageNumber(String pageNumber) {
		this.pageNo = 0;
		try {
			this.pageNo = Integer.parseInt(pageNumber);
		} catch (NumberFormatException nfe) {
			this.pageNo = 0;
		}

		computeArticleNumbers();
		
		this.nowGroupNumber = this.pageNo / this.printPage;
		this.groupStartPage = (this.nowGroupNumber * this.printPage) + 1;

		this.nextGroupPageNumber = this.groupStartPage + this.printPage - 1;
		this.prevGroupPageNumber = this.groupStartPage - this.printPage - 1;
	}
	
	protected abstract void computeArticleNumbers();
	
	/**
	 * ì¡°íšŒ?•˜? ¤?Š” ì¡°ê±´(Query)?˜ ì´? ê²Œì‹œë¬? ?ˆ˜ë¥? ? •?˜?•œ?‹¤.
	 * @param count
	 */
	public void setTotalArticleCount(int count) {
		this.totalArticleCount = count;

		this.totalPage = (int) Math.ceil((double) this.totalArticleCount
				/ this.printArticle);
		this.totalGroup = (int) Math.ceil((double) this.totalPage
				/ this.printPage);
	}
	
	/**
	 * ì¡°íšŒ?•˜? ¤?Š” ì¡°ê±´(Query)?˜ ì´? ê²Œì‹œë¬? ?ˆ˜ë¥? ê°?? ¸?˜¨?‹¤.
	 * @return
	 */
	public int getTotalArticleCount() {
		return this.totalArticleCount;
	}

	/**
	 * Query?—?„œ ?‚¬?š©?  ?ƒ?ƒ‰ ?‹œ?‘ ë²ˆí˜¸ 
	 * Oracle?˜ ê²½ìš° rownum?˜ ?‹œ?‘ ë²ˆí˜¸
	 * @return
	 */
	public int getStartArticleNumber() {
		return this.startArticleNumber;
	}
	
	/**
	 * Query?—?„œ ?‚¬?š©?  ?ƒ?ƒ‰ ?‹œ?‘ ë²ˆí˜¸ë¥? ? •?˜?•œ?‹¤.
	 * @param startArticleNumber
	 */
	public void setStartArticleNumber(int startArticleNumber) {
		this.startArticleNumber = startArticleNumber;
	}
	
	/**
	 * Query?—?„œ ?‚¬?š©?  ?ƒ?ƒ‰ ? ë²ˆí˜¸ë¥? ? •?˜?•œ?‹¤.
	 * @param endArticleNumber
	 */
	public abstract void setEndArticleNumber(int endArticleNumber);

	/**
	 * Query?—?„œ ?‚¬?š©?  ?ƒ?ƒ‰ ë§ˆì?ë§? ë²ˆí˜¸
	 * Oracle?˜ ê²½ìš° rownum?˜ ë§ˆì?ë§? ë²ˆí˜¸
	 * @return
	 */
	public abstract int getEndArticleNumber();
	
}
