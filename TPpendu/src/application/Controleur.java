package application;

import java.util.HashSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controleur {
	
	@FXML
    private TextField saisieLettre;
    @FXML
    private TextField affichageMot;
    @FXML
    private TextField affichageTentatives;
    @FXML
    private ImageView imageafficher;
    @FXML
    private TextField lettre;
    
    private String lettreUtiliser;
    private String devant = "C:\\Users\\JOEL\\eclipse-workspace\\TPpendu\\src\\application\\images\\pendu_";
    private String deriere = ".jpg";
    private HashSet<Lettre> mot;
    private StringBuffer chaineTrouvee = new StringBuffer();
    private int tentativesRestantes;
    private String[] listeMot = {"avion","bateau","voiture","robot","informatique"};
    private String mot1;
     
    @FXML
    private void initialize()
	{
    	//chaineTrouvee = new StringBuffer();
    	lettreUtiliser = "";
    	mot  = new HashSet<Lettre>();
		int p = (int)(Math.random()*5);
		mot1 = listeMot[p];
		if (this.chaineTrouvee != null) {
            this.chaineTrouvee = new StringBuffer();
        }
		for (int i = 0; i < mot1.length(); i++) {
			Lettre l = new Lettre((mot1.charAt(i)),mot1);
			mot.add(l);
			chaineTrouvee.append("_");
		}
		
		this.tentativesRestantes = 6; 
		String img = this.devant+tentativesRestantes+this.deriere;
        Image uneImage= new Image(img);
        imageafficher.setImage(uneImage);
        affichageMot.setText(chaineTrouvee.toString());
        
        saisieLettre.setText("");
        lettre.setText("");
        affichageTentatives.setText(""+this.tentativesRestantes);
	}
    
    public void actionValiderLettre(ActionEvent ae) 
    {
    	String ls = saisieLettre.getText();
    	char ls2 = ls.charAt(0);
    	
    	if (ls2 == ' ') 
    	{
            throw new IllegalArgumentException("La saisie ne peut pas être vide");
        }
        Lettre lettreSaisie = new Lettre(ls2,mot1);
        if (this.mot.contains(lettreSaisie))
        {
            for (Lettre l : this.mot)
            {
                for (int pos : l.getPositions())
                {
                    if (l.getLettre() == ls2) 
                    {
                        this.chaineTrouvee.setCharAt(pos, ls2);
                    }
                }
            }
        }
        else 
        {
        	this.lettreUtiliser += (ls2+"-");
        	lettre.setText(lettreUtiliser);
            System.out.println("Lettre déjà tirée: " + ls2);
            this.tentativesRestantes--;
        }
    	if(this.tentativesRestantes == 0)
    	{
    		this.tentativesRestantes = 7;
    		String img = this.devant+tentativesRestantes+this.deriere;
            Image uneImage= new Image(img);
            imageafficher.setImage(uneImage);
            affichageTentatives.setText(""+this.tentativesRestantes);
            affichageMot.setText(chaineTrouvee.toString());
    		Alert dialog = new Alert(AlertType.CONFIRMATION); dialog.setTitle("FIN DE PARTIE");
    		dialog.setHeaderText(null);
    		dialog.setContentText("Vous avez perdu !!");
    		dialog.showAndWait(); 
    	}
    	String img = this.devant+tentativesRestantes+this.deriere;
        Image uneImage= new Image(img);
        imageafficher.setImage(uneImage);
        affichageTentatives.setText(""+this.tentativesRestantes);
        affichageMot.setText(chaineTrouvee.toString());
        
        if(chaineTrouvee.toString().equals(mot1))
        {
        	Alert dialog = new Alert(AlertType.CONFIRMATION); dialog.setTitle("FIN DE PARTIE");
    		dialog.setHeaderText(null);
    		dialog.setContentText("Vous avez Gagné !!");
    		dialog.showAndWait(); 
        }
    }
}