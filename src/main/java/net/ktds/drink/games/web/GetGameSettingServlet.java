package net.ktds.drink.games.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.play.vo.PlayVO;

public class GetGameSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetGameSettingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<PlayVO> plays = (List<PlayVO>) session.getAttribute(Session.GAME_SETTING);

		PrintWriter out = response.getWriter();

		if (plays != null) {
			int playsSize = plays.size();
			for (int i = 0; i < playsSize; i++) {
				if (i != 0) {
					out.append(",");
				}
				out.append(plays.get(i).getGameId());
			}
		}

		out.flush();
		out.close();
	}
}
