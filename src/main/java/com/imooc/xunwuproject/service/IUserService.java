package com.imooc.xunwuproject.service;


import com.imooc.xunwuproject.entity.User;
import com.imooc.xunwuproject.web.dto.UserDTO;

/**
 * 用户服务
 */
public interface IUserService {

    User findUserByName(String username);

    ServiceResult<UserDTO> findById(Long userId);

    /**
     * 根据电话号码寻找用户
     * @param telephone
     * @return
     */
    User findUserByTelephone(String telephone);

    /**
     * 通过手机号注册用户
     * @param telephone
     * @return
     */
    User addUserByPhone(String telephone);

    /**
     * 修改指定属性值
     * @param profile
     * @param vale
     * @return
     */
    ServiceResult modifyUserProfile(String profile, String vale);

}
