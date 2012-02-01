package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class TimeSample1
{
  public TimeSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "The time is {time:h}:{time:m} {time:a}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new TimeSample1();
  }
}
