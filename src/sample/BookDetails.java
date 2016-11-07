package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.lang.reflect.Member;

/**
 * Created by Manash on 11/7/2016.
 */
public class BookDetails {

    private SimpleStringProperty bookName;
    private SimpleBooleanProperty renew;

    public BookDetails(String bookName, boolean renew){
        this.bookName = new SimpleStringProperty(bookName);
        this.renew = new SimpleBooleanProperty(renew);
    }

    // Get variables
    public String getBookName(){
        return bookName.get();
    }

    public boolean getRenew(){
        return renew.get();
    }


    // Get properties
    public StringProperty bookNameProperty(){
        return bookName;
    }

    public BooleanProperty renewProperty(){
        return renew;
    }

    // Set properties
    public void setBookName(String bookName){
        this.bookName.set(bookName);
    }

    public void setRenew(boolean renew){
        this.renew.set(renew);
    }


}
