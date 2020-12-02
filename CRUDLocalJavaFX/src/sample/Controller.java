package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField txtNombre, txtUsuario, txtContrasena;
    @FXML
    private Button btnAgregar, btnEditar, btnEliminar;
    @FXML
    private TableView tblUsuarios;
    @FXML
    private TableColumn colNombre, colUsuario, colContrasena;
    @FXML
    private ObservableList<Usuario> listaUsuarios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaUsuarios = FXCollections.observableArrayList();
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colUsuario.setCellValueFactory(new PropertyValueFactory("usuario"));
        this.colContrasena.setCellValueFactory(new PropertyValueFactory("contrasena"));
    }


    public void agregarUsuario(ActionEvent actionEvent) {
        String nombre = this.txtNombre.getText();
        String usuario = this.txtUsuario.getText();
        String contrasena = this.txtContrasena.getText();

        Usuario user = new Usuario(nombre, usuario, contrasena);

        if(!this.listaUsuarios.contains(user)) {
            this.listaUsuarios.add(user);
            this.tblUsuarios.setItems(listaUsuarios);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El usuario ya existe");
            alert.showAndWait();
        }
    }

    public void editarUsuario(ActionEvent actionEvent) {
        Usuario user = (Usuario) this.tblUsuarios.getSelectionModel().getSelectedItem();

        if(user == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Selecciona un usuario");
            alert.showAndWait();
        } else {
            String nombre = this.txtNombre.getText();
            String usuario = this.txtUsuario.getText();
            String contrasena = this.txtContrasena.getText();

            Usuario user1 = new Usuario(nombre, usuario, contrasena);
            if(!this.listaUsuarios.contains(user1)) {
                user.setNombre(user1.getNombre());
                user.setUsuario(user1.getUsuario());
                user.setContrasena(user1.getContrasena());
                this.tblUsuarios.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("El usuario ya existe");
                alert.showAndWait();
            }
        }

    }

    public void eliminarUsuario(ActionEvent actionEvent) {
        Usuario user = (Usuario) this.tblUsuarios.getSelectionModel().getSelectedItem();

        if(user == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Selecciona un usuario");
            alert.showAndWait();
        } else {
            this.listaUsuarios.remove(user);
            this.tblUsuarios.refresh();
        }
    }

    public void seleccionarUsuario(MouseEvent mouseEvent) {
        Usuario user = (Usuario) this.tblUsuarios.getSelectionModel().getSelectedItem();

        if(user != null) {
            this.txtNombre.setText(user.getNombre());
            this.txtUsuario.setText(user.getUsuario());
            this.txtContrasena.setText(user.getContrasena());
        }
    }
}
