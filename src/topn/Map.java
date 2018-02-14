package topn;

import java.io.IOException;
import java.util.PriorityQueue;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, NullWritable, Text> {

	private PriorityQueue<User> followersPriorityQueue = new PriorityQueue<>();
	private static int N = 3;
	
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String[] data = line.split("\t");
		
		Integer followers = Integer.parseInt(data[1]);
		
		User user = followersPriorityQueue.peek(); // user with the least followers in our top three
		
		if(followersPriorityQueue.size() <= N || followers > user.getFollowers()) {
			followersPriorityQueue.add(new User(followers, new Text(ivalue)));
		}
		
		if(followersPriorityQueue.size() > N) {
			followersPriorityQueue.poll();
		}
		
	}

	@Override
	protected void cleanup(Mapper<LongWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		
		while(!followersPriorityQueue.isEmpty()) {
			context.write(NullWritable.get(), followersPriorityQueue.poll().getRecord());
		}
	}

}
