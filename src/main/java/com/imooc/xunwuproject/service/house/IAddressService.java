package com.imooc.xunwuproject.service.house;

import com.imooc.xunwuproject.entity.SupportAddress;
import com.imooc.xunwuproject.service.ServiceMultiResult;
import com.imooc.xunwuproject.service.ServiceResult;
import com.imooc.xunwuproject.service.search.BaiduMapLocation;
import com.imooc.xunwuproject.web.dto.SupportAddressDTO;
import com.imooc.xunwuproject.web.dto.SubwayDTO;
import com.imooc.xunwuproject.web.dto.SubwayStationDTO;

import java.util.List;
import java.util.Map;

/**
 * 地址服务接口
 */
public interface IAddressService {
    ServiceMultiResult<SupportAddressDTO> findAllCities();

    ServiceMultiResult<SupportAddressDTO> findAllRegionsByCityName(String cityName);

    List<SubwayDTO> findAllSubwayByCity(String cityEnName);

    List<SubwayStationDTO> findAllStationBySubway(Long subwayId);

    /**
     * 根据英文简写获取具体区域的信息
     * @param cityEnName
     * @param regionEnName
     * @return
     */
    Map<SupportAddress.Level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName);

    ServiceResult<SubwayDTO> findSubway(Long subwayLineId);

    ServiceResult<SubwayStationDTO> findSubwayStation(Long subwayStationId);

    /**
     * 根据城市英文简写获取城市详细信息
     * @param cityEnName
     * @return
     */
    ServiceResult<SupportAddressDTO> findCity(String cityEnName);

    /**
     * 根据城市以及具体地址获取百度地图的经纬度
     */
    ServiceResult<BaiduMapLocation> getBaiduMapLocation(String city, String address);

    /**
     * 上传百度LBS数据
     */
    ServiceResult lbsUpload(BaiduMapLocation location, String title, String address,
                            long houseId, int price, int area);

    /**
     * 移除百度LBS数据
     * @param houseId
     * @return
     */
    ServiceResult removeLbs(Long houseId);
}
