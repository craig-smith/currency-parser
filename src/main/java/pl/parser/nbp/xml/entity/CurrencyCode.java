package pl.parser.nbp.xml.entity;

/**
 * Created by Craig on 1/29/2016.
 */
public enum CurrencyCode {
    USD("USD", "US Dollar"),
    AUD("AUD", "Australian Dollar"),
    CAD("CAD", "Canadian Dollar"),
    EUR("EUR", "Euro"),
    HUR("HUR", "Hungarian Forint"),
    CHF("CHF", "Swiss Frank"),
    GBP("GBP", "English Pound"),
    JPY("JPY", "Japanese Yen"),
    CZK("CZK", "Czek Corona"),
    DKK("DKK", "Norwegian Crown"),
    SEK("SEK", "Swedish Crown"),
    XDR("XDR", "Special Drawing Rights");


    private final String code;
    private final String name;

    CurrencyCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(String code) {
        switch (code) {
            case "USD":
                return USD.name;
            case "AUD":
                return AUD.name;
            case "CAD":
                return CAD.name;
            case "EUR":
                return EUR.name;
            case "HUR":
                return HUR.name;
            case "CHF":
                return CHF.name;
            case "GBP":
                return GBP.name;
            case "JPY":
                return JPY.name;
            case "CZK":
                return CZK.name;
            case "DKK":
                return DKK.name;
            case "SEK":
                return SEK.name;
            case "XDR":
                return XDR.name;
            default:
                return "Unknown";

        }
    }
}
