#!/bin/bash


for fich in `find ./MSPR_JAVA/agents_txt`
do
        if [ -f $fich ]
        then
		user=$(echo $fich | cut -d / -f 4 | cut -d . -f 1) 
                password=$(sed -n 4p $fich)
		printf "$user:$(openssl passwd -crypt $password)\n" >> .htpasswd
        fi
done
