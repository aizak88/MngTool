package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancel;

    @FXML
    private ComboBox<User> cbUser;

    @FXML
    void initialize() {

        btnOK.setOnAction(event -> authentification());
        DbHandler dbHandler = new DbHandler();

        cbUser.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User object) {
                return object.getName();
            }

            @Override
            public User fromString(String string) {
                return cbUser.getItems().stream().filter(ap -> ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

        ObservableList<User> list = FXCollections.observableList(dbHandler.getUsers());
        cbUser.setItems(list);

        /*cbUser.valueProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if (txtPassword.getText().equals(newValue.getPwd())){
                    System.out.println("OK");

                }else{
                    System.out.println("ERROR");
                }
            }
        });*/
    }

    private void authentification(){
        String pwd = txtPassword.getText();
        User user = cbUser.getValue();

        if (pwd.equals(user.getPwd())){
            Parent root = null;
            try {
                Stage stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
                stage.setScene(new Scene(root));
                stage.show();

                btnOK.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Неверные пароли");
            alert.show();
        }

    }
}
