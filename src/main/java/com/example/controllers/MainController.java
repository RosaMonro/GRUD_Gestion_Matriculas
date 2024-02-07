package com.example.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.services.AlumnoService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor

public class MainController {

    private final AlumnoService alumnoService;
    // private final CursoService cursoService;

    private final  Logger LOG = Logger.getLogger("MainController");


    @GetMapping("/all")
        public String dameAlumnos(Model model) { // model del org.springframework

            model.addAttribute("alumnos", alumnoService.dameTodosLosAlumnos());

            return "views/listadoAlumnos";
        }



}
