/*
Controller will contain the logic and commands executed by the JavaFX application.
 */

import Encoder.AES;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;   // Import the FileWriter class

public class Controller {
    private String databse;
    private boolean isAuth;
    private final String AUTH_FILE = "src/data/auth.txt";
    public Controller(){

    }

    public boolean auth(String password){
        // decrypt text_file
        try {
            Scanner file_in = new Scanner(new FileInputStream(AUTH_FILE));
            // decrypt the master password
            String master_password = AES.decrypt(file_in.nextLine().strip());
            if(master_password.equals(password))
                isAuth = true;
            file_in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // return the state of authentication
        return isAuth;
    }

    public boolean isAuthExist(){
        File file = new File(AUTH_FILE);
        return file.exists();
    }

    // assume password is already encrypted
    public void createAuthFile(String password) throws IOException {
        File file = new File(AUTH_FILE);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(AES.encrypt(password));
        writer.close();
    }


}
