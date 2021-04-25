package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;

import java.io.IOException;
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
				.sorted(Comparator.comparing(Article -> Article.getAuthor().length()))
				.collect(Collectors.toList());

	}
	

	public Object getData(NewsApi newsApi) throws NewsAnalyzerException, IOException {

		return newsApi.getNews();
	}
}
