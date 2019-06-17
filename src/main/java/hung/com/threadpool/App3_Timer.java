package hung.com.threadpool;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 
 *
 */
public class App3_Timer {

	static class FirstTask extends TimerTask{
		@Override
		public void run(){//gọi hàm này khi TimerTask đc kích hoạt
			//source code chạy trên Thread của Timer
			System.out.println("FirstTask:" +Thread.currentThread().getId());
		}
	};
	
	static class SecondTask extends TimerTask{
		@Override
		public void run(){//gọi hàm này khi TimerTask đc kích hoạt
			//source code chạy trên Thread của Timer
			System.out.println("SecondTask:" + Thread.currentThread().getId());
		}
	};



	public static void main(String[] args) throws InterruptedException {  

		Thread.sleep(20000); //to start "Jvisualvm" to count thread
		System.out.println("mainthread=" + Thread.currentThread().getId());
		System.out.println("************* main() thread : before timer");
		Timer timer = new Timer();
		
		//timer.schedule(task, delay, period);
		//Create a worker thread to make timer
		//cả 2 task này đều chạy trên cùng 1 thread.
		timer.schedule(new FirstTask(), 0, 1000);
		timer.schedule(new SecondTask(), 0, 2000);
		
		System.out.println("************* main() thread: was schedule");
		Thread.sleep(20000);
		
		System.out.println("************* main() thread: end");
		
	}  
}
