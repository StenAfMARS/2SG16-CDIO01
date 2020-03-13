package Function;

import Data.UserDTO;
import Exceptions.DALException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SaveInFile extends SaveInList{
    @Override
    public UserDTO getUser(int userId) throws DALException {
        return super.getUser(userId);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return super.getUserList();
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        super.createUser(user);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        super.updateUser(user);
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        super.deleteUser(userId);
    }
}
