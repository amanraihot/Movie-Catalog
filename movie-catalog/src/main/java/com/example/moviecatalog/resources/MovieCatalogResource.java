package com.example.moviecatalog.resources;

import com.example.moviecatalog.model.CatalogItem;
import com.example.moviecatalog.model.Movie;
import com.example.moviecatalog.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId)
    {
        RestTemplate restTemplate = new RestTemplate();
        List<Rating> ratings = Arrays.asList(new Rating("1234",4),
                new Rating("322332",4)
                );
        return ratings.stream().map(rating->{
             Movie movie =  restTemplate.getForObject(
                     "http://localhost:8082/movies/"+ rating.getMovieId(),Movie.class);
             return new CatalogItem(movie.getMovieName(), "desc",rating.getRating());
          }).collect(Collectors.toList());
    }
}
