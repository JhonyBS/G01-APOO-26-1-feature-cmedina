package com.example.tutoria03.services;

import java.util.ArrayList;

import com.example.tutoria03.exceptions.ConflictException;
import com.example.tutoria03.exceptions.TeacherNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tutoria03.models.Docente;
import com.example.tutoria03.repositories.DocenteRepository;

@Service
public class DocenteService {
 @Autowired
    private DocenteRepository docenteRepository;

    public ArrayList<Docente> getAll(){
        return (ArrayList<Docente>) docenteRepository.findAll();
    }
    
    public Docente getDocenteById(int id){
    return docenteRepository.findById(id)
            .orElseThrow(() -> new TeacherNotFound("Docente no encontrado"));
}

    // Crear docente

    public Docente save(Docente docente){
        if (docenteRepository.existsByTipoDocumentoAndNumeroDocumento(docente.getTipoDocumento(), docente.getNumeroDocumento())) throw new ConflictException("El docente con ese tipo y numero de documento ya existe");
        return docenteRepository.save(docente);
    }


    // Actualizar docente

    public Docente update(Docente docente, int id){

    Docente actual = docenteRepository.findById(id)
            .orElseThrow(() -> new TeacherNotFound("Docente no encontrado"));

    if (!actual.getTipoDocumento().equals(docente.getTipoDocumento()) ||
        !actual.getNumeroDocumento().equals(docente.getNumeroDocumento())) {

        if (docenteRepository.existsByTipoDocumentoAndNumeroDocumento(
                docente.getTipoDocumento(),
                docente.getNumeroDocumento())) {

            throw new ConflictException(
                "Ya existe un docente con ese tipo y numero de documento"
            );
        }
    }

    docente.setId(id);
    return docenteRepository.save(docente);
    }


    // Eliminar docente

    public void delete(int id){

    if (!docenteRepository.existsById(id)) {
        throw new TeacherNotFound("Docente no encontrado");
    }

    docenteRepository.deleteById(id);
    }


}
