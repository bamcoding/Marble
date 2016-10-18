package net.ktds.drink.admin.web.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.category.biz.CategoryBiz;
import net.ktds.category.biz.CategoryBizImpl;
import net.ktds.category.vo.CategoryVO;

public class ViewCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<CategoryVO> categories;
    private CategoryBiz biz = new CategoryBizImpl();
    public ViewCategoryServlet() {
    	super();
    	categories = new ArrayList<CategoryVO>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categories = biz.getAllCategory(); 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/administer/categoryInfo.jsp");
		request.setAttribute("categories", categories);
		rd.forward(request, response);
	}

}
