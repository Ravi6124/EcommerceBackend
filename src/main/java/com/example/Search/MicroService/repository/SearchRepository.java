package com.example.Search.MicroService.repository;

import com.example.Search.MicroService.entity.SearchEntity;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  SearchRepository extends SolrCrudRepository<SearchEntity,String>
{

}
