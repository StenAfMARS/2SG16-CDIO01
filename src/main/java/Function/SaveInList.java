package Function;

import Data.UserDTO;
import Exceptions.DALException;

import java.util.ArrayList;
import java.util.List;

public class SaveInList implements IUserDAO {
    protected List<UserDTO> brugere = new ArrayList<>();

    @java.lang.Override
    public UserDTO getUser(int userId) throws DALException {
        for (UserDTO bruger: brugere) {
            if (userId == bruger.getUserID()){
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
        int i = 0;

        for ( UserDTO bruger: brugere) {
            if (bruger.getUserID() == user.getUserID()) {
                brugere.remove(i);
                brugere.add(i, user);
                break;
            }
            i++;
        }
    }

    @java.lang.Override
    public void deleteUser(int userId) throws DALException {
        for (UserDTO bruger: brugere) {
            if (userId == bruger.getUserID()){
                brugere.remove(bruger);
                break;
            }
        }
    }
}
