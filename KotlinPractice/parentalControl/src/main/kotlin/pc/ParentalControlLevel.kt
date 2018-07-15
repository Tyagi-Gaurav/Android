package pc

import java.util.concurrent.atomic.AtomicReferenceArray

enum class ParentalControlLevel(val level: String) {
    U("U"),
    PG("PG"),
    TWELVE("12"),
    FIFTEEN("15"),
    EIGHTEEN("18");

    companion object {
        fun from(v : String) : ParentalControlLevel? {
            return values()
                    .find { x -> x.level.toLowerCase() == v.toLowerCase()}
        }
    }

    fun isGreaterThanOrEqualTo(other: ParentalControlLevel): Boolean =
            this.ordinal >= other.ordinal
}
