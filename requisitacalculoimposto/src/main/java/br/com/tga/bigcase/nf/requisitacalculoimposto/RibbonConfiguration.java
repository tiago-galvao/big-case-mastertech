package br.com.tga.bigcase.nf.requisitacalculoimposto;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import org.springframework.context.annotation.Bean;

public class  RibbonConfiguration {
    
    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}