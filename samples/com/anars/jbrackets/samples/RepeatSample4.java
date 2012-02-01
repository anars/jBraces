package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class RepeatSample4
{
  public RepeatSample4()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Threw bunch of random dice : {repeat:dice:3:9}\n" +
                      "Die {get:dice.-offset} of {get:dice.-length} " +
                      "is {random-number:1:6}{/repeat:dice}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new RepeatSample4();
  }
}
