
package com.mycompany.jgitapi;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;

public class JGitAPI {
    /**
     * @param args
     * @throws IOException Se lanza la excepción si se ha producido un error en la entrada/salida.
     * @throws GitAPIException Excepción que se lanza cuando hay algún problema con la API de Github.
     */

    public static void main(String[] args) throws IOException, GitAPIException {
        int opcion;
        
        /**
         * Menú con las 5 opciones posibles a ejecutar.
         */
        
        do{
            opcion = Integer.parseInt(JOptionPane.showInputDialog(""
                    + "1. Crear repositorio\n"
                    + "2. Clonar repositorio\n"
                    + "3. Hacer commit\n"
                    + "4. Inicializar repositorio\n"
                    + "5. Push"));
            switch(opcion){
                /**
                * Conexión con github usando las credenciales a través el fichero .gitHub
                * Se crea el constructor del repositorio.
                * Y creamos el repositorio.
                */
                      
                case 1:
                    String repositorio = JOptionPane.showInputDialog("Nombre del repositorio");
                    
                    try{
                        GitHub github = GitHub.connect();
                        GHCreateRepositoryBuilder builder;
                        builder=github.createRepository(repositorio);
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
                    
                    String url = JOptionPane.showInputDialog("Escribe la url del proyecto que quieres clonar");
                    String nombre = JOptionPane.showInputDialog("Escribe el nombre para la carpeta del proyecto");
                    
                    try {
                        Git.cloneRepository()
                                .setURI(url)
                                .setDirectory(new File("/home/local/DANIELCASTELAO/asotodominguez/NetBeansProjects/"+nombre))
                                .call();
                    } catch (GitAPIException ex) {
                        Logger.getLogger(JGitAPI.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                    break;
                    
                /**
                 * Esta opción hace un commit de nuestro proyecto.
                 * La variable mensaje nos pedirá que escribamos por pantalla la descripción del commit.
                 * Definimos la ruta del repositorio.
                 */        
                    
                case 3:
                    
                    String mensaje = JOptionPane.showInputDialog("Descripción del commit");
                    String ruta = JOptionPane.showInputDialog("Escribe la ruta del proyecto");

                    FileRepositoryBuilder repositoryBuilder=new FileRepositoryBuilder();
                    Repository repository=repositoryBuilder.setGitDir(new File(ruta))
                            .readEnvironment()
                            .findGitDir()
                            .setMustExist(true)
                            .build();

                    Git git=new Git(repository);
                    AddCommand add=git.add();
                    add.addFilepattern(ruta).call();
                    CommitCommand commit=git.commit();
                    commit.setMessage(mensaje).call(); 
                    
                    break;
                    
                /**
                 * Inicializa el repositorio en la ruta que el pasamos.
                 */    
                
                case 4:
                    String rutaInicializar = JOptionPane.showInputDialog("Ruta del proyecto");
                    InitCommand repositorio2 = new InitCommand();
                    repositorio2.setDirectory(new File(rutaInicializar)).call();
                    
                    break;
                    
                    
            }
            
        }while(opcion!=0);
    }
    
}
