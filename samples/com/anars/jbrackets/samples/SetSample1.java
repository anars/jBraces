package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class SetSample1
{
  public SetSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    templateProcessor.putValueObject("hero", "Batman");
    String template = "{set:hero}Superman{/set:hero}My childhood hero is {get:hero}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new SetSample1();
  }
}
