package net.ktds.drink.boards.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.MultipartHttpServletRequest;
import net.ktds.drink.support.MultipartHttpServletRequest.MultipartFile;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.biz.UserBiz;
import net.ktds.drink.user.biz.UserBizImpl;
import net.ktds.drink.user.vo.UserVO;

public class DoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz;
	private UserBiz userBiz;
       
    public DoWriteServlet() {
        super();
        boardBiz = new BoardBizImpl();
        userBiz = new UserBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		
		String boardSubject = multipartRequest.getParameter("boardSubject");
		String boardContent = multipartRequest.getParameter("boardContent");
		String categoryId = "categoryName";
		
		String fileName = "";
		MultipartFile uploadFile = multipartRequest.getFile("file");
		if ( uploadFile.getFileSize() > 0 ) {
			File uploadFileDirectory = new File( "D:\\board\\uploadfiles" );
			if ( !uploadFileDirectory.exists() ){
				uploadFileDirectory.mkdirs();
			}
			uploadFile.write("D:\\board\\uplaodfiles\\" + uploadFile.getFileName());
			fileName = uploadFile.getFileName();
		}
		
		boardContent = boardContent.replaceAll("\n", "<br/>")
									.replaceAll("\r", "");
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute(Session.USER_INFO);
		
		BoardVO board = new BoardVO();
		board.setBoardSubject(boardSubject);
		board.setBoardContent(boardContent);
		board.setUserId(userVO.getUserId());
		board.setFileName(fileName);
		board.setCategoryId(categoryId);
		
		boolean isSuccess = boardBiz.addBoard(board);

		if (isSuccess) {
			response.sendRedirect("/Mables/board/list");
		} else {
			response.sendRedirect("/Mables/board/write?errorCode=1");
		}
	}
}
