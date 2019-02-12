package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainForm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

    @FXML
    void initialize() {

        ObservableList<String> list = FXCollections.observableArrayList("Студенты", "Преподаватели","Курсы", "Пользователи");
        listView.setItems(list);

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showForm(listView.getSelectionModel().getSelectedIndex());
            }
        });

    }

    private void showForm(int index){
        String formName = new String();
        switch (index){
            case 0:
                formName = "/sample/appendStudentView.fxml";
                break;
            case 1:
                formName = "/sample/appendTeacherView.fxml";
                break;
            case 2:
                formName = "/sample/appendCourseView.fxml";
                break;
            case 3:
                formName = "/sample/appendUserView.fxml";
                break;
        }
        Parent root;
        Stage stage = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource(formName));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
