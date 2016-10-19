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
	public List<GamesVO> allGetGames() {
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
				query.append(" AND		G.TYP_ID NOT IN (SELECT TYP_ID FROM GAME_TYPE WHERE TYP_NM = '황금열쇠' OR TYP_NM = '내게임') ");

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
	public int addGame(String gameName, String gameInfo) {
		return insert(new Query() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();

				query.append(" INSERT INTO GAME ( ");
				query.append(" 			 GM_ID ");
				query.append(" 			, GM_NM ");
				query.append(" 			, GM_INFO ");
				query.append(" 			, CTGR_ID ");
				query.append(" 			, TYP_ID ) ");
				query.append(" VALUES ( ");
				query.append(" 'GM-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(GM_ID_SEQ.NEXTVAL,6,0) ");
				query.append(" 	, ?, ?, '8', '4' ) ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gameName);
				pstmt.setString(2, gameInfo);

				return pstmt;
			}
		});
	}

	@Override
	public GamesVO getGameBy(String gameName, String gameInfo) {
		return (GamesVO) selectOne(new QueryAndResult() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	GM_ID ");
				query.append(" 			, GM_NM ");
				query.append(" 			, GM_INFO ");
				query.append(" 			, CTGR_ID ");
				query.append("			, TYP_ID ");
				query.append(" FROM		GAME G");
				query.append(" WHERE	GM_NM = ? ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, gameName);

				return pstmt;
			}

			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				GamesVO game = null;
				if (rs.next()) {
					game = new GamesVO();

					game.setGameId(rs.getString("GM_ID"));
					game.setGameName(rs.getString("GM_NM"));
					game.setGameInfo(rs.getString("GM_INFO"));
					game.setCategoryId(rs.getString("CTGR_ID"));
					game.setTypeId(rs.getString("TYP_ID"));

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

}
