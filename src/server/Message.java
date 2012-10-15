package server;

public class Message {

    private String text;
    private User srcUser;

    /**
     * Get the value of srcUser
     *
     * @return the value of srcUser
     */
    public User getSrcUser() {
        return srcUser;
    }

    /**
     * Set the value of srcUser
     *
     * @param srcUser new value of srcUser
     */
    public void setSrcUser(User srcUser) {
        this.srcUser = srcUser;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message(String text, User srcUser) {
        this.setText(text);
        this.setSrcUser(srcUser);
    }
}