package net.ktds.drink.admin.web.ctgr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.category.biz.CategoryBiz;
import net.ktds.drink.category.biz.CategoryBizImpl;
import net.ktds.drink.category.vo.CategoryVO;

public class ViewCategorySetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<CategoryVO> categories;;
	private CategoryBiz biz;
	public ViewCategorySetServlet() {
    	super();
    	biz = new CategoryBizImpl();
    	categories = new ArrayList<CategoryVO>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categories = biz.setCategoryLevel();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/administer/categorySet.jsp");
		request.setAttribute("categories", categories);
		rd.forward(request, response);
	}

}
