package com.anars.jbrackets;

import java.awt.geom.Point2D;

import java.io.File;

public class ApplyToFileSample
{
  public ApplyToFileSample()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    templateProcessor.loadAllSpanFormatters();
    templateProcessor.putValueObject("company", "Anar Software");
    Point2D.Double coordinates = new Point2D.Double(40.75773, -73.985708);
    templateProcessor.putValueObject("TimesSqCoordinates", coordinates);
    String[] favoriteLanguages = { "C", "C++", "Java", "Scala", "Python", "PHP" };
    templateProcessor.putValueObject("languages", favoriteLanguages);
    try
      {
        String output = templateProcessor.apply(new File("samples.template"));
        System.out.println(output);
      }
    catch (Exception exception)
      {
        exception.printStackTrace();
      }
  }

  public static void main(String[] args)
  {
    new ApplyToFileSample();
  }
}
