package com.example.tutoria03.services;

import java.util.ArrayList;

import com.example.tutoria03.exceptions.ConflictException;
import com.example.tutoria03.exceptions.StudentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tutoria03.models.Estudiante;
import com.example.tutoria03.repositories.IEstudianteRepository;

@Service
public class EstudianteService {

    // crear una instancia de repository
    @Autowired
    private IEstudianteRepository estudianteRepository;

    public ArrayList<Estudiante> GetAll(){
        return (ArrayList<Estudiante>) estudianteRepository.findAll();
    }

    public Estudiante GetStudentById(int id){
        return estudianteRepository.findById(id).orElse(null);
    }

    public Estudiante save(Estudiante estudiante){
        if (estudianteRepository.existsByCorreo(estudiante.getCorreo())) throw new ConflictException("El estudiante con ese correo ya existe");
        return estudianteRepository.save(estudiante);
    }

    //mejorar logica de update//
    
    public Estudiante update(Estudiante estudiante,int id){
        if (estudianteRepository.existsByCorreo(estudiante.getCorreo())) throw new ConflictException("El estudiante con ese correo ya existe");
        if(existeEstudiante(id)) estudiante.setId(id);
        else throw new StudentNotFound("Estudiante no encontrado");
        return estudianteRepository.save(estudiante);
    }

    private boolean existeEstudiante(int id){
        return estudianteRepository.findById(id).orElse(null) == null ? false : true;
    }

    public void Delete(int id){
       if (existeEstudiante(id)) {
           estudianteRepository.deleteById(id);
       } else {
           throw new StudentNotFound("No existe el estudiante");
       }
    }
}
