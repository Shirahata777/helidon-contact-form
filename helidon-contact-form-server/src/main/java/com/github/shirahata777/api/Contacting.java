package com.github.shirahata777.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import com.github.shirahata777.dao.FormDao;

public class Contacting implements Service {

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

		ObjectMapper mapper = new ObjectMapper();

		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(formDataList);
		} catch (IOException e) {
			jsonString = "No Create jsonObject";
			e.printStackTrace();
		}

		sendResponse(response, jsonString);
	}

	private void sendResponse(ServerResponse response, String msg) {
		response.send(msg);
	}

}
