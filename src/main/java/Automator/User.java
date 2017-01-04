package Automator;

/**
 * Created by Manash on 04-Jan-17.
 */
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;



import java.util.ArrayList;


/**
 * Created by Manash on 11/5/2016.
 */
public class User {

    //Information
    private String userID;
    private String password;
    private UserAgent browser;

    private static final String LIBRARY_URL = "http://library.kuet.ac.bd";
    private static final String BOOKNAME_SELECTOR = "<td class=title>";
    private static final String RENEWALS_SELECTOR = "<span class=renewals>";
    private static final String DUE_DATE_SELECTOR = "<td class=date_due>";
    private static final String CALL_NO_SELECTOR = "<td class=call_no>";
    private static final String LINK_SELECTOR = "<a>";
    private static final String AUTHOR_SELECTOR = "<span class=item-details>";


    //Indices
    private static final int RENEWAL_INDEX = 1;


    //Variables
    private ArrayList<String> RenewalsLeft;
    private ArrayList<String> BookNames;
    private ArrayList<String> Authors;
    private ArrayList<String> Calls;
    private ArrayList<String> Dues;
    private ArrayList<String> SerialNo;

    private void init(){
        browser = new UserAgent();
        RenewalsLeft = new ArrayList<String>();
        Calls = new ArrayList<String>();
        Authors = new ArrayList<String>();
        Dues = new ArrayList<String>();
        SerialNo = new ArrayList<String>();
        BookNames = new ArrayList<String>();
    }

    public User () {
        init();
    }

    public User(String userID, String password){
        this.userID = userID;
        this.password = password;
        init();
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

    public void debug(){
        for (int i = 0; i < BookNames.size(); i++){
            System.out.println(" ===== DEBUG =====");
            System.out.println("BOOK NAME: " + BookNames.get(i));
            System.out.println("AUTHOR NAME: " + Authors.get(i));
            System.out.println("DUE : " + Dues.get(i));
            System.out.println("RENEWALS : " + RenewalsLeft.get(i));
            System.out.println("CALL NO : " + Calls.get(i));
            System.out.println();
        }
    }

    public void updateDetails(){

        //Clearing the arrays
        RenewalsLeft.clear();
        Dues.clear();
        SerialNo.clear();
        BookNames.clear();
        Calls.clear();
        Authors.clear();

        Elements renewalsElements       = browser.doc.findEvery(RENEWALS_SELECTOR);
        Elements dueDatesElements       = browser.doc.findEvery(DUE_DATE_SELECTOR);
        Elements callNoElements         = browser.doc.findEvery(CALL_NO_SELECTOR);
        Elements bookNameElements       = browser.doc.findEvery(BOOKNAME_SELECTOR).findEvery(LINK_SELECTOR);
        Elements authorElements         = browser.doc.findEvery(BOOKNAME_SELECTOR).findEvery(AUTHOR_SELECTOR);

        System.out.println("Size: " + String.valueOf(renewalsElements.size()));

        for (int i = 0; i < renewalsElements.size(); i++) {
            try {
                RenewalsLeft.add(extractRenewals(renewalsElements.getElement(i).innerText()));
                Dues.add(dueDatesElements.getElement(i).innerText());
                SerialNo.add(String.valueOf(i + 1));
                BookNames.add(bookNameElements.getElement(i).innerText().replaceAll(" /", "").replaceAll(" :", ""));
                Calls.add(callNoElements.getElement(i).innerText());
                Authors.add(authorElements.getElement(i).innerText().trim());
            } catch (JauntException e){
                System.err.println(e);
            }
        }
    }

    // Extracts the number of renewals available
    private String extractRenewals(String text){
        text = text.replace('(', ' ');
        String[] texts = text.split(" ");
        return texts[RENEWAL_INDEX];
    }

}