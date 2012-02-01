package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class PropertySample1
{
  public PropertySample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "The operating system is {property:os.name}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new PropertySample1();
  }
}
