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
import net.ktds.drink.support.Param;
import net.ktds.drink.support.MultipartHttpServletRequest.MultipartFile;


public class DoUpdateCategoryGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;       
 
    public DoUpdateCategoryGameServlet() {
        super();
        biz = new GamesBizImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		
		String gameId = multipartRequest.getParameter("gameId");
		String gameName = multipartRequest.getParameter("gameName");
		String gameInfo = multipartRequest.getParameter("gameInfo");
		String categoryId = multipartRequest.getParameter("categoryId");
		
		String detailDeleteBtn = multipartRequest.getParameter("detailDeleteBtn");
		String cellDeleteBtn = multipartRequest.getParameter("cellDeleteBtn");
		
		if( gameName.length() == 0){
			response.sendRedirect("/Marble/admin/addCategoryGame?errorCode=1");
			return;
		}
		if(gameInfo.length() == 0){
			response.sendRedirect("/Marble/admin/addCategoryGame?errorCode=1");
			return;
		}
		boolean isExsit = biz.isExsistGameName(gameName);
		if ( !isExsit ) {
			response.sendRedirect("/Marble/admin/addCategoryGame?errorCode=3");
		}

		
		gameInfo = gameInfo.replace("\n", "<br/>").replaceAll("\r", " ");
		
		GamesVO gamesVO = new GamesVO();
		gamesVO.setGameId(gameId);
		gamesVO.setGameName(gameName);
		gamesVO.setGameInfo(gameInfo);                                  
		gamesVO.setCategoryId(categoryId);
		
		if ( detailDeleteBtn != null && detailDeleteBtn.equals("delete"))  {
			String detailImage = biz.getDetailImageofGamesBy(gameName);
			File file = new File("D:\\detail\\uploadfiles\\" + detailImage);
			file.delete();
			
			gamesVO.setDetailImage("");
			
		}
		
		
		if ( cellDeleteBtn != null && cellDeleteBtn.equals("delete"))  {
			String cellImage = biz.getCellImageofGamesBy(gameName);
			File file = new File("D:\\cell\\uploadfiles\\" + cellImage);
			file.delete();
			
			gamesVO.setCellImage("");
			
		}
		
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
			String detailImage = uploadFile.getFileName();
			gamesVO.setDetailImage(detailImage);
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
			String cellImage = uploadFile2.getFileName();	
			gamesVO.setCellImage(cellImage);
		}
		
		biz.updateGame(gamesVO);
		response.sendRedirect("/Marble/admin/gameMenuList?categoryId="+categoryId);
	}

}
