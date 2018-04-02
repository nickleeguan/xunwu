package com.imooc.xunwuproject.repository;

import com.imooc.xunwuproject.entity.HouseDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HouseDetailRepository extends CrudRepository<HouseDetail,Long>{
    HouseDetail findByHouseId(long id);

    List<HouseDetail> findAllByHouseIdIn(List<Long> houseIds);
}
