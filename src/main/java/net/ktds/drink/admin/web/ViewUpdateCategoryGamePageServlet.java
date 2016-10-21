package net.ktds.drink.admin.web;

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


public class ViewUpdateCategoryGamePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;
    

    public ViewUpdateCategoryGamePageServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/view/administer/updateCategoryGame.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		
		String gameId = Param.getStringParam(request, "gameId");
		String categoryId = Param.getStringParam(request, "categoryId");
		
		GamesVO gamesVO = biz.getGameDetailBy(gameId);
		String categoryName = gamesVO.getCategoryVO().getCategoryName();
		
		
		CategoryVO categoryVO = new CategoryVO();
		//부모 카테고리 = 게임 
		categoryVO.setParentCategoryId("10");
		List<CategoryVO> categories = biz.getAdminCategory(categoryVO);
		
		String gameInfo = gamesVO.getGameInfo();
		gameInfo = gameInfo.replaceAll("<br/>", "\n");
		gameInfo = gameInfo.toString();
		gamesVO.setGameInfo(gameInfo);
		
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("gamesVO", gamesVO);
		request.setAttribute("categories", categories);
		request.setAttribute("categoryName", categoryName);
		
		rd.forward(request, response);
	}

}
