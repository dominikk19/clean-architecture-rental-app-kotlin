package pl.dkiszka.api.apartment

import io.restassured.http.ContentType
import pl.dkiszka.BaseIntegrationTest
import pl.dkiszka.utils.factories.ApartmentRequestDtoFactory

class ApartmentEndpointSpec extends BaseIntegrationTest {

    def 'should create apartment'() {
        given:
        def apartmentRequest = ApartmentRequestDtoFactory.someApartmentRequestDto()
                .build()
        when:
        def response = specWithoutAuth()
                .when()
                .body(apartmentRequest)
                .contentType(ContentType.JSON)
                .post(baseApiUrl + "/apartment")
        then:
        response.statusCode() == 201
        def apartmentId = UUID.fromString(response.body().jsonPath().get("id").toString())
        apartmentId != null
    }
}
