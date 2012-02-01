package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

import java.awt.geom.Point2D;

public class GetSample2
{
  public GetSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    Point2D.Double coordinates = new Point2D.Double(40.75773, -73.985708);
    templateProcessor.putValueObject("TimesSqCoordinates", coordinates);
    String template = "The famous Times Square coordinates : " +
                      "latitude={get:TimesSqCoordinates.x}, " +
                      "longitude={get:TimesSqCoordinates.y}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new GetSample2();
  }
}
