package Function;

import Data.UserDTO;
import Exceptions.DALException;

import Exceptions.JsonWriteException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SaveInFile extends SaveInList{
    JSONObject jObj = new JSONObject();
    JSONArray jArray = new JSONArray();
    private static FileWriter file;

    @Override
    public UserDTO getUser(int userId) throws DALException {
        return super.getUser(userId);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return super.getUserList();
    }

    @Override
    public void createUser(UserDTO user) throws DALException, IOException {
        super.createUser(user);

        jObj.put("Name", user.getUserName());
        jObj.put("Password", user.getPassword());
        jObj.put("Ini", user.getIni());
        jObj.put("CPR", user.getCpr());

        for (int i=0; i< user.getRoles().size(); i++){
            jArray.add(user.getRoles().get(i));
        }
        jObj.put("Roles", jArray);

        writeDataToFile(user, jObj);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        super.updateUser(user);
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        super.deleteUser(userId);
    }

    public void writeDataToFile(UserDTO user, JSONObject obj) throws java.io.IOException {
        Path currentDir = Paths.get(".");

        try {
            file = new FileWriter(  currentDir.toAbsolutePath() + "\\Users\\" + user.getUserName() +".txt");
            file.write(obj.toJSONString());
        } catch (JsonWriteException e){

        }finally {
            try{
                file.flush();
                file.close();
            }catch (IOException e){

            }
        }
    }

}
