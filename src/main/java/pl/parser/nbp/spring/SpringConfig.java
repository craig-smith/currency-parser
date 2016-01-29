package pl.parser.nbp.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Craig on 1/28/2016.
 */
@Configuration
@ComponentScan({"pl.parser.nbp.exchangerate.factory", "pl.parser.nbp.http, pl.parser.nbp.xml.entity"})
public class SpringConfig {
}
