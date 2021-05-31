#!/bin/bash

#supprimer l'ancien fichier .htpasswd
rm ./.htpasswd
#créer un nouveau fichier .htpasswd
touch ./.htpasswd

#pour chaque fichier présent dans le dossier agents_txt
for fich in `find ./MSPR_JAVA/agents_txt`
do
        if [ -f $fich ]
        then
		#nom d'utilisateur
		user=$(echo $fich | cut -d / -f 4 | cut -d . -f 1) 
                #mot de passe
		password=$(sed -n 4p $fich)
		#Ajout du nom d'utilisateur et du mot de pass crypté au fichier .htpasswd
		printf "$user:$(openssl passwd -crypt $password)\n" >> ./.htpasswd
        fi
done



