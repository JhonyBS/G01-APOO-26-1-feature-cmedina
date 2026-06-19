package com.example.tutoria03.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tutoria03.models.Docente;

@Repository
public interface DocenteRepository extends CrudRepository<Docente,Integer> {

    public boolean existsByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);


}