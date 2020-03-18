package com.song.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author song
 * @data 2019/12/22
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("B");
    }



    //配置swagger的Docket的Bean实例
    @Bean
    public Docket docket(Environment environment){
        //设置要显示的swagger的环境
        Profiles profiles = Profiles.of("dev");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                //.apiInfo(apiInfo())
                //是否启动swagger  默认true启用  false浏览器访问不到ui页面
                .enable(flag)
                .groupName("宋高帅")
                .select()
                //RequestHandlerSelectors 配置要扫描的接口方式
                .apis(RequestHandlerSelectors.basePackage("com.song.controller"))
                //扫面全部
                //.apis(RequestHandlerSelectors.any())
                //过滤 路径
                //.paths(PathSelectors.ant("/com.aaa/**"))
                .build();
    }
    //配置swagger的信息=apiInfo
   /* private ApiInfo apiInfo(){
        //作者信息
        Contact contact=new Contact("宋高帅", "http://www.swordman.top", "1742057357@qq.com");

        return new ApiInfo(
                "song",
                "宋高帅",
                "v1.0",
                "http://www.swordman.top",
                 contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                 new ArrayList()
        );
    }*/
}
