package vn.techmaster.imdb.repository;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import vn.techmaster.imdb.model.Film;

@Repository
public class FilmRepository implements IFilmRepo {
    private List<Film> films;

    public FilmRepository(@Value("${datafile}") String datafile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + datafile);
            ObjectMapper mapper = new ObjectMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            films = Arrays.asList(mapper.readValue(file, Film[].class));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Film> getAll() {
        return films;
    }

    @Override
    public Map<String, List<Film>> getFilmByCountry() {
        Map<String, List<Film>> result = films.stream().collect(Collectors.groupingBy(Film::getCountry));
        return result;
    }

    @Override
    public Entry<Integer, Long> yearMakeMostFilms() {
        var result = films.stream()
                .collect(Collectors.groupingBy(Film::getYear, Collectors.counting()));
        return result.entrySet().stream().max(Comparator.comparing(Map.Entry<Integer, Long>::getValue)).get();
    }

    @Override
    public Set<String> getAllGeneres() {
        Set<String> generes = new HashSet<>();
        films.stream().map(Film::getGeneres).forEach(generes::addAll);
        return generes;
    }

    @Override
    public List<Film> getFilmsMadeByCountryFromYearToYear(String country, int fromYear, int toYear) {
        var result = films.stream()
                .filter(h -> h.getYear() >= fromYear)
                .filter(h -> h.getYear() <= toYear)
                .filter(h -> h.getCountry().matches(country));
        return result.collect(Collectors.toList());
    }

    @Override
    public Entry<String, Long> getCountryMakeMostFilms() {
        var result = films.stream()
                .collect(Collectors.groupingBy(Film::getCountry, Collectors.counting()));
        return result.entrySet().stream().max(Comparator.comparing(Map.Entry<String, Long>::getValue)).get();
    }

    @Override
    public Map<String, List<Film>> categorizeFilmByGenere() {
        Map<String, List<Film>> result = new HashMap<>();
        films.stream().forEach(film -> {
            film.getGeneres().forEach(generes -> {
                List<Film> filmsByGeneres = result.get(generes);
                if (null == result.get(generes)) {
                    filmsByGeneres = new ArrayList<>();
                }
                filmsByGeneres.add(film);
                result.put(generes, filmsByGeneres);
            });
        });
        return result;
    }

    @Override
    public List<Film> top5HighMarginFilms() {
        var result = films.stream().sorted((h1, h2) -> (h1.getRevenue() - h1.getCost()) - (h2.getRevenue()) - h2.getCost())
                .limit(5)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Film> top5HighMarginFilmsIn1990to2000() {
        var result = films.stream().filter(h -> h.getYear() >= 1990 && h.getYear() <= 2000)
                .sorted(Comparator.comparing(Film::getRevenue))
                .limit(5);
        return result.collect(Collectors.toList());
    }

    @Override
    public double ratioBetweenGenere(String genreX, String genreY) {
        Map<String, List<Film>> filmsByGeneres = categorizeFilmByGenere();
        double ratioX = filmsByGeneres.get(genreX).size();
        double ratioY = filmsByGeneres.get(genreY).size();
        return ratioX / ratioY;
    }

    @Override
    public List<Film> top5FilmsHighRatingButLowMargin() {
        var result = films.stream()
                .sorted(Comparator.comparing(Film::getRating)
                        .reversed()
                        .thenComparing(h -> h.getRevenue() - h.getCost()))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Film> top5HighMarginHorrorAndDramaFilms() {
        return null;
    }

    public void test() {

    }


}
