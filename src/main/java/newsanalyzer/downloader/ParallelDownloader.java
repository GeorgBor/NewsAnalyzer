package newsanalyzer.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader{


    @Override
    public int process(List<String> urls) {

        long timer = System.currentTimeMillis();

        int count =0;
        List<Future<String>> futures = new ArrayList<>();

        for (String url: urls){
            ThreadCallable threadCallable = new ThreadCallable(this, url);
            ExecutorService callable = Executors.newCachedThreadPool();
            futures.add(callable.submit(threadCallable));

            //String file = saveUrl2File(url);
            //byte[] bs = result.get();
        }

        for (Future<String> future: futures){
            try {
                String fileName = future.get();
                if (fileName != null){
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        System.out.println("Paralelldownloader: "+(System.currentTimeMillis() - timer));

        return count;
    }



}
