package com.imooc.xunwuproject.repository;

import com.imooc.xunwuproject.entity.HousePicture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HousePictureRepository extends CrudRepository<HousePicture, Long> {
    List<HousePicture> findAllByHouseId(long id);
}
