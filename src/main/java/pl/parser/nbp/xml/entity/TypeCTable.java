package pl.parser.nbp.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

/**
 * Created by Craig on 1/24/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RootTable")
//@XmlType(propOrder = {"currency", "exchangeMultiple", "currencyCode", "buyRate"})
public class TypeCTable implements BuyRateInterface, SellRateInterface {

    @XmlElement(name = "nazwa_waluty")
    private String currencyName;

    @XmlElement(name = "kod_waluty")
    private String currencyCode;

    @XmlJavaTypeAdapter(NumberAdapter.class)
    @XmlElement(name = "kurs_kupna")
    private BigDecimal buyRate;

    @XmlJavaTypeAdapter(NumberAdapter.class)
    @XmlElement(name = "przelicznik")
    private BigDecimal exchangeMultiple;

    @XmlJavaTypeAdapter(NumberAdapter.class)
    @XmlElement(name = "kurs_sprzedazy")
    private BigDecimal sellRate;

    public String getCurrencyName() {
        return CurrencyCode.getNameByCode(getCurrencyCode());
    }

    public void setCurrency(String currency) {
        this.currencyName = currency;
    }


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    public BigDecimal getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(BigDecimal buyRate) {
        this.buyRate = buyRate;
    }


    public BigDecimal getExchangeMultiple() {
        return exchangeMultiple;
    }

    public void setExchangeMultiple(BigDecimal exchangeMultiple) {
        this.exchangeMultiple = exchangeMultiple;
    }

    public BigDecimal getSellRate() {
        return sellRate;
    }

    public void setSellRate(BigDecimal sellRate) {
        this.sellRate = sellRate;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Currency Name: " + getCurrencyName() + "\r\n");
        stringBuilder.append("Currency Code: " + getCurrencyCode() + "\r\n");
        stringBuilder.append("Buy Rate: " + getBuyRate() + "\r\n");
        stringBuilder.append("Sell Rate: " + getSellRate() + "\r\n");
        stringBuilder.append("Exchange Multiple: " + getExchangeMultiple() + "\r\n");
        return stringBuilder.toString();
    }
}
