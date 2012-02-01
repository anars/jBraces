package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class GetSample4
{
  public GetSample4()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String[] favoriteLanguages = { "C", "C++", "Java", "Scala", "Python", "PHP" };
    templateProcessor.putValueObject("languages", favoriteLanguages);
    String template = "There are {get:languages.-length} favorite programming languages";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new GetSample4();
  }
}
