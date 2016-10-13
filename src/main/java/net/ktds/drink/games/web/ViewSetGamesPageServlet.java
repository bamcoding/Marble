package net.ktds.drink.games.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.CategoryVO;


public class ViewSetGamesPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GamesBiz biz;

    public ViewSetGamesPageServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* SearchGamesVO searchGames = new SearchGamesVO();
	     int searchType = Param.getIntParam(request, "searchType");
	     searchGames.setSearchType(searchType);
	     
	     GamesListVO dummyGames = biz.getAllArticles(searchGames);*/
		
		String viewPath = "/WEB-INF/view/game/setGames.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId("5");
		List<CategoryVO> categories = biz.getCategory(categoryVO);
		

		request.setAttribute("categoryVO", categoryVO);
/*		request.setAttribute("games", dummyGames.getGames());
		request.setAttribute("searchGames", searchGames);*/
		   
		rd.forward(request, response);
		
	
		
	}

}
