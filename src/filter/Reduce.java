package filter;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<NullWritable, Text, NullWritable, Text> {

	public void reduce(final NullWritable _key, final Iterable<Text> values, final Context context) throws IOException, InterruptedException {
		
		for (Text val : values) {
			context.write(_key, val);
		}
		
	}

}
