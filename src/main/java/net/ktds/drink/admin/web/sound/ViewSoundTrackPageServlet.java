package net.ktds.drink.admin.web.sound;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.admin.biz.AdminBiz;
import net.ktds.drink.admin.biz.AdminBizImpl;
import net.ktds.drink.admin.vo.AdvertisementListVO;
import net.ktds.drink.admin.vo.SearchAdvertisementVO;
import net.ktds.drink.admin.vo.SearchSoundTrackVO;
import net.ktds.drink.admin.vo.SoundTrackListVO;
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;
import net.ktds.drink.support.pager.ClassicPageExplorer;
import net.ktds.drink.support.pager.PageExplorer;

public class ViewSoundTrackPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz;
       
    public ViewSoundTrackPageServlet() {
        super();
        adminBiz = new AdminBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int pageNo = Param.getIntParam(request, "pageNo", -1);
		SearchSoundTrackVO searchSoundTrack = null;
		int searchType = Param.getIntParam(request, "searchType");
		
		String searchKeyword = Param.getStringParam(request, "searchKeyword");
		if ( pageNo == -1 ){
			searchSoundTrack = (SearchSoundTrackVO)session.getAttribute(Session.SEARCH_SOUNDTRACK_INFO);
			if ( searchSoundTrack == null){
				searchSoundTrack = new SearchSoundTrackVO();
				searchSoundTrack.setPageNumber(0);
			}
		}
		else {
			
			searchSoundTrack = new SearchSoundTrackVO();
			searchSoundTrack.setPageNumber(pageNo);
			searchSoundTrack.setSearchType(searchType);
			searchSoundTrack.setSearchKeyword(searchKeyword);
			
			
		}
		session.setAttribute(Session.SEARCH_SOUNDTRACK_INFO, searchSoundTrack);
		SoundTrackListVO soundTracks = adminBiz.getSoundTrack(searchSoundTrack);
		
		String viewPath = "/WEB-INF/view/administer/soundTrack.jsp";
		request.setAttribute("soundTracks", soundTracks.getSoundTracks());
		request.setAttribute("pager",soundTracks.getPager());
		PageExplorer pageExplorer = new ClassicPageExplorer(soundTracks.getPager());
		
		String pager = pageExplorer.getPagingList("pageNo", "@", "이전", "다음", "searchForm");
		request.setAttribute("paging", pager);
		request.setAttribute("searchSoundTrack",searchSoundTrack);
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		rd.forward(request, response);
	}

}
