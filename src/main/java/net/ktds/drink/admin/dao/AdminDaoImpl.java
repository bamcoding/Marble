package net.ktds.drink.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.admin.vo.SearchAdvertisementVO;
import net.ktds.drink.admin.vo.SearchSoundTrackVO;
import net.ktds.drink.admin.vo.SoundTrackVO;
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
				query.append("				, FILE_NM ) ");
				query.append(" VALUES (  ");
				query.append("  	   'AD-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(AD_ID_SEQ.NEXTVAL,6,0) ");
				query.append(" 		   , ? ");
				query.append(" 		   , ? ");
				query.append(" 		   , ? ) ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, advertisementVO.getContractDate());
				pstmt.setString(2, advertisementVO.getExpirationDate());
				pstmt.setString(3, advertisementVO.getFileName());
				return pstmt;
			}
			
		});
	}

	@Override
	public List<AdvertisementVO> getAdvertisementVideo(SearchAdvertisementVO searchAdvertisement) {
		return  (List<AdvertisementVO>) selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT 		AD_ID ");
				query.append("  			, TO_CHAR( CNTRCT_DT, 'YYYY-MM-DD') CNTRCT_DT  ");
				query.append("  			, TO_CHAR( EXPRTN_DT, 'YYYY-MM-DD') EXPRTN_DT  ");
				query.append("				, FILE_NM ");
				query.append(" FROM 		AD ");
				
				if ( searchAdvertisement.getSearchType() == 1 ) {
					query.append(" WHERE   ( FILE_NM LIKE '%' || ? || '%' ");
					query.append(" OR	    CNTRCT_DT LIKE '%' || ? || '%' ) ");
				}
				else if ( searchAdvertisement.getSearchType() == 2 ) {
					query.append(" WHERE   ( FILE_NM LIKE '%' || ? || '%' ");
					query.append(" OR	    EXPRTN_DT LIKE '%' || ? || '%' ) ");
				}
				else if ( searchAdvertisement.getSearchType() == 3 ) {
					query.append(" WHERE  	CNTRCT_DT LIKE '%' || ? || '%' ");
				}
				else if ( searchAdvertisement.getSearchType() == 4 ) {
					query.append(" WHERE	EXPRTN_DT LIKE '%' || ? || '%' ");
				}
				
				String pagingQuery = appendPagingQueryFormat(query.toString());
				
				PreparedStatement pstmt = conn.prepareStatement(pagingQuery);
				int index = 1;
				
				if ( searchAdvertisement.getSearchType() == 1 ) {
					pstmt.setString(index++, searchAdvertisement.getSearchKeyword());
					pstmt.setString(index++, searchAdvertisement.getSearchKeyword());
				}
				else if ( searchAdvertisement.getSearchType() == 2 ) {
					pstmt.setString(index++, searchAdvertisement.getSearchKeyword());
					pstmt.setString(index++, searchAdvertisement.getSearchKeyword());
				}
				else if ( searchAdvertisement.getSearchType() == 3 ) {
					pstmt.setString(index++, searchAdvertisement.getSearchKeyword());
				}
				else if ( searchAdvertisement.getSearchType() == 4 ) {
					pstmt.setString(index++, searchAdvertisement.getSearchKeyword());
				}
				
				pstmt.setInt(index++, searchAdvertisement.getEndRowNumber());
				pstmt.setInt(index++, searchAdvertisement.getStartRowNumber());
				
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
					advertisement.setFileName(rs.getString("FILE_NM"));
					advertisements.add(advertisement);
				}
				return advertisements;
			}
		});
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
				}
				return advertisement;
			}
			
		});
	}

	@Override
	public int deleteAdvertisement(String advertisementId) {
		return (int) insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" DELETE ");
				query.append(" FROM AD");
				query.append(" WHERE AD_ID = ? ");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, advertisementId);
				return pstmt;
			}
			
		});
	}

	@Override
	public AdvertisementVO getRandomAdvertisementVideoBy() {
		return (AdvertisementVO) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT AD_ID ");
				query.append(" 		  , CNTRCT_DT ");
				query.append(" 		  , EXPRTN_DT ");
				query.append(" 		  , FILE_NM ");
				query.append(" FROM ( ");
				query.append(" 		  SELECT AD_ID");
				query.append("  	  ,  CNTRCT_DT");
				query.append("  	  ,  EXPRTN_DT");
				query.append(" 		  , FILE_NM ");
				query.append(" 				FROM DRINK.AD     ");
				query.append(" 				WHERE TO_DATE(CNTRCT_DT,'YYYY:MM:DD') <= TO_DATE(SYSDATE, 'YYYY:MM:DD') ");
				query.append("  			AND   TO_DATE(EXPRTN_DT,'YYYY:MM:DD') >= TO_DATE(SYSDATE, 'YYYY:MM:DD') ");
				query.append("  			ORDER BY dbms_random.value ");
				query.append("  	 ) ");
				query.append("  WHERE   ROWNUM <=1 ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				AdvertisementVO advertisement = null;
				if (rs.next()){
					advertisement = new AdvertisementVO();
					advertisement.setAdvertisementId(rs.getString("AD_ID"));
					advertisement.setContractDate(rs.getString("CNTRCT_DT"));
					advertisement.setExpirationDate(rs.getString("EXPRTN_DT"));
					advertisement.setFileName(rs.getString("FILE_NM"));
					
				}
				return advertisement;
			}
		});
	}

	@Override
	public int getCountOfAdvertisements(SearchAdvertisementVO searchAdvertisement) {
		return (int) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		AD ");			
				
				if ( searchAdvertisement.getSearchType() == 1 ) {
					query.append(" WHERE ( FILE_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  CNTRCT_DT LIKE '%' || ? || '%' ) ");
				}
				else if ( searchAdvertisement.getSearchType() == 2 ) {
					query.append(" WHERE ( FILE_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  EXPRTN_DT LIKE '%' || ? || '%' ) ");
				}
				else if ( searchAdvertisement.getSearchType() == 3 ) {
					query.append(" WHERE  CNTRCT_DT LIKE '%' || ? || '%' ");
				}
				else if ( searchAdvertisement.getSearchType() == 4 ) {
					query.append(" WHERE  EXPRTN_DT LIKE '%' || ? || '%' ");
				}
				
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				
				if ( searchAdvertisement.getSearchType() == 1 ) {
					pstmt.setString(1, searchAdvertisement.getSearchKeyword());
					pstmt.setString(2, searchAdvertisement.getSearchKeyword());
				}
				else if ( searchAdvertisement.getSearchType() == 2 ) {
					pstmt.setString(1, searchAdvertisement.getSearchKeyword());
					pstmt.setString(2, searchAdvertisement.getSearchKeyword());
				}
				else if ( searchAdvertisement.getSearchType() == 3 ) {
					pstmt.setString(1, searchAdvertisement.getSearchKeyword());
				}
				else if ( searchAdvertisement.getSearchType() == 4 ) {
					pstmt.setString(1, searchAdvertisement.getSearchKeyword());
				}
				
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				rs.next();
				
				return rs.getInt("CNT");
			}
		}); 
	}

	@Override
	public int addSoundTrack(SoundTrackVO soundTrackVO) {
		return (int) insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT INTO SND_TRCK ( ");
				query.append("  			TRCK_ID ");
				query.append("  			, FILE_NM  ");
				query.append("				, SND_INFO ) ");
				query.append(" VALUES (  ");
				query.append("  	   'SND-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(AD_ID_SEQ.NEXTVAL,6,0) ");
				query.append(" 		   , ? ");
				query.append(" 		   , ? ) ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, soundTrackVO.getFileName());
				pstmt.setString(2, soundTrackVO.getSoundInfo());
				return pstmt;
			}
			
		});
	}

	@Override
	public List<SoundTrackVO> getSoundTrack(SearchSoundTrackVO searchSoundTrack) {
		return  (List<SoundTrackVO>) selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT 		TRCK_ID ");
				query.append("  			, FILE_NM  ");
				query.append("				, SND_INFO ");
				query.append(" FROM 		SND_TRCK ");
				query.append(" WHERE   FILE_NM LIKE '%' || ? || '%' ");
					
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				
				pstmt.setString(1, searchSoundTrack.getSearchKeyword());
				
//				pstmt.setInt(1, searchSoundTrack.getEndRowNumber());
//				pstmt.setInt(1, searchSoundTrack.getStartRowNumber());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				
				List<SoundTrackVO> soundTracks = new ArrayList<SoundTrackVO>();
				SoundTrackVO soundTrack= null;
				while ( rs.next() ){
					soundTrack = new SoundTrackVO();
					soundTrack.setSoundTrackId(rs.getString("TRCK_ID"));
					soundTrack.setFileName(rs.getString("FILE_NM"));
					soundTrack.setSoundInfo(rs.getString("SND_INFO"));
					soundTracks.add(soundTrack);
				}
				return soundTracks;
			}
		});
	}

	@Override
	public SoundTrackVO getFileNameOfSoundTrackBy(String soundTrackId) {
		return (SoundTrackVO)selectOne(new QueryAndResult(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT TRCK_ID ");
				query.append(" 		  , FILE_NM ");
				query.append("		  , SND_INFO ");
				query.append(" FROM  SND_TRCK ");
				query.append(" WHERE TRCK_ID = ? ");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, soundTrackId);
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				SoundTrackVO soundTrack = null;
				if(rs.next()){
					soundTrack = new SoundTrackVO();
					soundTrack.setSoundTrackId(rs.getString("TRCK_ID"));
					soundTrack.setFileName(rs.getString("FILE_NM"));
					soundTrack.setSoundInfo(rs.getString("SND_INFO"));
				}
				return soundTrack;
			}
			
		});
	}

	@Override
	public int deleteSoundTrack(String soundTrackId) {
		return (int) insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append( " DELETE ");
				query.append( " FROM SND_TRCK " );
				query.append( " WHERE TRCK_ID = ? ");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, soundTrackId);
				return pstmt;
			}
			
		});
	}

	@Override
	public int getCountOfSoundTracks(SearchSoundTrackVO searchSoundTrack) {
		return (int) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		AD ");			
				query.append(" WHERE  FILE_NM LIKE '%' || ? || '%' ");
				
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				
				pstmt.setString(1, searchSoundTrack.getSearchKeyword());
				
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				rs.next();
				
				return rs.getInt("CNT");
			}
		}); 
	}
}