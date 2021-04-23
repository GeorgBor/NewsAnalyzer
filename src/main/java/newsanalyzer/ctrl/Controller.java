package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Endpoint;
import newsapi.enums.Category;
import newsapi.enums.Country;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class Controller {

	public static final String APIKEY = "1742bdaea27f46f4a303693c5327ae64";

	public void process(NewsApi news) throws IOException, NewsAnalyzerException {
		System.out.println("Start process");

		NewsReponse reponse = news.getNews();
		if (reponse != null){
			List<Article> articles = reponse.getArticles();

			articles.stream().forEach(article -> System.out.println(articles.toString()));
		}

		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		/*NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOPHEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.health)
				.createNewsApi();
		*/



		System.out.println("End process");
	}
	public void analyze1(){

	}
	public void analyze2(){

	}
	

	public Object getData() {


		
		return null;
	}
}
