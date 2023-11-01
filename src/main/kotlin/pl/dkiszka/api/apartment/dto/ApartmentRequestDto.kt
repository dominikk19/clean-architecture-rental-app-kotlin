package pl.dkiszka.api.apartment.dto

import pl.dkiszka.application.apartment.command.CreateApartmentCommand

data class ApartmentRequestDto(
    val ownerId: String,
    val street: String,
    val postalCode: String,
    val houseNumber: String,
    val apartmentNumber: String?,
    val city: String,
    val country: String,
    val description: String?,
    val roomsDefinition: Map<String, Double> = emptyMap()
) {
    fun toCommand() = CreateApartmentCommand(
        ownerId = ownerId,
        street = street,
        postalCode = postalCode,
        houseNumber = houseNumber,
        apartmentNumber = apartmentNumber,
        city = city,
        country = country,
        description = description,
        roomsDefinition = roomsDefinition,
    )
}
