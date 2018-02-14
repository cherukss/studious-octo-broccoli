package topn;

import org.apache.hadoop.io.Text;

public class User implements Comparable<User> {
	
	private int followers;
	private Text record;
	
	// constructor
	public User(int followers, Text record) {
		this.followers = followers;
		this.record = record;
	}
	
	// getters
	public int getFollowers() {
		return followers;
	}
	public Text getRecord() {
		return record;
	}
	
	// sort would be in ascending order => 
	@Override
	public int compareTo(User o) {
		return this.followers - o.followers;
	}
	

}
