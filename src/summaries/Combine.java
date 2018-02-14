package summaries;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Combine extends Reducer<Text, NumPair, Text, NumPair> {

	public void reduce(Text _key, Iterable<NumPair> values, Context context) throws IOException, InterruptedException {

		Double sum = 0.0;
		Integer count = 0;
		for (NumPair val : values) {
			sum += val.getFirst().get();
			count += val.getSecond().get();
		}
		
		context.write(_key, new NumPair(sum, count));
	}

}
