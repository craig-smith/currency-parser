package pl.parser.nbp.utils;

import org.springframework.stereotype.Component;
import pl.parser.nbp.xml.entity.BuyRateInterface;
import pl.parser.nbp.xml.entity.SellRateInterface;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Craig on 1/30/2016.
 */
@Component
public class ExchangeCalculator {

    public BigDecimal calculateBuyRateAverage(List<? extends BuyRateInterface> buyRateList) {
        List<BigDecimal> numbers = getNumberListFromBuyRate(buyRateList);
        return calculateAverage(numbers);
    }

    public BigDecimal calculateSellRateAverage(List<? extends SellRateInterface> sellRateList) {
        List<BigDecimal> numbers = getNumberListFromSellRate(sellRateList);
        return calculateAverage(numbers);
    }

    /**
     * Do not use this value for currency manipulation. BigDecimal precision could be lost
     * @param buyRateList
     * @return
     */
    public BigDecimal calculateBuyRateStandardDeviation(List<? extends BuyRateInterface> buyRateList){
        List<BigDecimal> numbers = getNumberListFromBuyRate(buyRateList);
        return calculateStandardDeviation(numbers);
    }

    /**
     * Do not use this value for currency manipulation. BigDecimal precision could be lost
     * @param sellRateList
     * @return
     */
    public BigDecimal calculateSellRateStandardDeviation(List<? extends SellRateInterface> sellRateList){
        List<BigDecimal> numbers = getNumberListFromSellRate(sellRateList);
        return calculateStandardDeviation(numbers);
    }

    private List<BigDecimal> getNumberListFromBuyRate(List<? extends BuyRateInterface> buyRateList) {
        List<BigDecimal> numberList = new ArrayList<>();
        for (BuyRateInterface buyRate : buyRateList) {
            numberList.add(buyRate.getBuyRate());
        }
        return numberList;
    }

    private List<BigDecimal> getNumberListFromSellRate(List<? extends SellRateInterface> sellRateList) {
        List<BigDecimal> numberList = new ArrayList<>();
        for (SellRateInterface sellRate : sellRateList) {
            numberList.add(sellRate.getSellRate());
        }
        return numberList;
    }

    private BigDecimal calculateAverage(List<BigDecimal> numbers) {
        BigDecimal sum = new BigDecimal(0);
        BigDecimal count = new BigDecimal(numbers.size());

        for (BigDecimal num : numbers) {
            sum = sum.add(num);
        }

        return sum.divide(count, MathContext.DECIMAL128);
    }

    private BigDecimal calculateStandardDeviation(List<BigDecimal> numbers){
        BigDecimal mean = calculateAverage(numbers);
        BigDecimal variance = calculateVariance(numbers, mean);
        return new BigDecimal(Math.sqrt(variance.doubleValue())); //BigDecimal precision lost here....
    }

    private BigDecimal calculateVariance(List<BigDecimal> numbers, BigDecimal mean){
        List<BigDecimal> varian = new ArrayList<>();

        for(BigDecimal num : numbers){
            BigDecimal num2 = num.subtract(mean);
            BigDecimal power = num2.multiply(num2);
            varian.add(power);
        }
        return calculateAverage(varian);
    }

}
