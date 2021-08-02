package com.github.shirahata777;

import com.github.shirahata777.api.Contacting;

import io.helidon.common.LogConfig;
import io.helidon.common.reactive.Single;
import io.helidon.config.Config;
import io.helidon.health.HealthSupport;
import io.helidon.health.checks.HealthChecks;
import io.helidon.media.jsonp.JsonpSupport;
import io.helidon.metrics.MetricsSupport;
import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServer;

/**
 * The application main class.
 */
public final class Main {

	private Main() {
	}

	public static void main(final String[] args) {
		startServer();
	}

	static Single<WebServer> startServer() {

		// load logging configuration
		LogConfig.configureRuntime();

		// By default this will pick up application.yaml from the classpath
		Config config = Config.create();

		WebServer server = WebServer.builder(createRouting(config)).config(config.get("server"))
				.addMediaSupport(JsonpSupport.create()).build();

		Single<WebServer> webserver = server.start();

		// Try to start the server. If successful, print some info and arrange to
		// print a message at shutdown. If unsuccessful, print the exception.
		webserver.thenAccept(ws -> {
			System.out.println("WEB server is up! http://localhost:" + ws.port());
			ws.whenShutdown().thenRun(() -> System.out.println("WEB server is DOWN. Good bye!"));
		}).exceptionallyAccept(t -> {
			System.err.println("Startup failed: " + t.getMessage());
			t.printStackTrace(System.err);
		});

		return webserver;
	}

	/**
	 * Creates new {@link Routing}.
	 *
	 * @return routing configured with JSON support, a health check, and a service
	 * @param config configuration of this server
	 */
	private static Routing createRouting(Config config) {

		MetricsSupport metrics = MetricsSupport.create();
		Contacting contact = new Contacting();
		HealthSupport health = HealthSupport.builder().addLiveness(HealthChecks.healthChecks()).build(); 

		return Routing.builder()
				.register(health)
				.register("/api", contact)
				.register(metrics).build();
	}
}
