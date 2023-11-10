package pl.dkiszka.api.apartment.dto

import pl.dkiszka.domain.apartment.AddressView
import pl.dkiszka.domain.apartment.ApartmentView


data class GetApartmentResponseDto(
    val ownerId: String,
    val address: AddressDto,
    val rooms: List<RoomDto>,
    val description: String?,
) {

    companion object {
        fun from(agreementView: ApartmentView): GetApartmentResponseDto {
            return with(agreementView) {
                GetApartmentResponseDto(
                    ownerId = ownerId.id,
                    address = AddressDto.from(address),
                    rooms = rooms.map {
                        RoomDto(
                            name = it.name,
                            size = it.size,
                        )
                    },
                    description = description,
                )
            }
        }

    }

    data class AddressDto(
        val street: String,
        val postalCode: String,
        val houseNumber: String,
        val apartmentNumber: String?,
        val city: String,
        val country: String,
    ) {
        companion object {
            fun from(address: AddressView): AddressDto {
                return with(address) {
                    AddressDto(
                        street = street,
                        postalCode = postalCode,
                        houseNumber = houseNumber,
                        apartmentNumber = apartmentNumber,
                        city = city,
                        country = country,
                    )
                }
            }
        }
    }


    data class RoomDto(
        val name: String,
        val size: Double,
    )

}