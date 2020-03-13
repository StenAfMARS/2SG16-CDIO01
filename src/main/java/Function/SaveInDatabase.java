package Function;

import Data.UserDTO;
import Exceptions.DALException;
import Function.IUserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class SaveInDatabase implements IUserDAO {
    public static String dburl = "jdbc:mysql://mysql59.unoeuro.com:3306/vampire_live_dk_db_g16cdio1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static String dbusername = "vampire_live_dk";
    public static String dbpassword = "b2h4k9gc";

    @Override
    public UserDTO getUser(int userID){

        UserDTO userDTO = new UserDTO();
        System.out.println("Connecting database...");
        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            System.out.println("Connected database...");
            Statement stmt=connection.createStatement();
            System.out.println("execute command");
            ResultSet rs= stmt.executeQuery("SELECT userID,userName,userPassword,ini,cpr,Rolls FROM Users WHERE userID = "+userID+"");
            System.out.println("executed command");
            while(rs.next()) {
                userDTO.setUserID(rs.getInt(1));
                userDTO.setUserName(rs.getString(2));
                userDTO.setPassword(rs.getString(3));
                userDTO.setIni(rs.getString(4));
                userDTO.setCpr(rs.getString(5));
                userDTO.setRoles(Arrays.asList(rs.getString(6).split(" ")));
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
            ResultSet rs=stmt.executeQuery("SELECT * FROM Users");
            while(rs.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserID(rs.getInt(1));
                userDTO.setUserName(rs.getString(2));
                userDTO.setPassword(rs.getString(3));
                userDTO.setIni(rs.getString(4));
                userDTO.setCpr(rs.getString(5));
                userDTO.setRoles(Arrays.asList(rs.getString(6).split(" ")));
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
            System.out.println("connection estapleshed");
            String query = " insert into Users (userName, userPassword, ini, cpr, Rolls)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, userDTO.getUserName());
            preparedStmt.setString(2, userDTO.getPassword());
            preparedStmt.setString(3, userDTO.getIni());
            preparedStmt.setString(4, userDTO.getCpr());

            StringJoiner joiner = new StringJoiner(" ","","");
            userDTO.getRoles().forEach(joiner::add);

            preparedStmt.setString(5, joiner.toString());

            // execute the preparedstatement
            preparedStmt.execute();

            connection.close();
            System.out.println("Bruger oprettet connection closed");
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
            String query = " insert into vampire_live_dk_db_g16cdio1.Rolls (rollName, accessLVL)"
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
            String query = "update vampire_live_dk_db_g16cdio1.Users set userName = ?, userPassword = ?,ini = ?, cpr = ?, Rolls = ? where userId = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, userDTO.getUserName());
            preparedStmt.setString(2, userDTO.getPassword());
            preparedStmt.setString(3, userDTO.getIni());
            preparedStmt.setString(4, userDTO.getCpr());

            StringJoiner joiner = new StringJoiner(" ","","");
            userDTO.getRoles().forEach(joiner::add);

            preparedStmt.setString(5, joiner.toString());

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
        DEverything("Users", "userID", userId);
    }

    public static void Uroll(int rollID, String rollName, int accessLVL ){


        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword)) {
            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = "update vampire_live_dk_db_g16cdio1.Rolls set rollName = ?, accessLVL = ? where rollID = ?";

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
            System.out.println("connection estapleshed");
            String query = "delete from vampire_live_dk_db_g16cdio1."+tableName+" where "+tableRowName+" = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, IDOfItem);
            // execute the preparedstatement
            System.out.println("connection running sql command");
            preparedStmt.execute();
            System.out.println("connection sql command executed");
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
