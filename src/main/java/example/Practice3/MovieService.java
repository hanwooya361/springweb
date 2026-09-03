package example.Practice3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
    @Autowired private MovieRepository movieRepository;

    public boolean movieWrite(MovieDto movieDto){
        MovieEntity movieEntity = movieDto.toEntity();
        MovieEntity savedEntity = movieRepository.save(movieEntity);
        if(savedEntity.getMovieid() >= 1){return true;}
        return false;
    }

    public List<MovieDto> findAll(){
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDto> list = new ArrayList<>();
        entities.forEach((entity)->{
            MovieDto dto = MovieDto.from(entity);
            list.add(dto);
        });
        return list;
    }

    public MovieEntity movieDetail(int movieid){
        Optional<MovieEntity> optional = movieRepository.findById(movieid);
        if(optional.isPresent()){
            MovieEntity entity = optional.get();
            return entity;
        }
        return null;
    } 

    @Transactional
    public boolean movieUpdate(MovieDto movieDto){
        Optional<MovieEntity> optional = movieRepository.findById(movieDto.getMovieid());
        if(optional.isPresent()){
            MovieEntity entity = optional.get();
            entity.setTitle(movieDto.getTitle());
            entity.setDirector(movieDto.getDirector());
            entity.setReleasedate(movieDto.getReleasedate());
            entity.setRating(movieDto.getRating());
            return true;
        }
        return false;
    }

    public boolean movieDelete(int movieid){
        movieRepository.deleteById(movieid);
        return true;
    }
}
