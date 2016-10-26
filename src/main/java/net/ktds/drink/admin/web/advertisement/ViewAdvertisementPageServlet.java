package net.ktds.drink.admin.web.advertisement;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.admin.biz.AdminBiz;
import net.ktds.drink.admin.biz.AdminBizImpl;
import net.ktds.drink.admin.vo.AdvertisementListVO;
import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.admin.vo.SearchAdvertisementVO;
import net.ktds.drink.admin.vo.SearchUserVO;
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;
import net.ktds.drink.support.pager.ClassicPageExplorer;
import net.ktds.drink.support.pager.PageExplorer;

public class ViewAdvertisementPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz;

	public ViewAdvertisementPageServlet() {
		super();
		adminBiz = new AdminBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int pageNo = Param.getIntParam(request, "pageNo", -1);
		SearchAdvertisementVO searchAdvertisement = null;
		int searchType = Param.getIntParam(request, "searchType");
		
		String searchKeyword = Param.getStringParam(request, "searchKeyword");
		if ( pageNo == -1 ){
			searchAdvertisement = (SearchAdvertisementVO)session.getAttribute(Session.SEARCH_ADVERTISEMENT_INFO);
			if ( searchAdvertisement == null){
				searchAdvertisement = new SearchAdvertisementVO();
				searchAdvertisement.setPageNumber(0);
			}
		}
		else {
			
			searchAdvertisement = new SearchAdvertisementVO();
			searchAdvertisement.setPageNumber(pageNo);
			searchAdvertisement.setSearchType(searchType);
			searchAdvertisement.setSearchKeyword(searchKeyword);
			
			
		}
		session.setAttribute(Session.SEARCH_ADVERTISEMENT_INFO, searchAdvertisement);
		AdvertisementListVO advertisements = adminBiz.getAdvertisementVideo(searchAdvertisement);
		
		String viewPath = "/WEB-INF/view/administer/advertisement.jsp";
		request.setAttribute("advertisements", advertisements.getAdvertisements());
		request.setAttribute("pager",advertisements.getPager());
		
		PageExplorer pageExplorer = new ClassicPageExplorer(advertisements.getPager());
		
		String pager = pageExplorer.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm");
		request.setAttribute("paging", pager);
		request.setAttribute("searchAdvertisement",searchAdvertisement);
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		rd.forward(request, response);
	}

}
