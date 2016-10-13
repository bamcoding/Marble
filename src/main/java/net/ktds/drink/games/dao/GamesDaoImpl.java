package net.ktds.drink.games.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.GameTypeVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.QueryAndResult;

public class GamesDaoImpl extends DaoSupport implements GamesDao {
	
	public List<CategoryVO> getCategory(CategoryVO categoryVO) {
		return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	CTGR_ID ");
				query.append(" 			, CTGR_NM ");
				query.append(" 			, PRNT_CTGR_ID ");
				query.append(" FROM		CTGR ");					
				query.append(" WHERE	PRNT_CTGR_ID = ? ");			
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, categoryVO.getParentCategoryId());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<CategoryVO> categories = new ArrayList<CategoryVO>();
				CategoryVO category = null;
				
				while(rs.next()){
					category = new CategoryVO();
					
					category.setCategoryId(rs.getString("CTGR_ID"));
					category.setCategoryName(rs.getString("CTGR_NM"));
					category.setParentCategoryId(rs.getString("PRNT_CTGR_ID"));					
					categories.add(category);
			}
				return categories;	
		}
		});
	}
	
	@Override
	public List<GamesVO> getGames(GamesVO gamesVO) {
		return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	G.GM_ID ");
				query.append(" 			, G.GM_NM ");
				query.append(" 			, G.GM_INFO ");
				query.append(" 			, C.CTGR_ID ");
				query.append(" 			, C.CTGR_NM ");
				query.append(" 			, T.TYP_ID ");
				query.append(" FROM		GAME G ");			
				query.append(" 			, CTGR C ");			
				query.append(" 			, GAME_TYPE T ");			
				query.append(" WHERE	G.CTGR_ID = C.USR_ID ");			
				query.append(" AND		G.TYP_ID = T.TYP_ID ");	
				query.append(" AND		G.CTGR_ID = ? ");	
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gamesVO.getCategoryId());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<GamesVO> gamess = new ArrayList<GamesVO>();
				GamesVO gameVO = null;
				CategoryVO categoryVO = null;
				GameTypeVO gameTypeVO = null;
				
				while(rs.next()){
					gameVO = new GamesVO();
					
					gameVO.setGameId(rs.getString("GM_ID"));
					gameVO.setGameName(rs.getString("GM_NM"));
					gameVO.setGameInfo(rs.getString("GM_INFO"));
					
					categoryVO = gameVO.getCategoryVO();
					gameVO.setCategoryId(rs.getString("CTGR_ID"));
					categoryVO.setCategoryName(rs.getString("CTGR_NM"));
					
					gameTypeVO = gameVO.getGameTypeVO();
					gameVO.setTypeId(rs.getString("TYP_ID"));
					
					gamess.add(gameVO);
			}
				return gamess;	
		}
		
	});

}
}
