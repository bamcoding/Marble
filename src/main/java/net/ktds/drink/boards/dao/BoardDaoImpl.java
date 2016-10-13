package net.ktds.drink.boards.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.boards.vo.SearchBoardVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;
import net.ktds.drink.user.vo.UserVO;

public class BoardDaoImpl extends DaoSupport implements BoardDao{

	@Override
	public int insert(BoardVO board) {
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT	INTO	BOARD ( ");
				query.append("  				BRD_ID ");
				query.append("  				, BRD_SBJ, BRD_CONT, USR_ID ");
				query.append("  				, CRT_DT, FILE_NM, CTGR_ID, MDFY_DT ");
				query.append("  				, HIT_CNT, RCMD_CNT ) ");
				query.append("  		VALUES ( ");
				query.append("  				'BR-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(BRD_ID_SEQ.NEXTVAL,6,0) ");
				query.append("  				, ?, ?, ?, SYSDATE, ?, ?, SYSDATE, 0, 0 ) ");
			
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, board.getBoardSubject());
				pstmt.setString(2, board.getBoardContent());
				pstmt.setString(3, board.getUserId());
				pstmt.setString(4, board.getFileName());
				pstmt.setString(5, board.getCategoryId());
				
				return pstmt;
			}
		});
	}

	@Override
	public List<BoardVO> selectBoards(SearchBoardVO searchBoard) {
		@SuppressWarnings("unchecked")
		List<BoardVO> boards = (List<BoardVO>) selectList(new QueryAndResult(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	B.BRD_ID ");
				query.append(" 			, B.BRD_SBJ ");
				query.append(" 			, B.BRD_CONT ");
				query.append(" 			, TO_CHAR(B.CRT_DT, 'YYYY-MM-DD HH24:MI:SS') CRT_DT ");
				query.append(" 			, B.FILE_NM ");
				query.append(" 			, B.CTGR_ID ");
				query.append(" 			, B.HIT_CNT ");
				query.append(" 			, B.RCMD_CNT ");
				query.append(" 			, B.USR_ID ");
				query.append(" 			, U.USR_NICK_NM ");
				query.append(" 			, U.USR_EML ");
				query.append(" FROM		BOARD B ");
				query.append(" 			, USR U ");
				query.append(" WHERE	B.USR_ID = U.USR_ID(+) ");
				
				if (searchBoard.getSearchType() == 1) {
					query.append(" AND ( B.BRD_SBJ LIKE '%' || ? || '%' ");
					query.append(" OR 	B.BRD_CONT LIKE '%' || ? || '%' ) ");
				}
				else if (searchBoard.getSearchType() == 2) {
					query.append(" AND	B.BRD_SBJ LIKE '%' || ? || '%' ");
				}
				else if (searchBoard.getSearchType() == 3) {
					query.append(" AND	B.BRD_CONT LIKE '%' || ? || '%' ");
				}
				else if (searchBoard.getSearchType() == 4) {
					query.append(" AND	U.USR_NICK_NM LIKE '%' || ? || '%' ");
				}
				query.append(" ORDER	BY B.BRD_ID DESC ");
				
				String pagingQuery = appendPagingQueryFormat(query.toString());
				
				PreparedStatement pstmt = conn.prepareStatement(pagingQuery);
				
				int index = 1;
				
				if (searchBoard.getSearchType() == 1) {
					pstmt.setString(index++, searchBoard.getSearchKeyword());
					pstmt.setString(index++, searchBoard.getSearchKeyword());
				}
				else if (searchBoard.getSearchType() == 2) {
					pstmt.setString(index++, searchBoard.getSearchKeyword());
				}
				else if (searchBoard.getSearchType() == 3) {
					pstmt.setString(index++, searchBoard.getSearchKeyword());
				}
				else if (searchBoard.getSearchType() == 4) {
					pstmt.setString(index++, searchBoard.getSearchKeyword());
				}
				
				pstmt.setInt(index++, searchBoard.getEndRowNumber());
				pstmt.setInt(index++, searchBoard.getStartRowNumber());
				
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<BoardVO> boards = new ArrayList<BoardVO>();
				BoardVO board = null;
				
				while (rs.next()) {
					board = new BoardVO();
					board.setBoardId(rs.getString("BRD_ID"));
					board.setBoardSubject(rs.getString("BRD_SBJ"));
					board.setBoardContent(rs.getString("BRD_CONT"));
					board.setCreatedDate(rs.getString("CRT_DT"));
					board.setFileName(rs.getString("FILE_NM"));
					board.setCategoryId(rs.getString("CTGR_ID"));
					board.setHitCount(rs.getInt("HIT_CNT"));
					board.setRecommendCount(rs.getInt("RCMD_CNT"));
					board.setUserId(rs.getString("USR_ID"));
					
					board.setUserVO(new UserVO());
					board.getUserVO().setUserNickname(rs.getString("USR_NICK_NM"));
					board.getUserVO().setUserEmail(rs.getString("USR_EML"));
					
					boards.add(board);
				}
				return boards;
			}
		});
		return boards;
	}

	@Override
	public BoardVO selectBoard(String boardId) {
		
		return (BoardVO) selectOne(new QueryAndResult(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	B.BRD_ID ");
				query.append(" 			, B.BRD_SBJ ");
				query.append(" 			, B.BRD_CONT ");
				query.append(" 			, TO_CHAR(B.CRT_DT, 'YYYY-MM-DD HH24:MI:SS') CRT_DT ");
				query.append(" 			, B.FILE_NM ");
				query.append(" 			, B.CTGR_ID ");
				query.append(" 			, B.HIT_CNT ");
				query.append(" 			, B.RCMD_CNT ");
				query.append(" 			, B.USR_ID ");
				query.append(" 			, U.USR_NICK_NM ");
				query.append(" 			, U.USR_EML ");
				query.append(" FROM		BOARD B ");
				query.append(" 			, USR U ");
				query.append(" WHERE	B.USR_ID = U.USR_ID ");
				query.append(" AND		B.BRD_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, boardId);
				
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				BoardVO board = null;
				
				if (rs.next()) {
					board = new BoardVO();
					board.setBoardId(rs.getString("BRD_ID"));
					board.setBoardSubject(rs.getString("BRD_SBJ"));
					board.setBoardContent(rs.getString("BRD_CONT"));
					board.setCreatedDate(rs.getString("CRT_DT"));
					board.setHitCount(rs.getInt("HIT_CNT"));
					board.setRecommendCount(rs.getInt("RCMD_CNT"));
					board.setUserId(rs.getString("USR_ID"));
					board.setFileName(rs.getString("FILE_NM"));
					
					board.setUserVO(new UserVO());
					board.getUserVO().setUserNickname(rs.getString("USR_NICK_NM"));
					board.getUserVO().setUserEmail(rs.getString("USR_EML"));
				}
				return board;
			}
			
		});
	}

	@Override
	public int updateBoard(BoardVO board) {
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append( " UPDATE	BOARD " );
				query.append( " SET		MDFY_DT = SYSDATE " );
				if ( board.getBoardSubject() != null ) {
					query.append( " , BRD_SBJ = ? " );
				}
				if ( board.getBoardContent() != null ) {
					query.append( " , BRD_CONT = ? " );
				}
				if ( board.getFileName() != null ) {
					query.append( " , FILE_NM = ? " );
				}
				query.append(" WHERE	BRD_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				
				int index = 1;
				if ( board.getBoardSubject() != null ) {
					query.append( " , BRD_SBJ = ? " );
					pstmt.setString(index++, board.getBoardSubject());
				}
				if ( board.getBoardContent() != null ) {
					query.append( " , BRD_CONT = ? " );
					pstmt.setString(index++, board.getBoardContent());
				}
				if ( board.getFileName() != null ) {
					query.append( " , FILE_NM = ? " );
					pstmt.setString(index++, board.getFileName());
				}
				
				pstmt.setString(index++, board.getBoardId());
				return pstmt;
			}
		});
	}

	public int getCountOfBoards(SearchBoardVO searchBoard) {
		
		return (int) selectOne(new QueryAndResult(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		BOARD B ");
				query.append(" 			, USR U ");
				query.append(" WHERE	B.USR_ID = U.USR_ID ");
				
				if (searchBoard.getSearchType() ==1) {
					query.append(" AND	( B.BRD_SBJ LIKE '%' || ? || '%' ");
					query.append(" OR	B.BRD_CONT LIKE '%' || ? || '%' ) ");
				}
				else if (searchBoard.getSearchType() ==2) {
					query.append(" AND	B.BRD_SBJ LIKE '%' || ? || '%' ");
				}
				else if (searchBoard.getSearchType() ==3) {
					query.append(" AND	B.BRD_CONT LIKE '%' || ? || '%' ");
				}
				else if (searchBoard.getSearchType() ==4) {
					query.append(" AND	U.USR_NICK_NM LIKE '%' || ? || '%' ");
				}

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				
				if (searchBoard.getSearchType() == 1) {
					pstmt.setString(1, searchBoard.getSearchKeyword());
					pstmt.setString(2, searchBoard.getSearchKeyword());
				}
				else if (searchBoard.getSearchType() == 2) {
					pstmt.setString(1, searchBoard.getSearchKeyword());
				}
				else if (searchBoard.getSearchType() == 3) {
					pstmt.setString(1, searchBoard.getSearchKeyword());
				}
				else if (searchBoard.getSearchType() == 4) {
					pstmt.setString(1, searchBoard.getSearchKeyword());
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
	public int deleteBoard(String boardId) {
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" DELETE	FROM	BOARD ");
				query.append(" WHERE			BRD_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, boardId);
				return pstmt;
			}
		});
	}

	@Override
	public int updateHitCount(String boardId) {
		
		return insert(new Query() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	BOARD ");
				query.append(" SET		HIT_CNT = HIT_CNT+1 ");
				query.append(" WHERE	BRD_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, boardId);
				
				return pstmt;
			}
		});
	}

	@Override
	public int updateRecommendCount(String boardId) {

		return insert(new Query(){

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	BOARD ");
				query.append(" SET		RCMD_CNT ");
				query.append(" WHERE	BRD_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, boardId);
				
				return pstmt;
			}		
		});
	}
	
}

	
