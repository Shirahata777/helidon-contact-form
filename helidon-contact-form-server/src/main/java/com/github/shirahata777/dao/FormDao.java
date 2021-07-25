package com.github.shirahata777.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonException;
import javax.json.JsonObject;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.helidon.common.http.Http;
import io.helidon.common.http.Parameters;
import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

public class FormDao implements Service {
	
    private final AtomicReference<String> formDao = new AtomicReference<>();
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    
    public FormDao(Config config) {
    	formDao.set(null);
	}

	public boolean saveFormData(String name, String email, String content) {
		
		// Examines both filesystem and classpath for .properties file
		HikariConfig config = new HikariConfig("/src/path/resources/hikari.properties");
		HikariDataSource ds = new HikariDataSource(config);

		String sql = "INSERT INTO contact (name, email, content) values (?, ?, ?)";

		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, content);
			boolean ret = stmt.executeUpdate() != 0;
			ds.close();
			return ret;
		} catch (SQLException e) {
			ds.close();
			throw new RuntimeException(e);
		}

		
	}
	
	//	以下後に移行予定
	
	/**
     * A service registers itself by updating the routing rules.
     * @param rules the routing rules.
     */
    @Override
    public void update(Routing.Rules rules) {
        rules.get("/save", this::getFormDataHandler);
    }
  

    /**
     * Return a greeting message using the name that was provided.
     * @param request the server request
     * @param response the server response
     */
    private void getFormDataHandler(ServerRequest request, ServerResponse response) {
        Map params = request.queryParams().toMap();
        String name = params.get("name").toString();
        String email = params.get("email").toString();
        String content = params.get("content").toString();
        saveFormData(name, email, content);
        sendResponse(response);
    }

    private void sendResponse(ServerResponse response) {
        String msg = String.format("Save OK!");

        JsonObject returnObject = JSON.createObjectBuilder()
                .add("message", msg)
                .build();
        response.send(returnObject);
    }

}
