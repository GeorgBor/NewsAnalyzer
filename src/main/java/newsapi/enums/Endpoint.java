package newsapi.enums;

public enum Endpoint {
    TOP_HEADLINES("top-headlines"),

    EVERYTHING("everything"), TOPHEADLINES("TopHeadlines");

    private String endPoint;

    Endpoint(String endPoint){
        this.endPoint = endPoint;
    }

    public String getValue() {
        return endPoint;
    }
}
