package newsanalyzer.ctrl;

import newsanalyzer.downloader.Downloader;
import newsanalyzer.downloader.ParallelDownloader;
import newsanalyzer.downloader.SequentialDownloader;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "1742bdaea27f46f4a303693c5327ae64";

	public void process(NewsApi news) throws IOException, NewsAnalyzerException {
		System.out.println("Start process");

		NewsReponse response = news.getNews();
		if (response != null){
			List<Article> articles = response.getArticles();

			articles.stream().forEach(article -> System.out.println(articles.toString()));


			System.out.println("Number of Articel: "+ getNumberOfArticel(articles));
			System.out.println("Provider with most Article: "+ getNumberOfMostArticel(articles));
			System.out.println("Author with shortest Names: "+ getAuthorWithShortestName(articles));
			System.out.println("Longest Title: "+ getLongestTitleFromTheAlphabet(articles));
		}

		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		System.out.println("End process");
	}
	public String getNumberOfArticel(List<Article> data){
		return data
				.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet()
				.stream()
				.max(Comparator.comparing(Map.Entry::getValue)).get()
				.getKey().getSource().getName();

	}
	public long getNumberOfMostArticel(List<Article> data){
		return data.stream().count();
	}

	public List<Article> getLongestTitleFromTheAlphabet(List<Article> data){
		return data
				.stream()
				.sorted(Comparator.comparingInt(Article -> Article.getTitle().length()))
				.collect(Collectors.toList());
	}
	public List<Article> getAuthorWithShortestName(List<Article> data){
		return data
				.stream()
				//.filter(Article -> Article.getAuthor().length() <=)
				.sorted(Comparator.comparing(Article -> Article.getAuthor()))
				.collect(Collectors.toList());

	}
	public void getAllURLsInOneList(ParallelDownloader parallelDownloader) throws IOException,
			NewsAnalyzerException {
		System.out.println("Download in one List");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.technology)
				.createNewsApi();

		NewsReponse newsReponse = newsApi.getNews();

		List<Article> articles = newsReponse.getArticles();
		System.out.println(articles.size());
		List<String> urls = articles.stream()
				.map(Article::getUrl)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

		SequentialDownloader sequentialDownloader = new SequentialDownloader();
		System.out.println(urls.size());
		sequentialDownloader.process(urls);



	}

	public void getDownloadLastSearch(Downloader downloader) throws IOException, NewsAnalyzerException {
		System.out.println("Download Last Search");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.health)
				.createNewsApi();

		NewsReponse newsReponse = newsApi.getNews();

		if (newsReponse != null){
			List<Article> articles = newsReponse.getArticles();

			var urls = articles
					.stream()
					.map(Article::getUrl)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());

			long timer = System.currentTimeMillis();
			downloader.process(urls);

			if (downloader instanceof ParallelDownloader ){
				System.out.println("Paralelldownloader Time: "+ (System.currentTimeMillis() - timer));
			}
			else if (downloader instanceof SequentialDownloader){
				System.out.println("SequenceDownloader: " + (System.currentTimeMillis() -timer));
			}

			urls.forEach(System.out::println);



		}
	}



	

	public Object getData(NewsApi newsApi) throws NewsAnalyzerException, IOException {

		return newsApi.getNews();
	}



}
