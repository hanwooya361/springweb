package example.test.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import example.test.model.dao.ListDao;
import example.test.model.dto.ListDto;

@RestController
public class ListController {
    private ListDao ld = ListDao.getInstance();
    // [1] 등록
    @PostMapping( "/list/save" )
    public boolean save( ListDto listdto ){
        boolean result = ld.save(listdto);
        return result;
    }

    // [2] 전체조회 Controller 
    @GetMapping("/list/findall")
    public ArrayList<ListDto> findAll( ){
        ArrayList<ListDto> result = ld.findAll();
        return result;
    }

    // [3] 개별수정 Controller 
    @PutMapping("/list/update")
    public boolean update( ListDto listdto ){
        return ld.update( listdto );
    }

    // [4] 개별삭제 Controller
    @DeleteMapping("/list/delete")
    public boolean delete( ListDto listdto ){
        return ld.delete( listdto );
    }
}
