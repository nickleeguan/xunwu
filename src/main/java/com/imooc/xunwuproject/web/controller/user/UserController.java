package com.imooc.xunwuproject.web.controller.user;

import com.imooc.xunwuproject.base.ApiResponse;
import com.imooc.xunwuproject.base.HouseSubscribeStatus;
import com.imooc.xunwuproject.base.LoginUserUtil;
import com.imooc.xunwuproject.service.IUserService;
import com.imooc.xunwuproject.service.ServiceMultiResult;
import com.imooc.xunwuproject.service.ServiceResult;
import com.imooc.xunwuproject.service.house.IHouseService;
import com.imooc.xunwuproject.web.dto.HouseDTO;
import com.imooc.xunwuproject.web.dto.HouseSubscribeDTO;
import org.apache.kafka.common.security.auth.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private IHouseService houseService;

    @Autowired
    private IUserService userService;

    @GetMapping("/user/login")
    public String loginPage(){
        return "user/login";
    }

    @GetMapping("/user/center")
    public String centerPage(){
        return "user/center";
    }

    @PostMapping(value = "api/user/info")
    @ResponseBody
    public ApiResponse updateUserInfo(@RequestParam("profile") String profile,
                                      @RequestParam("value") String value){
        if (value.isEmpty()){
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        if ("email".equals(profile) && !LoginUserUtil.checkEmail(profile)){
            return ApiResponse.ofMessage(ApiResponse.Status.BAD_REQUEST.getCode(), "不支持的邮箱格式");
        }
        ServiceResult serviceResult = userService.modifyUserProfile(profile, value);
        if (serviceResult.isSuccess()){
            return ApiResponse.ofSuccess(null);
        }else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), serviceResult.getMessage());
        }
    }

    @PostMapping(value = "api/user/house/subscribe")
    @ResponseBody
    public ApiResponse subscribeHouse(@RequestParam(value = "house_id") long houseId){
        ServiceResult serviceResult = houseService.addSubscribeOrder(houseId);
        if (serviceResult.isSuccess()){
            return ApiResponse.ofSuccess("");
        }else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), serviceResult.getMessage());
        }
    }

    @GetMapping(value = "api/user/house/subscribe/list")
    @ResponseBody
    public ApiResponse subscribeList(@RequestParam(value = "start", defaultValue = "0") int start,
                                     @RequestParam(value = "size", defaultValue = "3") int size,
                                     @RequestParam(value = "status") int status){
        ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> result =
                houseService.querySubscribeList(HouseSubscribeStatus.of(status), start, size);

         if (result.getResultSize() == 0) {
             return ApiResponse.ofSuccess(result.getResult());
         }
         ApiResponse response = ApiResponse.ofSuccess(result.getResult());
         response.setMore(result.getTotal() > (start + size));
         return response;
    }

    @PostMapping(value = "api/user/house/subscribe/date")
    @ResponseBody
    public ApiResponse subscribeDate(
            @RequestParam(value = "houseId") long houseId,
            @RequestParam(value = "orderTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderTime,
            @RequestParam(value = "desc", required = false) String desc,
            @RequestParam(value = "telephone") String telephone
    ){
        if (orderTime == null){
            return ApiResponse.ofMessage(org.apache.http.HttpStatus.SC_BAD_REQUEST," 请选择预约时间");
        }
        if (LoginUserUtil.checkTelephone(telephone)){
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), "手机格式不正确");
        }

        ServiceResult result = houseService.subscribe(houseId, orderTime, telephone, desc);
        if (result.isSuccess()){
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        }else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }

    }

    @DeleteMapping(value = "api/user/house/subscribe")
    @ResponseBody
    public ApiResponse cancelSubscribe(@RequestParam(value = "houseId") long houseId){
        ServiceResult result = houseService.cancelSubscribe(houseId);
        if (result.isSuccess()){
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        }else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }
    }
}
