package tn.esprit.androidproject;

public class Option {
    private String title;
    private int icon;

    public Option(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }
}