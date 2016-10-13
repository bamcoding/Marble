package net.ktds.drink.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoSupport {
	
	public int insert(Query query) {
		oracleDriverClassLoad();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = query.query(conn);
			int insertCount = pstmt.executeUpdate();
			return insertCount;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			close(null,pstmt,conn);
		}
	}
	
	public int delete(Query query) {
		oracleDriverClassLoad();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = query.query(conn);
			int insertCount = pstmt.executeUpdate();
			return insertCount;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			close(null,pstmt,conn);
		}
	}
	
	public Object selectOne(QueryAndResult queryAndResult) {
		oracleDriverClassLoad();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = queryAndResult.query(conn);
			rs = pstmt.executeQuery();
			//selectOne 리턴 
			return queryAndResult.makeObject(rs);
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			close(rs,pstmt,conn);
		}
	}
	
	
	public List selectList(QueryAndResult queryAndResult) {
		oracleDriverClassLoad();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = queryAndResult.query(conn);
			rs = pstmt.executeQuery();
			//selectOne 리턴 
			return (List) queryAndResult.makeObject(rs);
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			close(rs,pstmt,conn);
		}
	}
	
	public String appendPagingQueryFormat(String query) {
        StringBuffer beforeAppend = new StringBuffer();
        beforeAppend.append(" SELECT    * ");
        beforeAppend.append(" FROM        ( ");
        beforeAppend.append("              SELECT  A.*, ROWNUM RNUM ");
        beforeAppend.append("              FROM        ( ");
        
        StringBuffer afterAppend = new StringBuffer();
        afterAppend.append("                           ) A ");
        afterAppend.append("                WHERE   ROWNUM <= ? ");
        afterAppend.append("              ) ");
        afterAppend.append(" WHERE   RNUM >= ? ");
        
        return beforeAppend.toString() + query + afterAppend.toString();
	}
	
	
	private void oracleDriverClassLoad() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@10.225.152.174:1521:XE","DRINK","DRINK");
	}
	
	private void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if ( rs != null ) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
		if ( pstmt != null ) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
		if ( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
}
