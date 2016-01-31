package pl.parser.nbp.xml.entity;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Craig on 1/27/2016.
 */
public class NumberAdapter extends XmlAdapter<String, Number> {

    @Override
    public String marshal(Number v) throws Exception {
        return v.toString();
    }

    @Override
    public Number unmarshal(String v) throws Exception {
        String formattedNumber = NumberFormat.getNumberInstance(Locale.FRANCE).parse(v).toString();
        return new BigDecimal(formattedNumber);

    }
}
