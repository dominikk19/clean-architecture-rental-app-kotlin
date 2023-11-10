package pl.dkiszka.domain.apartment


interface ApartmentView {
    val ownerId: OwnerId
    val address: AddressView
    val rooms: Set<Room>
    val description: String?
}

interface AddressView {
    val street: String
    val postalCode: String
    val houseNumber: String
    val apartmentNumber: String?
    val city: String
    val country: String
}

interface RoomView {
    val name: String
    val size: Double
}