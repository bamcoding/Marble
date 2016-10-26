package net.ktds.drink.admin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.DownloadUtil;
import net.ktds.drink.support.Param;


public class DoDownloadCellImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GamesBiz biz;    
 
    public DoDownloadCellImgServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameName = Param.getStringParam(request, "gameName");
		String cellImage = biz.getCellImageofGamesBy(gameName);
		
		DownloadUtil downloadUtil = DownloadUtil.getInstance("D:\\marble\\uploadfiles");
	       if(cellImage == null){
	    	   downloadUtil.download(request, response, "soccer.png" , "soccer.png");
	        }
	       else{
	    	   downloadUtil.download(request, response, cellImage , cellImage);
	        }
	}

}
