package br.com.tga.zull.zullserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZullServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZullServerApplication.class, args);
	}

}
