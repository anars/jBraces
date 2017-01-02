/**
 * Copyright (c) 2012-2017 Anar Software LLC <http://anars.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is furnished 
 * to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.anars.jbraces.samples;

import java.io.File;

import java.net.URL;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AllFormatterSamples {

    public AllFormatterSamples() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String rootPackageName = "com.anars.jbraces.samples.formatters";
            String path = rootPackageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> directories = new ArrayList<File>();
            while(resources.hasMoreElements())
                directories.add(new File(resources.nextElement().getFile()));
            ArrayList<Class> classes = new ArrayList<Class>();
            for(File directory : directories)
                classes.addAll(findClasses(directory, rootPackageName));
            for(Class formatterClass : classes) {
                System.out.println("\n" + formatterClass.getName());
                formatterClass.newInstance();
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new AllFormatterSamples();
    }
    private List<Class> findClasses(File directory, String packageName)
        throws ClassNotFoundException {
        List<Class> classList = new ArrayList<Class>();
        if(!directory.exists())
            return classList;
        File[] files = directory.listFiles();
        for(File file : files)
            if(file.isDirectory()) {
                if(packageName.startsWith("."))
                    packageName = packageName.substring(1);
                classList.addAll(findClasses(file, packageName + "." + file.getName()));
            }
            else if(file.getName().endsWith(".class")) {
                Class cls = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                classList.add(cls);
            }
        return (classList);
    }
}
