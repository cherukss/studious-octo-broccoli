package summaries;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Text, NumPair> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		
		String line = ivalue.toString(); // extract String value from Text input
		String[] data = line.split(",");
		
		try {
			String maritalStatus = data[5]; // intermediate key
			Double hrsPerWeek = Double.parseDouble(data[12]); // intermediate value
			
			context.write(new Text(maritalStatus), new NumPair(hrsPerWeek, 1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
