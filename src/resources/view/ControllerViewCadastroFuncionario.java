package resources.view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.dao.DaoFuncionario;
import main.entity.Funcionario;
import main.util.MaskFieldUtil;
import main.util.ShowAlert;
import main.util.ValidacaoCPF;

public class ControllerViewCadastroFuncionario implements Initializable{

    @FXML
    private Label labelChange;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Email;

    @FXML
    private TextField Cpf;

    @FXML
    private DatePicker Data_nascimento;

    @FXML
    private ComboBox<String> Cargo;

    @FXML
    private TextField Salario;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnVoltar;

    private Funcionario funcionarios = new Funcionario();
    
    @FXML
    void AdicionarFuncionario(ActionEvent event) {
    	
    	if(funcionarios.getId() != null && !funcionarios.getNome().isEmpty()) {
    		Editar();
    	}
    	else {
    		Adicionar();
    	}
    }

    @FXML
    void Voltar(ActionEvent event) {
    	 Stage stage = (Stage) btnVoltar.getScene().getWindow(); 
	     ControllerViewFuncionario irTela = new ControllerViewFuncionario();
	     irTela.start(stage);
	}

    @FXML
    void Fechar(ActionEvent event) {
 	    Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    new ShowAlert().mensagemAlert();
 	    stage.close();
     }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Cargo.getItems().setAll("Gerente", "Vendedor", "RH"); 
		MaskFieldUtil.cpfField(this.Cpf);
		Salario.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    Salario.setText(oldValue);
                }
            }
        });

	}
	
	public void Adicionar() {
		if(validacaoCampo()){
			Funcionario funcionario = new Funcionario();
			
			funcionario.setNome(Nome.getText());
			funcionario.setCpf(Cpf.getText());
			funcionario.setEmail(Email.getText());
			funcionario.setCargo(Cargo.getValue().toString());
			funcionario.setSalario(Float.parseFloat(Salario.getText()));
			funcionario.setData_nascimento(Data_nascimento.getValue());
			
			if(new ValidacaoCPF().validaCpf(funcionario.getCpf())) {
				
				if(!new DaoFuncionario().verificarFuncionario(funcionario.getCpf())) {
					if(new DaoFuncionario().inserirFuncionario(funcionario)) {
					
						new ShowAlert().sucessoAlert("Funcionário adicionado com sucesso!");
						
						Stage stage = (Stage) btnVoltar.getScene().getWindow(); 
					    ControllerViewFuncionario irTela = new ControllerViewFuncionario();
					    irTela.start(stage);
					} else {
						new ShowAlert().erroAlert("CPF já cadastrado!");

					}
				}
			} else {
				new ShowAlert().erroAlert("CPF Inválido!");
			}
		} 
	}
	
	public void Editar() {
		
		if(validacaoCampo()) {
			Funcionario funcionario = new Funcionario();
			
			funcionario.setNome(Nome.getText());
			funcionario.setCpf(Cpf.getText());
			funcionario.setEmail(Email.getText());
			funcionario.setCargo(Cargo.getValue().toString());
			funcionario.setSalario(Float.parseFloat(Salario.getText()));
			funcionario.setData_nascimento(Data_nascimento.getValue());
			funcionario.setId(funcionarios.getId());
			
			if(new DaoFuncionario().alterarFuncionario(funcionario)) {
				new ShowAlert().sucessoAlert("Funcionário editado com sucesso!");
	
				Stage stage = (Stage) btnVoltar.getScene().getWindow(); 
			    ControllerViewFuncionario irTela = new ControllerViewFuncionario();
			    irTela.start(stage);
			} else {
				new ShowAlert().erroAlert("Erro ao editar funcionário: CPF já esta cadastrado!");
			}
		}
	}
	
	public void setLabelText(Funcionario funcionario){
		 this.funcionarios = funcionario;
		 this.labelChange.setText("Editar"); 
		 this.btnAdd.setText("Editar");;
	     this.Cpf.setText(funcionario.getCpf());
	     this.Nome.setText(funcionario.getNome());
	     this.Email.setText(funcionario.getEmail());
	     this.Cargo.setValue(funcionario.getCargo());
	     this.Salario.setText(String.valueOf(funcionario.getSalario()));
	     this.Data_nascimento.setValue(funcionario.getData_nascimento());
	     this.Cpf.setEditable(false);
	}

	public boolean validacaoCampo() {
		if(Cpf.getText().isEmpty() | Nome.getText().isEmpty() | Email.getText().isEmpty() | Cargo.getSelectionModel().isEmpty() |
				Salario.getText().isEmpty() | Data_nascimento.getValue() == null) {
			
			new ShowAlert().validacaoAlert();
			
			return false;
		}
		
		return true;
	}
	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/CadastroFuncionario.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adicionar Funcionário - Uni RH");
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/worker.png"));
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

}
