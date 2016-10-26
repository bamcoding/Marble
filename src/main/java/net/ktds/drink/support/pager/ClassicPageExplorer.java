package net.ktds.drink.support.pager;

public class ClassicPageExplorer implements PageExplorer {

	private Pager pager;
	
	public ClassicPageExplorer(Pager pager) {
		this.pager = pager;
	}
	
	/**
	 * JSP?��?�� Paging 결과�? 보여�??��.
	 * getPagingList?�� &lt;form> ?���? ?��?�� ?��?��?��?��?�� ?��?��.
	 * @param link Page 번호�? ?��?��?�� Parameter Name
	 * @param pageFormat Page 번호�? 보여�? ?��?�� @(at)�? ?��?���? 번호�? 치환?��?��. [@]�? ?��?��?�� 경우 [1] [2] [3] 처럼 보여진다.
	 * @param prev ?��?�� ?��?���? 그룹?���? �??�� 버튼?�� ?��름을 ?��?��?��?��.
	 * @param next ?��?�� ?��?���? 그룹?���? �??�� 버튼?�� ?��름을 ?��?��?��?��.
	 * @param formId ?��버에�? ?��?��?�� Form ?�� ?��?��?���? ?��?��?��?��.
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
		buffer.append("<ul class='pagination'>");
		
		if (pager.nowGroupNumber > 0) {
			buffer.append("<li><a href=\"javascript:movePage('"+pager.prevGroupPageNumber+"')\">" + prev + "</a></li>");
		}

		int nextPrintPage = pager.groupStartPage + pager.printPage;
		if (nextPrintPage > pager.totalPage) {
			nextPrintPage = pager.totalPage + 1;
		}

		String pageNumber = "";

		for (int i = pager.groupStartPage; i < nextPrintPage; i++) {
			pageNumber = pageFormat.replaceAll("@", i + "");
			if ((i - 1) == pager.pageNo) {
//				pageNumber = "<b>" + pageNumber + "</b>";
				buffer.append("<li><a class='active' href=\"javascript:movePage('"+(i - 1)+"')\">" + pageNumber + "</a></li>");
			}else{
				buffer.append("<li><a href=\"javascript:movePage('"+(i - 1)+"')\">" + pageNumber + "</a></li>");
			}
		}

		if (pager.nowGroupNumber < (pager.totalGroup - 1)) {
			buffer.append("<li><a href=\"javascript:movePage('"+pager.nextGroupPageNumber+"')\">" + next + "</a></li>");
		}
		
		buffer.append("</ul>");

		return buffer.toString();
	}
	
}
