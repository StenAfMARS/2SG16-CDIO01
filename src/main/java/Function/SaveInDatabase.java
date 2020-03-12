package Function;

import Data.UserDTO;
import Exceptions.DALException;
import Function.IUserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveInDatabase implements IUserDAO {
    private static final String dburl = "jdbc:mysql://mysql59.unoeuro.com:3306/vampire_live_dk_db_g16cdio1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String dbusername = "vampire_live_dk";
    private static final String dbpassword = "b2h4k9gc";

    @Override
    public UserDTO getUser(int userID){

        UserDTO userDTO = new UserDTO();

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery("SELECT userID,userName,userPassword,ini,cpr,rollName FROM Users LEFT JOIN  Rolls ON Rolls.rollID = Users.fk_rollsID where userID == " + userID);

            while(rs.next()) {
                userDTO.setUserID(rs.getInt(1));
                userDTO.setUserName(rs.getString(2));
                userDTO.setPassword(rs.getString(3));
                userDTO.setIni(rs.getString(4));
                userDTO.setCpr(rs.getString(5));
                userDTO.setRoles(Arrays.asList(rs.getString(6)));
            }

            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return userDTO;
    }

    // S + something = select somthing
    @Override
    public List<UserDTO> getUserList(){
        List<UserDTO> userDTOs = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT userID,userName,userPassword,ini,cpr,rollName FROM Users LEFT JOIN  Rolls ON Rolls.rollID = Users.fk_rollsID");

            while(rs.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserID(rs.getInt(1));
                userDTO.setUserName(rs.getString(2));
                userDTO.setPassword(rs.getString(3));
                userDTO.setIni(rs.getString(4));
                userDTO.setCpr(rs.getString(5));
                userDTO.setRoles(Arrays.asList(rs.getString(6)));
                userDTOs.add(userDTO);
            }

            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return userDTOs;
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
    @Override
    public void createUser(UserDTO userDTO){
        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = " insert into users (userName, userPassword, ini, cpr, fk_rollID)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, userDTO.getUserName());
            preparedStmt.setString(2, userDTO.getPassword());
            preparedStmt.setString(3, userDTO.getIni());
            preparedStmt.setString(4, userDTO.getCpr());
            preparedStmt.setInt(5, 0);//userDTO.);

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

    @Override
    public void updateUser(UserDTO userDTO){
        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = "update vampire_live_dk_db_g16cdio1.users set userName = ?, userPassword = ?,ini = ?, cpr = ?, fk_rollId = ? where userId = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, userDTO.getUserName());
            preparedStmt.setString(2, userDTO.getPassword());
            preparedStmt.setString(3, userDTO.getIni());
            preparedStmt.setString(4, userDTO.getCpr());
            preparedStmt.setInt(5, 0); // userDTO.getRoles());
            preparedStmt.setInt(6, userDTO.getUserID());

            // execute the preparedstatement
            preparedStmt.execute();

            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        DEverything("users", "userID", userId);
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
