package com.example.Search.MicroService.services.searchserviceimpl;

import com.example.Search.MicroService.dto.SearchDTO;
import com.example.Search.MicroService.entity.SearchEntity;
import com.example.Search.MicroService.repository.SearchRepoCustom;
import com.example.Search.MicroService.repository.SearchRepository;
import com.example.Search.MicroService.services.SearchServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImplementation implements SearchServices
{
    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SearchRepoCustom searchRepoCustom;

    SearchDTO searchDTO=new SearchDTO();



    @KafkaListener(topics="productUpdate",groupId="group_id")
    @Override
    public void consume(String message)
    {
        SearchEntity searchProduct = new SearchEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            searchDTO = objectMapper.readValue(message, SearchDTO.class);
            SearchEntity searchEntity=new SearchEntity();
            BeanUtils.copyProperties(searchDTO,searchEntity);
            searchRepository.save(searchEntity);
            System.out.println(searchDTO.toString());
        }
        catch (Exception e)
        {
            //log.error("Error : ", e);
            System.out.println("Error : "+e);
        }
    }




    @Override
    public Page<SearchEntity> search(int pageSize,int pageNumber,String keyword)
    {
        return searchRepoCustom.search(pageSize,pageNumber,keyword);
    }

}
