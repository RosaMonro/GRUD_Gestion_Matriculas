package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.AlumnoDao;
import com.example.entities.Alumno;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoDao alumnoDao;

    @Override
    public List<Alumno> dameTodosLosAlumnos() {
        return alumnoDao.findAll();
    }

    @Override
    public void eliminarAlumno(int idAlumno) {
        alumnoDao.deleteById(idAlumno);
    }

    @Override
    public void persistirAlumno(Alumno alumno) {
        alumnoDao.save(alumno);
    }

    @Override
    public Alumno dameUnAlumno(int idAlumno) {
        return alumnoDao.findById(idAlumno).get();
    }

    @Override
    public void actualizarEmpleado(Alumno alumno) {
        alumnoDao.save(alumno);
    }



}
