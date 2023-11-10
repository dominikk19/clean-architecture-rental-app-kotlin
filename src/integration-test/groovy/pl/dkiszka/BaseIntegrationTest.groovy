package pl.dkiszka

import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured
import jakarta.annotation.PostConstruct
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0)
class BaseIntegrationTest extends Specification {

    @Autowired
    protected ObjectMapper objectMapper

    @LocalServerPort
    private int port

    @Shared
    protected String baseApiUrl

    @PostConstruct
    private void postConstruct() {
        this.baseApiUrl = "http://localhost:$port/api/v1"
    }

    @BeforeEach
    protected void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

    protected specWithoutAuth() {
        return RestAssured.given()
    }
}
