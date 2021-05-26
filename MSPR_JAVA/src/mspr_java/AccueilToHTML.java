package mspr_java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AccueilToHTML {
    public String render( String[] agents ) {
        StringBuilder html = new StringBuilder();
        html.append( "<!doctype html>\n" );
        html.append( "<html lang='fr'>\n" );

        html.append( "<head>\n" );
        html.append("<link rel=\"icon\" href=\"favicon.ico\" />");
        html.append( "<meta charset='utf-8'>\n" );
        html.append( "<title>Accueil Agent</title>\n" );
        html.append( "</head>\n\n" );
        html.append( "<body>\n" );
        html.append( "<p><font size=\"7\" face=\"Light, Calibri\" color=\"#379EC1\"><h1>Liste des agents</h1>\n</font></p>" );
        // Make a list in HTML
        html.append( "<ul>\n" );
        // Loop the list of reports passed as argument.
        for ( String agent : agents ) {
            FicheAgent.agent(agent);
            try {
                BufferedReader  fluxentree = new BufferedReader(new FileReader(agent + ".txt"));
                // BufferedReader  fluxentree = new BufferedReader(new FileReader(args[0]));
                String ligne = null;
                int i=0;
                String nomagent= null;
                String prenomagent= null;
                while ((ligne = fluxentree.readLine()) != null) {
                    i++;
                    if(i==1){
                        nomagent = ligne;
                    }
                    if(i==2) {
                        prenomagent = ligne;
                    }
                }

            System.out.println(nomagent);
            System.out.println(prenomagent);
            html.append("<li> <a href='"+agent+".html'>" + prenomagent + " " +nomagent + "</a></li>\n" );
            fluxentree.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("Erreur de l'ouverture du fichier");
            }
            catch (IOException e) {
                System.out.println("Erreur lecture staff.txt");
            }
            
            // ajouter des href
        }
        html.append( "</ul>\n" );
        html.append( "</body>\n\n" );

        html.append( "</html>" );

        return html.toString();
    }

}

