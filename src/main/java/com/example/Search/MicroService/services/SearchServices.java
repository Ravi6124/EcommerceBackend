package com.example.Search.MicroService.services;

import com.example.Search.MicroService.entity.SearchEntity;
import org.springframework.data.domain.Page;

public interface SearchServices
{
    void consume(String message);
    Page<SearchEntity> search( int pageSize, int pageNumber,String keyword);

}
