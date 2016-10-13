package net.ktds.drink.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



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
				query.append("				, USR_NICK_NM ) ");
				query.append(" VALUES ( ");
				query.append(" 'UR-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(USR_ID_SEQ.NEXTVAL,6,0) ");
				query.append("	, ? , ? , 0 , ? 		");
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
				}
				return userInfo;
				
			}
		});
		return userInfo;
	}



}
