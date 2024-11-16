package com.aluracursos.literalura.Principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aluracursos.literalura.Service.AutorService;
import com.aluracursos.literalura.Service.ConsumoApi;
import com.aluracursos.literalura.Service.ConvierteDatos;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.DatosApi;
import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.Service.LibroService;

@Component
public class AppPrincipal {
    private final String BASE_URL = "https://gutendex.com/books/";
    Scanner teclado = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;

    public void mostrarMenu() {
        System.out.println("Bienvenido a la Biblioteca de Literatura");
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1. Buscar libro
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    6. Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroEnApi();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnUnDeterminadoAño();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    System.out.println("Saliendo");
                    opcion = -1;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }

    }

    private void buscarLibroEnApi() {
        System.out.println("Ingrese el título del libro a buscar: ");
        String titulo = teclado.nextLine().replace(" ", "%20");
        String json = getDatosApi(titulo);
        DatosApi datos = convierteDatos.obtenerDatos(json, DatosApi.class);
        if (datos.librosEncontrados().isEmpty()) {
            System.out.println("No se encontraron libros.");
            return;
        }
        DatosLibro primerLibro = datos.librosEncontrados().get(0);
        System.out.println(primerLibro.toString());
        Libro libro = new Libro(primerLibro);
        Optional<Libro> existente = libroService.buscarPorTitulo(libro.getTitulo());
        if (existente.isEmpty()) {
            libroService.guardarLibros(libro);
            System.out.println("Libro guardado: " + libro.getTitulo());
        } else {
            System.out.println("El libro ya existe: " + libro.getTitulo());
        }    
    }

    private String getDatosApi(String libro) {
        String url = BASE_URL + "?search=" + libro;
        String json = consumoApi.obtenerDatos(url);
        return json;
    }

    private void listarLibrosRegistrados() {
        System.out.println("=== Listado de Libros Registrados ===");
        List<Libro> libros = libroService.listarLibros();
        
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(libro -> {
                List<Autor> autores = autorService.buscarAutorPorLibro(libro.getTitulo());
                System.out.println(autores);
                String nombresAutores = autores.stream()
                                           .map(Autor::getNombre)
                                           .collect(Collectors.joining(" \n -"));
                System.out.println();
                System.out.println("------------------------------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor(es): " + nombresAutores);
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Descargas: " + libro.getDescargas());
                System.out.println("Tipo de medio: " + libro.getTipoMedio());
            });
            System.out.println("------------------------------");
        }
        System.out.println("=====================================");
    }

    private void listarAutoresRegistrados() {
        System.out.println("=== Listado de Autores Registrados ===");
        List<Autor> autores = autorService.listarAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(autor -> {
                List<Libro> libros = libroService.buscarPorAutor(autor.getNombre());
                String titulosLibros = libros.stream()
                        .map(Libro::getTitulo)
                        .collect(Collectors.joining(" \n -"));
                System.out.println("------------------------------");
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Nacimiento: " + autor.getNacimiento());
                System.out.println("Muerte: " + autor.getMuerte());
                System.out.println("Libros: " + titulosLibros);
                System.out.println("------------------------------");
            });
            System.out.println("------------------------------");
        }
        System.out.println("=====================================");
    }

    public void listarAutoresVivosEnUnDeterminadoAño(){
        System.out.println("Ingrese el año: ");
        int año = teclado.nextInt();
        List<Autor> autoresVivos = autorService.findAutoresVivosEnAño(año);
        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + año);
        } else {
            autoresVivos.forEach(autor -> {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Nacimiento: " + autor.getNacimiento());
                System.out.println("Muerte: " + autor.getMuerte());
            });
            System.out.println("------------------------------");
        }
        System.out.println("=====================================");
    }

    public void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma: ");
        String idioma = teclado.nextLine();
        List<Libro> librosPorIdioma = libroService.buscarPorIdioma(idioma);
        System.out.println("Libros encontrados: " + librosPorIdioma.size());
        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros en el idioma " + idioma);
        } else {
            librosPorIdioma.forEach(libro -> {
                System.out.println("------------------------------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Descargas: " + libro.getDescargas());
                System.out.println("Tipo de medio: " + libro.getTipoMedio());
            });
            System.out.println("------------------------------");
        }
        System.out.println("=====================================");
    }

}