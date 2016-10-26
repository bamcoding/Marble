package net.ktds.drink.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.admin.vo.GameTypeVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;

public class GameTypeDaoImpl extends DaoSupport implements GameTypeDao {
	@Override
	public int getConutOfGameType(SearchGamesVO searchGame) {
		return (int) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		GAME_TYPE ");			
				
				if ( searchGame.getSearchType() == 1 ) {
					query.append(" WHERE	( TYP_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  TYP_INFO LIKE '%' || ? || '%' ) ");
					
				}
				else if ( searchGame.getSearchType() == 2 ) {
					query.append(" WHERE	  TYP_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGame.getSearchType() == 3 ) {
					query.append(" WHERE	TYP_INFO LIKE '%' || ? || '%' ");
				}


			
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
	
				if ( searchGame.getSearchType() == 1  ) {
					pstmt.setString(1, searchGame.getSearchKeyword());
					pstmt.setString(2, searchGame.getSearchKeyword());
					
				}
				else if ( searchGame.getSearchType() == 2 ) {
					pstmt.setString(1, searchGame.getSearchKeyword());
				
					
				}
				else if ( searchGame.getSearchType() == 3 ) {
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
	public List<GameTypeVO> getGameType(SearchGamesVO searchGame) {
		
		return selectList(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	TYP_ID ");
				query.append(" 			, TYP_NM ");
				query.append(" 			, TYP_INFO ");
				query.append(" FROM		GAME_TYPE ");											

				if ( searchGame.getSearchType() == 1 ) {
					query.append(" WHERE	( TYP_NM LIKE '%' || ? || '%' ");
					query.append(" OR	  TYP_INFO LIKE '%' || ? || '%' ) ");	
				}
				else if ( searchGame.getSearchType() == 2 ) {
					query.append(" WHERE	  TYP_NM LIKE '%' || ? || '%' ");
				}
				else if ( searchGame.getSearchType() == 3 ) {
					query.append(" WHERE	TYP_INFO LIKE '%' || ? || '%' ");
				}

				query.append(" ORDER	BY TYP_ID DESC ");			
				
				String pagingQuery = appendPagingQueryFormat(query.toString());
			
				PreparedStatement pstmt = conn.prepareStatement(pagingQuery);
		
				
				int index = 1;
				
				if ( searchGame.getSearchType() == 1  ) {
					pstmt.setString(index++, searchGame.getSearchKeyword());
					pstmt.setString(index++, searchGame.getSearchKeyword());
					
				}
				else if ( searchGame.getSearchType() == 2 ) {
					pstmt.setString(index++, searchGame.getSearchKeyword());
				}
				else if ( searchGame.getSearchType() == 3 ) {
					pstmt.setString(index++, searchGame.getSearchKeyword());
					
				}

				
				pstmt.setInt(index++, searchGame.getEndRowNumber());
				pstmt.setInt(index++, searchGame.getStartRowNumber());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				List<GameTypeVO> gameTypes = new ArrayList<GameTypeVO>();
				GameTypeVO gameTypeVO = null;

				while(rs.next()){
					
					gameTypeVO = new GameTypeVO();
					
					gameTypeVO.setTypeId(rs.getString("TYP_ID"));
					gameTypeVO.setTypeName(rs.getString("TYP_NM"));
					gameTypeVO.setTypeInfo(rs.getString("TYP_INFO"));

					gameTypes.add(gameTypeVO);
				}
				return gameTypes;	
			}
		});
	}
	@Override
	public int countTypeName(String typeName) {
		return (int) selectOne(new QueryAndResult() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		GAME_TYPE ");
				query.append(" WHERE	TYP_NM = ? ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, typeName);

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
	public int addType(GameTypeVO type) {
		return insert(new Query() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();

				query.append(" INSERT INTO GAME_TYPE ( ");
				query.append(" 			 TYP_ID ");
				query.append(" 			, TYP_NM ");
				query.append(" 			, TYP_INFO ) ");
				query.append(" VALUES ( ");
				query.append(" TYP_ID_SEQ.NEXTVAL ");
				query.append(" 	, ?, ? ) ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, type.getTypeName());
				pstmt.setString(2, type.getTypeInfo());

				return pstmt;
			}
		});
	}
	@Override
	public int deleteType(String typeId) {
		
		return insert(new Query() {

			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();

				query.append(" DELETE ");
				query.append(" FROM		GAME_TYPE  ");
				query.append(" WHERE	TYP_ID = ? ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, typeId);

				return pstmt;
			}
		});
	}
	@Override
	public GameTypeVO getTypeAt(String typeId) {
		return (GameTypeVO) selectOne ( new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	TYP_ID ");
				query.append(" 			, TYP_NM ");
				query.append(" 			, TYP_INFO ");
				query.append(" FROM		GAME_TYPE ");
				query.append(" WHERE	TYP_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, typeId);
				
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				GameTypeVO typeVO = new GameTypeVO();
				
				if( rs.next()) {
					typeVO.setTypeId(rs.getString("TYP_ID"));
					typeVO.setTypeName(rs.getString("TYP_NM"));
					typeVO.setTypeInfo(rs.getString("TYP_INFO"));
					
				}
				
				return typeVO;
			}
		});
	}
	@Override
	public int updateType(GameTypeVO typeVO) {
		return insert (new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	GAME_TYPE ");
				
				if(typeVO.getTypeName() != null ) {
					query.append(" SET		TYP_NM = ? ");
				}
				if (typeVO.getTypeInfo() != null ) {
					query.append(" 			, TYP_INFO = ? ");
				}
				query.append(" WHERE	TYP_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				
				int index = 1;
				
				if (typeVO.getTypeName() != null ) {
					pstmt.setString(index++, typeVO.getTypeName());
				}
				if (typeVO.getTypeInfo() != null ) {
					pstmt.setString(index++, typeVO.getTypeInfo());
				}
				pstmt.setString(index++, typeVO.getTypeId());
								
				return pstmt;
			}
		});
	}

	
	
}
