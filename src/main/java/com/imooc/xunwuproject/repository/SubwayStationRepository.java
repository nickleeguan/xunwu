package com.imooc.xunwuproject.repository;

import java.util.List;

import com.imooc.xunwuproject.entity.SubwayStation;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by 瓦力.
 */
public interface SubwayStationRepository extends CrudRepository<SubwayStation, Long> {
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}
