package pl.dkiszka.domain.apartment

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    @Column(name = "STREET")
    override val street: String,
    @Column(name = "POSTAL_CODE")
    override val postalCode: String,
    @Column(name = "HOUSE_NUMBER")
    override val houseNumber: String,
    @Column(name = "APARTMENT_NUMBER")
    override val apartmentNumber: String?,
    @Column(name = "CITY")
    override val city: String,
    @Column(name = "COUNTRY")
    override val country: String,
) : AddressView
