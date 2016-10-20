package net.ktds.drink.admin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;


public class DoAddGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;   
 
    public DoAddGameServlet() {
        super();
        biz = new GamesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameName = Param.getStringParam(request, "gameName");
		String gameInfo = Param.getStringParam(request, "gameInfo");
		String categoryId = Param.getStringParam(request, "categoryId");
		
		
		if( gameName.length() == 0){
			response.sendRedirect("/Marble/admin/addGame?errorCode=1");
			return;
		}
		if(gameInfo.length() == 0){
			response.sendRedirect("/Marble/admin/addGame?errorCode=1");
			return;
		}
		boolean isExsit = biz.isExsistGameName(gameName);
		if ( !isExsit ) {
			response.sendRedirect("/Marble/admin/addGame?errorCode=3");
		}

		
		gameInfo = gameInfo.replace("\n", "<br/>").replaceAll("\r", " ");
		
		GamesVO gamesVO = new GamesVO();
		gamesVO.setGameName(gameName);
		gamesVO.setGameInfo(gameInfo);
		gamesVO.setCategoryId(categoryId);
	
		if( gamesVO.getCategoryId().equals("카테고리를 선택해주세요") ) {
			response.sendRedirect("/Marble/admin/addGame?errorCode=1");
			return;
		}
		else {
			biz.addGame(gamesVO);
		}
		
	}

}