package pl.dkiszka.application.apartment.command

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkiszka.domain.apartment.Address
import pl.dkiszka.domain.apartment.Apartment
import pl.dkiszka.domain.apartment.ApartmentId
import pl.dkiszka.domain.apartment.ApartmentRepository
import pl.dkiszka.domain.apartment.Room
import pl.dkiszka.domain.shared.ClockProvider


@Service
@Transactional
private class CreateApartmentHandler(
    private val apartmentRepository: ApartmentRepository,
    private val clockProvider: ClockProvider,
) : CreateApartment {
    override fun invoke(command: CreateApartmentCommand): ApartmentId {
        val newApartment = Apartment.create(
            ownerId = command.ownerId,
            address = Address(
                street = command.street,
                postalCode = command.postalCode,
                houseNumber = command.houseNumber,
                apartmentNumber = command.apartmentNumber,
                city = command.city,
                country = command.country,
            ),
            description = command.description,
            rooms = command.roomsDefinition.map {
                Room.from(
                    name = it.key,
                    size = it.value,
                )
            }.toSet(),
            clock = clockProvider.get()
        )
        val savedApartment = apartmentRepository.save(newApartment)
            .also {
                log.info { "Create new apartment with id: ${it.apartmentId}" }
            }
        return savedApartment.apartmentId
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}