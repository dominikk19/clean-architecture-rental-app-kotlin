package pl.dkiszka.domain.shared

import java.time.Clock

interface ClockProvider {

    fun get(): Clock
}