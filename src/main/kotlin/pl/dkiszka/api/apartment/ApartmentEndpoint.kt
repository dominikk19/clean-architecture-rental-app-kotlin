package pl.dkiszka.api.apartment

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.dkiszka.api.apartment.dto.ApartmentRequestDto
import pl.dkiszka.api.apartment.dto.ApartmentResponseDto
import pl.dkiszka.api.apartment.dto.GetApartmentResponseDto
import pl.dkiszka.application.apartment.command.CreateApartment
import pl.dkiszka.domain.apartment.ApartmentId
import pl.dkiszka.domain.apartment.ApartmentQueryRepository

@RestController
@RequestMapping("/api/v1/apartment")
private class ApartmentEndpoint(
    private val createApartment: CreateApartment,
    private val apartmentQueryRepository: ApartmentQueryRepository,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createApartment(
        @RequestBody apartmentRequestDto: ApartmentRequestDto,
    ): ApartmentResponseDto {
        return ApartmentResponseDto(
            id = createApartment(apartmentRequestDto.toCommand()).toString()
        )
    }

    @GetMapping("/{apartmentId}")
    fun getById(
        @PathVariable("apartmentId") apartmentId: String,
    ): GetApartmentResponseDto {
        return GetApartmentResponseDto.from(
            apartment = apartmentQueryRepository.getByApartmentId(ApartmentId(apartmentId))
        )
    }

}