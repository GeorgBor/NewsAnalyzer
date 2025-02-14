package newsanalyzer.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import newsanalyzer.ctrl.Controller;
import newsanalyzer.ctrl.NewsAnalyzerException;
import newsanalyzer.downloader.Downloader;
import newsanalyzer.downloader.ParallelDownloader;
import newsanalyzer.downloader.SequentialDownloader;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Endpoint;
import newsapi.enums.Country;
import newsapi.enums.Category;

import static newsanalyzer.ctrl.Controller.APIKEY;

public class UserInterface 
{

	public static final String apiKey = "1742bdaea27f46f4a303693c5327ae64";

	private Controller ctrl = new Controller();

	private Downloader seqDown = new SequentialDownloader();
	private Downloader parDown = new ParallelDownloader();


	public void getDataFromCtrl1(){
		System.out.println("ABC");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.business)
				.createNewsApi();

		try {
			ctrl.process(newsApi);
		} catch (MalformedURLException e){
			System.out.println("Stimmt wos ned im ABC!");
		} catch (NewsAnalyzerException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void getDataFromCtrl2(){
		System.out.println("DEF");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.health)
				.createNewsApi();

		try {
			ctrl.process(newsApi);
		} catch (MalformedURLException e){
			System.out.println("Stimmt wos ned im DEF!");
		} catch (NewsAnalyzerException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void getDataFromCtrl3(){
		System.out.println("3");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.technology)
				.createNewsApi();

		try {
			ctrl.process(newsApi);
		} catch (MalformedURLException e){
			System.out.println("Stimmt wos ned im 3!");
		} catch (NewsAnalyzerException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	
	public void getDataForCustomInput() {
		System.out.println("Input choice!");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("USA")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.us)
				.setSourceCategory(Category.business)
				.createNewsApi();

		try {
			ctrl.process(newsApi);
		} catch (MalformedURLException e){
			System.out.println("Stimmt wos ned by your Choise!");
		} catch (NewsAnalyzerException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}


		
	}
	public void downloadLastSearch(){

		try {
			ctrl.getDownloadLastSearch(seqDown);
		} catch (MalformedURLException e){
			System.out.println("Stimmt wos ned by your Choise!");
		} catch (NewsAnalyzerException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	public void urlInOneList(){

		try {
			ctrl.getAllURLsInOneList((ParallelDownloader) parDown);
		} catch (MalformedURLException e){
			System.out.println("Stimmt wos ned by your Choise!");
		} catch (NewsAnalyzerException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice ABC", this::getDataFromCtrl1);
		menu.insert("b", "Choice DEF", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("s","Download Last Search", this::downloadLastSearch);
		menu.insert("l","URL in one List", this::urlInOneList);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
