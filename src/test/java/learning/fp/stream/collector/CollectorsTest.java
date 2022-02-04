package learning.fp.stream.collector;

import learning.oop.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsTest {

    public void testCollectorToCollection() {
        Stream<String> stream = Stream.of("hello", ", ", "world", ".");
        TreeSet<String> result = stream.collect(Collectors.toCollection(TreeSet::new));
    }

    public void testCollectorSummarizing() {
        Stream<String> stream = Stream.of("hello", ", ", "world", ".");
        IntSummaryStatistics summary = stream.collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
    }

    public void testCollectorToMapOf4Argument() {
        Stream<Person> people = Stream.empty();
        Map<String, Person> idToPerson = people.collect(
                Collectors.toMap(
                        Person::getName,
                        Function.identity(),
                        (existingValue, newValue) -> {
                            throw new IllegalStateException();
                        },
                        TreeMap::new));
    }

    public void testCollectorGroupingBy() {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, List<Locale>> countryToLocales = locales.collect(
                Collectors.groupingBy(Locale::getCountry));
    }

    public void testCollectorPartitioningBy() {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<Boolean, List<Locale>> englishAndOtherLocales = locales.collect(
                Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
        List<Locale> englishLocales = englishAndOtherLocales.get(true);
    }
}
