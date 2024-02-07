package com.example.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Alumno;
import com.example.services.AlumnoService;
import com.example.services.CursoService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor

public class MainController {

    private final AlumnoService alumnoService;
    private final CursoService cursoService;

    private final  Logger LOG = Logger.getLogger("MainController");


    @GetMapping("/all")
        public String dameAlumnos(Model model) { // model del org.springframework

            model.addAttribute("alumnos", alumnoService.dameTodosLosAlumnos());

            return "views/listadoAlumnos";
        }


    @GetMapping("/formAltaModificacion")
    public String formularioAltaModificacion (Model model) {

        // Le paso un modelo vacío (eso se rellena en el formulario)
        Alumno alumno = new Alumno();

        model.addAttribute("alumno", alumno);

        // Necesito envíar tb los departamentos
        model.addAttribute("cursos", cursoService.dameTodosLosCursos());

        return "views/formAltaModificacion";
    }



}
