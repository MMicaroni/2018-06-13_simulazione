package it.polito.tdp.flightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.Model;
import it.polito.tdp.flightdelays.model.Passenger;
import it.polito.tdp.flightdelays.model.Route;
import it.polito.tdp.flightdelays.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FlightDelaysController {
	Model model ;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ComboBox<Airline> cmbBoxLineaAerea;

    @FXML
    private Button caricaVoliBtn;

    @FXML
    private TextField numeroPasseggeriTxtInput;

    @FXML
    private TextField numeroVoliTxtInput;

    @FXML
    void doCaricaVoli(ActionEvent event) {
    	txtResult.clear();	
    	Airline airline = cmbBoxLineaAerea.getValue() ;
    		if(airline==null) {
    			txtResult.appendText("Scegliere una linea aerea ");
    			return ;
    		}
    		model.createGraph(airline); 
    		int i = 1 ;
    		for(Route r : model.getTenWorstRoutes()) {
    			
    			txtResult.appendText(i+") "+r.getOrigin()+" "+r.getDestination()+" "+r.getWeight()+"\n");
    			i++ ;
    		}
    
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    int K = Integer.parseInt(numeroPasseggeriTxtInput.getText()) ;
    int V = Integer.parseInt(numeroVoliTxtInput.getText()) ;
    
	Airline airline = cmbBoxLineaAerea.getValue() ;
	if(airline==null) {
		txtResult.appendText("Scegliere una linea aerea ");
		return ;
	}
    	Simulatore simula = new Simulatore(model, K, V, airline) ;
    	simula.init();
    	simula.run();
    	
    	for(Passenger p : simula.getDelay().values()) {
    		
    		txtResult.appendText(p+"\n");
    		
    	}
    	
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert cmbBoxLineaAerea != null : "fx:id=\"cmbBoxLineaAerea\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert caricaVoliBtn != null : "fx:id=\"caricaVoliBtn\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert numeroPasseggeriTxtInput != null : "fx:id=\"numeroPasseggeriTxtInput\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert numeroVoliTxtInput != null : "fx:id=\"numeroVoliTxtInput\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        txtResult.setStyle("-fx-font-family: monospace");
    }
    
	public void setModel(Model model) {
		this.model = model ;
		cmbBoxLineaAerea.getItems().addAll(model.getAirlines()) ;
	}
}
