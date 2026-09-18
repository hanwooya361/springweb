package example.totalpractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.totalpractice.service.ReviewService;
import example.totalpractice.model.dto.ReviewsDto;

@RestController 
@RequestMapping("/api/reviews")
@CrossOrigin(value = "http://localhost:5173")

public class ReviewController {
    @Autowired ReviewService reviewService;

    @GetMapping
    public List<ReviewsDto> getreview(@RequestParam(name="bno") Integer bno) {
        return reviewService.getreviews(bno);
    }

    @PostMapping 
    public boolean createreview( @RequestBody ReviewsDto reviewDto){
        return reviewService.createreview( reviewDto );
    }

    @DeleteMapping
    public boolean deletereview(
    @RequestParam ( name = "rno" ) Integer rno){
        return reviewService.deletereview( rno );
    }
}
