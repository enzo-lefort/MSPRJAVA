package mspr_java;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PrintWriter fluxsortie = null; //Initialisation du flux d'écriture du fichier index.html
        try { // Tente la création du fichier index.html
            fluxsortie = new PrintWriter(new FileOutputStream("index.html"));
        }
        catch (FileNotFoundException e) { //Affiche un message en cas d'erreur
            System.out.println("Erreur lors de la création de index.html");
            System.exit(0);
        }
        System.out.println("Ecriture du fichier");
        List<String> listedesAgents = new ArrayList<>(); //Initialise la liste Liste des agents
        try { //Tente la lecture du fichier staff.txt
            
            BufferedReader  fluxentree = new BufferedReader(new FileReader("staff.txt")); // "scan" le fichier pour retranscrire ensuite la liste
            String ligne = null; //Déclaration de la variable qui recevra chacune des lignes une après l'autre du fichier staff.txt
            
            while ((ligne = fluxentree.readLine()) != null) // Parcour ligne par ligne le fichier staff.txt
            { 
                //System.out.println(ligne);  //L'affichage de la ligne permettait de débuguer lors du développement.
                listedesAgents.add(ligne); //Insertion de la ligne actuelle dans la liste 
            }

            fluxentree.close(); // ferme le flux (à ne pas oublier lorsque la fonction n'est plus utilisée)

        }
        catch (FileNotFoundException e) {
            System.out.println("Erreur de l'ouverture du fichier");
        }                                                           // comme ci-dessus : message en cas d'erreur si le fichier n'est pas trouvé ou à être lu
        catch (IOException e) {
            System.out.println("Erreur lecture staff.txt");
        }
        
        //Trie par ordre alphabétique la liste des agents
        Collections.sort(listedesAgents);
        
        AccueilToHTML accueilToHTML = new AccueilToHTML(); //Initialisation de la classe AccueiltoHTML pour rédiger la page index.html
        String listedesagentsenHTML = accueilToHTML.render(listedesAgents.toArray(new String[listedesAgents.size()]));//Utilisation de la classe AccueilToHTML, en paramètre les liste des agents
        //retourne une chaine de caractère contenant le code du fichier index.html
        fluxsortie.println(listedesagentsenHTML); //Utilise la chaîne de caractère reçu pour l'écrire dans le flux de sortie index.html

        fluxsortie.close(); //Fermeture du flux de  sortie ( d'écriture du fichier index.html )

        System.out.println("Fin du programme");
    }

}

