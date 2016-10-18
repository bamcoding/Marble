package net.ktds.drink.play.web.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;

public class GetGoldenCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GamesBiz gamesBiz;
       
    public GetGoldenCardServlet() {
        super();
        gamesBiz = new GamesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		List<GamesVO> goldenCards = gamesBiz.getGoldenCards();
		Random rnd = new Random();
		int number = rnd.nextInt(goldenCards.size());
		
		PrintWriter out = response.getWriter();
		out.write("<div>" + goldenCards.get(number).getGameName() +"</div>");
		out.write("<div>" + goldenCards.get(number).getGameInfo() + "</div>");
		out.flush();
		out.close();
		
	}

}
