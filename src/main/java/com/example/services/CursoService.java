package com.example.services;

import java.util.List;

import com.example.entities.Alumno;
import com.example.entities.Curso;

public interface CursoService {

    public List<Curso> dameTodosLosCursos();
    public Curso dameUnCurso(int idCurso);
   //public List<Alumno> dameAlumndosDelCurso(int idCurso);
    public void persistirCurso(Curso curso);

    // relacionado con la obtención de información específica sobre 
    // los alumnos que pertenecen a un curso en particular. 
    // El servicio de cursos actuará como un intermediario para obtener
    // la información de los alumnos asociados a un curso específico utilizando el DAO de alumnos.

}
