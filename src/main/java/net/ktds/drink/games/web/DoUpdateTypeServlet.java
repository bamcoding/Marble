package net.ktds.drink.games.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.GameTypeBiz;
import net.ktds.drink.admin.biz.GameTypeBizImpl;
import net.ktds.drink.admin.vo.GameTypeVO;
import net.ktds.drink.support.Param;

public class DoUpdateTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GameTypeBiz biz;
	
    public DoUpdateTypeServlet() {
        super();
        biz = new GameTypeBizImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeId = Param.getStringParam(request, "typeId");
		String typeName = Param.getStringParam(request, "typeName");
		String typeInfo = Param.getStringParam(request, "typeInfo");
		
		if( typeName.length() == 0){
			response.sendRedirect("/Marble/admin/addType?errorCode=1");
			return;
		}
		if(typeInfo.length() == 0){
			response.sendRedirect("/Marble/admin/addType?errorCode=1");
			return;
		}
		boolean isExsit = biz.isExsistTypeName(typeName);
		if ( !isExsit ) {
			response.sendRedirect("/Marble/admin/addType?errorCode=3");
		}

		
		typeInfo = typeInfo.replace("\n", "<br/>").replaceAll("\r", " ");
		
		
		GameTypeVO typeVO = new GameTypeVO();
		typeVO.setTypeId(typeId);
		typeVO.setTypeName(typeName);
		typeVO.setTypeInfo(typeInfo);
		
		biz.updateType(typeVO);
		
	}

}
