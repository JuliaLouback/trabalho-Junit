package resources.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.dao.DaoFuncionario;
import main.entity.Funcionario;
import main.util.ShowAlert;

public class ControllerViewFuncionario implements Initializable{

    @FXML
    private TableView<Funcionario> tabela;

    @FXML
    private TableColumn<Funcionario, String> Nome;

    @FXML
    private TableColumn<Funcionario, String> Cpf;

    @FXML
    private TableColumn<Funcionario, String> Cargo;
    
    @FXML
    private Button btnAdd;
    
    @FXML
    private Label labelChange;
  

    @FXML
    void AdicionarFuncionario(ActionEvent event) {
    	 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	     ControllerViewCadastroFuncionario irTela = new ControllerViewCadastroFuncionario();
	     irTela.start(stage);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Nome.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Nome"));
		Cpf.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Cpf"));
		Cargo.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Cargo"));
       
		Listar();
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    
	    addBotaoEditar();
	    addBotaoExcluir();
	}
	
	
	private void Listar(){
		  ArrayList<Funcionario> listas = new DaoFuncionario().listarFuncionario();

		  ObservableList<Funcionario> lista = FXCollections.observableArrayList(listas);
		  
		  labelChange.setText(String.valueOf(lista.size()));
		  tabela.setItems(lista);
	}
	
	private void addBotaoEditar() {
        TableColumn<Funcionario, Void> colBtn = new TableColumn("Editar");

        Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>> cellFactory = new Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>>() {
            @Override
            public TableCell<Funcionario, Void> call(final TableColumn<Funcionario, Void> param) {
                final TableCell<Funcionario, Void> cell = new TableCell<Funcionario, Void>() {

                    private final Button btn = new Button("Editar");{
                        btn.setOnAction((ActionEvent event) -> {
                                            
                            try {
                            	
                                Funcionario funcionario = getTableView().getItems().get(getIndex());

                            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/CadastroFuncionario.fxml"));
 	                            Parent root = loader.load();
 	
 	                            ControllerViewCadastroFuncionario controllerView = loader.getController();
 	                            controllerView.setLabelText(funcionario);
 	                    
 	                            Stage stage = new Stage();
 	                            stage.setScene(new Scene(root));
 	                            stage.setTitle("Editar Funcionário - Uni RH");
 	                			stage.setResizable(false);
 	                			stage.centerOnScreen();
 	                			stage.getIcons().add(new Image("/resources/img/worker.png"));
 	                            stage.show();
 	                            
 	                            
 	                            Stage stages = (Stage) btn.getScene().getWindow();
 	                            stages.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            
                        });
                       
                        btn.setPrefWidth(280);
                        btn.setStyle("-fx-background-color:#28A745;-fx-text-fill:#ffffff;"); 
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tabela.getColumns().add(colBtn);

    }
	
	private void addBotaoExcluir() {
        TableColumn<Funcionario, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>> cellFactory = new Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>>() {
            @Override
            public TableCell<Funcionario, Void> call(final TableColumn<Funcionario, Void> param) {
                final TableCell<Funcionario, Void> cell = new TableCell<Funcionario, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	Funcionario funcionario = getTableView().getItems().get(getIndex());

                        	if(new ShowAlert().confirmationAlert("Excluir", "Tem certeza que deseja excluir?", "Confirmação de Exclusão - "+funcionario.getNome())){
                        		new DaoFuncionario().excluirFuncionario(funcionario.getCpf());
                        		
                        	    Listar();
                        	    
                        	    new ShowAlert().sucessoAlert("Funcionário excluído com sucesso!");
                        	}
                           
                        });
                       
                        btn.setPrefWidth(280);
                        btn.setStyle("-fx-background-color:#e04b59;-fx-text-fill:#ffffff;"); 
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tabela.getColumns().add(colBtn);

    }

   @FXML
   void Fechar(ActionEvent event) {
	    Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    new ShowAlert().mensagemAlert();
	    stage.close();
    }
	  
	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/ListaFuncionario.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Painel - Uni RH");
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/worker.png"));
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

}
