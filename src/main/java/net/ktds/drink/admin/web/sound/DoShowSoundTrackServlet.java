package net.ktds.drink.admin.web.sound;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.AdminBiz;
import net.ktds.drink.admin.biz.AdminBizImpl;
import net.ktds.drink.support.Param;
import net.ktds.drink.support.SoundStreamingUtil;

public class DoShowSoundTrackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz;
	
    public DoShowSoundTrackServlet() {
        super();
        adminBiz = new AdminBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String soundTrackId = Param.getStringParam(request, "soundTrackId");
		String fileName = adminBiz.getFileNameOfSoundTrackBy(soundTrackId);
		
        if( fileName != null && fileName.length() > 0 ){
        	
        	SoundStreamingUtil downloadUtil = SoundStreamingUtil.getInstance("D:\\board\\uploadfiles");
            
            downloadUtil.download(request, response, fileName, fileName);
        }
	}

}
