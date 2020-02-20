package Function;

import Data.UserDTO;
import Exceptions.DALException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveInList implements IUserDAO {
    List<UserDTO> brugere = new ArrayList<>();

    @java.lang.Override
    public UserDTO getUser(int userId) throws DALException {
        for (UserDTO bruger: brugere) {
            if (userId == bruger.userID){
                return bruger;
            }
        }
        throw new DALException("User not found");
    }

    @java.lang.Override
    public List<UserDTO> getUserList() throws DALException {
        return brugere;
    }

    @java.lang.Override
    public void createUser(UserDTO user) throws DALException {
        brugere.add(user);
    }

    @java.lang.Override
    public void updateUser(UserDTO user) throws DALException {
        for ( UserDTO bruger: brugere) {
            if (bruger.userID == user.userID) {
                bruger.userName = user.userName;
                bruger.password = user.password;
                bruger.cpr = user.cpr;
                bruger.ini = user.ini;
                bruger.roles = user.roles;
            }
        }
    }

    @java.lang.Override
    public void deleteUser(int userId) throws DALException {
        for (UserDTO bruger: brugere) {
            if (userId == bruger.userID){
                brugere.remove(bruger);
                break;
            }
        }
    }
}
