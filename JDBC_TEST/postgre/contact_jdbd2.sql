CREATE TABLE contact(
   id SERIAL,
   nom VARCHAR(100)  NOT NULL,
   prenom VARCHAR(200)  NOT NULL,
   genre VARCHAR(10)  NOT NULL,
   date_de_naissance DATE,
   pseudo VARCHAR(100) ,
   adresse VARCHAR(250) ,
   tel_perso VARCHAR(50) ,
   tel_pro VARCHAR(50) ,
   mail VARCHAR(250) ,
   code_postale VARCHAR(250),
   git_link VARCHAR(250) ,
   PRIMARY KEY(id)
);