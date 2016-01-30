package pl.parser.nbp.xml.entity;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Craig on 1/24/2016.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tabela_kursow")
public class RootTable implements CurrencyTableIface {



    @XmlElement(name = "pozycja")
    private List<TypeCTable> currencyListings;

    @XmlElement(name = "data_publikacji")
    private Date tableDate;

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


    public Date getTableDate() {
        return tableDate;
    }

    public void setTableDate(Date tableDate) {
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
