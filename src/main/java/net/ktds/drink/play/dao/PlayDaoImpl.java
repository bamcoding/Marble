package net.ktds.drink.play.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.play.vo.HistoryVO;
import net.ktds.drink.play.vo.PlayVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;

public class PlayDaoImpl extends DaoSupport implements PlayDao{

	@Override
	public List<HistoryVO> getHistoryByUserId(String userId) {
		return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	PLY_INFO_ID ");
				query.append(" 			, TO_CHAR(PLY_TM, 'YYYY/MM/DD HH:MI:SS') PLY_TM ");
				query.append(" 			, USR_ID ");
				query.append(" FROM		PLAY_INFO ");
				query.append(" WHERE	USR_ID = ? ");
				query.append(" ORDER	BY PLY_TM DESC ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, userId);
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<HistoryVO> historyList = new ArrayList<HistoryVO>();
				HistoryVO history = null;
				
				while (rs.next()) {
					history = new HistoryVO();

					history.setPlayInfo(rs.getString("PLY_INFO_ID"));
					history.setPlayDate(rs.getString("PLY_TM"));
					history.setUserId(rs.getString("USR_ID"));
					
					historyList.add(history);
				}
				return historyList;
			}
		});
	}

	@Override
	public List<PlayVO> getPlayById(String playInfo) {
		return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	GS.PLY_INFO_ID ");
				query.append(" 			, GS.USR_ID ");
				query.append(" 			, GS.GM_ID ");
				query.append(" 			, G.GM_NM ");
				query.append(" 			, G.GM_INFO ");
				query.append(" 			, G.CTGR_ID ");
				query.append(" 			, G.TYP_ID ");
				query.append(" FROM		GAME_SET GS");
				query.append(" 			GAME G");
				query.append(" WHERE	GS.GM_ID = G.GM_ID(+) ");
				query.append(" AND		GS.PLY_INFO_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, playInfo);
				
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				
				List<PlayVO> plays = new ArrayList<PlayVO>();
				PlayVO play = null;
				
				while(rs.next()){
					play = new PlayVO();
					play.setPlayInfoId(rs.getString("PLY_INFO_ID"));
					play.setUserId(rs.getString("USR_ID"));
					play.setGameId(rs.getString("GM_ID"));
					
					play.setGames(new GamesVO());
					play.getGames().setGameId(rs.getString("GM_ID"));
					play.getGames().setGameName(rs.getString("GM_INFO"));
					play.getGames().setGameInfo(rs.getString(""));
					play.getGames().setCategoryId(rs.getString("CTGR_ID"));
					play.getGames().setTypeId(rs.getString("TYP_ID"));
					
					plays.add(play);
				}
				
				return plays;
			}
		});
	}

	@Override
	public int addHistory(String userId) {
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT 	INTO	PLAY_INFO 	(		PLY_INFO_ID");
				query.append(" 										, PLY_TM ");
				query.append(" 										, USR_ID ");
				query.append(" 								) ");
				query.append(" 			VALUES 				( 	");
				query.append(" 										'PLY-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(PLY_INFO_ID_SEQ.NEXTVAL,6,0)  ");
				query.append(" 										, SYSDATE ");
				query.append(" 										, ? ");
				query.append(" 								) ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, userId);
				return pstmt;
			}
		});
	}

	@Override
	public int addPlays(PlayVO playVO) {
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT 	INTO	GAME_SET 	(		PLY_INFO_ID");
				query.append(" 										, USR_ID ");
				query.append(" 										, GM_ID ");
				query.append(" 								) ");
				query.append(" 			VALUES 				( 	");
				query.append(" 										?  ");
				query.append(" 										, ? ");
				query.append(" 										, ? ");
				query.append(" 								) ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, playVO.getPlayInfoId());
				pstmt.setString(2, playVO.getUserId());
				pstmt.setString(3, playVO.getGameId());
				return pstmt;
			}
		});
	}

}
