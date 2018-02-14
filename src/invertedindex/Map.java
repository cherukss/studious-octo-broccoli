package invertedindex;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class Map extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		
		// get the data to be processed by an individual Mapper
		FileSplit currentSplit =  (FileSplit) context.getInputSplit(); // inputsplit represents the data that is present on one mapper node
		
		// Access the name of the file
		String filenameStr = currentSplit.getPath().getName();
		Text filename = new Text(filenameStr); // analogus to an URL
		
		String line  = ivalue.toString();
		StringTokenizer tokenizer = new  StringTokenizer(line);
		
		Text word = new Text();
		while (tokenizer.hasMoreTokens()) {
			word.set(tokenizer.nextToken());
			context.write(word, filename);
		}
	}

}
