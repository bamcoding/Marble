package net.ktds.drink.admin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.CustomListVO;
import net.ktds.drink.games.vo.GamesListVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.support.Param;
import net.ktds.drink.support.pager.ClassicPageExplorer;
import net.ktds.drink.support.pager.PageExplorer;


public class ViewCustomListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GamesBiz biz;  

    public ViewCustomListPageServlet() {
        super();
        biz = new GamesBizImpl();
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
                    session.getAttribute(Session.SEARCH_CUSTOM_INFO);
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
        
        session.setAttribute(Session.SEARCH_CUSTOM_INFO, searchGame);

		String viewPath = "/WEB-INF/view/administer/customList.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(viewPath);

	    CustomListVO dummyGames = biz.getCustomGames(searchGame);

		request.setAttribute("customs", dummyGames.getCustom());
		request.setAttribute("pager", dummyGames.getPager());
		    
		PageExplorer pageExplorer = 
		        new ClassicPageExplorer(dummyGames.getPager());
		String pager = pageExplorer.getPagingList("pageNo", "@", "이전", "다음", "searchForm");

		request.setAttribute("paging", pager);
		request.setAttribute("searchGame", searchGame);
		
		rd.forward(request, response);
		
		
	}

}
