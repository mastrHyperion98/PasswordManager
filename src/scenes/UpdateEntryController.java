package scenes;

import Encoder.AES;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import struct.Password;
import java.sql.SQLException;

public class UpdateEntryController   {
    @FXML
    private TextField tfDomain;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;

    private struct.Controller myController;
    private final int PASSWORD_GEN_LENGTH=32;
    private Password password_entry;

    @FXML
    void btnUpdateClicked(ActionEvent event) {
        String domain = tfDomain.getText();
        int id = password_entry.getId();
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfPassword.getText();

        boolean success = myController.getSession().editEntry(id,domain,email,username,password);
        if(success) {
            password_entry.setUsername(username);
            password_entry.setEmail(email);
            password_entry.setPassword(password);
            closeStage(event);
        }
    }

    @FXML
    void btnGeneratePassowrd(ActionEvent event) {
        String password= getAlphaNumericString(PASSWORD_GEN_LENGTH);
        tfPassword.setText(password);
    }

    private String getAlphaNumericString(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"
                + "#$%&=+-!%";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public void setController(struct.Controller _controller){

        myController = _controller;
    }

    public void setPassword(Password password){
        password_entry=password;
        tfDomain.setText(password.getDomain());
        tfEmail.setText(password.getEmail());
        tfUsername.setText(password.getUsername());
        tfPassword.setText(password.getPassword());
        // disable editing on the domain
        tfDomain.setEditable(false);
    }
    private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}