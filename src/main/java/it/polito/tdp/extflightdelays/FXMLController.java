package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Adiacenti;
import it.polito.tdp.extflightdelays.model.AffluenzaStato;
import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Stato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<Stato> cmbBoxStati;

    @FXML
    private Button btnVisualizzaVelivoli;

    @FXML
    private TextField txtT;

    @FXML
    private TextField txtG;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	//FUNZIONA MA E' IMPRECISO
    	this.cmbBoxStati.getItems().clear();
    	this.cmbBoxStati.getItems().addAll(model.getState());
    	
    	this.model.creaGrafo();
    	
    	
    	
    	this.txtResult.appendText("Grafo Creato!");
    }

    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Stato partenza = this.cmbBoxStati.getValue();
    	
    	if(partenza == null) {
    		this.txtResult.appendText("ERRORE DEVI SELEZIONARE UNO STATO!");
    		return;
    	}
    	
    	Integer T;
    	Integer G;
    	try {
    		T= Integer.parseInt(this.txtT.getText());
    		G=Integer.parseInt(this.txtG.getText());
    	}catch(NumberFormatException e) {
    		this.txtResult.appendText("Inserire valori corretti ");
    		return;
    	}
    	
    	List<AffluenzaStato> affluenza = model.getAffluenza(T, G, partenza);
    	
    	for(AffluenzaStato af: affluenza) {
    		this.txtResult.appendText(af.getStato().getStato()+" ---> "+af.getNumTuristi());
    	}
    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Stato partenza = this.cmbBoxStati.getValue();
    	
    	if(partenza == null) {
    		this.txtResult.appendText("ERRORE DEVI SELEZIONARE UNO STATO!");
    		return;
    	}
    	
    	List<Adiacenti> result= model.getVelivoliByStato(partenza);
    	
    	this.txtResult.appendText("Gli stati collegati a qquello di partenza sono i seguenti con il numero di velivoli: \n");
    	for(Adiacenti a: result) {
    		this.txtResult.appendText(a.getStatodestinazione().getStato()+" ("+a.getNumeroVelivoli()+") \n");
    	}
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxStati != null : "fx:id=\"cmbBoxStati\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnVisualizzaVelivoli != null : "fx:id=\"btnVisualizzaVelivoli\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtT != null : "fx:id=\"txtT\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtG != null : "fx:id=\"txtG\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
