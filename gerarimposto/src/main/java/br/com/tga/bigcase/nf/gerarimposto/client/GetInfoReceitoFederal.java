package br.com.tga.bigcase.nf.gerarimposto.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class GetInfoReceitoFederal {

    @JsonProperty("capital_social")
    @Getter @Setter  private String capital;
}