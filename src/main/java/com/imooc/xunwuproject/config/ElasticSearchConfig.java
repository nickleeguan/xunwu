package com.imooc.xunwuproject.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticSearchConfig {
    @Bean
    public TransportClient esClient() throws UnknownHostException{
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();

        //elasticsearch tcp端口默认9300
        InetSocketTransportAddress master = new InetSocketTransportAddress(
                InetAddress.getByName("192.168.187.199"), 9300
        );

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(master);
        return client;
    }
}
