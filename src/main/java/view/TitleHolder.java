package view;

public class TitleHolder {
    private String title;
    private Boolean isViewer;
    private final static TitleHolder INSTANCE = new TitleHolder();

    private TitleHolder() {
    }

    public static TitleHolder getInstance() {
        return INSTANCE;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public String getTitle() {
        return this.title;
    }

    public void setIsViewer(boolean b) {
        this.isViewer = b;
    }

    public Boolean getIsViewer() {
        return this.isViewer;
    }
}
