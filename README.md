# JBillingAppDirect

Steps to build :  Build in following order
  1. Build JBilling-dto
  2. Build JBilling-Rule-Processor
  3. Build JBilling

## Individual project details
  ### JBilling-dto
    Contains all the pojos and entities for main projects
    
  ### JBilling-Rule-Processor
    Module that contains main logic to process all the rules configured for given client in database in json form. This project creates a chain of decorators for every rule and executes them in the order they are defined in json rule. This takes rule json and list of prices as input.
    
  ### JBilling
    This module contains all the rest apis related to product, store, pricing and to trigger ideal price calculator scheduler. This module also has a scheduler that runs at a given interval of time and calculates ideal price for every product id. This takes JBilling-Rule-Processor and JBilling-dto as dependecy.

## JSON Rules
  This product runs on json rules defined for given deployment. Rules are defined in json format and saved in db. A scheduler keeps on running and fetchies the rules from db and puts it up in cache. This helps in dynamically changing rules for a given client. Currently, 3 rules are defined as below:  
  * RuleKey - LIST_MODIFIER, ,
    Sample Input - 1,-1	  
    Explanation - This accepts maximum of 2 and min 1 vaue. +ve means trimming list from head, -ve means trimming from end
 
  * RuleKey - AVERAGE_CALCULATOR	  
    Sample Input - NA	    
    Explanation - This calculates average of given list of integers


  * RuleKey - PERCENTAGE_CALCULATOR	
    Sample Input - 10/-10 	
    Explanation - This calculates percentage of given value and adds or subtracts it.

## Steps to add new rules
  * Create a new key like given above eg; DUMMY_RULE
  * Create its decorator class that extends IRuleProcessorDecorator under package com.jbilling.appdirect.processor.decorator.impl of prject JBIlling-Rule-Processor eg; DUMMY_RULE_DECORATOR
  * Change config class' rulePRocessorMap and add your entry
    map.put("DUMMY_RULE", new DUMMY_RULE_DECORATOR());

## Scripts SQL 
  Execute scripts.sql before executing the JBilling project.
  
## Executing project
  The main project is JBilling which is spring boot based. After building projects in the above given order, run JBilling project.

## Running JUnit
  Junit test cases can be executed for JBilling and JBilling-Rule-Processor projects by executing below command in their respective           directories.
  mvn test

## Code coverage
  Code coverage of JBilling and JBilling-Rule-Processor projects can be seen after running "maven test" command. Code coverage index pages   for respective projects is available at respective project's "target/jacoco-ut/index.html"
  JBilling-Rule-Processor/target/jacoco-ut/index.html and 
  JBilling/target/jacoco-ut/index.html
