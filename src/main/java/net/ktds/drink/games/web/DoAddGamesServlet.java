package net.ktds.drink.games.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.vo.UserVO;


public class DoAddGamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;

    public DoAddGamesServlet() {
        super();
        biz = new GamesBizImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameName = Param.getStringParam(request, "gameName");
		String gameInfo = Param.getStringParam(request, "gameInfo");
		
		gameInfo = gameInfo.replace("\n", "<br/>").replaceAll("\r", " ");
		
		HttpSession session =  request.getSession();
		UserVO userInfo = (UserVO) session.getAttribute(Session.USER_INFO);
		
		GamesVO gamesVO = new GamesVO();
		gamesVO.setGameName(gameName);
		gamesVO.setGameInfo(gameInfo);
		gamesVO.setCategoryId("16");
		biz.addGame(gamesVO);
		
		GamesVO gameVO =  biz.getGameBy(gameName);
		
		
		CustomVO customVO = new CustomVO();
		customVO.setGamesVO(gameVO);
		customVO.setUserVO(userInfo);
		
		
		biz.addCustom(gameVO, userInfo);
		
	
	}

}
