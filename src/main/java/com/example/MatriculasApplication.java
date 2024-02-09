package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Alumno;
import com.example.entities.Correo;
import com.example.entities.Curso;
import com.example.entities.Genero;
import com.example.entities.Horario;
import com.example.entities.Telefono;
import com.example.services.AlumnoService;
import com.example.services.CorreoService;
import com.example.services.CursoService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor

public class MatriculasApplication implements CommandLineRunner {

	private final AlumnoService alumnoService;
	private final CursoService cursoService;
	private final TelefonoService telefonoService;
	private final CorreoService correoService;

	public static void main(String[] args) {
		SpringApplication.run(MatriculasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	// CURSOS

		Curso curso1 = Curso.builder()
			.descripcion("CSS")
			.horario(Horario.DIURNO)
			.build();

		Curso curso2 = Curso.builder()
			.descripcion("JAVA")
			.horario(Horario.NOCTURNO)
			.build();

		Curso curso3 = Curso.builder()
			.descripcion("JS")
			.horario(Horario.NOCTURNO)
			.build();

		Curso curso4 = Curso.builder()
			.descripcion("HTML")
			.horario(Horario.DIURNO)
			.build();


			cursoService.persistirCurso(curso1);
			cursoService.persistirCurso(curso2);
			cursoService.persistirCurso(curso3);
			cursoService.persistirCurso(curso4);



	// ALUMNOS

		Alumno alumno1 = Alumno.builder()
			.nombre("Rosa")
			.apellidos("Montero Román")
			.genero(Genero.MUJER)
			.fechaMatricula(LocalDate.of(2021, Month.OCTOBER, 11))
			.totalAsignaturas(4)
			.curso(cursoService.dameUnCurso(1))
			.foto("foto1")
			.build();


		Alumno alumno2 = Alumno.builder()
			.nombre("Daniela")
			.apellidos("García Lomas")
			.genero(Genero.MUJER)
			.fechaMatricula(LocalDate.of(2020, Month.SEPTEMBER, 1))
			.totalAsignaturas(3)
			.curso(cursoService.dameUnCurso(1))
			.foto("foto2")
			.build();

		Alumno alumno3 = Alumno.builder()
			.nombre("Victor")
			.apellidos("Moreno Marchante")
			.genero(Genero.HOMBRE)
			.fechaMatricula(LocalDate.of(2009, Month.MARCH, 21))
			.totalAsignaturas(5)
			.curso(cursoService.dameUnCurso(2))
			.foto("foto4")
			.build();

		Alumno alumno4 = Alumno.builder()
			.nombre("Sara")
			.apellidos("Luján Salvador")
			.genero(Genero.MUJER)
			.fechaMatricula(LocalDate.of(2021, Month.NOVEMBER, 11))
			.totalAsignaturas(4)
			.curso(cursoService.dameUnCurso(2))
			.foto("foto4")
			.build();

		Alumno alumno5 = Alumno.builder()
			.nombre("Pau")
			.apellidos("Sanchis Montoro")
			.genero(Genero.OTRO)
			.fechaMatricula(LocalDate.of(2018, Month.DECEMBER, 28))
			.totalAsignaturas(3)
			.curso(cursoService.dameUnCurso(3))
			.foto("foto5")
			.build();

		Alumno alumno6 = Alumno.builder()
			.nombre("Salvador")
			.apellidos("Patricio López")
			.genero(Genero.HOMBRE)
			.fechaMatricula(LocalDate.of(2020, Month.FEBRUARY, 12))
			.totalAsignaturas(3)
			.curso(cursoService.dameUnCurso(3))
			.foto("foto7")
			.build();

		Alumno alumno7 = Alumno.builder()
			.nombre("Leví")
			.apellidos("Corrales Camuñas")
			.genero(Genero.HOMBRE)
			.fechaMatricula(LocalDate.of(2023, Month.MAY, 11))
			.totalAsignaturas(2)
			.curso(cursoService.dameUnCurso(4))
			.foto("foto7")
			.build();

		Alumno alumno8 = Alumno.builder()
			.nombre("Clem")
			.apellidos("Mory Romain")
			.genero(Genero.MUJER)
			.fechaMatricula(LocalDate.of(2021, Month.DECEMBER, 1))
			.totalAsignaturas(4)
			.curso(cursoService.dameUnCurso(4))
			.foto("foto8")
			.build();


			alumnoService.persistirAlumno(alumno1);
			alumnoService.persistirAlumno(alumno2);
			alumnoService.persistirAlumno(alumno3);
			alumnoService.persistirAlumno(alumno4);
			alumnoService.persistirAlumno(alumno5);
			alumnoService.persistirAlumno(alumno6);
			alumnoService.persistirAlumno(alumno7);
			alumnoService.persistirAlumno(alumno8);


		
	// TELEFONOS

		Telefono telefono1Alumno1 = Telefono.builder()
			.numero("699845688")
			.alumno(alumnoService.dameUnAlumno(1))
			.build();

		Telefono telefono1Alumno2 = Telefono.builder()
			.numero("976543257")
			.alumno(alumnoService.dameUnAlumno(2))
			.build();

		Telefono telefono1Alumno3 = Telefono.builder()
			.numero("699845688")
			.alumno(alumnoService.dameUnAlumno(3))

			.build();

		Telefono telefono1Alumno4 = Telefono.builder()
			.numero("876654325")
			.alumno(alumnoService.dameUnAlumno(4))

			.build();

		Telefono telefono1Alumno5 = Telefono.builder()
			.numero("965437865")
			.alumno(alumnoService.dameUnAlumno(5))

			.build();

		Telefono telefono1Alumno6 = Telefono.builder()
			.numero("997665339")
			.alumno(alumnoService.dameUnAlumno(6))

			.build();

		Telefono telefono1Alumno7 = Telefono.builder()
			.numero("977654437")
			.alumno(alumnoService.dameUnAlumno(7))

			.build();

		Telefono telefono1Alumno8 = Telefono.builder()
			.numero("987769877")
			.alumno(alumnoService.dameUnAlumno(8))
			.build();


			telefonoService.persistirTelefono(1, telefono1Alumno1);
			telefonoService.persistirTelefono(2, telefono1Alumno2);
			telefonoService.persistirTelefono(3, telefono1Alumno3);
			telefonoService.persistirTelefono(4, telefono1Alumno4);
			telefonoService.persistirTelefono(5, telefono1Alumno5);
			telefonoService.persistirTelefono(6, telefono1Alumno6);
			telefonoService.persistirTelefono(7, telefono1Alumno7);
			telefonoService.persistirTelefono(8, telefono1Alumno8);





	// CORREOS

		Correo correo1Alumno1 = Correo.builder()
			.correo("xxxxxx@gmail.com")
			.alumno(alumnoService.dameUnAlumno(1))
			.build();

		Correo correo1Alumno2 = Correo.builder()
			.correo("xxxxxx@gmail.com")
			.alumno(alumnoService.dameUnAlumno(2))
			.build();

		Correo correo1Alumno3 = Correo.builder()
			.correo("xxxxxx@gmail.com")
			.alumno(alumnoService.dameUnAlumno(3))

			.build();

		Correo correo1Alumno4 = Correo.builder()
			.correo("xxxxxx@gmail.com")
			.alumno(alumnoService.dameUnAlumno(4))

			.build();

		Correo correo1Alumno5 = Correo.builder()
			.correo("xxxxxx@gmail.com")
			.alumno(alumnoService.dameUnAlumno(5))

			.build();

		Correo correo1Alumno6 = Correo.builder()
			.correo("xxxxxx@gmail.com")
			.alumno(alumnoService.dameUnAlumno(6))

			.build();

		Correo correo1Alumno7 = Correo.builder()
			.correo("xxxxxx@gmail.com")
			.alumno(alumnoService.dameUnAlumno(7))

			.build();

		Correo correo1Alumno8 = Correo.builder()
			.correo("xxxxxx@gmail.com")
			.alumno(alumnoService.dameUnAlumno(8))
			.build();


			correoService.persistirCorreo(1, correo1Alumno1);
			correoService.persistirCorreo(2, correo1Alumno2);
			correoService.persistirCorreo(3, correo1Alumno3);
			correoService.persistirCorreo(4, correo1Alumno4);
			correoService.persistirCorreo(5, correo1Alumno5);
			correoService.persistirCorreo(6, correo1Alumno6);
			correoService.persistirCorreo(7, correo1Alumno7);
			correoService.persistirCorreo(8, correo1Alumno8);


	}

}
