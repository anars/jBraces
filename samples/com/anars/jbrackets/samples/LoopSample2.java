package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class LoopSample2
{
  public LoopSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String[] favoriteLanguages = { "C", "C++", "Java", "Scala", "Python", "PHP" };
    templateProcessor.putValueObject("languages", favoriteLanguages);
    String template = "List of our favorite programming languages :" +
                      "{loop:languages} {get:languages}{/loop:languages}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new LoopSample2();
  }
}
