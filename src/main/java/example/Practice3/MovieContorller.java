package example.Practice3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie")
public class MovieContorller {
    @Autowired private MovieService movieService;
    // {"title":"조현우", "director":"조현우", "releasedate":"2026-09-03","rating":9.9}
    @PostMapping("")
    public boolean movieWrite(@RequestBody MovieDto movieDto){
        return movieService.movieWrite(movieDto);
    }

    @GetMapping("")
    public List<MovieDto> findAll(){
        return movieService.findAll();
    }

    @GetMapping("/detail")
    public MovieEntity movieDetail(@RequestParam(name="movieid") int movieid){
        return movieService.movieDetail(movieid);
    }

    @DeleteMapping("")
    public boolean movieDelete(@RequestParam(name="movieid") int movieid){
        return movieService.movieDelete(movieid);
    }

    @PutMapping("")
    public boolean movieUpdate(@RequestBody MovieDto movieDto){
        return movieService.movieUpdate(movieDto);
    }
}
