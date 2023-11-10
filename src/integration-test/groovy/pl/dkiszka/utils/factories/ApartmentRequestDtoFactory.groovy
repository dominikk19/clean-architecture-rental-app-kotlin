package pl.dkiszka.utils.factories

import pl.dkiszka.api.apartment.dto.ApartmentRequestDto

class ApartmentRequestDtoFactory {

    String ownerId = "OWNER_1111"


    static ApartmentRequestDtoFactory someApartmentRequestDto() {
        return new ApartmentRequestDtoFactory()
    }

    ApartmentRequestDto build() {
        return new ApartmentRequestDto(
                ownerId,
                "some_street",
                "00-000",
                "3A",
                "12",
                "some_city",
                "some_country",
                "some_description",
                Map.of("Day Room", Double.valueOf(25))
        )
    }

}
