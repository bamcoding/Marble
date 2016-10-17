package net.ktds.drink.boards.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.support.DownloadUtil;
import net.ktds.drink.support.Param;

public class DoDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz;
       
    public DoDownloadServlet() {
        super();
        this.boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardId = Param.getStringParam(request, "boardId");
		String fileName = boardBiz.getFileNameOfBoardBy(boardId);
		
		if ( fileName != null || fileName.length() >= 0 ) {
			
			DownloadUtil downloadUtil = DownloadUtil.getInstance("D:\\board\\uploadfiles");
			
			downloadUtil.download(request, response, fileName, fileName);
		}
	}

}
