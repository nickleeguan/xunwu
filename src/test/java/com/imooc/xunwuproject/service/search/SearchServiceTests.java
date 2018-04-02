package com.imooc.xunwuproject.service.search;

import com.imooc.xunwuproject.ApplicationTests;
import com.imooc.xunwuproject.service.ServiceMultiResult;
import com.imooc.xunwuproject.web.form.RentSearch;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchServiceTests extends ApplicationTests{

    @Autowired
    private ISearchService searchService;

    @Test
    public void testIndex(){
        Long targetHouseId = 15L;
        searchService.index(targetHouseId);

    }

    @Test
    public void testRemove(){
        Long tartgetHouseId = 15L;
        searchService.remove(tartgetHouseId);
    }

    @Test
    public void testQuery(){
        RentSearch rentSearch = new RentSearch();
        rentSearch.setCityEnName("bj");
        rentSearch.setStart(0);
        rentSearch.setSize(10);
        ServiceMultiResult<Long> result = searchService.query(rentSearch);
        Assert.assertEquals(8, result.getTotal());
    }
}
