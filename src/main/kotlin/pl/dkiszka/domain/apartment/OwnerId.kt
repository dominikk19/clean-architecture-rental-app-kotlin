package pl.dkiszka.domain.apartment

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class OwnerId(
    @Column(name = "OWNER_ID")
    val id: String,
)
