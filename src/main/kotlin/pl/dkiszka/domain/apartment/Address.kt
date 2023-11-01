package pl.dkiszka.domain.apartment

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    @Column(name = "STREET")
    val street: String,
    @Column(name = "POSTAL_CODE")
    val postalCode: String,
    @Column(name = "HOUSE_NUMBER")
    val houseNumber: String,
    @Column(name = "APARTMENT_NUMBER")
    val apartmentNumber: String?,
    @Column(name = "CITY")
    val city: String,
    @Column(name = "COUNTRY")
    val country: String,
)
