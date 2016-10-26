package net.ktds.drink.admin.web.history;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.HistoryBiz;
import net.ktds.drink.admin.biz.HistoryBizImpl;
import net.ktds.drink.admin.vo.GameSetVO;
import net.ktds.drink.support.Param;

public class ViewHistoryDetailPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HistoryBiz biz;
  
    public ViewHistoryDetailPageServlet() {
        super();
        biz = new HistoryBizImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String viewPath = "/WEB-INF/view/administer/historyDetail.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		
		String playInfoId = Param.getStringParam(request, "playInfoId");
		
		GameSetVO gameSetVO = biz.getGameSetAt(playInfoId);
		List<GameSetVO> gameSetList = biz.getAllGameSet(playInfoId);

		request.setAttribute("gameSetList", gameSetList);
		request.setAttribute("gameSetVO", gameSetVO);
		rd.forward(request, response);
		
	}

}
