package net.ktds.drink.category.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ktds.drink.category.vo.CategoryVO;
import net.ktds.drink.support.DaoSupport;
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
				query.append(" SELECT	CTGR_ID ");
				query.append(" 			, CTGR_NM ");
				query.append(" 			, PRNT_CTGR_ID ");
				query.append(" FROM		CTGR ");

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
}
