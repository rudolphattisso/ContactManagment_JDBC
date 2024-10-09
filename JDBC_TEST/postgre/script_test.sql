

--1.Récupération de toutes les informations de tous les contacts
select * from contact c ;

--2.Récupération d’un contact par identifiant
select * from  contact 
where id = 125; 

--3.Suppression d’un contact en fonction de son identifiant
delete  from  contact 
where id = 125; 

--4.Suppression d’un contact en fonction de son nom et de son prénom
delete from contact  
where nom like 'sdf^mkùs' and prenom like 'jfblordffhp' ;

--5.Ajout d’un nouveau contact
insert into contact (id, nom,prenom ,genre ,date_de_naissance , pseudo, adresse, tel_perso, tel_pro, mail, code_postale, git_link)
VALUES(123, 'zbape', 'blorp','homme' , '2012-05-25', 'robou', '1 allée de la bulle' ,'0605589463','0121010101','ddfgfd@yaho.fr','41000','rzjfze.fr' );

insert into contact (id, nom,prenom ,genre ,date_de_naissance , pseudo, adresse, tel_perso, tel_pro, mail, code_postale, git_link)
VALUES(124, 'iosdv;', 'dfgblghorp','femme' , '2012-05-22', 'robou', '1 allée de la bulle' ,'0605589463','02121010101','ddffd@yaho.fr','41000','rzd4fze.fr' );

insert into contact (id, nom,prenom ,genre ,date_de_naissance , pseudo, adresse, tel_perso, tel_pro, mail, code_postale, git_link)
VALUES(125, 'sdf^mkùs', 'jfblordffhp','homme' , '2012-05-23', 'robou', '1 allée de la bulle' ,'060552189463','0311010101','dfsdfd@yaho.fr','41000','rzd5fze.fr' );

insert into contact (id, nom,prenom ,genre ,date_de_naissance , pseudo, adresse, tel_perso, tel_pro, mail, code_postale, git_link)
VALUES(126, 'oibaze', 'bhdflorp','femme' , '2012-05-24', 'robou', '1 allée de la bulle' ,'06055894623','01010132101','dfcvd@yaho.fr','41000','rzdf6ze.fr' );

--6. Modification de toutes les informations d’un contact
UPDATE contact 
SET nom = 'zibo'
WHERE id = 123;
