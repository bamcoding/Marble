package net.ktds.drink.games.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;


public class SearchTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GamesBiz biz;
       

    public SearchTypeServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categoryId = Param.getStringParam(request, "categoryId");
		
		GamesVO gamesVO = new GamesVO();
		gamesVO.setCategoryId(categoryId);
		
		List<GamesVO> games = biz.getGames(gamesVO);
		
		StringBuffer options = new StringBuffer();
		
		for (GamesVO game : games) {
			options.append(String.format("%s", game.getGameId(), game.getGameName() ));
		}
		
		PrintWriter out = response.getWriter();
		out.write(options.toString());
		out.flush();
		out.close();
	}
}


