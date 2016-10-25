package net.ktds.drink.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.admin.vo.GameSetVO;
import net.ktds.drink.admin.vo.GameTypeVO;
import net.ktds.drink.admin.vo.HistoryVO;
import net.ktds.drink.admin.vo.PlayInfoVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;
import net.ktds.drink.user.vo.UserVO;

public class HistoryDaoImpl extends DaoSupport implements HistoryDao {

	@Override
	public int getConutOfHistory(SearchGamesVO searchGame) {
		return (int) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		PLAY_INFO P  ");							
				query.append(" 			, USR  U ");					
				query.append(" WHERE		P.USR_ID = U.USR_ID  ");								
				
				if ( searchGame.getSearchType() == 1 ) {
					query.append(" AND		P.PLY_INFO_ID LIKE '%' || ? || '%' ");
				}
				else if ( searchGame.getSearchType() == 2 ) {
					query.append(" AND		U.USR_NICK_NM LIKE '%' || ? || '%' ");
				}
		
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
	
				if ( searchGame.getSearchType() == 1 ) {
					pstmt.setString(1, searchGame.getSearchKeyword());
				
					
				}
				else if ( searchGame.getSearchType() == 2 ) {
					pstmt.setString(1, searchGame.getSearchKeyword());
					
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
	public List<HistoryVO> getHistory(SearchGamesVO searchGame) {
	return selectList(new QueryAndResult() {
		
		
		
	
		
		
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	P.PLY_INFO_ID ");
				query.append(" 			, P.USR_ID ");
				query.append(" 			, TO_CHAR(P.PLY_TM, 'YYYY-MM-DD HH24:MI:SS')PLY_TM ");
				query.append(" 			, U.USR_NICK_NM ");
				query.append(" FROM		PLAY_INFO P  ");							
				query.append(" 			, USR  U ");					
				query.append(" WHERE		P.USR_ID = U.USR_ID  ");		
	
												
				
				if ( searchGame.getSearchType() == 1 ) {
					query.append(" AND		P.PLY_INFO_ID LIKE '%' || ? || '%' ");
				}
				else if ( searchGame.getSearchType() == 2 ) {
					query.append(" AND		U.USR_NICK_NM LIKE '%' || ? || '%' ");
				}

				
				query.append(" ORDER	BY P.PLY_INFO_ID DESC ");			
				
				String pagingQuery = appendPagingQueryFormat(query.toString());
			
				PreparedStatement pstmt = conn.prepareStatement(pagingQuery);
		
				
				int index = 1;
				
				if ( searchGame.getSearchType() == 1 ) {
					pstmt.setString(index++, searchGame.getSearchKeyword());
				}
				else if ( searchGame.getSearchType() == 2 ) {
					pstmt.setString(index++, searchGame.getSearchKeyword());
					
				}
				
				pstmt.setInt(index++, searchGame.getEndRowNumber());
				pstmt.setInt(index++, searchGame.getStartRowNumber());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<HistoryVO> historyList = new ArrayList<HistoryVO>();
				HistoryVO historyVO = null;
				
				UserVO userVO = null;
				
				while(rs.next()){
					
					historyVO = new HistoryVO();
					
					userVO = historyVO.getUserVO();
					userVO.setUserId(rs.getString("USR_ID"));
					userVO.setUserNickname(rs.getString("USR_NICK_NM"));
					
					historyVO.setPlayInfoId(rs.getString("PLY_INFO_ID"));
					historyVO.setPlayTime(rs.getString("PLY_TM"));
					historyList.add(historyVO);
				}
				return historyList;	
			}
		});
	}

	@Override
	public int deleteHistory(String playInfoId) {
		return insert (new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				
				StringBuffer query = new StringBuffer();
				query.append(" DELETE ");
				query.append(" FROM		PLAY_INFO ");
				query.append(" WHERE	PLY_INFO_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, playInfoId);
				return pstmt;
			}
		});
	}

	@Override
	public GameSetVO getGameSetAt(String playInfoId) {
		return (GameSetVO) selectOne (new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	GS.PLY_INFO_ID ");
				query.append(" 			, GS.USR_ID ");
				query.append(" 			, GS.GM_ID ");
				query.append(" 			, TO_CHAR(P.PLY_TM, 'YYYY-MM-DD HH24:MI:SS')PLY_TM ");
				query.append(" 			, U.USR_NICK_NM ");
				query.append(" 			, G.GM_NM ");
				query.append(" FROM		GAME_SET GS ");			
				query.append(" 			, PLAY_INFO P ");			
				query.append(" 			, GAME G ");			
				query.append(" 			, USR  U ");			
				query.append(" WHERE	GS.PLY_INFO_ID = P.PLY_INFO_ID  ");			
				query.append(" AND		GS.GM_ID = G.GM_ID  ");			
				query.append(" AND		GS.USR_ID = U.USR_ID  ");	
				query.append(" AND		GS.PLY_INFO_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, playInfoId);
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				GameSetVO gameSetVO = null;
				HistoryVO historyVO = null;
				UserVO userVO = null;
				GamesVO gamesVO = null;
				
				if(rs.next()) {
					gameSetVO = new GameSetVO();
					
					userVO = gameSetVO.getUserVO();
					userVO.setUserId(rs.getString("USR_ID"));
					userVO.setUserNickname(rs.getString("USR_NICK_NM"));
					
					historyVO = gameSetVO.getHistoryVO();
					historyVO.setPlayInfoId(rs.getString("PLY_INFO_ID"));
					historyVO.setPlayTime(rs.getString("PLY_TM"));
					
					gamesVO = gameSetVO.getGamesVO();
					gamesVO.setGameId(rs.getString("GM_ID"));
					gamesVO.setGameId(rs.getString("GM_NM"));
				}
				return gameSetVO;
			}
		});
	}

	@Override
	public List<GameSetVO> getAllGameSet(String playInfoId) {
		return selectList(new QueryAndResult() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	G.GM_NM ");
				query.append(" 			, G.GM_ID ");
				query.append(" FROM		GAME_SET GS ");
				query.append(" 			, PLAY_INFO P ");
				query.append(" 			, GAME G ");
				query.append(" 			, USR U ");
				query.append(" WHERE	GS.PLY_INFO_ID = P.PLY_INFO_ID ");
				query.append(" AND		GS.GM_ID = G.GM_ID ");
				query.append(" AND		GS.USR_ID = U.USR_ID ");
				query.append(" AND		GS.PLY_INFO_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, playInfoId);
				
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				
				List<GameSetVO> gameSetList = new ArrayList<GameSetVO>();
				GameSetVO gameSetVO = null;
				UserVO userVO = null;
				GamesVO gamesVO = null;
				
				while(rs.next()) {
					gameSetVO = new GameSetVO();
					
					gamesVO = gameSetVO.getGamesVO();
					gamesVO.setGameName(rs.getString("GM_NM"));
					gamesVO.setGameId(rs.getString("GM_ID"));
					
					gameSetList.add(gameSetVO);
				}
				return gameSetList;
			}
		});
	}

}
