package br.com.tga.bigcase.nf.requisitacalculoimposto.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Nfe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    @Getter @Setter private BigDecimal valorInicial;
    @Getter @Setter private BigDecimal valorIRRF;
    @Getter @Setter private BigDecimal valorCSLL;
    @Getter @Setter private BigDecimal valorCofins;
    @Getter @Setter private BigDecimal valorFinal;

}