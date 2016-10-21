package net.ktds.drink.games.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.play.vo.PlayVO;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.vo.UserVO;


public class DoSetGamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private GamesBiz gamesBiz;

    public DoSetGamesServlet() {
        super();
        gamesBiz = new GamesBizImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String games = Param.getStringParam(request, "games");
		String[] gamesArr = games.split(",");
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute(Session.USER_INFO);
		String userId = null;
		if(user != null){
			userId = user.getUserId();
		}else{
			userId = "anonymous";
		}
		
		
		List<PlayVO> plays = new ArrayList<PlayVO>();
		PlayVO play = null;
		GamesVO game = null;
		
		for(int i=0; i<gamesArr.length; i++){
			play = new PlayVO();
			play.setUserId(userId);
			game = gamesBiz.getGame(gamesArr[i]);
			play.setGameId(game.getGameId());
			play.setGames(game);
			plays.add(play);
		}
		
		if(session.getAttribute(Session.GAME_SETTING) != null){
			session.removeAttribute(Session.GAME_SETTING);
		}
		
		session.setAttribute(Session.GAME_SETTING, plays);
		
		PrintWriter out = response.getWriter();
		for(int i=0; i<plays.size(); i++){
			out.write(plays.get(i).getGames().getGameName()+"\n\r");
		}
		out.write("셋팅되었습니다.");
		out.flush();
		out.close();
		
		
		
	}

}
