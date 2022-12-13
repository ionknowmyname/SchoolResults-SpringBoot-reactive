package com.faithfulolaleru.SchoolResultreactive.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebProperties.Resources resources() {   // for global exception handling
        return new WebProperties.Resources();
    }

//    @Bean
//    public ServerCodecConfigurer serverCodecConfigurer() {
//        // return new DefaultServerCodecConfigurer();
//        return ServerCodecConfigurer.create();
//    }
}
