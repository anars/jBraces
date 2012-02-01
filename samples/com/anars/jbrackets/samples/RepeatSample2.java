package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class RepeatSample2
{
  public RepeatSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Random 6 lottery numbers : {repeat:lottery:6} " +
                      "{random-number:1:49}{/repeat:lottery}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new RepeatSample2();
  }
}
