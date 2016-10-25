package net.ktds.drink.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;

public class AdminDaoImpl extends DaoSupport implements AdminDao {

	@Override
	public int addAdvertisement(AdvertisementVO advertisementVO) {
		return (int) insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT INTO AD ( ");
				query.append("  			AD_ID ");
				query.append("  			, CNTRCT_DT ");
				query.append("  			, EXPRTN_DT ");
				query.append("  			, FILE_PTH  ");
				query.append("				, FILE_NM ) ");
				query.append(" VALUES (  ");
				query.append("  	   'AD-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(AD_ID_SEQ.NEXTVAL,6,0) ");
				query.append(" 		   , ? ");
				query.append(" 		   , ? ");
				query.append(" 		   , ? ");
				query.append(" 		   , ? ) ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, advertisementVO.getContractDate());
				pstmt.setString(2, advertisementVO.getExpirationDate());
				pstmt.setString(3, advertisementVO.getFilePath());
				pstmt.setString(4, advertisementVO.getFileName());
				return pstmt;
			}
			
		});
	}

	@Override
	public List<AdvertisementVO> getAdvertisementVideo() {
		List<AdvertisementVO> advertisements =  (List<AdvertisementVO>) selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT 		AD_ID ");
				query.append("  			, TO_CHAR( CNTRCT_DT, 'YYYY-MM-DD') CNTRCT_DT  ");
				query.append("  			, TO_CHAR( EXPRTN_DT, 'YYYY-MM-DD') EXPRTN_DT  ");
				query.append("  			, FILE_PTH  ");
				query.append("				, FILE_NM ");
				query.append(" FROM 		AD ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				
				List<AdvertisementVO> advertisements = new ArrayList<AdvertisementVO>();
				AdvertisementVO advertisement = null;
				while ( rs.next() ){
					advertisement = new AdvertisementVO();
					advertisement.setAdvertisementId(rs.getString("AD_ID"));
					advertisement.setContractDate(rs.getString("CNTRCT_DT"));
					advertisement.setExpirationDate(rs.getString("EXPRTN_DT"));
					advertisement.setFilePath(rs.getString("FILE_PTH"));
					advertisement.setFileName(rs.getString("FILE_NM"));
					advertisements.add(advertisement);
				}
				return advertisements;
			}
		});
		return advertisements;
	}

	@Override
	public AdvertisementVO getFileNameofAdvertisementBy(String advertisementId) {
		return (AdvertisementVO)selectOne(new QueryAndResult(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT AD_ID ");
				query.append(" 		  , CNTRCT_DT ");
				query.append(" 		  , EXPRTN_DT ");
				query.append("		  , FILE_PTH ");
				query.append(" 		  , FILE_NM ");
				query.append(" FROM  AD ");
				query.append(" WHERE AD_ID = ? ");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, advertisementId);
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				AdvertisementVO advertisement = null;
				if(rs.next()){
					advertisement = new AdvertisementVO();
					advertisement.setAdvertisementId(rs.getString("AD_ID"));
					advertisement.setContractDate(rs.getString("CNTRCT_DT"));
					advertisement.setExpirationDate(rs.getString("EXPRTN_DT"));
					advertisement.setFileName(rs.getString("FILE_NM"));
					advertisement.setFilePath(rs.getString("FILE_PTH"));
				}
				return advertisement;
			}
			
		});
	}

	@Override
	public boolean deleteAdvertisement(String advertisementId) {
		return false;
	}	
}
