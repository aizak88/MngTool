package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AppendUserView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPwd1;

    @FXML
    private PasswordField txtPwd2;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClear;


    @FXML
    private CheckBox chbIsActive;

    @FXML
    void initialize() {

        btnClear.setOnAction(event -> {
            if (chbIsActive.isSelected()){
                System.out.println("OK");
            }else{
                System.out.println("ELSE");
            };
            clearAll();
        });

        btnSave.setOnAction(event -> appendUser());

    }

    private void clearAll(){
        txtLogin.clear();
        txtName.clear();
        txtPwd1.clear();
        txtPwd2.clear();
    }

    private boolean comparePwd(String pwd1, String pwd2){
        return pwd1.equals(pwd2);
    }


    private void appendUser(){
        String name = txtName.getText();
        String login = txtLogin.getText();
        String pwd1 = txtPwd1.getText();
        String pwd2 = txtPwd2.getText();

        if (!pwd1.equals(pwd2)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Неверные пароли");
            alert.show();
        }

        DbHandler dbHandler = new DbHandler();
        dbHandler.appendUser(name, login, pwd1);

        clearAll();

    }
}
