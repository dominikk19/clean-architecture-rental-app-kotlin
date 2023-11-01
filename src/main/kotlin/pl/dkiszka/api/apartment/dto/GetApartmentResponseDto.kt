package pl.dkiszka.api.apartment.dto

import pl.dkiszka.domain.apartment.ApartmentView

data class GetApartmentResponseDto(
    val ownerId: String,
) {
    companion object {
        fun from(apartment: ApartmentView): GetApartmentResponseDto {
            return GetApartmentResponseDto(
                ownerId = apartment.ownerId,
            )
        }
    }
}
