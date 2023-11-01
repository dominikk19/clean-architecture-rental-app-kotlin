package pl.dkiszka.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.dkiszka.domain.shared.ClockProvider
import java.time.Clock

@Configuration
private class ClockConfiguration : ClockProvider {
    val clock: Clock = Clock.systemDefaultZone()

    @Bean
    fun clock(): Clock {
        return clock
    }

    override fun get() = clock
}