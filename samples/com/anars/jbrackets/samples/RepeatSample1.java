package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class RepeatSample1
{
  public RepeatSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "It is really funny {repeat:laugh:5}Ha{/repeat:laugh}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new RepeatSample1();
  }
}
