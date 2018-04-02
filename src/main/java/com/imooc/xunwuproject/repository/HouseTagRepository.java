package com.imooc.xunwuproject.repository;

import com.imooc.xunwuproject.entity.HouseTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HouseTagRepository extends CrudRepository<HouseTag, Long> {
    List<HouseTag> findAllByHouseId(long id);

    HouseTag findByNameAndHouseId(String tag, Long houseId);

    List<HouseTag> findAllByHouseIdIn(List<Long> houseIds);
}
