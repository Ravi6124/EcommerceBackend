package com.example.Search.MicroService.repository.repositoryImpl;

import com.example.Search.MicroService.entity.SearchEntity;
import com.example.Search.MicroService.repository.SearchRepoCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;

import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

@Repository
public class SearchRepoImpl implements SearchRepoCustom
{

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public Page<SearchEntity> search(int pageSize,int pageNumber,String keyword)
    {

        String[] split = keyword.split("\\s+");
        Query query = new SimpleQuery();
        for (String word : split) {
            query = new SimpleQuery(new Criteria("productName").boost(10).is(word)
                    .or(new Criteria("theme").boost(9).is(word))
                    .or(new Criteria("description").boost(5).is(word))
                    .or(new Criteria("color").boost(7).is(word))
                    .or(new Criteria("size").boost(8).is(word)));
        }
        query.setPageRequest(PageRequest.of(pageNumber, pageSize));
       // query.setRows(pageSize);
//        System.out.println(query.getCriteria());

//        query.addCriteria(Criteria.where("description").is(keyword));
//        query.addFilterQuery(new SimpleFilterQuery(Criteria.where("category").is(keyword)));
       // query.addFilterQuery(Criteria.where("color").is("red"));
       // StringUtils.isEmpty
        // query.addFilterQuery(new SimpleFilterQuery());
        //SimpleQuery q = new SimpleQuery(keyword);
        return solrTemplate.query("productCollection", query, SearchEntity.class);
    }


}
