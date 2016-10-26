package net.ktds.drink.admin.web.ajax;

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
import net.ktds.drink.games.vo.GamesListVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.support.Param;
import net.ktds.drink.support.pager.ClassicPageExplorer;
import net.ktds.drink.support.pager.PageExplorer;

public class ViewGameListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GamesBiz biz;

 
    public ViewGameListPageServlet() {
        super();
        biz = new GamesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		CategoryVO categoryVO = new CategoryVO();
		//부모 카테고리 = 게임 
		categoryVO.setParentCategoryId("10");
		List<CategoryVO> categories = biz.getAdminCategory(categoryVO);

		request.setAttribute("categories", categories);
		
		HttpSession session = request.getSession();
        int pageNo = Param.getIntParam(request, "pageNo", -1);
		
        SearchGamesVO searchGame = null;
        int searchType = Param.getIntParam(request, "searchType");
        String searchKeyword = Param.getStringParam(request, "searchKeyword");
		
		
        if ( pageNo == -1 ) { 
        	searchGame = (SearchGamesVO)
                    session.getAttribute(Session.SEARCH_GAME_INFO);
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
        
        session.setAttribute(Session.SEARCH_GAME_INFO, searchGame);

		String viewPath = "/WEB-INF/view/administer/gameList.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(viewPath);
	
	    GamesListVO dummyGames = biz.getAllGames(searchGame);

		
		request.setAttribute("games", dummyGames.getGames());
		request.setAttribute("pager", dummyGames.getPager());
		    
		PageExplorer pageExplorer = 
		        new ClassicPageExplorer(dummyGames.getPager());
		String pager = pageExplorer.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm");

		request.setAttribute("paging", pager);
		request.setAttribute("searchGame", searchGame);
		
		rd.forward(request, response);
	}

}
