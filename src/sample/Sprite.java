package sample;

public class Sprite {
    private String name;
    private String text;

    public Sprite (String names, String texts){
        name = names;
        text = texts;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
