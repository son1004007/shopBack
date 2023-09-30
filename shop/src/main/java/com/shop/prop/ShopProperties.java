package com.shop.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("com.shop")
public class ShopProperties {

	private String uploadPath;
	
	private String secretKey;
	
}
