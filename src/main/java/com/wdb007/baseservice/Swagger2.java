package com.wdb007.baseservice;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wdb007.baseservice.utility.Common;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
//注解标示,这是一个配置类,@Configuation注解包含了@Component注解
//可以不用在使用@Component注解标记这是个bean了,
@Configuration
public class Swagger2 {
	/**
	 * Swagger2 API文档类
	 * @throws IOException 
	 * 
	 */
	@Bean
	public Docket createRestApi() throws IOException {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				//@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
				.apis(RequestHandlerSelectors.basePackage("com.wdb007.baseservice.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	private ApiInfo apiInfo() throws IOException {
		String apiVersionText=Common.getProperties("application", "spring.profiles.active").equals("dev")?"【测试库环境】":"【SSH连接生产环境】";
		return new ApiInfoBuilder()
				//页面标题
				.title("我D邦基础数据 API接口文档")
				//创建人
				.contact("天之饺子")
				//版本号
				.version("1.0")
				//描述
				.description("--------"+apiVersionText+"--------")
				.build();
	}
}