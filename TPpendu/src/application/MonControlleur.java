package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class MonControlleur {
    @FXML
    private TextField saisieLettre;
    @FXML
    private TextField affichageMot;
    @FXML
    private Label affichageTentatives;
    @FXML
    private ImageView image;
    
    private String devant = "C:\\Users\\JOEL\\eclipse-workspace\\TPpendu\\src\\application\\images\\pendu_";
    private String deriere = ".jpg";
    private HashSet<Lettre> mot;
    private StringBuffer chaineTrouvee;
    private int tentativesRestantes;
    private ArrayList<String> mots;

    /*public MonControleur() {
        this.mot = new HashSet<>();
        this.chaineTrouvee = new StringBuffer();
        this.tentativesRestantes = 6;
        this.mots = new ArrayList<>();
    }*/

    public void initialize() {
    	this.mot = new HashSet<>();
        this.chaineTrouvee = new StringBuffer();
        this.tentativesRestantes = 6;
        this.mots = new ArrayList<>();
        mots.add("pollution");
        // Ajoutez d'autres mots ici
        String motADeviner = mots.get((int) (Math.random() * mots.size()));

        for (int i = 0; i < motADeviner.length(); i++) {
            this.mot.add(new Lettre(motADeviner.charAt(i), motADeviner));
            this.chaineTrouvee.append("_");
        }
        affichageMot.setText(chaineTrouvee.toString());
        affichageTentatives.setText("Tentatives restantes : " + tentativesRestantes);
        String img = this.devant+tentativesRestantes+this.deriere;
        Image uneImage= new Image(img);
        image.setImage(uneImage);
    }

    @FXML
    public void actionValiderLettre() {
        String lettreStr = saisieLettre.getText();
        if (lettreStr.isEmpty()) {
            // Générer une exception si la case de saisie est vide
            affichageTentatives.setText("Veuillez entrer une lettre.");
            return;
        }

        char lettre = lettreStr.charAt(0);
        Lettre lettreSaisie = new Lettre(lettre, "");
        if (this.mot.contains(lettreSaisie)) {
            for (Lettre l : this.mot) {
                for (int pos : l.getPositions()) {
                    if (l.getLettre() == lettre) {
                        this.chaineTrouvee.setCharAt(pos, lettre);
                    }
                }
            }
            affichageMot.setText(chaineTrouvee.toString());
        } else {
            // Afficher le caractère dans un label indiquant qu'il est déjà tiré
            affichageTentatives.setText("Lettre déjà tirée: " + lettre);
            // Décrémenter le nombre de tentatives restantes
            this.tentativesRestantes--;
            affichageTentatives.setText("Tentatives restantes : " + tentativesRestantes);
        }
    }
}
/*-------------------------------------------------------------------------------------------------------------------------*/
/*package application;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MonControlleur {
	
	@FXML
	TextField lettreEntrer;
	@FXML
	TextField chercher;
	@FXML
	ImageView image;
	protected HashSet<Lettre> mot;
	
	private String devant = "C:\\Users\\JOEL\\eclipse-workspace\\TPpendu\\src\\application\\images\\pendu_";
    private String deriere = ".jpg";
	protected StringBuffer chaineTrouvee;
	
	private int tentativesRestantes;
	
	public void initialize()
	{
		String[] listeMot = {"avion","bateau","voiture","robot","informatique"};
		int p = (int)(Math.random()*5);
		String mot1 = listeMot[p];
		for (int i = 0; i < mot1.length(); i++) {
			Lettre l = new Lettre((mot1.charAt(i)),mot1);
			mot.add(l);
			chaineTrouvee.append("_");
		}
		this.tentativesRestantes = 6; 
		String img = this.devant+tentativesRestantes+this.deriere;
        Image uneImage= new Image(img);
        image.setImage(uneImage);
	}
	
	public void actionValiderLettre(char lettre) {
        if (lettre == ' ') {
            // Générer une exception si la case de saisie est vide
            throw new IllegalArgumentException("La saisie ne peut pas être vide");
        }

        Lettre lettreSaisie = new Lettre(lettre, "");
        if (this.mot.contains(lettreSaisie)) {
            for (Lettre l : this.mot) {
                for (int pos : l.getPositions()) {
                    if (l.getLettre() == lettre) {
                        this.chaineTrouvee.setCharAt(pos, lettre);
                    }
                }
            }
        } else {
            System.out.println("Lettre déjà tirée: " + lettre);
            this.tentativesRestantes--;
        }
    }
}*/
