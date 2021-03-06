package net.ktds.drink.category.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.category.vo.CategoryVO;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.Query;
import net.ktds.drink.support.QueryAndResult;

public class CategoryDaoImpl extends DaoSupport implements CategoryDao {

	@Override
	public List<CategoryVO> getAllCategoryById(String parentCategoryId) {
		return selectList(new QueryAndResult(){

			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	CTGR_ID ");
				query.append(" 			, CTGR_NM ");
				query.append(" 			, PRNT_CTGR_ID ");
				query.append(" FROM		CTGR ");
				query.append(" WHERE	PRNT_CTGR_ID = ? ");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, parentCategoryId);
				return pstmt;
			}

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
	public List<CategoryVO> getAllCategory() {
		// TODO Auto-generated method stub
		return selectList(new QueryAndResult(){

			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT *			");
				query.append(" FROM CTGR C 			");
				query.append(" CONNECT BY PRIOR C.CTGR_ID = C.PRNT_CTGR_ID			");
				query.append(" START WITH C.PRNT_CTGR_ID = 0 AND C.CTGR_NM != 'ROOT'			");

				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				return pstmt;
			}

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
	public int addCategory(CategoryVO categoryVO) {
		return insert(new Query() {
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT INTO DRINK.CTGR (		");
				query.append("    CTGR_ID, CTGR_NM, PRNT_CTGR_ID) 		");
				query.append(" VALUES ( ?,			");
				query.append("  ?, ?		");
				query.append("  )			");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, categoryVO.getCategoryId());
				pstmt.setString(2, categoryVO.getCategoryName());
				pstmt.setString(3, categoryVO.getParentCategoryId());
				return pstmt;
			}
		});
	}

	@Override
	public int modifyCategory(String input, String selectedName) {
		return insert(new Query() {
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	DRINK.CTGR 	");
				query.append(" SET		CTGR_NM = ?	");
				query.append(" WHERE	CTGR_NM = ?	");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, input);
				pstmt.setString(2, selectedName);
				return pstmt;
			}
		});
	}

	@Override
	public int deleteCategory(String selectedName) {
		return insert(new Query() {
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" DELETE				");
				query.append(" FROM		DRINK.CTGR 	");
				query.append(" WHERE	CTGR_NM = ?	");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, selectedName);
				return pstmt;
			}
		});
	}

	@Override
	public int countName(String input) {
		return (int) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT	");
				query.append(" FROM		DRINK.CTGR 		");
				query.append(" WHERE	CTGR_NM = ?		");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, input);
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				int count=0;
				if(rs.next()){
					count = rs.getInt("CNT");
				}
				return count;
			}
		});
	}

	@Override
	public int countChild(String input) {
		// TODO Auto-generated method stub
		return (int) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT					");
				query.append(" FROM	DRINK.CTGR C1, DRINK.CTGR C2 		");
				query.append(" WHERE	C1.CTGR_ID = C2.PRNT_CTGR_ID	");
				query.append(" AND     C1.CTGR_NM = ?					");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, input);
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				int count=0;
				if(rs.next()){
					count = rs.getInt("CNT");
				}
				return count;
			}
		});
	}

	@Override
	public int getNewCategoryId() {
		
		return (int) selectOne(new QueryAndResult() {			
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	CTGR_ID_SEQ.NEXTVAL SEQ	");
				query.append(" FROM		DRINK.CTGR 		");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				int result = 0;
				if(rs.next()){
					result = rs.getInt(1);
				}
				return result;
			}
		});
	}
}
