package com.example.demo.movie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MovieController {

    private MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        return "addMovie";
    }

    @PostMapping("/add")
    public String add(Movie movie) {
        movieRepository.addMovie(movie);
        return "redirect:/";
    }

    @GetMapping("/showList")
    public String showAll(Model model) {
        model.addAttribute("list", movieRepository.showAll());
        return "list";
    }

    @GetMapping("/find")
    public String findMovie(Model model, @RequestParam long id) {
        model.addAttribute("movie", movieRepository.findById(id));
        return "movie";
    }

    @GetMapping("/filter")
    public String filterCategory(Model model, @RequestParam Categories categories) {
        model.addAttribute("movie", movieRepository.filteredByCategory(categories));
        return "filter";
    }
}
