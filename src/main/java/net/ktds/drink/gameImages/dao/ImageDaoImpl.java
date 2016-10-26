package net.ktds.drink.gameImages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import net.ktds.drink.gameImages.vo.ImageVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;

public class ImageDaoImpl extends DaoSupport implements ImageDao {
	
	public int upLoadImage(ImageVO image) {
		System.out.println("query3 : "+image.getImageDescription());
		System.out.println("query4 : "+image.getManagerId());
		
		return insert(new Query() {
			
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT INTO DRINK.GAME_IMG ( IMG_ID,														");
				query.append("   IMG_NM, IMG_TITLE, IMG_DES, 															");
				query.append("   MANAGER_ID, LATEST_MDFY_DT ) 												");
				query.append(" VALUES ( 'GI-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(IMG_ID_SEQ.NEXTVAL,6,0), 	");
				query.append("   ?, ?, ?, ?, SYSDATE ) 															");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, image.getImageName());
				pstmt.setString(2, image.getImageTitle());
				pstmt.setString(3, image.getImageDescription());
				pstmt.setString(4, image.getManagerId());
				return pstmt;
			}
		});
	}

	public List<ImageVO> downLoadImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
