package Function;

import Data.UserDTO;
import Exceptions.DALException;

import java.io.IOException;
import java.util.List;

public interface IUserDAO {
    UserDTO getUser(int userId) throws DALException;
    List<UserDTO> getUserList() throws DALException;
    void createUser(UserDTO user) throws DALException, IOException;
    void updateUser(UserDTO user) throws DALException;
    void deleteUser(int userId) throws DALException;
}

