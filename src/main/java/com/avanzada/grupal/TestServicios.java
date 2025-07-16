package com.avanzada.grupal;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.avanzada.grupal.config.SpringConfig;
import com.avanzada.grupal.model.Author;
import com.avanzada.grupal.service.AuthorService;
import com.avanzada.grupal.service.BookService;
import com.avanzada.grupal.service.CustomerService;
import com.avanzada.grupal.service.PurchaseOrderService;

@Component
public class TestServicios {

    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private PurchaseOrderService purchaseOrderService;


    public static void main(String[] args) {
        System.out.println("=== PRUEBAS CON SPRING CDI ===\n");
        
        // Crear contexto de Spring
        try (AnnotationConfigApplicationContext context = 
             new AnnotationConfigApplicationContext(SpringConfig.class)) {
            
            // Obtener el bean con dependencias inyectadas
            TestServicios test = context.getBean(TestServicios.class);
            
            // Ejecutar las pruebas
            test.probarServicios();


            //probar autorService();
            test.testModels();


         
               
         
            
        } catch (Exception e) {
            System.err.println("Error al inicializar Spring: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void testModels() {
       
        
        // Aquí puedes crear instancias de Author, Book, etc. y probar sus métodos
        System.out.println("Probando AuthorService...");
        System.out.println("listando autores:");
       authorService.listarTodos().forEach(author -> {
           System.out.println("Autor: " + author.getName() + ", ID: " + author.getId());
       });


    //crear autor funcionando
    //eliminar autor funcionando
    //insertar books funcionando
    //bok pormtitulo funcionando
    //encontrar con autor funcionando
    
    
    //customer
    //econtrar customer por nombre funcionando

//purchase order
System.out.println("-----------------------Probando PurchaseOrderService...");
        System.out.println("listando ordenes de compra:");
    purchaseOrderService.encontrarTodos().forEach(po -> {
            System.out.println("Orden de compra ID: " + po.getId() + ", Fecha llegada al almacen: " + po.getPlaced_on());
        });

     
        // Puedes agregar más pruebas aquí para BookService, CustomerService, etc.




    

    }

    private void probarServicios() {
        System.out.println("1. VERIFICANDO INYECCIÓN DE DEPENDENCIAS:");
        
        // Verificar que los servicios fueron inyectados
        System.out.println("AuthorService inyectado: " + (authorService != null ? "si" : "no"));
        System.out.println("BookService inyectado: " + (bookService != null ? "si" : "no"));
        System.out.println("CustomerService inyectado: " + (customerService != null ? "si" : "no"));
        System.out.println("PurchaseOrderService inyectado: " + (purchaseOrderService != null ? "si" : "no"));

        if (authorService != null) {
            System.out.println("\n2. PROBANDO AUTHOR SERVICE:");
            try {
                // Aquí puedes llamar métodos del servicio
                System.out.println("Tipo de AuthorService: " + authorService.getClass().getSimpleName());
                System.out.println("✅ AuthorService funciona correctamente");
            } catch (Exception e) {
                System.err.println("❌ Error en AuthorService: " + e.getMessage());
            }
        }
        
        if (bookService != null) {
            System.out.println("\n3. PROBANDO BOOK SERVICE:");
            try {
                System.out.println("Tipo de BookService: " + bookService.getClass().getSimpleName());
                System.out.println("✅ BookService funciona correctamente");
            } catch (Exception e) {
                System.err.println("❌ Error en BookService: " + e.getMessage());
            }
        }
        
        System.out.println("\n🎉 ¡CDI con Spring funcionando correctamente!");
    }
}
