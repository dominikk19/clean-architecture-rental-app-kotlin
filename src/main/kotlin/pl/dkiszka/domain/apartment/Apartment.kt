package pl.dkiszka.domain.apartment

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import pl.dkiszka.domain.shared.BaseEntity
import pl.dkiszka.domain.shared.ENTITY_GENERATOR_NAME
import java.time.Clock

@Entity
@Table(name = "APARTMENT")
@SequenceGenerator(name = ENTITY_GENERATOR_NAME, sequenceName = "APARTMENT_SEQ_ID", allocationSize = 1)
class Apartment private constructor(
    @Embedded
    override val ownerId: OwnerId,
    @Embedded
    override val address: Address,
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ROOM")
    override val rooms: Set<Room> = mutableSetOf(),
    @Column(name = "DESCRIPTION")
    override val description: String?,
    clock: Clock
) : BaseEntity(clock), ApartmentView {

    @Embedded
    val apartmentId: ApartmentId = ApartmentId()

    companion object {
        fun create(
            ownerId: String,
            address: Address,
            rooms: Set<Room>,
            description: String?,
            clock: Clock,
        ) = Apartment(
            ownerId = OwnerId(ownerId),
            address = address,
            rooms = rooms,
            description = description,
            clock = clock,
        )

    }
}