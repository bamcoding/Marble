package net.ktds.drink.games.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
	private List<GamesVO> games;

	public SearchTypeServlet() {
		super();
		biz = new GamesBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String categoryId = Param.getStringParam(request, "categoryId");

		GamesVO gamesVO = new GamesVO();

		gamesVO.setCategoryId(categoryId);
		if (gamesVO.getCategoryId().equals("Category")) {
			games = biz.allGetGames();
		} else {
			games = biz.getGames(gamesVO);
		}

		String result = "";
		int gamesSize = games.size();
		for (int i = 0; i < gamesSize; i++) {
			result += games.get(i).getGameId();
			if (i != gamesSize-1) {
				result += ",";
			}
		}

		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}
}
