package topn;

import java.io.IOException;
import java.util.PriorityQueue;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<NullWritable, Text, NullWritable, Text> {
	
	private PriorityQueue<User> followersPriorityQueue = new PriorityQueue<>();
	private static int N = 3;

	public void reduce(NullWritable _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		for (Text val : values) {
			String line = val.toString();
			String[] data = line.split("\t");
			
			int followers = Integer.parseInt(data[1]);
			
			User user = followersPriorityQueue.peek(); // user with the least followers in our top three
			
			if(followersPriorityQueue.size() <= N || followers > user.getFollowers()) {
				followersPriorityQueue.add(new User(followers, new Text(val)));
			}
			
			if(followersPriorityQueue.size() > N) {
				followersPriorityQueue.remove(user);
			}
		}
		
		while(!followersPriorityQueue.isEmpty()) {
			context.write(NullWritable.get(), followersPriorityQueue.poll().getRecord());
		}
	}

}
