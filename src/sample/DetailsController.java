package sample;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


import java.awt.print.Book;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Manash on 11/7/2016.
 */



public class DetailsController implements Initializable {


    @FXML
    private TableView tableView;

    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<BookDetails> bookDetails = FXCollections.observableArrayList();
        bookDetails.add(new BookDetails("Hello", true));
        bookDetails.add(new BookDetails("Madafaka", false));

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableView.setItems(bookDetails);

        TableColumn<BookDetails, String> c1 = new TableColumn<>("Name");
        c1.setCellValueFactory(new PropertyValueFactory<BookDetails, String>("bookName"));

        tableView.getColumns().add(c1);

        TableColumn<BookDetails, Boolean> c2 = new TableColumn<>("Select");

        c2.setCellValueFactory(new PropertyValueFactory<BookDetails, Boolean>("renew"));

        c2.setCellFactory(CheckBoxTableCell.forTableColumn(c2));


        ObservableList selectedCells = tableView.getSelectionModel().getSelectedCells();

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookDetails>() {
            @Override
            public void changed(ObservableValue<? extends BookDetails> observable, BookDetails oldValue, BookDetails newValue) {
                System.out.println( tableView.getSelectionModel().getSelectedIndex() );
            }
        });



        tableView.getColumns().add(c2);



    }
}
