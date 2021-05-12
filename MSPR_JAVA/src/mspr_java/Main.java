package mspr_java;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PrintWriter fluxsortie = null;
        try {
            fluxsortie = new PrintWriter(new FileOutputStream("index.html"));
        }
        catch (FileNotFoundException e) {
            System.out.println("Erreur d'ouverture de index.html");
            System.exit(0);
        }

        System.out.println("Ecriture du fichier");
        List<String> listedesAgents = new ArrayList<>();
        //RÃ©cuperation des ident via le fichier staff.txt
        try {
            BufferedReader  fluxentree = new BufferedReader(new FileReader("staff.txt"));
            // BufferedReader  fluxentree = new BufferedReader(new FileReader(args[0]));
            String ligne = null;
            while ((ligne = fluxentree.readLine()) != null) {
                System.out.println(ligne);
                listedesAgents.add(ligne);
            }

            fluxentree.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("Erreur de l'ouverture du fichier");
        }
        catch (IOException e) {
            System.out.println("Erreur lecture staff.txt");
        }

        //RÃ©cup avec une liste existante
        //String[] listedesAgetns = {"Corrine Berthier" , "Sarah"};
        //Trie par ordre alphabétique la liste des agents
        Collections.sort(listedesAgents);
        AccueilToHTML accueilToHTML = new AccueilToHTML();
        String listedesagentsenHTML = accueilToHTML.render(listedesAgents.toArray(new String[listedesAgents.size()]));

        fluxsortie.println(listedesagentsenHTML);

        fluxsortie.close();

        System.out.println("fin programme");
    }

}


