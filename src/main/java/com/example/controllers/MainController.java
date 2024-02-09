package com.example.controllers;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Alumno;
import com.example.entities.Correo;
import com.example.entities.Curso;
import com.example.entities.Telefono;
import com.example.services.AlumnoService;
import com.example.services.CorreoService;
import com.example.services.CursoService;
import com.example.services.TelefonoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor

public class MainController {

    private final AlumnoService alumnoService;
    private final CursoService cursoService;
    

    private final  Logger LOG = Logger.getLogger("MainController");

// MOSTRAR LISTADO

    @GetMapping("/all")
        public String dameAlumnos(Model model) { // model del org.springframework

            model.addAttribute("alumnos", alumnoService.dameTodosLosAlumnos());

            return "views/listadoAlumnos";
        }




// MOSTRAR FORMULARIO
    @GetMapping("/formAltaModificacion")
    public String formularioAltaModificacion (Model model) {

        // Le paso un modelo vacío (eso se rellena en el formulario)
        Alumno alumno = new Alumno();

        model.addAttribute("alumno", alumno);

        // Necesito envíar tb los departamentos
        model.addAttribute("cursos", cursoService.dameTodosLosCursos());

        return "views/formAltaModificacion";
    }


// ELIMINAR ALUMNO

        @GetMapping("/eliminar/{id}")
        @Transactional
        public String eliminarAlumno(@PathVariable(name = "id", required = true) int idAlumno) {
            alumnoService.eliminarAlumno(idAlumno);

            return "redirect:/all";
        }



// MODIFICAR ALUMNO
        

        @GetMapping("/actualizar/{id}")
        @Transactional
        public String actualizarAlumno(@PathVariable(name = "id", required = true) int idAlumno, Model model) {

            // llamamos al método de buscar al alumno para recuperar al alumno cuyo id se recibe como parámetro
            Alumno alumno = alumnoService.dameUnAlumno(idAlumno);
            model.addAttribute("alumno", alumno);

            // También necesito los cursos
            List<Curso> cursos = cursoService.dameTodosLosCursos();
            model.addAttribute("cursos", cursos);

            // la lista de teléfonos y correos ya los tengo por el cascadeo. 
            // pero necesito construir el numero y la dirección unidos por ; a partir 
            // de los teléfonos y correos recibidos conjuntamente con el alumno
            if (alumno.getTelefonos() != null) {
                String numerosTelefono = alumno.getTelefonos()
                                                .stream()
                                                .map(Telefono::getNumero)
                                                .collect(Collectors.joining(";"));

                model.addAttribute("numerosTelefono", numerosTelefono);
            }


            // Mandar el correo al formulario de alta
            if (alumno.getCorreos() != null) {
                String direccionesDeCorreo = alumno.getCorreos()
                                                .stream()
                                                .map(Correo::getCorreo)
                                                .collect(Collectors.joining(";"));
                                                
                model.addAttribute("direccionesDeCorreo", direccionesDeCorreo);
            }

            return "views/formAltaModificacion";
        }


}
