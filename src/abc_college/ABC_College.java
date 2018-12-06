/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc_college;

import abc_db.DBHelper;
import abc_db.User;

/**
 *
 * @author aslam
 */
public class ABC_College {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Login login = new Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null); 

//        DBHelper db = new DBHelper();
//        db.connect();
//        db.initTables();

    }
    
}
