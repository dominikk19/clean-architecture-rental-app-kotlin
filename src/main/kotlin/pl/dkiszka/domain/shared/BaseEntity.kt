package pl.dkiszka.domain.shared

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.time.Clock
import java.time.Instant

const val ENTITY_GENERATOR_NAME = "idgenerator"

@MappedSuperclass
abstract class BaseEntity(clock: Clock) {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_GENERATOR_NAME)
    @Column(name = "ID")
    private var id: Long? = null

    @Column(name = "CREATED_AT")
    val createdAt: Instant = Instant.now(clock)
}