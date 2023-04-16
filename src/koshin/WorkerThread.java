
package koshin;
//
//public class WorkerThread extends Thread {
//    
//    DataTransferObject dto;
//        
//    public WorkerThread(DataTransferObject dto){
//        this.dto = dto;
//    }
//    
//    @Override
//    public void run() {
//        System.out.println("The worker thread has started");
//        
//        // the main loop runs forever
//        while (true) {
//        
//            // Wait to be notified            
//            try{synchronized(this){wait();}}
//            catch (InterruptedException e){}
//            
//            
//            // do the thing
//            
//            System.out.println("doing the thing...");
//            
//            
//            // we're now finished
//            System.out.println("...finished the thing");
//            dto.setRunning(false);
//        
//        }
//        
//    }    
//}
