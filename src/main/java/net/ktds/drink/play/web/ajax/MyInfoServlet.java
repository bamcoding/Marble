package net.ktds.drink.play.web.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.play.biz.PlayBiz;
import net.ktds.drink.play.biz.PlayBizImpl;
import net.ktds.drink.play.vo.HistoryVO;
import net.ktds.drink.user.vo.UserVO;

public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PlayBiz playBiz;
	
    public MyInfoServlet() {
        super();
        playBiz = new PlayBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		List<HistoryVO> history = (List<HistoryVO>) playBiz.getHistory(request);
		
		String viewPath = "/WEB-INF/view/play/myInfo.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		
		request.setAttribute("history", history);
		
		rd.forward(request, response);
		
	}

}
