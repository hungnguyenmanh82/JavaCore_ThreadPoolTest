package hung.com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * dùng JConsole để kiểm tra số threads đc tạo ra.
 * Mặc định 1 Process có 11 threads hệ thống + 1 thread chạy hàm main()
 * >>cmd
 * >>jconsole
 * >>Jvisualvm
 *
 */
public class App1_ThreadPool {
	
	static class MyRunable implements Runnable {  
		private String message;  
		public MyRunable(String s){  
			this.message=s;  
		}  
		public void run() {  
			System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);  
			processmessage();//call processmessage method that sleeps the thread for 2 seconds  
			System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name  
		}  
		private void processmessage() {  
			try {  Thread.sleep(5000);  } catch (InterruptedException e) { e.printStackTrace(); }  
		}  
	}
	
    public static void main(String[] args) throws InterruptedException {  
    	System.out.println("################### 12 threads = 11 + main thread");
    	Thread.sleep(20000); //to start "jconsole"
    	//fix 5
        ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads  
        
        System.out.println("################### 13 threads: + 1 thread of executor");
        Thread.sleep(4000);
        for (int i = 0; i < 10; i++) {  
            Runnable worker = new MyRunable("" + i);  
            
            executor.execute(worker);//put all Runable to Queue of Threadpool and return immediately
        }  
        
        System.out.println("###################  18 threads: + 5 thread of threadpool");
        System.out.println("********shutdown");
        //hàm này chỉ set flag = shutdown và trả về luôn
        //Threadpool will not release until all threads stop running
        executor.shutdown(); 
        
        while (!executor.isTerminated()) {  //Threadpool will not release until all threads stop running
        	Thread.sleep(1000);
        	System.out.println("***isTerminated = false: sleep 2 seconds"); //never happen
        }  
  
        System.out.println("###################  13 threads: -5 threads of threadpool");
        Thread.sleep(30000);
        System.out.println("Finished all threads");  
        
    }  
}
