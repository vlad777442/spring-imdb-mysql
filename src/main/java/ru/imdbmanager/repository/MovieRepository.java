package ru.imdbmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.imdbmanager.model.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Page<Movie> findAll(Pageable pageable);
    Page<Movie> findMovieByRankBetween(Float r1, Float r2, Pageable page);
    Page<Movie> findMovieByRankGreaterThan(Float r1, Pageable page);
    Page<Movie> findMovieByRankLessThan(Float r1, Pageable page);


}
