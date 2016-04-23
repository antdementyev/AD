package listen_impl;

public abstract class Counter {

	private long count;
	
	public void resetCounter() {
		count = 0;
	}
	
	public long getCounter() {
		return count;
	}
	
	protected void count(int n) {
		count = count + n;
	}
}
