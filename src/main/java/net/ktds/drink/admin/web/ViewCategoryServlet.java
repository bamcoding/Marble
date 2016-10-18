package net.ktds.drink.admin.web;

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

public class ViewCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryBiz biz;
	private List<CategoryVO> categories;;
	public ViewCategoryServlet() {
    	super();
    	biz = new CategoryBizImpl();
    	categories = new ArrayList<CategoryVO>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		String parentCategoryId = request.getParameter("parentCategoryId");
		if (categoryId == null || categoryId.length()==0) {
			categoryId = "0";
		}
		if (parentCategoryId == null && categoryId.length()==0) {
			parentCategoryId = "0";
		}

		boolean isLeafNode = biz.isCategoryLeafNode(categoryId);
		if (isLeafNode) {
			categories = biz.getAllCategory(parentCategoryId);
		} else {
			categories = biz.getAllCategory(categoryId);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/administer/categoryInfo.jsp");
		request.setAttribute("categories", categories);
		rd.forward(request, response);
	}

}
