package net.ktds.drink.games.web;

import java.io.IOException;

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
		
		
		
		biz.addGame(gameName, gameInfo);
		
		GamesVO gameVO =  biz.getGameBy(gameName, gameInfo);
		gameVO.getGameName();
		gameVO.setGameInfo(gameInfo);
		
		
		CustomVO customVO = new CustomVO();
		customVO.setGamesVO(gameVO);
		customVO.setUserVO(userInfo);
		
		System.out.println("ddd"+gameVO.getGameName());
		System.out.println("qwe"+userInfo.getUserNickname());
		
		biz.addCustom(gameVO, userInfo);
		

		
	
	}

}
