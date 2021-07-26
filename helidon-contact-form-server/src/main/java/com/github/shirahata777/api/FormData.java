package com.github.shirahata777.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import org.codehaus.jackson.map.ObjectMapper;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import com.github.shirahata777.dao.FormDao;

public class FormData implements Service {

	private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

	@Override
	public void update(Routing.Rules rules) {
		rules.get("/save", this::saveFormDataHandler).get("/get_all", this::getFormDataHandler);
	}

	private void saveFormDataHandler(ServerRequest request, ServerResponse response) {
		Map<String, List<String>> params = request.queryParams().toMap();
		String name = params.get("name").get(0);
		String email = params.get("email").get(0);
		String content = params.get("content").get(0);

		FormDao formDao = new FormDao();
		formDao.saveFormData(name, email, content);

		sendResponse(response, "Save OK!");
	}

	private void getFormDataHandler(ServerRequest request, ServerResponse response) {
		List<Map<String, String>> formDataList = new ArrayList<>();

		FormDao formDao = new FormDao();
		formDataList = formDao.getAllFormData();

		// Map⇒JSON文字列
		ObjectMapper mapper = new ObjectMapper();
		List<String> jsonObjects = new ArrayList<>();
		
		for (Map<String, String> map : formDataList) {	
			try {
				String jsonMap = new ObjectMapper().writeValueAsString(map);
				jsonObjects.add(jsonMap);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
		}
		
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(jsonObjects);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		sendResponse(response, jsonString);
	}

	private void sendResponse(ServerResponse response, String msg) {

		JsonObject returnObject = JSON.createObjectBuilder().add("message", msg).build();
		response.send(returnObject);
	}

}
