package pl.dkiszka.domain.apartment

import org.springframework.data.jpa.repository.JpaRepository

interface ApartmentRepository : JpaRepository<Apartment, Long>, ApartmentQueryRepository

interface ApartmentQueryRepository {

    fun getByApartmentId(apartmentId: ApartmentId): ApartmentView
}