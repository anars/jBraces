package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class LoremIpsumSample1
{
  public LoremIpsumSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Here is placeholder text: {lorem-ipsum:2:5}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] arg)
  {
    new LoremIpsumSample1();
  }
}
