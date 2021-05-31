package mspr_java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AccueilToHTML {
    public String render( String[] agents ) {
        StringBuilder html = new StringBuilder(); //Initialisation de la classe StringBuilder qui contiendra le code html de la page index
        html.append( "<!doctype html>\n" );
        html.append( "<html lang='fr'>\n" );

        html.append( "<head>\n" );
        html.append("<link rel=\"icon\" href=\"favicon.ico\" />");
        html.append( "<meta charset='utf-8'>\n" );
        html.append( "<title>Accueil - Go Securi</title>\n" );
        html.append("<link href=\"agent.css\" rel=\"stylesheet\">\n");
        html.append( "</head>\n\n" );
        html.append( "<body>\n" );
        
        html.append("<div class=ligne>\n");
        html.append("<div class=box>\n");
        html.append( "<h1>Liste des agents</h1>\n" );
        html.append("</div>\n");
        
        html.append("<div class=box>\n");
        html.append("<img id='logo'  src='logo.jpg' />\n");
        html.append("</div>\n");
        
        html.append("</div>\n");
        
        html.append("<div class=ligne>\n");
        html.append("<div class=box>\n");
        html.append( "<ul>\n" );
        // Parcour de la liste des agents reçu en paramètre
        for ( String agent : agents ) {
            FicheAgent.agent(agent); //Appel de la classe FicheAgent en paramètre l'agent concerné. Cette classe génère la fiche de l'agent en question
            try {   //Tente la lecture du fichier txt de l'agent concerné.
                BufferedReader  fluxentree = new BufferedReader(new FileReader("agents_txt/"+agent + ".txt")); //Lit le fichier txt de l'agent concerné
                String ligne = null, nomagent= null, prenomagent= null; 
                int i=0; // Compteur de ligne
                while ((ligne = fluxentree.readLine()) != null) {
                    i++;
                    if(i==1){ // Le nom de l'agent se situe à la ligne 1
                        nomagent = ligne;
                    }
                    if(i==2) { //Le prénom de l'agent se situe à la ligne 2
                        prenomagent = ligne;
                    }
                }

            //System.out.println(nomagent);
            //System.out.println(prenomagent);
            html.append("<li> <a href='agents_html/"+agent+".html'>" + prenomagent + " " + nomagent + "</a></li>\n" ); //Créer le lien hypertexte dans le fichier html.
                                        //Lien de la fiche agent en html
            fluxentree.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("Erreur de l'ouverture du fichier");
            }
            catch (IOException e) {
                System.out.println("Erreur lecture "+agent+".txt");
            }
            
            // ajouter des href
        }
        html.append( "</ul>\n" );
        html.append("</div>\n");
        html.append("</div>\n");
        html.append( "</body>\n\n" );

        html.append( "</html>" );

        return html.toString(); //Renvoie la chaine de caractère contenant le code html du fichier index.
    }

}

