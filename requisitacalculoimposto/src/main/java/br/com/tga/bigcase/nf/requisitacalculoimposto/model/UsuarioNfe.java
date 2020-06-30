package br.com.tga.bigcase.nf.requisitacalculoimposto.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class UsuarioNfe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String documento;
    @Getter @Setter private BigDecimal valor;
    @Getter @Setter private String status;

    @OneToOne
    @Getter @Setter private Nfe nfe;

}