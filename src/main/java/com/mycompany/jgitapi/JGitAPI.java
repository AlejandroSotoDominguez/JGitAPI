
package com.mycompany.jgitapi;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
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
                    
                /**
                 * Opción para clonar un repositorio.
                 * Añadimos las dependencias de la librería JGit.
                 * Usamos el método cloneRepository() para clonar el repositorio.
                 * Le decimos la ruta del repositorio que queremos clonar.
                 * Y en que ruta queremos crear el proyecto
                 */    
                    
                case 2:
            
                try {
                    Git.cloneRepository()
                            .setURI("https://github.com/AlejandroSotoDominguez/MaquinaDeCafe.git")
                            .setDirectory(new File("/home/local/DANIELCASTELAO/asotodominguez/proyectoAPIGithub"))
                            .call();
                } catch (GitAPIException ex) {
                    Logger.getLogger(JGitAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                    break;
                    
            }
            
        }while(opcion!=0);
    }
    
}
