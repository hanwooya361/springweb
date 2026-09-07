package example.Practice4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice4.model.dto.EnrollDto;
import example.Practice4.model.entity.EnrollEntity;
import example.Practice4.service.EnrollService;

@RestController 
@RequestMapping("/api/enroll")
public class EnrollController {
    @Autowired private EnrollService enrollService;

    // 등록3
    @PostMapping("")
    public boolean eAdd(@RequestBody EnrollDto enrollDto){
        return enrollService.eAdd(enrollDto);
    }

    // 조회1
    @GetMapping("")
    public EnrollEntity eView(@RequestParam (name="enrollId") int enrollId){
        return enrollService.eView(enrollId);
    }
}
