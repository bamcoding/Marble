package net.ktds.drink.boards.web;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.UploadContext;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.support.MultipartHttpServletRequest;
import net.ktds.drink.support.MultipartHttpServletRequest.MultipartFile;

public class DoModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz;
       
    public DoModifyServlet() {
        super();
        boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		
		String boardId = multipartRequest.getParameter("boardId");
		String boardSubject = multipartRequest.getParameter("boardSubject");
		String boardContent = multipartRequest.getParameter("boardContent");
		String fileDeleteBtn = multipartRequest.getParameter("fileDeleteBtn");
		
		boardContent = boardContent.replaceAll("\n", "<br/>")
									.replaceAll("\r", "");
		

		BoardVO board = new BoardVO();
		board.setBoardId(boardId);
		board.setBoardSubject(boardSubject);
		board.setBoardContent(boardContent);
		
		if ( fileDeleteBtn != null && fileDeleteBtn.equals("delete")) {
			String fileName = boardBiz.getFileNameOfBoardBy(boardId);
			File file = new File("D:\\board\\uploadfiles\\" + fileName );
			file.delete();
			
			board.setFileName("");
		}
		
		MultipartFile uploadedFile = multipartRequest.getFile("file");
		if( uploadedFile.getFileSize() > 0 ) {
			
			File uploadedFileDirectory = new File("D:\\board\\uploadfiles");
			if( !uploadedFileDirectory.exists() ){
				uploadedFileDirectory.mkdirs();
			}
			
			uploadedFile.write("D:\\board\\uploadfiles\\" + uploadedFile.getFileName());
			String fileName = uploadedFile.getFileName();
			board.setFileName(fileName);
		}
		boolean isSuccess = boardBiz.updateBoard(board);
		if ( isSuccess ) {
			response.sendRedirect("/Mables/board/detail?boardId=" + boardId);
		}
		else{
			response.sendRedirect("/Mables/board/write?errorCode=1");
		}
	}
	

}
