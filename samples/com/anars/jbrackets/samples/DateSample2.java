package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class DateSample2
{
  public DateSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "The day is '{date:EEEE:TR}' in Turkish, " +
                      "'{date:EEEE:DE}' in German and '{date:EEEE:FR}' in French.";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new DateSample2();
  }
}
