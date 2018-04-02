package com.imooc.xunwuproject.repository;

import java.util.List;

import com.imooc.xunwuproject.entity.Subway;
import org.springframework.data.repository.CrudRepository;



/**
 * Created by 瓦力.
 */
public interface SubwayRepository extends CrudRepository<Subway, Long>{
    List<Subway> findAllByCityEnName(String cityEnName);
}
