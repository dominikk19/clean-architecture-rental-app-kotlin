package pl.dkiszka.domain.apartment

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class ApartmentId(
    @Column(name = "APARTMENT_ID", nullable = false)
    val id: UUID = UUID.randomUUID()
) {

    constructor(id: String) : this(UUID.fromString(id))

    override fun toString(): String {
        return id.toString()
    }
}
