package net.ktds.drink.play.web.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.play.biz.PlayBiz;
import net.ktds.drink.play.biz.PlayBizImpl;
import net.ktds.drink.play.vo.PlayVO;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.vo.UserVO;

public class ConfirmPlaysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PlayBiz playBiz;
       
    public ConfirmPlaysServlet() {
        super();
        playBiz = new PlayBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String playInfo = Param.getStringParam(request, "playInfo");
		
		List<PlayVO> plays = null;
		
		if(playInfo != null && playInfo != ""){
			System.out.println(playInfo);
			
			 plays = playBiz.getPlaysByPlayInfo(playInfo);
			
		}else{
			HttpSession session = request.getSession();
			plays = (List<PlayVO>) session.getAttribute(Session.GAME_SETTING);
		}
		
		String viewPath = "/WEB-INF/view/play/confirmPage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		request.setAttribute("plays", plays);
		request.setAttribute("playInfo", playInfo);
		rd.forward(request, response);

	}

}
