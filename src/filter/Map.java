package filter;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, NullWritable, Text> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		String[] data = line.split("\t");
		
		// filter only those records associated with books
		if(data[2].equalsIgnoreCase("Books")) {
			context.write(NullWritable.get(), ivalue);
		}
	}

}
