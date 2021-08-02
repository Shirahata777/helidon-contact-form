package com.github.shirahata777.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.shirahata777.query.ContactQuery;
import com.zaxxer.hikari.HikariDataSource;

public class FormDao {

	private static HikariDataSource setDataSource() throws SQLException {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl("jdbc:mysql://mysql:3306/contact_db?useSSL=false");
		ds.setUsername("root");
		ds.setPassword("contact_db_password");
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return ds;
	}

	public boolean saveFormData(ContactQuery query) {
		String sql = "INSERT INTO contact (name, email, content) values (?, ?, ?)";

		try (Connection conn = setDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, query.getName());
			stmt.setString(2, query.getEmail());
			stmt.setString(3, query.getContent());
			boolean ret = stmt.executeUpdate() != 0;
			return ret;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Map<String, String>> getAllFormData() {

		String sql = "SELECT * FROM contact";

		List<Map<String, String>> formDataList = new ArrayList<>();

		try (Connection conn = setDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, String> formData = new LinkedHashMap<>();
//				formData.add(String.format("%d", rs.getInt("id")));
				formData.put("name", rs.getString("name"));
				formData.put("email", rs.getString("email"));
				formData.put("content", rs.getString("content"));
				formDataList.add(formData);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return formDataList;

	}
}
