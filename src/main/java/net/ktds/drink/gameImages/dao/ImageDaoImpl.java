package net.ktds.drink.gameImages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.gameImages.vo.ImageVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;

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

	@Override
	public List<ImageVO> getAllImageList() {
		return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	");
				query.append(" IMG_ID, IMG_NM, MANAGER_ID,	");
				query.append(" IMG_DES, LATEST_MDFY_DT, IMG_TITLE	");
				query.append(" FROM DRINK.GAME_IMG	");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<ImageVO> images= new ArrayList<ImageVO>();
				ImageVO imageVO = null;
				while(rs.next()){
					imageVO = new ImageVO();
					imageVO.setImageId(rs.getString("IMG_ID"));
					imageVO.setImageName(rs.getString("IMG_NM"));
					imageVO.setManagerId(rs.getString("MANAGER_ID"));
					imageVO.setImageDescription(rs.getString("IMG_DES"));
					imageVO.setLatestModifyDate(rs.getString("LATEST_MDFY_DT"));
					imageVO.setImageTitle(rs.getString("IMG_TITLE"));
					images.add(imageVO);
				}
				return images;
			}
		});
	}

	@Override
	public int deleteImage(String imageId) {
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append("	DELETE	");
				query.append("	FROM	DRINK.GAME_IMG ");
				query.append("	WHERE	IMG_ID=? ");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, imageId);
				return pstmt;
			}
		});
	}
	
}
