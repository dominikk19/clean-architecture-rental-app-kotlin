package pl.dkiszka.application.apartment.command

import pl.dkiszka.domain.apartment.ApartmentId

interface CreateApartment {
    operator fun invoke(command: CreateApartmentCommand): ApartmentId
}

data class CreateApartmentCommand(
    val ownerId: String,
    val street: String,
    val postalCode: String,
    val houseNumber: String,
    val apartmentNumber: String?,
    val city: String,
    val country: String,
    val description: String?,
    val roomsDefinition: Map<String, Double>
)