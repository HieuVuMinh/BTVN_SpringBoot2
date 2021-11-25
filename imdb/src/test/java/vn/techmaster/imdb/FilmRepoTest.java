package vn.techmaster.imdb;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.imdb.model.Film;
import vn.techmaster.imdb.repository.FilmRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilmRepoTest {
    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void getAll() {
        List<Film> filmList = filmRepository.getAll();
    }

    @Test
    public void getFilmByCountry() {
        Map<String, List<Film>> filmList = filmRepository.getFilmByCountry();
        filmList.entrySet().stream()
                .forEach(h -> System.out.println("- " + h.getKey() + "\n" + h.getValue()));
    }

    @Test
    public void categorizeFilmByGenere() {
        Map<String, List<Film>> filmList = filmRepository.categorizeFilmByGenere();
        filmList.entrySet().stream()
                .forEach(h -> System.out.println("- " + h.getKey() + "\n" + h.getValue()));
    }

    @Test
    public void ratioBetweenGenere() {
        double result = filmRepository.ratioBetweenGenere("horror", "drama");
        System.out.println(result);
    }

    @Test
    public void getCountryMakeMostFilms() {
        Map.Entry<String, Long> top = filmRepository.getCountryMakeMostFilms();
        System.out.println(top);
    }

    @Test
    public void yearMakeMostFilms() {
        Map.Entry<Integer, Long> top = filmRepository.yearMakeMostFilms();
        System.out.println(top);
    }

    @Test
    public void getFilmsMadeByCountryFromYearToYear() {
        List<Film> films = filmRepository.getFilmsMadeByCountryFromYearToYear("China", 2002, 2011);
        films.stream().forEach(System.out::println);
    }

    @Test
    public void top5HighMarginFilms() {
        List<Film> films = filmRepository.top5HighMarginFilms();
        films.stream().forEach(film -> {
            System.out.println(film.getRevenue() - film.getCost());
        });
    }

    @Test
    public void top5HighMarginFilmsIn1990to2000() {
        List<Film> films = filmRepository.top5HighMarginFilmsIn1990to2000();
        films.stream().forEach(System.out::println);
    }

    @Test
    public void top5FilmsHighRatingButLowMargin() {
        List<Film> films = filmRepository.top5FilmsHighRatingButLowMargin();
        films.stream().forEach(System.out::println);
    }

    @Test
    public void top5HighMarginHorrorAndDramaFilms() {
        List<Film> films = filmRepository.top5HighMarginHorrorAndDramaFilms();
        films.stream().forEach(film -> {
            System.out.println(film.getRevenue() - film.getCost());
        });
    }

    @Test
    public void getAllGeneres() {
        Set<String> generes = filmRepository.getAllGeneres();
        generes.stream().forEach(System.out::println);
    }

    @Test
    void testConstructor() {
        assertNull((new FilmRepository("Datafile")).getAll());
    }

    @Test
    void testGetAllGeneres() {
        Set<String> allGeneres = filmRepository.getAllGeneres();
        assertThat(allGeneres.size()).isEqualTo(16);
    }
}
