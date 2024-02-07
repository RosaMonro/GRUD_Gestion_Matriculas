package com.example.services;

import java.util.List;

import com.example.entities.Alumno;

public interface AlumnoService {

    public List<Alumno> dameTodosLosAlumnos();
    public void eliminarAlumno(int idAlumno);
    public void persistirAlumno(Alumno alumno); //le pasamos el alumno que vamos a guardar
    public Alumno dameUnAlumno(int idAlumno);
    public void actualizarEmpleado(Alumno alumno);
}
