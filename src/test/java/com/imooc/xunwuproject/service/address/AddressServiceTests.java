package com.imooc.xunwuproject.service.address;

import com.imooc.xunwuproject.ApplicationTests;
import com.imooc.xunwuproject.service.ServiceResult;
import com.imooc.xunwuproject.service.house.IAddressService;
import com.imooc.xunwuproject.service.search.BaiduMapLocation;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressServiceTests extends ApplicationTests {

    @Autowired
    private IAddressService addressService;

    @Test
    public void testGetmapLocation(){
        String city = "北京";
        String address = "北京市昌平区巩华家园1号楼2单元";

        ServiceResult<BaiduMapLocation> location = addressService.getBaiduMapLocation(city, address);
        Assert.assertTrue(location.isSuccess());
        Assert.assertTrue(location.getResult().getLongitude() > 0);
        Assert.assertTrue(location.getResult().getLatitude() > 0);
    }

    /**
     * 为LBS新建属性
     */
    @Test
    public void createColumeOnLbs(){
        HttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("geotable_id", "186149"));
        nvps.add(new BasicNameValuePair("ak", "frrChV45XjsLI8w53PcCbWnuG3NV0O7G"));
        nvps.add(new BasicNameValuePair("key", "houseId"));
        nvps.add(new BasicNameValuePair("name", "编号"));
        nvps.add(new BasicNameValuePair("is_index_field", "1"));
        nvps.add(new BasicNameValuePair("type", "1"));
        nvps.add(new BasicNameValuePair("is_sortfilter_field", "1"));
        nvps.add(new BasicNameValuePair("is_search_field", "0"));

        HttpPost post = new HttpPost("http://api.map.baidu.com/geodata/v3/column/create");
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpClient.execute(post);

            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
