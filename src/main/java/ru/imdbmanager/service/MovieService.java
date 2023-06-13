package ru.imdbmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.imdbmanager.model.Movie;
import ru.imdbmanager.repository.MovieRepository;

import java.util.List;

@Service("movieService")
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public Movie find(Integer id) {
        return movieRepository.findOne(id);
    }
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
    public Page<Movie> findByRank(Float d1, Float d2, String customSort, Pageable page) {
        System.out.println("findbyrank calledservice");
        System.out.println(customSort+" csort");
        if (customSort.equals("greater")) {
            System.out.println(d1 + " start");
            System.out.println(page + " page service");
            return movieRepository.findMovieByRankGreaterThan(d1, page);
        }

        else if (customSort.equals("less"))
            return movieRepository.findMovieByRankLessThan(d1, page);
        else if (customSort.equals("equal")) {
            Float eps = 0.05F;
            return movieRepository.findMovieByRankBetween(d1 - eps, d1 + eps, page);
        }

        else if (customSort.equals("between")) {
            if (d1 > d2) {
                return movieRepository.findMovieByRankBetween(d2, d1, page);
            }
            else return movieRepository.findMovieByRankBetween(d1, d2, page);
        }

        return null;
    }


}
