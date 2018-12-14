
package com.library.config;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.library.entity.Author;
import com.library.entity.AuthorId;
import com.library.entity.Book;
import com.library.entity.Country;
import com.library.entity.Publisher;

public class RandomConfig {
    static Random random = new Random();

    public static final String[] FIRSTNAMES =
            {"John", "Robert", "Jackob", "Thomas", "Edward", "William", "Henry", "George", "Gregory", "Charles"};
    public static final String[] LASTNAMES =
            {"Johnson", "Smith", "Lee", "Linn", "Fox", "Simpson", "Ford", "Piper", "Moor", "Philips"};

    public static final String[] PUBLISHER_NAMES = {"Star", "Red Star", "Black Star", "Golden Star", "Dead Star",
            "Sun", "Green Sun", "Sea", "Yellow Sea", "Red Sea"};

    public static final String[] TITLES
            = {"Day and Night", "Summer and Winter", "Bread and Stone", "Eagle and Snake", "Head and Ass",
            "Love and Hate", "Red and Black", "Girls and Vodka", "Church and Yoghurt", "Drugs and Sex"};

    public static final Locale[] locales = Locale.getAvailableLocales();

    public static long randomIsbn() {
        long res = random.nextInt() * 10000 + random.nextInt(9999);
        return res < 0 ? res * -1 : res;
    }

    public static LocalDate randomDate(int year) {
        LocalDate start = LocalDate.of(year, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        LocalDate randomDate = start.plusDays(new Random().nextInt((int) days + 1));
        return randomDate;
    }

    public static Set <Author> randomAuthor() {
        Set <Author> authors = new HashSet <>();
        for (int i = 0; i < random.nextInt(4); i++) {
            authors.add(new Author(new AuthorId(RandomConfig.FIRSTNAMES[random.nextInt(RandomConfig.FIRSTNAMES.length)],
                    RandomConfig.LASTNAMES[random.nextInt(RandomConfig.LASTNAMES.length)])));
        }
        return authors;
    }

    public static Publisher randomPublisher() {
        String name = RandomConfig.PUBLISHER_NAMES[random.nextInt(RandomConfig.PUBLISHER_NAMES.length)];
        String country = locales[random.nextInt(locales.length)].getDisplayCountry();
        return new Publisher(name, new Country(country));
    }

    public static String randomTitle() {
        return RandomConfig.TITLES[random.nextInt(RandomConfig.TITLES.length)];
    }

    public static double randomPrice() {
        return ThreadLocalRandom.current().nextDouble(50.50, 800.00);
    }

    public static Book randomBook() {
        return new Book(randomIsbn(), randomAuthor(), randomTitle(), randomPublisher(), randomDate(1900), randomPrice());
    }
}
