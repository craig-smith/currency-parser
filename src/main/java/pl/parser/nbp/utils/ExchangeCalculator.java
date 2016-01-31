package pl.parser.nbp.utils;

import org.apache.log4j.Logger;
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

    static Logger log = Logger.getLogger(ExchangeCalculator.class);

    public BigDecimal calculateBuyRateAverage(List<? extends BuyRateInterface> buyRateList) {
        log.debug("Calculating buy rate average");
        List<BigDecimal> numbers = getNumberListFromBuyRate(buyRateList);
        return calculateAverage(numbers);
    }

    public BigDecimal calculateSellRateAverage(List<? extends SellRateInterface> sellRateList) {
        log.debug("Calculating sell rate average");
        List<BigDecimal> numbers = getNumberListFromSellRate(sellRateList);
        return calculateAverage(numbers);
    }

    /**
     * Do not use this value for currency manipulation. BigDecimal precision could be lost
     *
     * @param buyRateList
     * @return
     */
    public BigDecimal calculateBuyRateStandardDeviation(List<? extends BuyRateInterface> buyRateList) {
        log.debug("Calculating buy rate standard deviation");
        List<BigDecimal> numbers = getNumberListFromBuyRate(buyRateList);
        return calculateStandardDeviation(numbers);
    }

    /**
     * Do not use this value for currency manipulation. BigDecimal precision could be lost
     *
     * @param sellRateList
     * @return
     */
    public BigDecimal calculateSellRateStandardDeviation(List<? extends SellRateInterface> sellRateList) {
        log.debug("Calculating sell rate standard deviation");
        List<BigDecimal> numbers = getNumberListFromSellRate(sellRateList);
        return calculateStandardDeviation(numbers);
    }

    private List<BigDecimal> getNumberListFromBuyRate(List<? extends BuyRateInterface> buyRateList) {
        log.debug("getting buy rate list");
        List<BigDecimal> numberList = new ArrayList<>();
        for (BuyRateInterface buyRate : buyRateList) {
            numberList.add(buyRate.getBuyRate());
        }
        log.debug("buy rate list size: " + numberList.size());
        return numberList;
    }

    private List<BigDecimal> getNumberListFromSellRate(List<? extends SellRateInterface> sellRateList) {
        log.debug("getting sell rate list");
        List<BigDecimal> numberList = new ArrayList<>();
        for (SellRateInterface sellRate : sellRateList) {
            numberList.add(sellRate.getSellRate());
        }
        log.debug("sell rate list size: " + numberList.size());
        return numberList;
    }

    private BigDecimal calculateAverage(List<BigDecimal> numbers) {
        log.debug("calculating average");
        BigDecimal sum = new BigDecimal(0);
        BigDecimal count = new BigDecimal(numbers.size());

        for (BigDecimal num : numbers) {
            sum = sum.add(num);
        }
        BigDecimal average = sum.divide(count, MathContext.DECIMAL128);
        log.debug("average is: " + average.toPlainString());
        return average;
    }

    private BigDecimal calculateStandardDeviation(List<BigDecimal> numbers) {
        log.debug("calculating standard deviation");
        BigDecimal mean = calculateAverage(numbers);
        BigDecimal variance = calculateVariance(numbers, mean);
        BigDecimal standardDeviation = new BigDecimal(Math.sqrt(variance.doubleValue())); //BigDecimal precision lost here....
        log.debug("standard deviation is: " + standardDeviation.toPlainString());
        return standardDeviation;
    }

    private BigDecimal calculateVariance(List<BigDecimal> numbers, BigDecimal mean) {
        log.debug("calculating variance");
        List<BigDecimal> varian = new ArrayList<>();

        for (BigDecimal num : numbers) {
            BigDecimal num2 = num.subtract(mean);
            BigDecimal power = num2.multiply(num2);
            varian.add(power);
        }
        BigDecimal variance = calculateAverage(varian);
        log.debug("variance is: " + variance.toPlainString());
        return variance;
    }

}
