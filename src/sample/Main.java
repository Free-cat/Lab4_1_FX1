package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main extends Application {

    public TextField text;
    public Button parse;
    public Button delete;
    public Button add;
    public TableView table;
    public TableColumn name;
    public TableColumn texts;
    WorkWithFile fl = new WorkWithFile("D:\\lab4_1_FX1\\Temp.txt");

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lav4_1_FX");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void parse(ActionEvent actionEvent) throws FileNotFoundException {
        ObservableList<Sprite> parseData = FXCollections.observableArrayList();
        String line;
        line = fl.parseFile(text.getText().toLowerCase().toString());
        StringTokenizer st2 = new StringTokenizer(line, "-");
        while (st2.hasMoreTokens())
            parseData.add(new Sprite(st2.nextToken(), st2.nextToken()));
        name.setCellValueFactory(new PropertyValueFactory<Sprite, Integer>("name"));
        texts.setCellValueFactory(new PropertyValueFactory<Sprite, String>("text"));
        table.setItems(parseData);
    }

    public void delete(ActionEvent actionEvent) throws FileNotFoundException {
        fl.deleteFile(text.getText().toLowerCase().toString());
        open();
    }

    public void open() {
        String line;
        // Тут храним инфу из файла
        ObservableList<Sprite> usersData = FXCollections.observableArrayList();
        try {
            // Читаем инфу из файла
            FileInputStream fis = new FileInputStream("D:\\Lab4_1_FX1\\Temp.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            // Разделителем информации для колонок будет знак тире (-)
            while ((line = br.readLine()) != null) {
                StringTokenizer st2 = new StringTokenizer(line, "-");
                while (st2.hasMoreTokens())
                    usersData.add(new Sprite(st2.nextToken(), st2.nextToken()));
            }
            name.setCellValueFactory(new PropertyValueFactory<Sprite, Integer>("name"));
            texts.setCellValueFactory(new PropertyValueFactory<Sprite, String>("text"));
            table.setItems(usersData);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void add(ActionEvent actionEvent) {
        fl.writeFile(text.getText().toString());
        open();
    }

    private void open(ActionEvent actionEvent) {
        open();
    }
}
