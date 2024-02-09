package com.example.services;

import org.springframework.stereotype.Service;

import com.example.dao.AlumnoDao;
import com.example.dao.CorreoDao;
import com.example.entities.Correo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CorreoServiceImpl implements CorreoService {

    private final CorreoDao correoDao;
    private final AlumnoDao alumnoDao;

    @Override
    public void persistirCorreo(int idAlumno, Correo correo) {

        correo.setAlumno(alumnoDao.findById(idAlumno).get());
        correoDao.save(correo);
        
    }

}

// @Override
// public void persistirCorreo(int idEmpleado, Correo correo) {
//     correo.setEmpleado(empleadoDao.findById(idEmpleado).get());
//     correoDao.save(correo);
// }