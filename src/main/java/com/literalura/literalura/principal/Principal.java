package com.literalura.literalura.principal;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.literalura.literalura.model.Autor;
import com.literalura.literalura.model.Libro;
import com.literalura.literalura.model.RecordLibro;
import com.literalura.literalura.model.RecordPagina;
import com.literalura.literalura.repository.AutorRepository;
import com.literalura.literalura.repository.LibroRepository;
import com.literalura.literalura.service.ConsumoApi;
import com.literalura.literalura.service.ConvierteDatos;
import org.antlr.v4.runtime.InputMismatchException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumirApi=new ConsumoApi();
    private ConvierteDatos convierteDatos=new ConvierteDatos();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    private final String URL_BASE="https://gutendex.com/books/";

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {

        this.libroRepository=libroRepository;
        this.autorRepository=autorRepository;

    }


    public void muestraElMenu() {

         /*
            var opcion = -1;
            while (opcion != 0) {
                var menu = """
                        1 - Buscar libro por Titulo 
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos en un determinado año   
                        5-  Listar libros por idiomas   
                        0 - Salir
                        """;
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroTituloWeb();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        buscarAutoresVivosAño();
                        break;
                    case 5:
                        listarLibrosIdiomas();
                        break;

                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }
       */
        var opcion = -1;
        Scanner teclado = new Scanner(System.in);

        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por Titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año   
                    5 - Listar libros por idiomas   
                    0 - Salir
                    """;
            System.out.println(menu);
            System.out.print("Ingrese una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        buscarLibroTituloWeb();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        buscarAutoresVivosAño();
                        break;
                    case 5:
                        listarLibrosIdiomas();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida, por favor intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido. Intente nuevamente.");
            }
        }

        teclado.close();

    }

    private void listarLibrosIdiomas() {

        String menu= """
                **************************************
                Elija una idioma XX 
                **************************************
                es - español
                en - ingles
                
                """;
       System.out.println(menu);

       var idioma=teclado.nextLine();

       List<Libro>listaLiborsIdioma=libroRepository.findByIdiomaContainsIgnoreCase(idioma);

       if(listaLiborsIdioma.isEmpty())
       {
           System.out.println("No se encontraron libros para el idioma seleccionado");

       }
       else
       {
           System.out.println("Lista de Libros en el idioma seleccionado");
           listaLiborsIdioma.forEach(System.out::println);

           IntSummaryStatistics est=listaLiborsIdioma.stream()
                   .collect(Collectors.summarizingInt(Libro::getDescargas));

           System.out.println("Cantidad Libros encontrados:"+est.getCount()+" Promedio descargas Online:"+est.getAverage()+" Descargas OnLine Minimo:"+est.getMin()+" Descargas Online Maximo:"+est.getMax());



       }





    }

    private void buscarAutoresVivosAño() {

        System.out.println("Ingrese un Año para saber que Autores estaban vivos ");

        try {
            var ano = Integer.parseInt(teclado.nextLine());

            List<Autor> autorList = autorRepository.obtenerAutoresPorAñoNacimiento(ano);

            if (autorList.isEmpty()) {
                System.out.println("No se encontraron autores para el año ingresado");

            } else {
                System.out.println("Lista de Autores vivos encontrados");
                autorList.forEach(System.out::println);


            }

        }catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número de año válido, Ejemplo 1945. Intente nuevamente.");
        }


    }

    private void listarAutoresRegistrados() {

        List<Autor>listaAutoresRegistrados=autorRepository.findAll();
        System.out.println("Lista de Autores registrados en la base de datos local");

        listaAutoresRegistrados.sort(Comparator.comparing(Autor::getNombre));

        listaAutoresRegistrados.forEach(System.out::println);


    }

    private void listarLibrosRegistrados() {


        List<Libro>listaLibrosRegistrados=libroRepository.findAll();

        System.out.println("Lista de Libros Registrados en la base de datos local");

        listaLibrosRegistrados.sort(Comparator.comparing(Libro::getTitulo));
        listaLibrosRegistrados.forEach(System.out::println);



    }


    private void buscarLibroTituloWeb() {


        try {
            System.out.println("Ingrese un Titulo de Libro a buscar");
            var libro = teclado.nextLine();

            var json = consumirApi.obtenerDatos(URL_BASE + "?search=" + libro.replace(" ", "+"));

            var buscarLibrosApiList = convierteDatos.obtenerDatos(json, RecordPagina.class);

            List<RecordLibro> librosLista = buscarLibrosApiList.datosLibrosList();

            Optional<RecordLibro> librobuscar = librosLista.stream()
                    .filter(l -> l.titulo().toLowerCase().contains(libro.toLowerCase()))
                    .findFirst();

            if (librobuscar.isPresent()) {
                RecordLibro libroaux = librobuscar.get();
                Libro nuevolibro = new Libro(libroaux);

                Optional<Libro> libroVerificar = libroRepository.findById(libroaux.id());

                if (libroVerificar.isPresent()) {
                    System.out.println("El libro " + nuevolibro.getTitulo() + " buscado en la Api ya existe en la base de datos local");

                } else {

                    Autor nuevoAutor = nuevolibro.getAutor();

                    Optional<Autor>autorVerificarExistencia=autorRepository.findByNombre(nuevoAutor.getNombre());


                    if (autorVerificarExistencia.isPresent()) {

                       Autor autorAux=autorVerificarExistencia.get();
                       System.out.println("El Autor " + nuevoAutor.getNombre() + "ya existe en la base de datos local");

                       nuevolibro.setAutor(autorAux);

                    }
                    else
                    {

                      autorRepository.save(nuevoAutor);


                    }

                    libroRepository.save(nuevolibro);

                    System.out.println(nuevolibro.toString());
                    System.out.println("Se guardo con exito un nuevo libro en la base de datos local");


                }

            } else {

                System.out.println("No se encontro ningun libro con ese Titulo ingresado");

            }

        }catch (Exception e)
        {

            System.out.println("Se produjo un error "+e.getMessage());


        }

        }




}
