package net.ktds.drink.boards.comments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.boards.comments.vo.CommentVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;
import net.ktds.drink.user.vo.UserVO;

public class CommentDaoImpl extends DaoSupport implements CommentDao{

	@Override
	public int insert(CommentVO comment) {
		return insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT	INTO COMMENTS ( ");
				query.append(" 			CMMNT_ID, BRD_ID, CMMNT_CONT, USR_ID ");
				query.append(" 			, CRT_DT, MDFY_DT ) ");
				query.append(" VALUES	( ");
				query.append(" 			'CR-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(CMMNT_ID_SEQ.NEXTVAL,6,0) ");
				query.append(" 			, ?, ?, ?, SYSDATE, SYSDATE ) ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, comment.getBoardId());
				pstmt.setString(2, comment.getCommentContent());
				pstmt.setString(3, comment.getUserId());
				return pstmt;
			}
			
		});
	}

	@Override
	public List<CommentVO> selectComments(String boardId) {
		@SuppressWarnings("unchecked")
		List<CommentVO> comments = (List<CommentVO>) selectList(new QueryAndResult(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	C.CMMNT_ID, C.BRD_ID, C.CMMNT_CONT, C.USR_ID ");
				query.append(" 			, TO_CHAR(C.CRT_DT, 'YYYY-MM-DD HH24:MI:SS') CRT_DT ");
				query.append(" 			, TO_CHAR(C.MDFY_DT, 'YYYY-MM-DD HH24:MI:SS') MDFY_DT ");
				query.append(" 			, C.PRNT_CMMNT_ID, U.USR_NICK_NM  ");
				query.append(" FROM		COMMENTS C");
				query.append(" 			, USR U ");
				query.append(" 			, BOARD B ");
				query.append(" WHERE	C.BRD_ID = B.BRD_ID ");
				query.append(" AND		C.USR_ID = U.USR_ID ");
				query.append(" AND		B.BRD_ID = ? ");
				query.append(" ORDER	BY CMMNT_ID DESC ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1,boardId);
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<CommentVO> comments = new ArrayList<CommentVO>();
				CommentVO comment = null;
				
				while(rs.next()) {
					comment = new CommentVO();
					comment.setCommentId(rs.getString("CMMNT_ID"));
					comment.setBoardId(rs.getString("BRD_ID"));
					comment.setCommentContent(rs.getString("CMMNT_CONT"));
					comment.setUserId(rs.getString("USR_ID"));
					comment.setCreatedDate(rs.getString("CRT_DT"));
					comment.setModifyDate(rs.getString("MDFY_DT"));
					comment.setParentCommentId(rs.getString("PRNT_CMMNT_ID"));
					
					comment.setUserVO(new UserVO());
					comment.getUserVO().setUserNickname(rs.getString("USR_NICK_NM"));
					
					comments.add(comment);
				}
				return comments;
			}
			
		});
		return comments;
	}

	@Override
	public int updateComment(CommentVO comment) {
		return insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	COMMENTS ");
				query.append(" SET		MDFY_DT = SYSDATE ");
				
				if(comment.getCommentContent() != null) {
					query.append(" ,CMMNT_CONT = ? ");
				}
				query.append(" WHERE	CMMNT_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				
				int index = 1 ;
				if (comment.getCommentContent() != null) {
					query.append(" , CMMNT_CONT = ? ");
					pstmt.setString(index++, comment.getCommentContent());
				}
				pstmt.setString(index++, comment.getCommentId());
				
				return pstmt;
			}
		});
	}

	@Override
	public int deleteComment(String commentId) {

		return insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" DELETE	FROM COMMENTS ");
				query.append(" WHERE	CMMNT_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, commentId);
				
				return pstmt;
			}
			
		});
	}
}
