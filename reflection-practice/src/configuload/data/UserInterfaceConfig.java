package configuload.data;

public class UserInterfaceConfig {
    private String titleColor;
    private String titleText;
    private int titleFontSize;
    private int footerFontSize;

    public String getTitleColor() {
        return titleColor;
    }

    public String getTitleText() {
        return titleText;
    }

    public int getTitleFontSize() {
        return titleFontSize;
    }

    public int getFooterFontSize() {
        return footerFontSize;
    }

    @Override
    public String toString() {
        return "UserInterfaceConfig{" +
                "titleColor='" + titleColor + '\'' +
                ", titleText='" + titleText + '\'' +
                ", titleFontSize=" + titleFontSize +
                ", footerFontSize=" + footerFontSize +
                '}';
    }
}
