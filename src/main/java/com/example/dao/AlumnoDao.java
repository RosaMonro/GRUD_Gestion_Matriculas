package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Alumno;

@Repository

public interface AlumnoDao extends JpaRepository<Alumno, Integer> {

    
}
