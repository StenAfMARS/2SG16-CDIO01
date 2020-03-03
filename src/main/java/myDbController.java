import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;

public class myDbController {
    public static void main(String[] args){
        DEverything("Users","userId",4);
        SprintUserList();
    }
    // S + something = select somthing
    public static void SprintUserList(){

        String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "1234";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT userID,userName,userPassword,ini,cpr,rollName FROM mydb.users LEFT JOIN  mydb.rolls ON mydb.users.rollID = mydb.rolls.rollID");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+rs.getString(4)+"  "+rs.getInt(5)+"  "+rs.getString(6)+"  ");
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }
    // D + something = Delete something
    public static void DEverything(String tableName, String tableRowName, int IDOfItem ){
        String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "1234";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = "delete from "+tableName+" where "+tableRowName+" = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, IDOfItem);
            // execute the preparedstatement
            preparedStmt.execute();

            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
