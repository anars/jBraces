package com.anars.jbrackets;

import java.io.File;

import java.net.URL;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class RunAllSamples
{
  public RunAllSamples()
  {
    try
      {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String rootPackageName = "com.anars.jbrackets.samples";
        String path = rootPackageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> directories = new ArrayList<File>();
        while (resources.hasMoreElements())
          directories.add(new File(resources.nextElement().getFile()));
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : directories)
          classes.addAll(findClasses(directory, rootPackageName));
        for (Class formatterClass : classes)
          {
            System.out.println("\n" + formatterClass.getName());
            formatterClass.newInstance();
          }
      }
    catch (Exception exception)
      {
        exception.printStackTrace();
      }
  }

  public static void main(String[] args)
  {
    new RunAllSamples();
  }

  private List<Class> findClasses(File directory, String packageName)
    throws ClassNotFoundException
  {
    List<Class> classList = new ArrayList<Class>();
    if (!directory.exists())
      return classList;
    File[] files = directory.listFiles();
    for (File file : files)
      if (file.isDirectory())
        {
          if (packageName.startsWith("."))
            packageName = packageName.substring(1);
          classList.addAll(findClasses(file, packageName + "." + file.getName()));
        }
      else if (file.getName().endsWith(".class"))
        {
          Class cls = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
          classList.add(cls);
        }
    return (classList);
  }
}
