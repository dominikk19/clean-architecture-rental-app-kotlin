package pl.dkiszka.api.apartment

import io.restassured.http.ContentType
import pl.dkiszka.BaseIntegrationTest
import pl.dkiszka.api.apartment.dto.GetApartmentResponseDto
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

    def 'should return created apartment'() {
        given:
        def apartmentId = createSomeApartment()

        when:
        def response = specWithoutAuth()
                .when()
                .get(baseApiUrl + "/apartment/${apartmentId}")
        then:
        response.statusCode() == 200
        def getApartmentResponseDto = objectMapper.readValue(response.getBody().asString(), GetApartmentResponseDto.class)
        getApartmentResponseDto != null
    }


    private String createSomeApartment() {
        def apartmentRequest = ApartmentRequestDtoFactory.someApartmentRequestDto()
                .build()
        return specWithoutAuth()
                .when()
                .body(apartmentRequest)
                .contentType(ContentType.JSON)
                .post(baseApiUrl + "/apartment")
                .body().jsonPath().get("id").toString()
    }
}
