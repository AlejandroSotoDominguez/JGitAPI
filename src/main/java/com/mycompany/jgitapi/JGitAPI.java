
package com.mycompany.jgitapi;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;

public class JGitAPI {

    public static void main(String[] args) throws IOException {
        int opcion;
        
        /**
         * Menú con las 5 opciones posibles a ejecutar.
         */
        
        do{
            opcion = Integer.parseInt(JOptionPane.showInputDialog("1. Crear repositorio"
                    + "2. Clonar repositorio"
                    + "3. Hacer commit"
                    + "4. Inicializar repositorio"
                    + "5. Push"));
            switch(opcion){
                /**
                * Conexión con github usando las credenciales a través el fichero .gitHub
                * Se crea el constructor del repositorio.
                * Y creamos el repositorio.
                */
                      
                case 1:
                    try{
                        GitHub github = GitHub.connect();
                        GHCreateRepositoryBuilder builder;
                        builder=github.createRepository("repositorioMaven");
                        builder.create();
                    }catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
            }
            
        }while(opcion!=0);
    }
    
}
