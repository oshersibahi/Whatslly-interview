package whatslly.com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class EndpointTesting {

	public ServerLocation location = ServerLocation.BRAZIL;
	public String url;
	public RestTemplate restTemplate;
	
	@BeforeAll
	public void setupAll() {
			url = new StringBuilder("https://api-")
					.append(location.label)
					.append("1.whatslly.com/test/ping.json")
					.toString();
	}
	
	@BeforeEach
	public void setup() { 
		restTemplate = new RestTemplate();
	}
	
	@Test
	@DisplayName("Pinging the selected server")
	public void get() {
		Assertions.assertTrue(restTemplate.getForEntity(url, Object.class).getStatusCode() == HttpStatus.OK);
	}

	/**
	 * @author Osher Sibahi
	 * The enum contains country names and matched labels. 
	 */
	private enum ServerLocation {
		
		US("us") , BRAZIL("br");
		
		public final String label;
		
		ServerLocation(String label) {
			this.label = label;
		} 
		
	};
}
