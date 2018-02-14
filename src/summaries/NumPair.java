package summaries;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class NumPair implements WritableComparable<NumPair> {
	
	private DoubleWritable first;
	private IntWritable second;
	
	// constructors
	public NumPair() {
		set(new DoubleWritable(), new IntWritable());
	}
	public NumPair(Double first, Integer second) {
		set(new DoubleWritable(first), new IntWritable(second));
	}
	public NumPair(DoubleWritable first, IntWritable second) {
		set(first, second);
	}
	
	// private setter
	private void set(DoubleWritable first, IntWritable second) {
		this.first = first;
		this.second = second;
	}
	
	// getters
	public DoubleWritable getFirst() {
		return first;
	}
	public IntWritable getSecond() {
		return second;
	}
	
	// serialization and de-serialization
	@Override
	public void write(DataOutput arg0) throws IOException {
		// delegate serialization to their individual members
		first.write(arg0);	
		second.write(arg0);
	}
	@Override
	public void readFields(DataInput arg0) throws IOException {
		// delegate de-serialization  to their individual fields
		first.readFields(arg0);
		second.readFields(arg0);
	}

	// compares NumPair o to this
	@Override
	public int compareTo(NumPair o) {
		int cmp = first.compareTo(o.first);
		return cmp != 0 ? cmp : second.compareTo(o.second);
	}
	
	// Two objects that are equal should hash to the same bucket in a map
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NumPair) {
			NumPair o = (NumPair) obj;
			return first.equals(o.first) && second.equals(o.second);
		} else
			return false;
	}
	@Override
	public int hashCode() {
		return first.hashCode()*163 + second.hashCode();
	}
	
	@Override
	public String toString() {
		return first + "\t" + second;
	}

}
