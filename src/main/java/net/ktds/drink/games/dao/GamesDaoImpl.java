package net.ktds.drink.games.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GameTypeVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;
import net.ktds.drink.user.vo.UserVO;

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
				query.append(" AND		CTGR_ID NOT IN (SELECT CTGR_ID FROM CTGR WHERE CTGR_NM = '황금열쇠') ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, categoryVO.getParentCategoryId());
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<CategoryVO> categories = new ArrayList<CategoryVO>();
				CategoryVO category = null;

				while (rs.next()) {
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
	public List<CategoryVO> getAdminCategory(CategoryVO categoryVO) {
		return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	CTGR_ID ");
				query.append(" 			, CTGR_NM ");
				query.append(" 			, PRNT_CTGR_ID ");
				query.append(" FROM		CTGR ");					
				query.append(" WHERE	PRNT_CTGR_ID = ? ");			
				query.append(" AND		CTGR_ID NOT IN '16' ");		
				
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
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID ");
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

				while (rs.next()) {
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

	@Override
	public List<GamesVO> allGetGames(String userId) {
		return selectList(new QueryAndResult() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();

				query.append(" SELECT	G.GM_ID, G.GM_NM, G.GM_INFO, C.CTGR_ID, C.CTGR_NM, T.TYP_ID ");
				query.append(" FROM 	CTGR C, GAME_TYPE T, ");
				query.append(" ( ");
				query.append(" 		SELECT 	GM_ID, GM_NM, GM_INFO, CTGR_ID, TYP_ID ");
				query.append(" 		FROM 	GAME ");
				query.append(" 		WHERE CTGR_ID NOT IN (SELECT CTGR_ID FROM CTGR WHERE CTGR_NM = '내게임' OR CTGR_NM = '황금열쇠') ");
				query.append(" 		UNION ");
				query.append(" 		SELECT 	GM_ID, GM_NM, GM_INFO, CTGR_ID, TYP_ID ");
				query.append(" 		FROM 	GAME ");
				query.append(" 		WHERE 	GM_ID IN (SELECT GM_ID FROM CUSTOM WHERE USR_ID = ?) ");
				query.append(" ) G ");
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID AND G.TYP_ID = T.TYP_ID ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, userId);
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<GamesVO> games = new ArrayList<GamesVO>();
				GamesVO gameVO = null;
				CategoryVO categoryVO = null;
				GameTypeVO gameTypeVO = null;

				while (rs.next()) {
					gameVO = new GamesVO();

					gameVO.setGameId(rs.getString("GM_ID"));
					gameVO.setGameName(rs.getString("GM_NM"));
					gameVO.setGameInfo(rs.getString("GM_INFO"));

					categoryVO = gameVO.getCategoryVO();
					gameVO.setCategoryId(rs.getString("CTGR_ID"));
					categoryVO.setCategoryName(rs.getString("CTGR_NM"));

					gameTypeVO = gameVO.getGameTypeVO();
					gameVO.setTypeId(rs.getString("TYP_ID"));

					games.add(gameVO);
				}
				return games;
			}

		});

	}

	
	@Override
	public GamesVO getGame(String gameId) {
		return (GamesVO) selectOne(new QueryAndResult() {

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
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID ");
				query.append(" AND		G.TYP_ID = T.TYP_ID ");
				query.append(" AND		G.GM_ID = ? ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gameId);
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {

				GamesVO gameVO = new GamesVO();
				CategoryVO categoryVO = null;

				if (rs.next()) {

					gameVO.setGameId(rs.getString("GM_ID"));
					gameVO.setGameName(rs.getString("GM_NM"));
					gameVO.setGameInfo(rs.getString("GM_INFO"));

					categoryVO = gameVO.getCategoryVO();
					gameVO.setCategoryId(rs.getString("CTGR_ID"));
					categoryVO.setCategoryName(rs.getString("CTGR_NM"));

					gameVO.setTypeId(rs.getString("TYP_ID"));

				}
				return gameVO;
			}
		});
	}

	@Override
	public int addCustom(GamesVO gamesVO, UserVO userInfo) {
		return insert(new Query() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();

				query.append(" INSERT INTO CUSTOM ( ");
				query.append(" 			 GM_ID ");
				query.append(" 			, USR_ID ) ");
				query.append(" VALUES ( ");
				query.append(" ?, ? ) ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gamesVO.getGameId());
				pstmt.setString(2, userInfo.getUserId());

				return pstmt;
			}
		});
	}

	@Override
	public int addGame(GamesVO gamesVO) {
		return insert(new Query() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();

				query.append(" INSERT INTO GAME ( ");
				query.append(" 			 GM_ID ");
				query.append(" 			, GM_NM ");
				query.append(" 			, GM_INFO ");
				query.append(" 			, CTGR_ID ");
				query.append(" 			, TYP_ID  ");
				query.append(" 			, DTL_IN  ");
				query.append(" 			, TYP_ID  ");
				
				query.append(" VALUES ( ");
				query.append(" 'GM-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(GM_ID_SEQ.NEXTVAL,6,0) ");
				query.append(" 	, ?, ?, ?, '4' ) ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gamesVO.getGameName());
				pstmt.setString(2, gamesVO.getGameInfo());
				pstmt.setString(3, gamesVO.getCategoryId());

				return pstmt;
			}
		});
	}

	@Override
	public GamesVO getGameBy(String gameName) {
		return (GamesVO) selectOne(new QueryAndResult() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				
				query.append(" SELECT	G.GM_ID ");
				query.append(" 			, G.GM_NM ");
				query.append(" 			, G.GM_INFO ");
				query.append(" 			, G.CTGR_ID ");
				query.append(" 			, C.CTGR_NM ");
				query.append("			, G.TYP_ID ");
				query.append(" FROM		GAME G");			
				query.append(" 			, CTGR C");			
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID ");		
				query.append(" AND		G.GM_NM = ? ");		

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gameName);

				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				GamesVO game = null;


				CategoryVO category = null;
		
				if (rs.next()) {
					game = new GamesVO();

					game.setGameId(rs.getString("GM_ID"));
					game.setGameName(rs.getString("GM_NM"));
					game.setGameInfo(rs.getString("GM_INFO"));
					game.setCategoryId(rs.getString("CTGR_ID"));
					game.setTypeId(rs.getString("TYP_ID"));

					category = game.getCategoryVO();
					category.setCategoryName(rs.getString("CTGR_NM"));

				}
				return game;
			}
		});
	}

	@Override
	public int countGameName(String gameName) {
		return (int) selectOne(new QueryAndResult() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		GAME ");
				query.append(" WHERE	GM_NM = ? ");
				query.append(" AND		CTGR_ID NOT IN '16' ");		

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gameName);

				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {

				if (rs.next()) {
					return rs.getInt("CNT");

				}
				return 0;
			}

		});
	}

	@Override
	public List<GamesVO> getGoldenCards() {
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
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID ");
				query.append(" AND		G.TYP_ID = T.TYP_ID ");
				query.append(" AND		G.TYP_ID = (SELECT TYP_ID FROM GAME_TYPE WHERE TYP_NM = '황금열쇠') ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<GamesVO> games = new ArrayList<GamesVO>();
				GamesVO gameVO = null;
				CategoryVO categoryVO = null;
				GameTypeVO gameTypeVO = null;

				while (rs.next()) {
					gameVO = new GamesVO();

					gameVO.setGameId(rs.getString("GM_ID"));
					gameVO.setGameName(rs.getString("GM_NM"));
					gameVO.setGameInfo(rs.getString("GM_INFO"));

					categoryVO = gameVO.getCategoryVO();
					gameVO.setCategoryId(rs.getString("CTGR_ID"));
					categoryVO.setCategoryName(rs.getString("CTGR_NM"));

					gameTypeVO = gameVO.getGameTypeVO();
					gameVO.setTypeId(rs.getString("TYP_ID"));

					games.add(gameVO);
				}
				return games;
			}

		});
	}

	@Override
	public GamesVO getGameDetailBy(String gameId) {
		return (GamesVO) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	G.GM_ID ");
				query.append(" 			, G.GM_NM ");
				query.append(" 			, G.GM_INFO ");
				query.append(" 			, G.CTGR_ID ");
				query.append(" 			, C.CTGR_NM ");
				query.append("			, G.TYP_ID ");
				query.append(" FROM		GAME G");			
				query.append(" 			, CTGR C");			
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID ");		
				query.append(" AND		G.GM_ID = ? ");		
			
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gameId);

				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				GamesVO game = null;
				CategoryVO category = null;
				if(rs.next()){
					game = new GamesVO();
					
					game.setGameId(rs.getString("GM_ID"));
					game.setGameName(rs.getString("GM_NM"));
					game.setGameInfo(rs.getString("GM_INFO"));
					game.setCategoryId(rs.getString("CTGR_ID"));
					game.setTypeId(rs.getString("TYP_ID"));
					
					category = game.getCategoryVO();
					category.setCategoryName(rs.getString("CTGR_NM"));
					
				}
				return game;	
			}
		});
	}

	
	
	@Override
	public CustomVO getCustomDetailBy(String gameId) {
	return (CustomVO) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	CT.GM_ID ");
				query.append(" 			, CT.USR_ID ");
				query.append(" 			, G.GM_NM ");
				query.append(" 			, G.GM_INFO ");
				query.append(" 			, G.CTGR_ID ");
				query.append(" 			, G.TYP_ID ");
				query.append(" 			, U.USR_NICK_NM ");
				query.append(" FROM		CUSTOM CT ");			
				query.append(" 			, GAME G ");			
				query.append(" 			, USR U ");			
				query.append(" WHERE	CT.GM_ID = G.GM_ID ");	
				query.append(" AND		CT.USR_ID = U.USR_ID ");	
				query.append(" AND		CT.GM_ID = ? ");		
			
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gameId);

				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				CustomVO customVO = null;
				GamesVO gameVO = null;
				UserVO userVO = null;
				if(rs.next()){
					
					customVO = new CustomVO();
					
					customVO.setGameId(rs.getString("GM_ID"));
					customVO.setUserId(rs.getString("USR_ID"));
					
					gameVO = customVO.getGamesVO();
					
					gameVO.setGameName(rs.getString("GM_NM"));
					gameVO.setGameInfo(rs.getString("GM_INFO"));
					gameVO.setCategoryId(rs.getString("CTGR_ID"));
					gameVO.setTypeId(rs.getString("TYP_ID"));
					
					userVO = customVO.getUserVO();
					userVO.setUserNickname(rs.getString("USR_NICK_NM"));
					
				}
				return customVO;	
			}
		});
	}
	
	@Override
	public int deleteGames(String gameId) {
	return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				
				query.append(" DELETE ");
				query.append(" FROM		GAME ");
				query.append(" WHERE	GM_ID = ? ");

			
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gameId);

				return pstmt;
			}
		});
	}

	

	@Override
	public int deleteCustom(String gameId) {
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				
				query.append(" DELETE ");
				query.append(" FROM		CUSTOM ");
				query.append(" WHERE	GM_ID = ? ");

			
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gameId);

				return pstmt;
			}
		});
	}

	
	@Override
	public int updateGame(GamesVO gamesVO) {
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	GAME ");
				if( gamesVO.getGameName() != null ) {
					query.append(" SET		GM_NM = ? ");
				}
				if( gamesVO.getGameInfo() != null ) {
					query.append(" , GM_INFO = ? ");
				}
				if( gamesVO.getCategoryId() != null ) {
					query.append(" , CTGR_ID = ? ");
				}
				query.append(" WHERE	GM_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
			
				int index = 1;
				
				if( gamesVO.getGameName() != null ) {
					pstmt.setString(index++, gamesVO.getGameName());
				}
				if( gamesVO.getGameInfo() != null  ) {
					pstmt.setString(index++, gamesVO.getGameInfo());
				}
				if(gamesVO.getCategoryId() != null ) {
					pstmt.setString(index++, gamesVO.getCategoryId());
				}
				
				pstmt.setString(index++, gamesVO.getGameId());
				return pstmt;
			}
		});
	}

	
	@Override
	public int updateCustom(GamesVO gamesVO) {
	return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	GAME ");
				if( gamesVO.getGameName() != null ) {
					query.append(" SET		GM_NM = ? ");
				}
				if( gamesVO.getGameInfo() != null ) {
					query.append(" , GM_INFO = ? ");
				}
				query.append(" WHERE	GM_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
			
				int index = 1;
				
				if( gamesVO.getGameName() != null ) {
					pstmt.setString(index++, gamesVO.getGameName());
				}
				if( gamesVO.getGameInfo() != null  ) {
					pstmt.setString(index++, gamesVO.getGameInfo());
				}

				
				pstmt.setString(index++, gamesVO.getGameId());
				return pstmt;
			}
		});
	}

	
	
	@Override
	public int getConutOfGames(SearchGamesVO searchGames) {
		return (int) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		GAME G ");			
				query.append(" 			,CTGR C ");					
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID ");	
				
				if ( searchGames.getSearchType() == 1 ) {
					query.append(" AND	  C.CTGR_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 2 ) {
					query.append(" AND	( G.GM_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  G.GM_INFO LIKE '%' || ? || '%' ) ");
				}
				else if ( searchGames.getSearchType() == 3 ) {
					query.append(" AND	G.GM_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 4 ) {
					query.append(" AND	  G.GM_INFO LIKE '%' || ? || '%' ");
				}

			
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				
				if ( searchGames.getSearchType() == 1  ) {
					pstmt.setString(1, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 2 ) {
					pstmt.setString(1, searchGames.getSearchKeyword());
					pstmt.setString(2, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 3 ) {
					pstmt.setString(1, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 4 ) {
					pstmt.setString(1, searchGames.getSearchKeyword());
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
	public List<GamesVO> getAllGames(SearchGamesVO searchGames) {
		return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	G.GM_ID ");
				query.append(" 			, G.GM_NM ");
				query.append(" 			, G.GM_INFO ");
				query.append(" 			, C.CTGR_ID ");
				query.append(" 			, C.CTGR_NM ");
				query.append(" 			, G.TYP_ID ");
				query.append(" FROM		GAME G ");			
				query.append(" 			, CTGR C ");			
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID ");					
				query.append(" AND		C.CTGR_ID NOT IN '16' ");					

	
				if ( searchGames.getSearchType() == 1 ) {
					query.append(" AND	  C.CTGR_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 2 ) {
					query.append(" AND	( G.GM_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  G.GM_INFO LIKE '%' || ? || '%' ) ");
				}
				else if ( searchGames.getSearchType() == 3 ) {
					query.append(" AND	G.GM_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 4 ) {
					query.append(" AND	  G.GM_INFO LIKE '%' || ? || '%' ");
				}

				query.append(" ORDER	BY G.GM_ID DESC ");			
				
				String pagingQuery = appendPagingQueryFormat(query.toString());
			
				PreparedStatement pstmt = conn.prepareStatement(pagingQuery);
		
				
				int index = 1;
				
				if ( searchGames.getSearchType() == 1  ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 2 ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
					pstmt.setString(index++, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 3 ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 4 ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
				}
	
				
				pstmt.setInt(index++, searchGames.getEndRowNumber());
				pstmt.setInt(index++, searchGames.getStartRowNumber());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<GamesVO> games = new ArrayList<GamesVO>();
				GamesVO gameVO = null;
				CategoryVO categoryVO = null;

				
				while(rs.next()){
					
					gameVO = new GamesVO();
					
					gameVO.setGameId(rs.getString("GM_ID"));
					gameVO.setGameName(rs.getString("GM_NM"));
					gameVO.setGameInfo(rs.getString("GM_INFO"));
					gameVO.setCategoryId(rs.getString("CTGR_ID"));
					gameVO.setTypeId(rs.getString("TYP_ID"));
					
					categoryVO = gameVO.getCategoryVO();
					categoryVO.setCategoryName(rs.getString("CTGR_NM"));

					
					games.add(gameVO);
				}
				return games;	
			}
		});
	}

	
	@Override
	public int getConutOfCategoryGames(SearchGamesVO searchGames, String categoryId) {
		return (int) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		GAME G ");			
				query.append(" 			,CTGR C ");					
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID ");	
				query.append(" AND		G.CTGR_ID = ? ");	
				
				if ( searchGames.getSearchType() == 1 ) {
					query.append(" AND	  C.CTGR_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 2 ) {
					query.append(" AND	( G.GM_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  G.GM_INFO LIKE '%' || ? || '%' ) ");
				}
				else if ( searchGames.getSearchType() == 3 ) {
					query.append(" AND	G.GM_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 4 ) {
					query.append(" AND	  G.GM_INFO LIKE '%' || ? || '%' ");
				}
			
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, categoryId);
				
				if ( searchGames.getSearchType() == 1  ) {
					pstmt.setString(2, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 2 ) {
					pstmt.setString(2, searchGames.getSearchKeyword());
					pstmt.setString(3, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 3 ) {
					pstmt.setString(2, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 4 ) {
					pstmt.setString(2, searchGames.getSearchKeyword());
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
	public List<GamesVO> getCategoryGames(SearchGamesVO searchGames, String categoryId) {
	return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	G.GM_ID ");
				query.append(" 			, G.GM_NM ");
				query.append(" 			, G.GM_INFO ");
				query.append(" 			, C.CTGR_ID ");
				query.append(" 			, C.CTGR_NM ");
				query.append(" 			, G.TYP_ID ");
				query.append(" FROM		GAME G ");			
				query.append(" 			, CTGR C ");			
				query.append(" WHERE	G.CTGR_ID = C.CTGR_ID ");					
				query.append(" AND		C.CTGR_ID NOT IN '16' ");					
				query.append(" AND		C.CTGR_ID = ? ");					

	
				if ( searchGames.getSearchType() == 1 ) {
					query.append(" AND	  C.CTGR_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 2 ) {
					query.append(" AND	( G.GM_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  G.GM_INFO LIKE '%' || ? || '%' ) ");
				}
				else if ( searchGames.getSearchType() == 3 ) {
					query.append(" AND	G.GM_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 4 ) {
					query.append(" AND	  G.GM_INFO LIKE '%' || ? || '%' ");
				}

				query.append(" ORDER	BY G.GM_ID DESC ");			
				
				String pagingQuery = appendPagingQueryFormat(query.toString());
			
				PreparedStatement pstmt = conn.prepareStatement(pagingQuery);
		
				pstmt.setString(1, categoryId);
				int index = 2;
				
				if ( searchGames.getSearchType() == 1  ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 2 ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
					pstmt.setString(index++, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 3 ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 4 ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
				}
	
				
				pstmt.setInt(index++, searchGames.getEndRowNumber());
				pstmt.setInt(index++, searchGames.getStartRowNumber());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<GamesVO> games = new ArrayList<GamesVO>();
				GamesVO gameVO = null;
				CategoryVO categoryVO = null;

				
				while(rs.next()){
					
					gameVO = new GamesVO();
					
					gameVO.setGameId(rs.getString("GM_ID"));
					gameVO.setGameName(rs.getString("GM_NM"));
					gameVO.setGameInfo(rs.getString("GM_INFO"));
					gameVO.setCategoryId(rs.getString("CTGR_ID"));
					gameVO.setTypeId(rs.getString("TYP_ID"));
					
					categoryVO = gameVO.getCategoryVO();
					categoryVO.setCategoryName(rs.getString("CTGR_NM"));

					
					games.add(gameVO);
				}
				return games;	
			}
		});
	}



	
	
	@Override
	public int getConutOfCustomGames(SearchGamesVO searchGames) {
		return (int) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		CUSTOM CT ");			
				query.append(" 			, GAME G ");			
				query.append(" 			, USR U ");					
				query.append(" WHERE	CT.GM_ID = G.GM_ID ");	
				query.append(" AND		CT.USR_ID = U.USR_ID ");	
				query.append(" AND		G.CTGR_ID = '16' ");	
				
				if ( searchGames.getSearchType() == 1 ) {
					query.append(" AND	  U.USR_NICK_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 2 ) {
					query.append(" AND	( G.GM_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  G.GM_INFO LIKE '%' || ? || '%' ) ");
				}
				else if ( searchGames.getSearchType() == 3 ) {
					query.append(" AND	G.GM_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 4 ) {
					query.append(" AND	  G.GM_INFO LIKE '%' || ? || '%' ");
				}

			
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				
				if ( searchGames.getSearchType() == 1  ) {
					pstmt.setString(1, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 2 ) {
					pstmt.setString(1, searchGames.getSearchKeyword());
					pstmt.setString(2, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 3 ) {
					pstmt.setString(1, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 4 ) {
					pstmt.setString(1, searchGames.getSearchKeyword());
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
	public List<CustomVO> getCustomGames(SearchGamesVO searchGames) {
		
		return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	CT.GM_ID ");
				query.append(" 			, CT.USR_ID ");
				query.append(" 			, G.GM_NM ");
				query.append(" 			, G.GM_INFO ");
				query.append(" 			, G.CTGR_ID ");
				query.append(" 			, G.TYP_ID ");
				query.append(" 			, U.USR_NICK_NM ");
				query.append(" FROM		CUSTOM CT ");			
				query.append(" 			, GAME G ");			
				query.append(" 			, USR U ");			
				query.append(" WHERE	CT.GM_ID = G.GM_ID ");	
				query.append(" AND		CT.USR_ID = U.USR_ID ");	
				query.append(" AND		G.CTGR_ID = '16' ");								

				if ( searchGames.getSearchType() == 1 ) {
					query.append(" AND	  U.USR_NICK_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 2 ) {
					query.append(" AND	( G.GM_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  G.GM_INFO LIKE '%' || ? || '%' ) ");
				}
				else if ( searchGames.getSearchType() == 3 ) {
					query.append(" AND	G.GM_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGames.getSearchType() == 4 ) {
					query.append(" AND	  G.GM_INFO LIKE '%' || ? || '%' ");
				}
				query.append(" ORDER	BY G.GM_ID DESC ");			
				
				String pagingQuery = appendPagingQueryFormat(query.toString());
			
				PreparedStatement pstmt = conn.prepareStatement(pagingQuery);
		
				
				int index = 1;
				
				if ( searchGames.getSearchType() == 1  ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 2 ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
					pstmt.setString(index++, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 3 ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
					
				}
				else if ( searchGames.getSearchType() == 4 ) {
					pstmt.setString(index++, searchGames.getSearchKeyword());
				}
	
				
				pstmt.setInt(index++, searchGames.getEndRowNumber());
				pstmt.setInt(index++, searchGames.getStartRowNumber());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<CustomVO> customs = new ArrayList<CustomVO>();
				CustomVO customVO = null;
				GamesVO gameVO = null;
				UserVO userVO = null;
				
				
				while(rs.next()){
					
					customVO = new CustomVO();
					
					customVO.setGameId(rs.getString("GM_ID"));
					customVO.setUserId(rs.getString("USR_ID"));
					
					gameVO = customVO.getGamesVO();
					
					gameVO.setGameName(rs.getString("GM_NM"));
					gameVO.setGameInfo(rs.getString("GM_INFO"));
					gameVO.setCategoryId(rs.getString("CTGR_ID"));
					gameVO.setTypeId(rs.getString("TYP_ID"));
					
					userVO = customVO.getUserVO();
					userVO.setUserNickname(rs.getString("USR_NICK_NM"));

					customs.add(customVO);
				}
				return customs;	
			}
		});
	}




	@Override
	public List<GamesVO> allGetGames() {
		return selectList(new QueryAndResult() {

            @Override
            public PreparedStatement query(Connection conn) throws SQLException {
                StringBuffer query = new StringBuffer();
                query.append(" SELECT    G.GM_ID ");
                query.append("             , G.GM_NM ");
                query.append("             , G.GM_INFO ");
                query.append("             , C.CTGR_ID ");
                query.append("             , C.CTGR_NM ");
                query.append("             , T.TYP_ID ");
                query.append(" FROM        GAME G ");
                query.append("             , CTGR C ");
                query.append("             , GAME_TYPE T ");
                query.append(" WHERE    G.CTGR_ID = C.CTGR_ID ");
                query.append(" AND        G.TYP_ID = T.TYP_ID ");
                query.append(" AND        G.TYP_ID NOT IN (SELECT TYP_ID FROM GAME_TYPE WHERE TYP_NM = '황금열쇠' OR TYP_NM = '내게임') ");
                
                
                PreparedStatement pstmt = conn.prepareStatement(query.toString());
                return pstmt;
            }

            @Override
            public Object makeObject(ResultSet rs) throws SQLException {
                List<GamesVO> games = new ArrayList<GamesVO>();
                GamesVO gameVO = null;
                CategoryVO categoryVO = null;
                GameTypeVO gameTypeVO = null;

                while (rs.next()) {
                    gameVO = new GamesVO();

                    gameVO.setGameId(rs.getString("GM_ID"));
                    gameVO.setGameName(rs.getString("GM_NM"));
                    gameVO.setGameInfo(rs.getString("GM_INFO"));

                    categoryVO = gameVO.getCategoryVO();
                    gameVO.setCategoryId(rs.getString("CTGR_ID"));
                    categoryVO.setCategoryName(rs.getString("CTGR_NM"));

                    gameTypeVO = gameVO.getGameTypeVO();
                    gameVO.setTypeId(rs.getString("TYP_ID"));

                    games.add(gameVO);
                }
                return games;
            }

        });
	}

	

	






}
