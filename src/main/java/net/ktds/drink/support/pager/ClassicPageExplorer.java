package net.ktds.drink.support.pager;

public class ClassicPageExplorer implements PageExplorer {

	private Pager pager;
	
	public ClassicPageExplorer(Pager pager) {
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
		
		if (pager.nowGroupNumber > 0) {
			buffer.append("<a href=\"javascript:movePage('"+pager.prevGroupPageNumber+"')\">" + prev + "</a>");
		}

		int nextPrintPage = pager.groupStartPage + pager.printPage;
		if (nextPrintPage > pager.totalPage) {
			nextPrintPage = pager.totalPage + 1;
		}

		String pageNumber = "";

		for (int i = pager.groupStartPage; i < nextPrintPage; i++) {
			pageNumber = pageFormat.replaceAll("@", i + "");
			if ((i - 1) == pager.pageNo) {
				pageNumber = "<b>" + pageNumber + "</b>";
			}
			buffer.append("<a href=\"javascript:movePage('"+(i - 1)+"')\">" + pageNumber + "</a>");
		}

		if (pager.nowGroupNumber < (pager.totalGroup - 1)) {
			buffer.append("<a href=\"javascript:movePage('"+pager.nextGroupPageNumber+"')\">" + next + "</a>");
		}

		return buffer.toString();
	}
	
}
