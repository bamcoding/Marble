package net.ktds.drink.admin.web.history;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.HistoryBiz;
import net.ktds.drink.admin.biz.HistoryBizImpl;
import net.ktds.drink.support.Param;


public class DoDeleteHistoryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HistoryBiz biz;      
    
    public DoDeleteHistoryDetailServlet() {
        super();
        biz = new HistoryBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String playInfoId = Param.getStringParam(request, "playInfoId");
		
		boolean isSuccess = biz.deleteHistory(playInfoId);
		
		if ( isSuccess ) {
			response.sendRedirect("/Marble/admin/historyList");
		}
		
	}

}
