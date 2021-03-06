package net.ktds.drink.admin.web.history;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.admin.biz.HistoryBiz;
import net.ktds.drink.admin.biz.HistoryBizImpl;
import net.ktds.drink.admin.vo.HistoryListVO;
import net.ktds.drink.constants.Session;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.support.Param;
import net.ktds.drink.support.pager.ClassicPageExplorer;
import net.ktds.drink.support.pager.PageExplorer;


public class ViewHistoryPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HistoryBiz biz;
	
    public ViewHistoryPageServlet() {
        super();
        biz = new HistoryBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
        int pageNo = Param.getIntParam(request, "pageNo", -1);
        SearchGamesVO searchGame = null;
        int searchType = Param.getIntParam(request, "searchType");
        String searchKeyword = Param.getStringParam(request, "searchKeyword");
		
		
        if ( pageNo == -1 ) { 
        	searchGame = (SearchGamesVO)
                    session.getAttribute(Session.SEARCH_HISTORY_INFO);
            if ( searchGame == null ) {
            	searchGame = new SearchGamesVO();
            	searchGame.setPageNumber(0);
            }
        }
        else { // 한 번이라도 검색을 했을 때
            
        	searchGame = new SearchGamesVO();
        	searchGame.setPageNumber(pageNo);
        	searchGame.setSearchType(searchType);
        	searchGame.setSearchKeyword(searchKeyword);
            
        }
        
        session.setAttribute(Session.SEARCH_HISTORY_INFO, searchGame);

		String viewPath = "/WEB-INF/view/administer/historyList.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(viewPath);
				
	    HistoryListVO dummyHistory = biz.getAllHistory(searchGame);

		
		request.setAttribute("histories", dummyHistory.getHistory());
		request.setAttribute("pager", dummyHistory.getPager());
		    
		PageExplorer pageExplorer = 
		        new ClassicPageExplorer(dummyHistory.getPager());
		String pager = pageExplorer.getPagingList("pageNo", "@", "이전", "다음", "searchForm");

		request.setAttribute("paging", pager);
		request.setAttribute("searchGame", searchGame);
		
		rd.forward(request, response);
	}	

}
