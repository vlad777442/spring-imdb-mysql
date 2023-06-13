package ru.imdbmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.imdbmanager.model.Movie;
import ru.imdbmanager.model.NoSuchMovieException;
import ru.imdbmanager.repository.MovieRepository;
import ru.imdbmanager.service.MovieService;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieRepository repo;

    Float rangestart;
    Float rangeend;
    String sortBy;
    String sortDir;
    String customSort;

    @RequestMapping("/{movieId}")
    public String showMovie(
            @PathVariable("movieId") Integer movieId,
            Model model) throws NoSuchMovieException {
        Movie b = movieService.find(movieId);
        if (b!=null){
            model.addAttribute("movie", b);
            return "movie";
        }else{
            throw new NoSuchMovieException("Error movie id");
        }
    }

    @RequestMapping("/findall")
    public String findAll(Model model){
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "all_movies";
    }

    @GetMapping(value = "/findpage")
    public String showPageTable(Pageable page, Model model, Sort sort) {
        //N-строк на странице
        Pageable pageNew = new PageRequest(page.getPageNumber(), 5, page.getSort());
        if (!model.asMap().isEmpty()) {
            rangestart = (Float) model.asMap().get("rangestart");
            rangeend = (Float) model.asMap().get("rangeend");
            sortBy = (String) model.asMap().get("sort");
            sortBy = (String) model.asMap().get("dir");
            customSort = (String) model.asMap().get("customSort");
        }
        Sort.Order o = null;
        if (sort!=null){
            o = sort.iterator().next();
        }

        Page<Movie> page1 = movieService.findByRank(rangestart, rangeend, customSort, pageNew);
        model.addAttribute("sort", (sort != null)?o.getProperty():sortBy);
        model.addAttribute("dir", (sort != null)?o.getDirection():sortDir);
        model.addAttribute("page", page1);

        return "movies_page";
    }
    @GetMapping(value="/find")
    public String findMovie(Model model){
        return "find";
    }

    @PostMapping(value="/find")
    public String findPOST(Model model, RedirectAttributes attr, @RequestParam(name = "sortBy") String sortBy,
            @RequestParam(name = "sortDir") String sortDir,
            @RequestParam(name = "rangestart") Float rangestart,
            @RequestParam(required = false, name = "rangeend") Float rangeend,
            @RequestParam(name = "customSort") String customSort) {

        attr.addFlashAttribute("sort", sortBy);
        attr.addFlashAttribute("dir", sortDir);
        attr.addFlashAttribute("rangestart", rangestart);
        attr.addFlashAttribute("rangeend", rangeend);
        attr.addFlashAttribute("customSort", customSort);
        return "redirect:/movies/findpage?page=0&sort="+sortBy+","+sortDir;

    }

}
