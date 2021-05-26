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
        StringBuilder html =null;
        PrintWriter agenthtml = null;
        List<String> liste_matos = new ArrayList<>();
        String nom = null;
        String prenom = null;
        List<String>matosAgent = new ArrayList();
        
        try {
            agenthtml = new PrintWriter(new FileOutputStream(agent + ".html"));
        }
        catch (FileNotFoundException e) {
            System.out.println("Erreur d'ouverture de "+agent+".html");
            System.exit(0);
        }
        html = new StringBuilder();
       
        try {
            BufferedReader  liste = new BufferedReader(new FileReader("liste.txt"));
            // BufferedReader  fluxentree = new BufferedReader(new FileReader(args[0]));
            String ligne = null;
            while ((ligne = liste.readLine()) != null) {
                
                liste_matos.add(ligne);
            }

            liste.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("Erreur de l'ouverture du fichier");
        }
        catch (IOException e) {
            System.out.println("Erreur lecture"+agent+".txt");
        }
        
        try {
            BufferedReader  fluxentree = new BufferedReader(new FileReader(agent + ".txt"));
            // BufferedReader  fluxentree = new BufferedReader(new FileReader(args[0]));
            String ligne = null;
            int i = 0;
            
            
            while ((ligne = fluxentree.readLine()) != null) {
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
        html.append("<!doctype html>\n");
        html.append("<html lang='fr'>\n");

        html.append("<head>\n");
        html.append("<meta charset='utf-8'>\n");
        html.append("<title>"+nom+" "+prenom+"</title>\n");
        html.append("<link rel=\"icon\" href=\"favicon.ico\" />");
        html.append("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\n");
        html.append("<link href=\"https://fonts.googleapis.com/css2?family=Roboto&display=swap\" rel=\"stylesheet\"> \n");
        html.append("</head>\n\n");
        html.append("<link href=\"agent.css\" rel=\"stylesheet\">\n");
        
        html.append("<body>\n");
        html.append("<div class=ligne>\n");
        html.append("<div class=box>\n");
        html.append("<h1>"+nom + " " + prenom +"</h1> <br> <a class='menu' href='index.html'>Retour à l'accueil</a>\n");
        html.append("</div>\n");
        html.append("<div class=box>\n");
        html.append("<img id='logo'  src='logo.jpg' />\n");
        html.append("</div>\n");
        html.append("<div class=box>\n");
        html.append("<img src=\""+agent+".jpg\" />\n");
        html.append("</div>\n");
        html.append("</div>\n");
        html.append("<div class=ligne>\n");
        html.append("<div class=box>\n");
        html.append("</div>\n");
        html.append("<div class=box>\n");
        for(String i : liste_matos){
            if(matosAgent.contains(i.substring(0, i.indexOf("\t")))){
                html.append("<input  type=\"checkbox\" id=\"scales\" name=\"scales\" checked><label for=\"scales\">"+i.substring(i.indexOf("\t"), i.length())+"</label><br />\n");
            }else{
                html.append("<input  type=\"checkbox\" id=\"scales\" name=\"scales\"><label for=\"scales\">"+i.substring(i.indexOf("\t"), i.length())+"</label><br />\n");
            }
        }
        html.append("</div>\n");
        html.append("<div class=box>\n");
        html.append("</div>\n");
        html.append("</div>\n");
        html.append("</body>\n");
        html.append("</html>\n");
        agenthtml.print(html.toString());
        agenthtml.close();
        
    }
}
