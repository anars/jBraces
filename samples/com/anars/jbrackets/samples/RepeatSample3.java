package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class RepeatSample3
{
  public RepeatSample3()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Threw 3 dice : {repeat:dice:3}\nDie " +
                      "{get:dice.-offset} is {random-number:1:6}{/repeat:dice}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new RepeatSample3();
  }
}
