package net.ktds.drink.admin.web.ajax;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.boards.vo.BoardListVO;
import net.ktds.drink.boards.vo.SearchBoardVO;
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;
import net.ktds.drink.support.pager.ClassicPageExplorer;
import net.ktds.drink.support.pager.PageExplorer;

public class ViewArticleInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardBiz boardBiz;
    public ViewArticleInfoServlet() {
        super();
        boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int pageNo = Param.getIntParam(request, "pageNo", -1);
		int searchType = Param.getIntParam(request, "searchType");
		String searchKeyword = Param.getStringParam(request, "searchKeyword");
		String categoryId = Param.getStringParam(request, "categoryId");
		
		
		SearchBoardVO searchBoard = null;
		if (pageNo == -1) {
			searchBoard = (SearchBoardVO)session.getAttribute(Session.SEARCH_INFO);
			if (searchBoard == null) {
				searchBoard = new SearchBoardVO();
				searchBoard.setPageNo(0);
			}
		}
		else {
			searchBoard = new SearchBoardVO();
			searchBoard.setPageNo(pageNo);
			searchBoard.setSearchType(searchType);
			searchBoard.setSearchKeyword(searchKeyword);
		}
		searchBoard.setCategoryId("12");
		
		session.setAttribute(Session.SEARCH_INFO, searchBoard);
		BoardListVO boardList = boardBiz.getBoardListsOf(searchBoard);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/administer/articleInfo.jsp");
		request.setAttribute("boards", boardList.getBoardLists());
		request.setAttribute("pager", boardList.getPager());
		request.setAttribute("categoryId", categoryId);
		PageExplorer page = new ClassicPageExplorer(boardList.getPager());
		String pager = page.getPagingList("pageNo", "[@]", "<< prev", "next >>", "searchForm");
		
		request.setAttribute("paging", pager);
		request.setAttribute("searchBoard", searchBoard);
		
		rd.forward(request, response);
	}

}
