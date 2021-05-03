package newsanalyzer.downloader;

import java.util.concurrent.Callable;

public class ThreadCallable implements Callable {

    @Override
    public String call() throws Exception {

        return downloader.saveUrl2File(url);

    }
    private Downloader downloader;
    private String url;

    public ThreadCallable(Downloader downloader, String url) {
        this.downloader= downloader;
        this.url = url;


    }







}
