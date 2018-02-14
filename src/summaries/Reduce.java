package summaries;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, NumPair, Text, DoubleWritable> {

	public void reduce(Text _key, Iterable<NumPair> values, Context context) throws IOException, InterruptedException {
		// process values
		Double sum = 0.0;
		Integer count = 0;
		for (NumPair val : values) {
			sum += val.getFirst().get();
			count += val.getSecond().get();
		}
		
		Double avg = sum/count;	
		context.write(_key, new DoubleWritable(avg));
	}
}
