package pl.parser.nbp.xml.entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

/**
 * Created by Craig on 1/24/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RootTable")
//@XmlType(propOrder = {"currency", "exchangeMultiple", "currencyCode", "buyRate"})
public class TypeCTable {

    @XmlElement(name = "nazwa_waluty")
    private String currency;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
}
