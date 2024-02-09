package com.example.services;

import org.springframework.stereotype.Service;

import com.example.dao.AlumnoDao;
import com.example.dao.TelefonoDao;
import com.example.entities.Telefono;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TelefonoServiceImpl implements TelefonoService {

    private final AlumnoDao alumnoDao;
    private final TelefonoDao telefonoDao;

    @Override
    public void persistirTelefono(int idAlumno, Telefono telefono) {

        telefono.setAlumno(alumnoDao.findById(idAlumno).get());
        telefonoDao.save(telefono);
        
    }

}
// public void persistirTelefono(int idEmpleado, Telefono telefono) {
//     telefono.setEmpleado(empleadoDao.findById(idEmpleado).get());
//     telefonoDao.save(telefono);
// }