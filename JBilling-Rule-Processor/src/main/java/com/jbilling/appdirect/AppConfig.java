package com.jbilling.appdirect;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jbilling.appdirect.processor.IRuleProcessor;
import com.jbilling.appdirect.processor.decorator.impl.AverageCalculatorRuleProcessorDecorator;
import com.jbilling.appdirect.processor.decorator.impl.ListModifierRuleProcessorDecorator;
import com.jbilling.appdirect.processor.decorator.impl.PercentageCalculatorRuleProcessorDecorator;
import com.jbilling.appdirect.processor.factory.RuleProcessorFactory;

@Configuration
@ComponentScan(basePackages = {"com.jbilling.appdirect"})
public class AppConfig {

	@Bean
	public RuleProcessorFactory ruleProcessorFactory() {
		RuleProcessorFactory factory = new RuleProcessorFactory();
		factory.setRuleProcessorMap(ruleProcessorMap());
		return factory;
	}
	
	public Map<String, IRuleProcessor> ruleProcessorMap() {
		Map<String, IRuleProcessor> map = new HashMap<String, IRuleProcessor>();
		map.put("PERCENTAGE_CALCULATOR", percentageCalculatorBean());
		map.put("LIST_MODIFIER", listModifierBean());
		map.put("AVERAGE_CALCULATOR", averageCalculatorBean());
		return map;
	}

	@Bean
	public IRuleProcessor averageCalculatorBean() {
		return new AverageCalculatorRuleProcessorDecorator();
	}

	@Bean
	public IRuleProcessor listModifierBean() {
		return new ListModifierRuleProcessorDecorator();
	}

	@Bean
	public IRuleProcessor percentageCalculatorBean() {
		return new PercentageCalculatorRuleProcessorDecorator();
	}
	
	/*@Bean
	public RuleProcessorFactory ruleProcessorFactory() {
		return new RuleProcessorFactory();
	}
	
	@Bean
	public IRuleProcessorService ruleProcessorService(){
		return new RuleProcessorServiceImpl();
	}
	
	@Bean
	public IRuleProcessor ruleProcessor(){
		return new RuleProcessor();
	}*/
}
