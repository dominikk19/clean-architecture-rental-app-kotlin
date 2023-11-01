package pl.dkiszka.domain.apartment

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import pl.dkiszka.domain.shared.BaseEntity
import pl.dkiszka.domain.shared.ENTITY_GENERATOR_NAME
import java.time.Clock

@Entity
@Table(name = "APARTMENT")
@SequenceGenerator(name = ENTITY_GENERATOR_NAME, sequenceName = "APARTMENT_SEQ_ID", allocationSize = 1)
class Apartment private constructor(
    @Column(name = "OWNER_ID")
    override val ownerId: String,
    @Embedded
    val address: Address,
    @Column(name = "DESCRIPTION")
    val description: String?,
    clock: Clock
) : BaseEntity(clock), ApartmentView {

    @Embedded
    val apartmentId: ApartmentId = ApartmentId()

    companion object {
        fun create(
            ownerId: String,
            address: Address,
            description: String?,
            clock: Clock,
        ) = Apartment(
            ownerId = ownerId,
            address = address,
            description = description,
            clock = clock,
        )

    }
}