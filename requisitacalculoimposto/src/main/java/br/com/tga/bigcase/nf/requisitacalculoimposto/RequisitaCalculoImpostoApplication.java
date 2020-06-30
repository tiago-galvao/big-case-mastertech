package br.com.tga.bigcase.nf.requisitacalculoimposto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class RequisitaCalculoImpostoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequisitaCalculoImpostoApplication.class, args);
	}

}
