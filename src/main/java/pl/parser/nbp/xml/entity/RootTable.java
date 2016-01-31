package pl.parser.nbp.xml.entity;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Craig on 1/24/2016.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tabela_kursow")
public class RootTable implements CurrencyTableIface {


    @XmlElement(name = "pozycja")
    private List<TypeCTable> currencyListings;

    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlElement(name = "data_publikacji")
    private DateTime tableDate;

    @XmlElement(name = "numer_tabeli")
    private String tableNumber;

    @XmlAttribute(name = "typ")
    private String tableType;

    @XmlAttribute(name = "uid")
    private String tableUID;


    public List<TypeCTable> getCurrencyListings() {
        return currencyListings;
    }

    public void setCurrencyListings(List<TypeCTable> typeCTableList) {
        this.currencyListings = typeCTableList;
    }


    public DateTime getTableDate() {
        return tableDate;
    }

    public void setTableDate(DateTime tableDate) {
        this.tableDate = tableDate;
    }


    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }


    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }


    public String getTableUID() {
        return tableUID;
    }

    public void setTableUID(String tableUID) {
        this.tableUID = tableUID;
    }

    @Override
    public String toString() {
        return "RootTable{" +
                "currencyListings=" + currencyListings.toString() +
                ", tableDate=" + tableDate +
                ", tableNumber='" + tableNumber + '\'' +
                ", tableType='" + tableType + '\'' +
                ", tableUID='" + tableUID + '\'' +
                '}';
    }

    @Override
    public List<TypeCTable> getCompleteCurrencyList() {
        return currencyListings;
    }

    @Override
    public List<TypeCTable> getFilteredCurrencyList(CurrencyCode currencyCode) {
        List<TypeCTable> filteredCurrencyList = new ArrayList<>();
        for (TypeCTable currencyListing : currencyListings) {
            if (currencyListing.getCurrencyCode().equals(currencyCode.getCode())) {
                filteredCurrencyList.add(currencyListing);
            }
        }

        return filteredCurrencyList;
    }
}
