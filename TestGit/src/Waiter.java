public class Waiter {
	int semaphore = 0;
	int max=1;
	
	/**
	 * Adds thread to the queue, counts waiting threads.
	 * @throws InterruptedException
	 */
	public synchronized void up() throws InterruptedException{
		while (semaphore == max) {
			wait();
		}
		semaphore++;
	}
	/**
	 * Notifies waiting threads, after one of them ended its job.
	 * @throws InterruptedException
	 */
	public synchronized void down() throws InterruptedException {
		if (semaphore > 0) {
			notify();
		}
		semaphore--;
	}
}
