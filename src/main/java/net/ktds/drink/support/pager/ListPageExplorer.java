package net.ktds.drink.support.pager;

public class ListPageExplorer implements PageExplorer {

	private Pager pager;
	
	public ListPageExplorer(Pager pager) {
		this.pager = pager;
	}
	
	/**
	 * JSP?—?„œ Paging ê²°ê³¼ë¥? ë³´ì—¬ì¤??‹¤.
	 * getPagingList?Š” &lt;form> ?ƒœê·? ?•ˆ?— ?‘?„±?˜?–´?•¼ ?•œ?‹¤.
	 * @param link Page ë²ˆí˜¸ë¥? ? „?‹¬?•  Parameter Name
	 * @param pageFormat Page ë²ˆí˜¸ë¥? ë³´ì—¬ì¤? ?Œ¨?„´ @(at)ê°? ?˜?´ì§? ë²ˆí˜¸ë¡? ì¹˜í™˜?œ?‹¤. [@]ë¡? ?‘?„±?•  ê²½ìš° [1] [2] [3] ì²˜ëŸ¼ ë³´ì—¬ì§„ë‹¤.
	 * @param prev ?´? „ ?˜?´ì§? ê·¸ë£¹?œ¼ë¡? ê°??Š” ë²„íŠ¼?˜ ?´ë¦„ì„ ?‘?„±?•œ?‹¤.
	 * @param next ?‹¤?Œ ?˜?´ì§? ê·¸ë£¹?œ¼ë¡? ê°??Š” ë²„íŠ¼?˜ ?´ë¦„ì„ ?‘?„±?•œ?‹¤.
	 * @param formId ?„œë²„ì—ê²? ? „?‹¬?  Form ?˜ ?•„?´?””ë¥? ?‘?„±?•œ?‹¤.
	 * @return
	 */
	public String getPagingList(String link, String pageFormat, String prev, String next, String formId) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("<script>");
		buffer.append("function movePage(pageNo) {");
		buffer.append("$(\"#"+link+"\").val(pageNo);");
		buffer.append("$(\"#"+formId+"\").attr('action', '');");
		buffer.append("$(\"#"+formId+"\").attr('method', 'post');");
		buffer.append("$(\"#"+formId+"\").submit();");
		buffer.append("}");
		buffer.append("</script>");
		
		buffer.append("<input type=\"hidden\" id=\""+link+"\" name=\""+link+"\" />");
		
		int centerPage = pager.printPage / 2;
		int startPageNumber = pager.pageNo - centerPage;
		if ( startPageNumber < 0 ) {
			startPageNumber = 0;
		}
		
		int endPageNumber = startPageNumber + pager.printPage;
		if ( endPageNumber > pager.totalPage ) {
			endPageNumber = pager.totalPage;
		}
		
		if ( endPageNumber - startPageNumber < pager.printPage ) {
			startPageNumber = startPageNumber - (pager.printPage - (endPageNumber - startPageNumber));
		}
		
		String pageNumber = "";
		
		if ( startPageNumber > 0 ) {
			buffer.append("<a href=\"javascript:movePage('" + (pager.pageNo - 1) + "')\">" + prev + "</a>");
		}
		
		for (int i = startPageNumber; i < endPageNumber; i++) {
			pageNumber = pageFormat.replaceAll("@", (i+1) + "");
			if (i == pager.pageNo) {
				pageNumber = "<b>" + pageNumber + "</b>";
			}
			
			buffer.append("<a href=\"javascript:movePage('" + i + "')\">" + pageNumber + "</a>");
		}
		
		if ( pager.pageNo < endPageNumber ) {
			buffer.append("<a href=\"javascript:movePage('" + (pager.pageNo + 1) + "')\">" + next + "</a>");
		}

		return buffer.toString();
	}
	
}
