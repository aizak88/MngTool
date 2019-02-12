package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AppendTeacherView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtEmail;

    @FXML
    private CheckBox isActive;

    @FXML
    void initialize() {
        btnSave.setOnAction(event -> createTeacher());
    }

    private void createTeacher(){
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();

        int is_active = 0;

        if (isActive.isSelected()){
            is_active = 1;
        }

        DbHandler dbHandler = new DbHandler();
        dbHandler.appendTeacher(name, phone, email, is_active);

        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        isActive.setSelected(true);
    }
}
