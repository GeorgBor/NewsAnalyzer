package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.enums.Endpoint;
import newsapi.enums.Category;
import newsapi.enums.Country;

public class Controller {

	public static final String APIKEY = "1742bdaea27f46f4a303693c5327ae64";

	public void process() {
		System.out.println("Start process");

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
