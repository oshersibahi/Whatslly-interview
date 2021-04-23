package whatslly.com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import whatslly.com.enums.ServerLocation;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestPropertySource(locations = "integration.properties")
public class EndpointTesting {

	@Value("${whatslly.com.server.location}")
	private String location;
	public ServerLocation host;
	public String url;
	public RestTemplate restTemplate;

	/**
	 * setupAll(): void - Builds the end point URL accordingly the location
	 * configured in the 'integration.properties' file.
	 * 
	 * @throws Exception When location isn't matching a {@link ServerLocation} value.
	 * 
	 */
	@BeforeAll
	public void setupAll() throws Exception {

		for (ServerLocation curr : ServerLocation.values()) {
			if (location.equalsIgnoreCase(curr.name)) {
				this.host = curr;
			}
		}

		if (host != null) {
			url = new StringBuilder("https://api-").append(host.label).append("1.whatslly.com/test/ping.json")
					.toString();
		} else {
			throw new Exception("setupAll() failed; location '" + location + "' not exists");
		}

	}

	/**
	 * setup(): void - Sets up a new restTemplate object for each test;
	 */
	@BeforeEach
	public void setup() {
		restTemplate = new RestTemplate();
	}

	/**
	 * get(): void - Tests if the server is alive
	 */
	@Test
	@DisplayName("Pinging the selected server")
	public void get() {
		Assertions.assertTrue(restTemplate.getForEntity(url, Object.class).getStatusCode() == HttpStatus.OK);
	}

}
