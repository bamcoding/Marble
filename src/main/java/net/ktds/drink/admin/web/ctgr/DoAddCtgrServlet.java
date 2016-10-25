package net.ktds.drink.admin.web.ctgr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.category.biz.CategoryBiz;
import net.ktds.drink.category.biz.CategoryBizImpl;
import net.ktds.drink.category.vo.CategoryVO;
import net.ktds.drink.support.Param;

public class DoAddCtgrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Param param = new Param();
    CategoryBiz biz;
    public DoAddCtgrServlet() {
        super();
        biz = new CategoryBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = param.getStringParam(request, "ctgr_input");
		String categoryId = param.getStringParam(request, "categoryId");
		
		categoryId = categoryId.substring(4);
		
		CategoryVO categoryVO = new CategoryVO();
		String categorySeq = biz.getNewCategoryId()+"";
		
		categoryVO.setCategoryId(categorySeq);
		categoryVO.setCategoryName(name);
		categoryVO.setParentCategoryId(categoryId);
		
		boolean isExist = biz.checkExistName(name);
		boolean isTrue = false;
		
		if(!isExist){
			isTrue = biz.addCategory(categoryVO);
		}
		PrintWriter out = response.getWriter();
		if(isTrue){
			out.write(categorySeq+"");			
		}else{
			out.write(isTrue+"");						
		}
		out.flush();
		out.close();
	}

}
