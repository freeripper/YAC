package client;  

public class Message {
        private String _text;
	private int _timestamp;
	private String _User;
	public Client _unnamed_Client_;

	public Message(String text, int timestamp, String User) {
                this._text=text;
                this._timestamp=timestamp;
                this._User=User;
                
	}
    
    public String getUser() {
        return _User;
    }

    public String getText() {
        return _text;
    }

    public int getTimestamp() {
        return _timestamp;
    }
        
        
        
        
}