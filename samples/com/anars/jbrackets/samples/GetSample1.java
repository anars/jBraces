package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class GetSample1
{
  public GetSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    templateProcessor.putValueObject("company", "Anar Software");
    String template = "Company name is {get:company}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new GetSample1();
  }
}
