package net.ktds.drink.admin.web.gameType;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.GameTypeBiz;
import net.ktds.drink.admin.biz.GameTypeBizImpl;
import net.ktds.drink.admin.vo.GameTypeVO;
import net.ktds.drink.support.Param;


public class ViewTypeDetailPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GameTypeBiz biz;
       

    public ViewTypeDetailPageServlet() {
        super();
        biz = new GameTypeBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/view/administer/typeDetail.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		
		String typeId = Param.getStringParam(request, "typeId");
		
		GameTypeVO typeVO = biz.getTypeAt(typeId);

		request.setAttribute("typeVO", typeVO);
		rd.forward(request, response);
		
		
	}

}
