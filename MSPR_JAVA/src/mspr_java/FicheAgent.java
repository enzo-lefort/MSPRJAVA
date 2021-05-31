package mspr_java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FicheAgent {
    public static void agent(String agent){
        //Initialisation des variables.
        StringBuilder html =null;
        PrintWriter agenthtml = null;
        
        List<String> liste_matos = new ArrayList<>();
        List<String>matosAgent = new ArrayList();
        
        String nom = null, prenom = null;
        
        
        try { //Tente la création de la fiche de l'agent indiqué en paramètre
            agenthtml = new PrintWriter(new FileOutputStream("agents_html/"+agent + ".html"));
        }
        catch (FileNotFoundException e) {
            System.out.println("Erreur d'ouverture de "+agent+".html");
            System.exit(0);
        }
        html = new StringBuilder();
       
        try { //Tente la lecture du fichier liste.txt ce fichier contient la liste du matériel
            BufferedReader  liste = new BufferedReader(new FileReader("liste.txt"));
            // BufferedReader  fluxentree = new BufferedReader(new FileReader(args[0]));
            String ligne = null;
            while ((ligne = liste.readLine()) != null) {//Chacune des lignes sont insérées dans la variable liste_matos.
                
                liste_matos.add(ligne);
            }

            liste.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("Erreur de l'ouverture du fichier");
        }
        catch (IOException e) {
            System.out.println("Erreur lecture liste.txt");
        }
        
        try { //Tente la lecture de la fiche agent au format texte
            BufferedReader  fluxentree = new BufferedReader(new FileReader("agents_txt/"+agent + ".txt"));
            // BufferedReader  fluxentree = new BufferedReader(new FileReader(args[0]));
            String ligne = null;
            int i = 0; //Compteur de ligne 
            
            
            while ((ligne = fluxentree.readLine()) != null) {
                /*  Ligne 1 : nom de l'agent
                    Ligne 2 : prénom de l'agent
                    Ligne 3 : rôle de l'agent
                    Ligne 4 : mdp de l'agent pour accéder au l'interface web
                    Ligne 5 et + : matériel utilisé par l'agent
                
                    Le rôle et le mot de passe ne nous interesse pas ici.
                */
                i++;
                    if(i==1){ 
                        nom = ligne;
                    }
                    if(i==2) {
                        prenom = ligne;
                    }
                    if(i>5){
                        matosAgent.add(ligne);
                    }
            }

            fluxentree.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("Erreur de l'ouverture du fichier");
        }
        catch (IOException e) {
            System.out.println("Erreur lecture"+agent+".txt");
        }
        
        //Création de la fiche agent en html
        html.append("<!doctype html>\n");
        html.append("<html lang='fr'>\n");
        html.append("<head>\n");
        html.append("<meta charset='utf-8'>\n");
        html.append("<title>"+nom+" "+prenom+"</title>\n"); //Le nom et prénom apparaîtra dans le titre ( au niveau de l'onglet )
        html.append("<link rel=\"icon\" href=\"..\\favicon.ico\" />");
        html.append("</head>\n\n");
        html.append("<link href='..\\agent.css' rel=\"stylesheet\">\n"); //Pour ne pas prendre le place inutilement dans chacun des fichiers html le fichier css est détaché du code
        html.append("<body>\n");
        html.append("<div class=ligne>\n");
        html.append("<div class=box>\n");
        html.append("<h1>"+nom + " " + prenom +"</h1> \n<br> \n "); //Nom prénom en haut à gauche
        html.append("</div>\n");
        html.append("<div class=box>\n");
        html.append("<img id='logo'  src='..\\logo.jpg' />\n");
        html.append("</div>\n");
        html.append("<div class=box>\n");
        html.append("<img src=..\\agents_img/"+agent+".jpg />\n"); //Photo de l'agent en haut à droite
        html.append("</div>\n");
        html.append("</div>\n");
        html.append("<div class=ligne>\n");
        html.append("<div class=box>\n");
        html.append("</div>\n");
        html.append("<div class=box>\n");
        for(String i : liste_matos){ //Parcour de la liste de matériel issu du fichier liste.txt
            if(matosAgent.contains(i.substring(0, i.indexOf("\t")))){ // si la ligne matériel contient le mot avant la tabulation (le \t = une tabulation dans le fichier texte) 
                html.append("<input  type=\"checkbox\" id=\"scales\" name=\"scales\" checked><label for=\"scales\">"+i.substring(i.indexOf("\t"), i.length())+"</label><br />\n");
                //On créer un champ de séléction avec l'attribut checked, qui cochera le champ
            }else{
                html.append("<input  type=\"checkbox\" id=\"scales\" name=\"scales\"><label for=\"scales\">"+i.substring(i.indexOf("\t"), i.length())+"</label><br />\n");
                //Si ça ne le contient pas on n'écrit pas l'attribut checked
            }
        }
        html.append("</div>\n");
        html.append("<div class=box>\n");
        html.append("</div>\n");
        html.append("</div>\n");
        html.append("<div class=ligne>\n");
        html.append("<div class=box>\n");
        html.append("</div>\n");
        html.append("<div class=box>\n");
        html.append("</div>\n");
        html.append("<div class=box>\n");
        html.append("<form action='..\\index.html'>\n <input type='submit' class='btn' value='Retour' /> \n </form>\n");
        html.append("</div>\n");
        html.append("</div>\n");
        html.append("</body>\n");
        html.append("</html>\n");
        
        agenthtml.print(html.toString());
        agenthtml.close();
    }
}
