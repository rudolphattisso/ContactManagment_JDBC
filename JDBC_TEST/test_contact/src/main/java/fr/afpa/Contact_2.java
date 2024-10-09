package fr.afpa;

import java.time.LocalDate;

public class Contact_2 {
    private int id;
    private String nom ;
    private String prenom;
    private String genre;
    private LocalDate date_de_naissance;
    private String  pseudo;
    private String adresses;
    private String tel_perso;
    private String tel_pro;
    private String mail;
    private String code_postale;
    private String git_link;


    //constructeur
    public Contact_2(int id, String nom, String prenom, String genre, LocalDate date_de_naissance, String pseudo,
            String adresses, String tel_perso, String tel_pro, String mail, String code_postale, String git_link) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.date_de_naissance = date_de_naissance;
        this.pseudo = pseudo;
        this.adresses = adresses;
        this.tel_perso = tel_perso;
        this.tel_pro = tel_pro;
        this.mail = mail;
        this.code_postale = code_postale;
        this.git_link = git_link;
    }
    
    //toString 
    @Override
    public String toString() {
        return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", date_de_naissance="
                + date_de_naissance + ", pseudo=" + pseudo + ", adresses=" + adresses + ", tel_perso=" + tel_perso
                + ", tel_pro=" + tel_pro + ", mail=" + mail + ", code_postale=" + code_postale + ", git_link="
                + git_link + "]";
    }


    //getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getGenre() {
        return this.genre;
    }

    public LocalDate getDate_de_naissance() {
        return this.date_de_naissance;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public String getAdresses() {
        return this.adresses;
    }

    public String getTel_perso() {
        return this.tel_perso;
    }

    public String getTel_pro() {
        return this.tel_pro;
    }

    public String getMail() {
        return this.mail;
    }

    public String getCode_postale() {
        return this.code_postale;
    }

    public String getGit_link() {
        return this.git_link;
    }


     // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDate_de_naissance(LocalDate date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setAdresses(String adresses) {
        this.adresses = adresses;
    }

    public void setTel_perso(String tel_perso) {
        this.tel_perso = tel_perso;
    }

    public void setTel_pro(String tel_pro) {
        this.tel_pro = tel_pro;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCode_postale(String code_postale) {
        this.code_postale = code_postale;
    }

    public void setGit_link(String git_link) {
        this.git_link = git_link;
    }
    
   
}

