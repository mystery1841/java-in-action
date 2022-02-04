package learning.fp.stream.collector;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class DownstreamCollectorsTest {

    public void testCollectorToSet() {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(
                groupingBy(Locale::getCountry, toSet()));
    }

    public void testCollectorCounting() {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locales.collect(
                groupingBy(Locale::getCountry, counting()));
    }

    public void testCollectorSumming() {
        Stream<City> cities = Stream.empty();
        Map<String, Integer> stateToCityPopulation = cities.collect(
                groupingBy(City::getState, summingInt(City::getPopulation)));
    }

    public void testCollectorMaxBy() {
        Stream<City> cities = Stream.empty();
        Map<String, Optional<City>> stateToLargestCity = cities.collect(
                groupingBy(City::getState,
                        maxBy(Comparator.comparing(City::getPopulation))));
    }

    public void testCollectorCollectingAndThen() {
        Stream<String> strings = Stream.of("hello", ", ", "world", ".");
        Map<Character, Integer> stringCountsByStartingLetter = strings.collect(
                groupingBy(s -> s.charAt(0),
                        collectingAndThen(toSet(), Set::size)));
    }

    public void testCollectorMapping() {
        Stream<String> strings = Stream.of("hello", ", ", "world", ".");
        Map<Character, Set<Integer>> stringLengthsByStartingLetter = strings.collect(
                groupingBy(s -> s.charAt(0),
                        mapping(String::length, toSet())));
    }

    public void testCollectorSummarizingInt() {
        Stream<City> cities = Stream.empty();
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(
                groupingBy(City::getState,
                        summarizingInt(City::getPopulation)));
    }

    public void testCollectorFiltering() {
        Stream<City> cities = Stream.empty();
        Map<String, Set<City>> largeCitiesByState = cities.collect(
                groupingBy(City::getState,
                        filtering(c -> c.getPopulation() > 500000, toSet())));
    }
}
