package com.example.Search.MicroService.repository;

import com.example.Search.MicroService.entity.SearchEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;


public interface SearchRepoCustom
{
    public Page<SearchEntity> search(String keyword);
}
