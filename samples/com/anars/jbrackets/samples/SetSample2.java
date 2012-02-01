package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class SetSample2
{
  public SetSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "{set:hero}{draw:Superman:Batman}{/set:hero}Hmm, {get:hero} " +
                      "is my childhood hero. {get:hero} always fight against bad people.";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new SetSample2();
  }
}
