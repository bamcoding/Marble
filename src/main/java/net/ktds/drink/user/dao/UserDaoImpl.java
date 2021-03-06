package net.ktds.drink.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.admin.vo.SearchUserVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;
import net.ktds.drink.user.vo.UserVO;

public class UserDaoImpl extends DaoSupport implements UserDao {

	@Override
	public int countUserEmail(final String userEmail) {
		return (int) selectOne( new QueryAndResult(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		USR ");
				query.append(" WHERE	USR_EML = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, userEmail);
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				if( rs.next() ){
					return rs.getInt("CNT");
				}
				return 0;
			}
			
		});
	}

	@Override
	public int countUserNickname(final String userNickname) {
		return (int) selectOne( new QueryAndResult() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		USR ");
				query.append(" WHERE	USR_NICK_NM = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, userNickname);
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				if ( rs.next() ){
					return rs.getInt("CNT");
				}
				return 0;
			}
			
		});
	}

	@Override
	public int signUpUser(UserVO user) {
		
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT INTO USR ( ");
				query.append(" 				USR_ID ");
				query.append(" 				, USR_EML ");
				query.append("				, USR_PWD	  ");
				query.append("              , POINTS      ");
				query.append("				, USR_NICK_NM  ");
				query.append("				, CRT_DT ) ");
				query.append(" VALUES ( ");
				query.append(" 'UR-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(USR_ID_SEQ.NEXTVAL,6,0) ");
				query.append("	, ? , ? , 0 , ? , SYSDATE  ");
				query.append("        )  ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, user.getUserEmail());
				pstmt.setString(2, user.getUserPassword());
				pstmt.setString(3, user.getUserNickname());
				return pstmt;
			}
		});
			
		
	}

	@Override
	public UserVO getUserBy(UserVO user) {
		UserVO userInfo = (UserVO)selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	USR_ID ");
				query.append(" 			, USR_EML ");
				query.append(" 			, USR_PWD ");
				query.append(" 			, POINTS ");
				query.append(" 			, USR_NICK_NM ");
				query.append("			, CRT_DT");
				query.append(" FROM		USR	");
				query.append(" WHERE	USR_EML = ?	");
				query.append(" AND		USR_PWD = ? ");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, user.getUserEmail());
				pstmt.setString(2, user.getUserPassword());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				UserVO userInfo = null;
				if(rs.next()){
					userInfo = new UserVO();
					userInfo.setUserId(rs.getString("USR_ID"));
					userInfo.setUserEmail(rs.getString("USR_EML"));
					userInfo.setUserPassword(rs.getString("USR_PWD"));
					userInfo.setUserNickname(rs.getString("USR_NICK_NM"));
					userInfo.setPoints(rs.getInt("POINTS"));
					userInfo.setCreatedDate(rs.getString("CRT_DT"));
				}
				return userInfo;
				
			}
		});
		return userInfo;
	}

	@Override
	public List<UserVO> getListUserInfo(SearchUserVO searchUserVO) {
		
		return (List)selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	USR_ID ");				
				query.append(" 			, USR_PWD ");
				query.append("			, POINTS ");
				query.append("			, USR_EML ");
				query.append("			, USR_NICK_NM ");
				query.append("			, TO_CHAR(CRT_DT,'YYYY-MM-SS HH24:MI:SS') CRT_DT ");
				query.append(" FROM		USR	");
				
				if(searchUserVO.getSearchType() == 1){
					query.append(" WHERE USR_EML LIKE '%' || ? || '%' ");
					query.append(" AND USR_NICK_NM LIKE '%' || ? || '%' ");
				}
				else if(searchUserVO.getSearchType() == 2 ){
					query.append(" WHERE USR_EML LIKE '%' || ? || '%' ");
				}
				else if(searchUserVO.getSearchType() == 3 ){
					query.append(" WHERE USR_NICK_NM LIKE '%' || ? || '%' ");
				}
				
				query.append(" ORDER BY USR_ID ASC ");
				
				String pagingQuery = appendPagingQueryFormat(query.toString());
				
				PreparedStatement pstmt = conn.prepareStatement(pagingQuery);
				int index = 1;
				if ( searchUserVO.getSearchType() == 1){
					pstmt.setString(index++, searchUserVO.getSearchKeyword());
					pstmt.setString(index++, searchUserVO.getSearchKeyword());
				}
				else if( searchUserVO.getSearchType() == 2){
					pstmt.setString(index++, searchUserVO.getSearchKeyword());
				}
				else if( searchUserVO.getSearchType() == 3){
					pstmt.setString(index++, searchUserVO.getSearchKeyword());
				}
				pstmt.setInt(index++, searchUserVO.getEndRowNumber());
				pstmt.setInt(index++, searchUserVO.getStartRowNumber());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				
				List<UserVO> userInfo = new ArrayList();
				UserVO user = null;
				while( rs.next()){
					user = new UserVO();
					user.setUserId(rs.getString("USR_ID"));
					user.setUserEmail(rs.getString("USR_EML"));
					user.setPoints(rs.getInt("POINTS"));
					user.setUserNickname(rs.getString("USR_NICK_NM"));
					user.setCreatedDate(rs.getString("CRT_DT"));
					
					userInfo.add(user);
				}
				return userInfo;
			}
		});
	}

	@Override
	public int deleteUserInfo(String userId) {
		return (int)insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" DELETE ");
				query.append(" FROM	USR ");
				query.append(" WHERE USR_ID = ? ");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, userId);
				return pstmt;
			}
			
		});
	}

	@Override
	public int getCountUsers(SearchUserVO searchUser) {
		return (int) selectOne(new QueryAndResult(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM 	USR ");
				
				if(searchUser.getSearchType() == 1){
					query.append(" WHERE USR_EML LIKE '%' || ? || '%' ");
					query.append(" OR USR_NICK_NM LIKE '%' || ? || '%' ");
				}
				else if(searchUser.getSearchType() == 2){
					
					query.append(" WHERE USR_EML LIKE '%' || ? || '%' ");
					
				}
				else if(searchUser.getSearchType() == 3){
					
					query.append(" WHERE USR_NICK_NM LIKE '%' || ? || '%' ");
					
				}
				query.append(" ORDER BY USR_ID DESC ");
				
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				if(searchUser.getSearchType() == 1){
					pstmt.setString(1, searchUser.getSearchKeyword());
					pstmt.setString(2, searchUser.getSearchKeyword());
				}
				else if ( searchUser.getSearchType() == 2 ){
					pstmt.setString(1, searchUser.getSearchKeyword());
				}
				else if ( searchUser.getSearchType() == 3) {
					pstmt.setString(1, searchUser.getSearchKeyword());
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
	public int userPasswordReset(String UserId) {
		
		return (int)insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	USR ");
				query.append(" SET		USR_PWD = '1' ");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				return pstmt;
			}
			
		});
	}




}
