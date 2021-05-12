package mspr_java;


public class AccueilToHTML {
    public String render( String[] agents ) {
        StringBuilder html = new StringBuilder();
        html.append( "<!doctype html>\n" );
        html.append( "<html lang='fr'>\n" );

        html.append( "<head>\n" );
        html.append( "<meta charset='utf-8'>\n" );
        html.append( "<title>Accueil Agent</title>\n" );
        html.append( "</head>\n\n" );
        html.append( "<body>\n" );
        html.append( "<p><font size=\"7\" face=\"Light, Calibri\" color=\"#379EC1\"><h1>Liste des agents</h1>\n</font></p>" );
        // Make a list in HTML
        html.append( "<ul>\n" );
        // Loop the list of reports passed as argument.
        for ( String agent : agents ) {
            html.append( "<li>" + agent + "</li>\n" );
            // ajouter des href
        }
        html.append( "</ul>\n" );
        html.append( "</body>\n\n" );

        html.append( "</html>" );

        return html.toString();
    }

}

