package view;

public class TitleHolder {
    private String title;
   private final static TitleHolder INSTANCE = new TitleHolder();

    private TitleHolder() {}

    public static TitleHolder getInstance(){
        return INSTANCE;
    }

    public void setTitle(String t){
        this.title = t;
    }
    public String getTitle(){
        return this.title;
    }
}
