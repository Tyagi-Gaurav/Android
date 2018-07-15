package fb

import java.util.stream.Collectors
import java.util.stream.Collectors.groupingBy

open class FizzBuzzAggregator {
    fun aggregate(fbList: List<FizzBuzzDomain>) : Map<FizzBuzzTypeEnum, Int> {
        return fbList.stream()
                .collect(
                        groupingBy (
                                FizzBuzzDomain::type,
                                Collectors.summingInt({_ -> 1})))
    }

}
