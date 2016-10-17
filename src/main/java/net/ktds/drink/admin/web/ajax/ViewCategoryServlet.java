package net.ktds.drink.admin.web.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.vo.CategoryVO;

public class ViewCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<CategoryVO> categories;
    private CategoryVO categoryVO;
    public ViewCategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/administer/categoryInfo.jsp");
		rd.forward(request, response);
	}

}
