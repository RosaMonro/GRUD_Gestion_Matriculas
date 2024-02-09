package com.example.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Alumno;
import com.example.entities.Correo;
import com.example.entities.Curso;
import com.example.entities.Horario;
import com.example.entities.Telefono;
import com.example.services.AlumnoService;
import com.example.services.CursoService;

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

    // Guardar los datos del alumno cuando se modifican o se crea

    @PostMapping("/persistir")
    @Transactional
    public String persistirUnAlumno(@ModelAttribute(name = "alumno") Alumno alumno,
                @RequestParam(name = "numerosTel", required = false) String telefonosRecibidos,
                @RequestParam(name = "direccionesCorreo", required = false) String correosRecibidos,
                @RequestParam(name = "file", required = false) MultipartFile imagen) {  // para las imagenes


                // Comprobamos si hemos recibido un archivo (en este caso de img)
            if(!imagen.isEmpty()) {

                // Recuperar ruta (path) relativa de la carpeta donde se almacenará el archivo
                Path imageFolder = Path.of("src/main/resources/static/images"); //del paquete java.nio.file

                // También necesitamos la ruta absoluta
                Path rutaAbsoluta = imageFolder.toAbsolutePath();

                // La ruta completa = rutaAbsoluta + nombre del archivo recibido.
                Path rutaCompleta = Path.of(rutaAbsoluta + "/" + imagen.getOriginalFilename());


                try {

                    byte[] baytesImage = imagen.getBytes();
                    Files.write(rutaCompleta, baytesImage);

                    // establecer la propiedad foto al nombre original del archivo recibido
                    alumno.setFoto(imagen.getOriginalFilename());


                } catch (IOException e) {
                    // TODO: handle exception
                }
            }




            // Procesar los telefonos

            if(telefonosRecibidos != null) {
                String[] arrayTelefonos = telefonosRecibidos.split(";");
                List<String> numerosTelefonos = Arrays.asList(arrayTelefonos);
    
                List<Telefono> telefonos = new ArrayList<>();
    
                numerosTelefonos.stream()
                    .forEach(numeroTelefono -> {
                        telefonos.add(Telefono.builder()
                            .numero(numeroTelefono)
                            .alumno(alumno)
                            .build());
                    });
                
                alumno.setTelefonos(telefonos);
            }



            // Procesar los correos

            if(correosRecibidos != null) {
                String[] arrayCorreos = correosRecibidos.split(";");
                List<String> direccionesDeCorreo = Arrays.asList(arrayCorreos);
    
                List<Correo> correos = new ArrayList<>();
    
                direccionesDeCorreo.stream()
                    .forEach(direccionDeCorreo -> {
                        correos.add(Correo.builder()
                            .correo(direccionDeCorreo)
                            .alumno(alumno)
                            .build());
                    });
    
                alumno.setCorreos(correos);
            }

            alumnoService.persistirAlumno(alumno);

            return "redirect:/all";

    }
    

    
    // Añadir datos del alumno en el formulario cuando hago click en enviar:

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



 
// ALUMNOS TURNO DE MAÑANA
 
        @GetMapping("/listadoAlumnosMañana")
        public String alumnosTurnoMañana (Model model) { 
            List<Alumno>todosLosAlumnos = alumnoService.dameTodosLosAlumnos();
            List<Alumno>listaAlumnosMañana = todosLosAlumnos.stream()
                                                            .filter(alumno -> alumno.getCurso().getHorario() == Horario.DIURNO)
                                                            .collect(Collectors.toList());

            model.addAttribute("listaAlumnosMañana", listaAlumnosMañana);

            return "views/listadoAlumnosMañana";
        }

}
