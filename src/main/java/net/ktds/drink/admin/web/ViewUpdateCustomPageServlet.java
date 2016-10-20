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
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;

public class ViewUpdateCustomPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;      

    public ViewUpdateCustomPageServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/view/administer/updateCustom.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		
		String gameId = Param.getStringParam(request, "gameId");
		CustomVO customVO = biz.getCustomDetailBy(gameId);

		
		String gameName = customVO.getGamesVO().getGameName();
		
		String gameInfo = customVO.getGamesVO().getGameInfo();
		
		gameInfo = gameInfo.replaceAll("<br/>", "\n");
		gameInfo = gameInfo.toString();
		
		GamesVO game = new GamesVO();
		game.setGameInfo(gameInfo);
		game.setGameName(gameName);
		customVO.setGamesVO(game);
	
		request.setAttribute("customVO", customVO);
		request.setAttribute("gameName", gameName);
		
		rd.forward(request, response);
	}

}
