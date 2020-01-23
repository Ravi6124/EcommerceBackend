package com.example.Search.MicroService.controller;

import com.example.Search.MicroService.dto.SearchDTO;
import com.example.Search.MicroService.entity.SearchEntity;
import com.example.Search.MicroService.services.SearchServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController
{

    @Autowired
    SearchServices searchServices;

//    @PostMapping("/addProductInSearch")
//    public ResponseEntity<String> addProductInSearch(@RequestBody SearchDTO searchDTO)
//    {
//        SearchEntity searchEntity=new SearchEntity();
//        BeanUtils.copyProperties(searchDTO,searchEntity);
//        SearchEntity searchProductCreated=searchServices.save(searchEntity);
//        return new ResponseEntity<String>(searchProductCreated.getProductId(),HttpStatus.CREATED);
//    }

    @GetMapping("/searchFunction")
    public ResponseEntity<Page<SearchEntity>> search(@RequestParam("keyword") String keyword)
    {
        return new ResponseEntity<Page<SearchEntity>>(searchServices.search(keyword),HttpStatus.OK);
    }
}
