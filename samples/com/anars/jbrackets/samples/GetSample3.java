package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class GetSample3
{
  public GetSample3()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String[] favoriteLanguages = { "C", "C++", "Java", "Scala", "Python", "PHP" };
    templateProcessor.putValueObject("languages", favoriteLanguages);
    String template = "Our third favorite programming language is {get:languages[3]}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new GetSample3();
  }
}
