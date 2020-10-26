package main.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ShowAlert {

	public void validacaoAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");
		alert.setHeaderText(null);
		alert.setContentText("Preencha todos os campos!");
		alert.showAndWait();
	}
	
	public boolean confirmationAlert(String title, String mensagem, String headerText ) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(mensagem);
		
		Optional<ButtonType> result = alert.showAndWait();
		ButtonType button = result.orElse(ButtonType.CANCEL);

		if (button == ButtonType.OK) {
		    return true;
		} 
		
		return false;
	}
	
	public void sucessoAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sucesso");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public void erroAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public void mensagemAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("O sistema será encerrado!");
		alert.showAndWait();
	}
	
}
