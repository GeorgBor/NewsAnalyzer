package newsanalyzer.downloader;

import java.util.List;

public class SequentialDownloader extends Downloader{



    @Override
    public int process(List<String> urls) {
        long timer = System.currentTimeMillis();
        int count = 0;
        for (String url : urls) {
            String fileName = saveUrl2File(url);
            if(fileName != null)
                count++;
        }
        System.out.println("SeqenceDownloader: "+(System.currentTimeMillis() - timer));

        return count;
    }

}
