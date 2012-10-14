package client;

public class Message {
	private String _text;
	private int _timestamp;
	private User[] _dstUser;
	private User _srcUser;
	public Client _unnamed_Client_;

	public Message(String text, int timestamp, User[] dstUser, User srcUser) {
		int i=0;
                this._text=text;
                this._timestamp=timestamp;
                while (dstUser[i] != null){
                    this._dstUser[i]=dstUser[i];
                    i++;
                }
                this._srcUser=srcUser;
	}
}