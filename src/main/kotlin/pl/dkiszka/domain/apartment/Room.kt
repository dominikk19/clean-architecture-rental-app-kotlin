package pl.dkiszka.domain.apartment

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class Room private constructor(
    @Column(name = "NAME")
    override val name: String,
    @AttributeOverride(name = "size", column = Column(name = "SIZE"))
    val squareMeter: SquareMeter,
) : RoomView {

    companion object {
        fun from(name: String, size: Double): Room {
            return Room(
                name = name,
                squareMeter = SquareMeter(
                    size = size,
                ),
            )
        }
    }

    @AttributeOverride(name = "uuid", column = Column(name = "ROOM_ID"))
    val roomId: RoomId = RoomId()

    override val size: Double
        get() = squareMeter.size

    @Embeddable
    data class RoomId(
        val uuid: UUID = UUID.randomUUID(),
    )

    @Embeddable
    data class SquareMeter(
        val size: Double,
    )
}