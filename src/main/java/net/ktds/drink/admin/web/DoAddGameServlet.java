package net.ktds.drink.admin.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.MultipartHttpServletRequest;
import net.ktds.drink.support.MultipartHttpServletRequest.MultipartFile;
import net.ktds.drink.support.Param;


public class DoAddGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;   
 
    public DoAddGameServlet() {
        super();
        biz = new GamesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		
		
		String gameName = multipartRequest.getParameter("gameName");
		String gameInfo = multipartRequest.getParameter("gameInfo");
		String categoryId = multipartRequest.getParameter("categoryId");
		
		String detailImage = "";
		String cellImage = "";
		
		MultipartFile uploadFile = multipartRequest.getFile("detailImage");
		MultipartFile uploadFile2 = multipartRequest.getFile("cellImage");
		
		if ( uploadFile.getFileSize() > 0 ) {
			// 이미지 업로드할 폴더 생성
				File uploadFileDirectory = new File("D:\\detail\\uploadfiles\\");
				if( !uploadFileDirectory.exists() ) {
					//폴더가 없다면...만들어라
					uploadFileDirectory.mkdir();
				}
						//디드라이브에 업로드한 파일의 이름으로 파일을 써라.
			uploadFile.write("D:\\detail\\uploadfiles\\" + uploadFile.getFileName());
			detailImage = uploadFile.getFileName();
			
		}
		
		if ( uploadFile2.getFileSize() > 0 ) {
			// 이미지 업로드할 폴더 생성
			File uploadFileDirectory2 = new File("D:\\cell\\uploadfiles\\");
			if( !uploadFileDirectory2.exists() ) {
				//폴더가 없다면...만들어라
				uploadFileDirectory2.mkdir();
			}
			//디드라이브에 업로드한 파일의 이름으로 파일을 써라.
			uploadFile2.write("D:\\cell\\uploadfiles\\" + uploadFile2.getFileName());
			cellImage = uploadFile2.getFileName();	
		}
		
		
		
		
		if( gameName.length() == 0){
			response.sendRedirect("/Marble/admin/addGame?errorCode=1");
			return;
		}
		if(gameInfo.length() == 0){
			response.sendRedirect("/Marble/admin/addGame?errorCode=1");
			return;
		}
	

		
		gameInfo = gameInfo.replace("\n", "<br/>").replaceAll("\r", " ");
		
		GamesVO gamesVO = new GamesVO();
		gamesVO.setGameName(gameName);
		gamesVO.setGameInfo(gameInfo);
		gamesVO.setCategoryId(categoryId);
		gamesVO.setDetailImage(detailImage);
		gamesVO.setCellImage(cellImage);

		if( gamesVO.getCategoryId().equals("카테고리를 선택해주세요") ) {
			response.sendRedirect("/Marble/admin/addGame?errorCode=1");
			return;
		}
		else {
			 biz.addGame(gamesVO);
			response.sendRedirect("/Marble/admin/gameList");
			
		}
		
	}

}
