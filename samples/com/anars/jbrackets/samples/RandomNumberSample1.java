package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class RandomNumberSample1
{
  public RandomNumberSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Your lucky number is {random-number:1:100}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] arg)
  {
    new RandomNumberSample1();
  }
}
