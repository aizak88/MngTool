package sample;

import org.sqlite.SQLiteConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbHandler {

    private SQLiteConnection getConnection(){
        SQLiteConnection connection;

        try {
            connection = new SQLiteConnection("jdbc:sqlite:", "db/mydb.db");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void appendCourse(Course course){
        SQLiteConnection conn = getConnection();

        String query;

        if (course.getId() == 0) {
            query = "insert into courses(course_name, price, is_active) values(?,?,?)";
        }else {
            query = "update courses set course_name = ?, price = ?, is_active = ? \n" +
                    "where courseid = ?";
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,course.getName());
            preparedStatement.setInt(2, course.getPrice());
            preparedStatement.setInt(3, course.getIsActive());
            if (course.getId() != 0){
                preparedStatement.setInt(4, course.getId());
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public void editCourse(Course course){
        SQLiteConnection conn = getConnection();

        String query = "update courses set course_name = ?, price = ?, is_active = ? \n" +
                "where courseid = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,course.getName());
            preparedStatement.setInt(2, course.getPrice());
            preparedStatement.setInt(3, course.getIsActive());
            preparedStatement.setInt(4, course.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public void appendUser(String name, String login, String pwd){
        SQLiteConnection conn = getConnection();

        String query = "insert into users(login, name, pwd) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, pwd);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public void appendTeacher(String name, String phone, String email, int is_active){
        SQLiteConnection conn = getConnection();

        String query = "insert into teachers(name, phone, email, is_active) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, is_active);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }


    public void ShowCourses(){
        SQLiteConnection connection = getConnection();

        String query = "select * from courses";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while (set.next()){
                System.out.println(set.getString(1));
                System.out.println(set.getString(2));
                System.out.println(set.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers(){
        SQLiteConnection conn = getConnection();
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("select name, login, pwd from users");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                User user = new User(resultSet.getString("name"),resultSet.getString("login"), resultSet.getString("pwd"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public ArrayList<Course> getCourses(){
        SQLiteConnection conn = getConnection();
        ArrayList<Course> list = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("select courseid, course_name, price, is_active from courses");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                list.add(new Course(resultSet.getInt(1),resultSet.getString(2), resultSet.getInt(3),resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
