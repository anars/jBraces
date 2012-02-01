package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class DateSample1
{
  public DateSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Today is {date:EEEE}, {date:MMMM} " +
                      "{date:d}, {date:yyyy}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new DateSample1();
  }
}
