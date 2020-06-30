package br.com.tga.bigcase.nf.gerarimposto.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "receita", url = "https://www.receitaws.com.br")
public interface ReceitaFederal {
    @RequestMapping("/v1/cnpj/{cnpj}")
    GetInfoReceitoFederal getCapital(@PathVariable String cnpj);
}