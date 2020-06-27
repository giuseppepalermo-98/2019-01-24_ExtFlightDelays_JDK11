package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.AffluenzaStato;
import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Vicino;
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
    private ComboBox<String> cmbBoxStati;

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
    	
    	this.model.creaGrafo();
    	this.txtResult.appendText("Grafo creato!");
    	
    	this.cmbBoxStati.getItems().addAll(model.getStati());
    }

    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Integer G = Integer.parseInt(this.txtG.getText());
    	Integer T = Integer.parseInt(this.txtT.getText());
    	String partenza = this.cmbBoxStati.getValue();
    	
    	List<AffluenzaStato> result = model.simula(G, T, partenza);
    	
    	this.txtResult.appendText("I turisti nei diversi STATI: \n \n");
    	
    	for(AffluenzaStato as: result)
    		this.txtResult.appendText(as.getStato()+" ("+as.getNumTuristi()+")\n");
    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	this.txtResult.clear();
    
    	String selezionato = this.cmbBoxStati.getValue();
    	
    	if(selezionato == null ) {
    		this.txtResult.appendText("ERRORE SELEZIONA UNO STATO");
    		return;
    	}
    	
    	List<Vicino> result = model.getVicini(selezionato);
    	
    	this.txtResult.appendText("Stati connessi: \n");
    	for(Vicino v: result)
    		this.txtResult.appendText(v.getStato()+" ("+v.getPeso()+")\n");
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
