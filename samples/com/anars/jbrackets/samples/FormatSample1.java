package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class FormatSample1
{
  public FormatSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    templateProcessor.loadAllSpanFormatters();
    String template = "Price of an average house in London is {format:FormatCurrency:en:gb}398476{/format:FormatCurrency}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new FormatSample1();
  }
}
