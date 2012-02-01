package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class TimeSample3
{
  public TimeSample3()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Current timezone is {time:zzzz}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new TimeSample3();
  }
}
