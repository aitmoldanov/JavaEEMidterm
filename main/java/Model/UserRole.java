package Model;

public class UserRole {
    private int id;
    private String logIn;
    private String userPassword;
    private STATUS STATUS;

    public UserRole(int id, String logIn, String userPassword, STATUS STATUS) {
        this.id = id;
        this.logIn = logIn;
        this.userPassword = userPassword;
        this.STATUS = STATUS;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogIn() {
        return logIn;
    }
    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public STATUS getSTATUS() {
        return STATUS;
    }
    public void setSTATUS(STATUS STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + logIn + '\'' +
                ", password='" + userPassword + '\'' +
                ", status=" + STATUS +
                '}';
    }
}
