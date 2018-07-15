package fb

import java.util.stream.Collectors

class Fizzbuzz(val aggregator : FizzBuzzAggregator) {
    fun evaluate(lowerLimit: Int, upperLimit: Int): String {
        val list = fizzBuzzCalculator(lowerLimit, upperLimit)

        val collect = list.stream()
                .map { x -> x.toString() }
                .collect(Collectors.joining(" "))

        return collect + report(aggregator.aggregate(list))
    }

    private fun report(aggregate: Map<FizzBuzzTypeEnum, Int>): String {
        val s = StringBuilder()

        s.append(" fizz: ${aggregate.get(FizzBuzzTypeEnum.FIZZ)} ")
        s.append("buzz: ${aggregate.get(FizzBuzzTypeEnum.BUZZ)} ")
        s.append("fizzbuzz: ${aggregate.get(FizzBuzzTypeEnum.FIZZBUZZ)} ")
        s.append("lucky: ${aggregate.get(FizzBuzzTypeEnum.LUCKY)} ")
        s.append("integer: ${aggregate.get(FizzBuzzTypeEnum.INTEGER)}")

        return s.toString()
    }

    private fun fizzBuzzCalculator(lowerLimit: Int, upperLimit: Int): ArrayList<FizzBuzzDomain> {
        val list = ArrayList<FizzBuzzDomain>()
        for (num in lowerLimit..upperLimit) {
            list.add(FizzBuzzConverter.doWork(num))
        }

        return list
    }
}
