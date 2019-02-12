package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class AppendCourseView {


    private int courseId = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtCoursePrice;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnRefresh;

    @FXML
    private TableView<Course> tbCourses;

    @FXML
    private TableColumn<Course, String> clmnID;

    @FXML
    private TableColumn<Course, String> clmnName;

    @FXML
    private TableColumn<Course, Integer> clmnPrice;

    @FXML
    private TableColumn<Course, Integer> clmnActive;

    @FXML
    private CheckBox checkActive;


    @FXML
    private MenuItem barNew;

    @FXML
    private MenuItem barEdit;

    @FXML
    void onEditClicked(ActionEvent event) {


        Course course = tbCourses.getSelectionModel().getSelectedItem();
        courseId = course.getId();
        txtCourseName.setText(course.getName());
        txtCoursePrice.setText(String.valueOf(course.getPrice()));
        if (course.getIsActive() == 1){
            checkActive.setSelected(true);
        }else{
            checkActive.setSelected(false);
        }

    }



    @FXML
    void onNewClicked(ActionEvent event) {

        courseId = 0;


    }


    @FXML
    void initialize() {




        clmnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmnActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));

        refresh();

        btnOk.setOnAction(event -> {
            appendCourse();
        });

        btnRefresh.setOnAction(event -> {
            DbHandler db = new DbHandler();
            db.ShowCourses();
        });
    }

    private void appendCourse(){
        String name = txtCourseName.getText();
        int price = Integer.parseInt(txtCoursePrice.getText());
        int is_active = 0;

        if (checkActive.isSelected()){
            is_active = 1;
        }

        DbHandler db = new DbHandler();

        db.appendCourse(new Course(courseId, name, price, is_active));

        System.out.println("OK");

        courseId = 0;
        txtCourseName.clear();
        txtCoursePrice.clear();
        checkActive.setSelected(false);

        refresh();
    }

    @FXML
    void onRowClicked(MouseEvent event) {
        if(event.getClickCount() == 2){

            Course course = tbCourses.getSelectionModel().getSelectedItem();
            courseId = course.getId();
            txtCourseName.setText(course.getName());
            txtCoursePrice.setText(String.valueOf(course.getPrice()));
            if (course.getIsActive() == 1){
                checkActive.setSelected(true);
            }else{
                checkActive.setSelected(false);
            }
        }
    }

    private void refresh(){
        DbHandler dbHandler = new DbHandler();
        ObservableList<Course> list = FXCollections.observableArrayList(dbHandler.getCourses());
        tbCourses.setItems(list);
    }
}
