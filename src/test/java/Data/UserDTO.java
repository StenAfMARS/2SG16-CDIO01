package Data;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {
    public int userID;
    public String userName;
    public String ini;
    public String cpr;
    List<String> roles;
}