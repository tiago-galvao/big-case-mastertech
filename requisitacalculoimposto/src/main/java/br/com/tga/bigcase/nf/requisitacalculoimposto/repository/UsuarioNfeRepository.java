package br.com.tga.bigcase.nf.requisitacalculoimposto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.tga.bigcase.nf.requisitacalculoimposto.model.UsuarioNfe;

public interface UsuarioNfeRepository extends CrudRepository<UsuarioNfe, Long> {
    List<UsuarioNfe> findAllByDocumento(String documento);
}