package com.imooc.xunwuproject.service;

/**
 * 验证码服务
 */
public interface ISmsService {
    /**
     * 发送验证码到指定手机并缓存验证码 10分钟，以及请求间隔时间 1 分钟
     * @param telephone
     * @return
     */
    ServiceResult<String> sendSms(String telephone);

    /**
     * 获取缓存中的验证码
     * @param telephone
     * @return
     */
    String getSmsCode(String telephone);

    /**
     * 移除指定手机号的验证码缓存
     *
     */
    void remove(String telephone);
}
