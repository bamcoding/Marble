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
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;


public class ViewSetGamesPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GamesBiz biz;
	private List<GamesVO> games;

    public ViewSetGamesPageServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/view/game/setGames.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);

		games = biz.allGetGames();
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("10");
		List<CategoryVO> categories = biz.getCategory(categoryVO);
		
		request.setAttribute("games", games);
		request.setAttribute("categories", categories);

		   
		rd.forward(request, response);
		
	
		
	}

}
