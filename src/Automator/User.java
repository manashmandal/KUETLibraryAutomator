package Automator;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;


/**
 * Created by Manash on 11/5/2016.
 */
public class User {

    //Information
    private String userID;
    private String password;
    private UserAgent browser;

    private static final String LIBRARY_URL = "http://library.kuet.ac.bd";

    private static final int MAX_COL = 6;
    private static final int MAX_ROW = 3;

    private static final int SERIAL_COL = 0;
    private static final int BOOK_NAME_COL = 1;
    private static final int AUTHOR_NAME_COL = 2;
    private static final int CALL_NO_COL = 3;
    private static final int DUE_DATE_COL = 4;
    private static final int RENEWALS_COL = 5;

    private static final int FIRST_ROW = 0;
    private static final int SECOND_ROW = 1;
    private static final int THIRD_ROW = 2;

    private static final String BOOKNAME_SELECTOR = "<td class=title>";
    private static final String RENEWALS_SELECTOR = "<span class=renewals>";
    private static final String DUE_DATE_SELECTOR = "<td class=date_due>";
    private static final String CALL_NO_SELECTOR = "<td class=call_no>";

    private static final int MAX_ITEMS = 3;

    //Indices
    private static final int RENEWAL_INDEX = 1;
    private static final int BOOKNAME_INDEX = 0;
    private static final int AUTHOR_INDEX = 1;

    private String[][] dataTable;

    public User () {
        browser = new UserAgent();
        dataTable = new String[MAX_ROW][MAX_COL];
    }

    public User(String userID, String password){
        this.userID = userID;
        this.password = password;
        browser = new UserAgent();
        dataTable = new String[MAX_ROW][MAX_COL];
    }

    public void login(){
        try {
            browser.visit(LIBRARY_URL);
            browser.doc.apply(this.userID, this.password);
            browser.doc.submit();
        } catch (JauntException e){
            System.err.println(e);
        }
    }

    public String getCurrentPage(){
        return browser.doc.innerHTML();
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }

    public String getUserID(){
        return this.userID;
    }

    public String getPassword(){
        return this.password;
    }

    public void updateDetails(){
        String[] renewalsLeft   = new String[MAX_ITEMS];
        String[] bookNames      = new String[MAX_ITEMS];
        String[] authors        = new String[MAX_ITEMS];
        String[] calls          = new String[MAX_ITEMS];
        String[] dues           = new String[MAX_ITEMS];
        String[] serials        = new String[MAX_ITEMS];

        Elements renewals       = browser.doc.findEvery(RENEWALS_SELECTOR);
        Elements bookDetails    = browser.doc.findEvery(BOOKNAME_SELECTOR);
        Elements dueDates       = browser.doc.findEvery(DUE_DATE_SELECTOR);
        Elements callNo         = browser.doc.findEvery(CALL_NO_SELECTOR);

        for (int i = 0; i < renewals.size(); i++) {

            try {
                // Getting number of available renewals
                renewalsLeft[i] = extractRenewals(renewals.getElement(i).innerText());

                // Getting due dates
                dues[i] = dueDates.getElement(i).innerText();

                // Getting the serials
                serials[i] = String.valueOf(i + 1);

                // Extract once
                String authorBookString = bookDetails.getElement(i).innerText();

                // Getting book name
                bookNames[i] = extractBookName(authorBookString);

                // Getting the author name
                authors[i] = extractAuthor(authorBookString);

                // Getting call numbers
                calls[i] = callNo.getElement(i).innerText();

                System.out.println(calls[i]);

            } catch (JauntException e){
                System.err.println(e);
            }
        }

    }


    private String extractAuthor(String text){
        String[] bookDetails = text.split("/");
        return bookDetails[AUTHOR_INDEX].trim();
    }

    private String extractBookName(String text){
        String[] bookDetails = text.split("/");
        return bookDetails[BOOKNAME_INDEX].trim();
    }

    private String extractRenewals(String text){
        text = text.replace('(', ' ');
        String[] texts = text.split(" ");
        return texts[RENEWAL_INDEX];
    }


}
