package invertedindex;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		StringBuilder stringBuilder = new StringBuilder();
		// process values
		for (Text val : values) {
			stringBuilder.append(val.toString());
			
			if(values.iterator().hasNext())
				stringBuilder.append(" | ");
		}
		
		context.write(_key, new Text(stringBuilder.toString()));
	}

}
