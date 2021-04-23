package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "1742bdaea27f46f4a303693c5327ae64";

	public void process(NewsApi news) throws IOException, NewsAnalyzerException {
		System.out.println("Start process");

		NewsReponse reponse = news.getNews();
		if (reponse != null){
			List<Article> articles = reponse.getArticles();

			articles.stream().forEach(article -> System.out.println(articles.toString()));


			System.out.println("Shortest Article: "+getNumberOfArcivel(articles));
			System.out.println("Provider with most Article: "+getNumberOfArcivel(articles));
			System.out.println("Author with shortest Names: "+getTitlelessSort(articles));
			System.out.println("Longest Title: "+ getMostFreqeunt(articles));
		}





		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		System.out.println("End process");
	}
	public String getMostFreqeunt(List<Article> data){
		return data
				.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet()
				.stream()
				.max(Comparator.comparing(Map.Entry::getValue)).get().getKey().getSource().getName();


	}
	public long getNumberOfArcivel(List<Article> data){
		return data.stream().count();
	}

	public List<Article> getTitlelessSort(List<Article> data){
		return data
				.stream()
				.sorted(Comparator.comparingInt(Article -> Article.getTitle().length()))
				.collect(Collectors.toList());
	}
	

	public Object getData(NewsApi newsApi) throws NewsAnalyzerException, IOException {

		return newsApi.getNews();
	}
}
