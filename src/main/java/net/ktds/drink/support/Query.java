package net.ktds.drink.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Query {
	
	public PreparedStatement query(Connection conn) throws SQLException ; 
}
