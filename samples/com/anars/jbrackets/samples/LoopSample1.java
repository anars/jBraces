package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class LoopSample1
{
  public LoopSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String[] favoriteLanguages = { "C", "C++", "Java", "Scala", "Python", "PHP" };
    templateProcessor.putValueObject("languages", favoriteLanguages);
    String template = "Each represents a programming language in the list :" +
                      "{loop:languages} X{/loop:languages}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new LoopSample1();
  }
}
