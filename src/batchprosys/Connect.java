/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchprosys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author akilajwasala
 */
public class Connect {
    static Connection con=null;
    
    public static Connection connecter(){
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/batchproductionsystem","root","");
        }catch(SQLException e){
            System.out.print(e.getMessage());
        }
        return con;

}
}
