package pl.parser.nbp.xml.entity;

import java.util.List;

/**
 * Created by Craig on 1/30/2016.
 */
public interface CurrencyTableIface {

    public List<TypeCTable> getCompleteCurrencyList();

    public List<TypeCTable> getFilteredCurrencyList(CurrencyCode currencyCode);

}
