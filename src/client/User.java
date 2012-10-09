package client;

/**
 *
 * @author roussfra
 */
public class User {
        private String username;
        private String nick;
        private int status;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
        
        public User(String username, String nick, int status){
            setNick(nick);
            setUsername(username);
            setStatus(status);
        }
        
        
    
}
