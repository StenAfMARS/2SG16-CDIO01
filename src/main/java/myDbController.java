import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;

public class myDbController {
    public static String dburl = "jdbc:mysql://mysql59.unoeuro.com:3306/vampire_live_dk_db_g16cdio1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static String dbusername = "vampire_live_dk";
    public static String dbpassword = "b2h4k9gc";
    public static void main(String[] args){
         DEverything("Users","userId",1);
        SprintUserList();
        SprintRollList();
    }


    // S + something = select somthing
    public static void SprintUserList(){

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT userID,userName,userPassword,ini,cpr,rollName FROM Users LEFT JOIN  Rolls ON Rolls.rollID = Users.fk_rollsID");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+rs.getString(4)+"  "+rs.getInt(5)+"  "+rs.getString(6)+"  ");
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }
    // S + something = select somthing
    public static void SprintRollList(){

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM vampire_live_dk_db_g16cdio1.Rolls");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  ");
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }
    // C + something = Create something
    public static void CUsers(String userName, String userPassword, String ini, int cpr, int rollID ){


        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = " insert into users (userName, userPassword, ini, cpr, fk_rollID)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, userName);
            preparedStmt.setString(2, userPassword);
            preparedStmt.setString(3, ini);
            preparedStmt.setInt(4, cpr);
            preparedStmt.setInt(5, rollID);

            // execute the preparedstatement
            preparedStmt.execute();

            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    public static void Croll(String rollName, int accessLVL ){


        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = " insert into vampire_live_dk_db_g16cdio1.rolls (rollName, accessLVL)"
                    + " values (?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, rollName);
            preparedStmt.setInt(2, accessLVL);

            // execute the preparedstatement
            preparedStmt.execute();

            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    // U + something = Update something
    public static void UUsers(int userId, String userName, String userPassword, String ini, int cpr, int rollID ){


        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = "update vampire_live_dk_db_g16cdio1.users set userName = ?, userPassword = ?,ini = ?, cpr = ?, fk_rollId = ? where userId = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, userName);
            preparedStmt.setString(2, userPassword);
            preparedStmt.setString(3, ini);
            preparedStmt.setInt(4, cpr);
            preparedStmt.setInt(5, rollID);
            preparedStmt.setInt(6, userId);

            // execute the preparedstatement
            preparedStmt.execute();

            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    public static void Uroll(int rollID, String rollName, int accessLVL ){


        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = "update vampire_live_dk_db_g16cdio1.rolls set rollName = ?, accessLVL = ? where rollID = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, rollName);
            preparedStmt.setInt(2, accessLVL);
            preparedStmt.setInt(3, rollID);

            // execute the preparedstatement
            preparedStmt.execute();

            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    // D + something = Delete something
    public static void DEverything(String tableName, String tableRowName, int IDOfItem ){

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = "delete from vampire_live_dk_db_g16cdio1."+tableName+" where "+tableRowName+" = ?";

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
