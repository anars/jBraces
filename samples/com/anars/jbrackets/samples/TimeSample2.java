package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class TimeSample2
{
  public TimeSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Time in military notation is {time:kk}{time:mm}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new TimeSample2();
  }
}
