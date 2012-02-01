package com.anars.jbrackets;

import com.anars.jbrackets.exceptions.ArrayNotFoundException;
import com.anars.jbrackets.exceptions.AttributeNotFoundException;
import com.anars.jbrackets.exceptions.NotArrayException;
import com.anars.jbrackets.exceptions.ObjectNotFoundException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.net.URL;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateProcessor
{
  public static final double VERSION = 0.1;
  public static final double BUILD = 20120131;
  private static final String[] LATIN_WORDS =
  {
    //
    "abduco", "accipio", "acer", "acquiro", "addo", "adduco", "adeo", "adepto", "adfero", "adficio", //
    "adflicto", "adhuc", "aequitas", "aequus", "affero", "affligo", "agnosco", "alo", "altum", "alui", //
    "amo", "amplexus", "amplitudo", "animus", "antepono", "aperio", "arcis", "arguo", "arx", "attero", //
    "attollo", "autem", "autus", "auxilium", "bellus", "Berlinmonte", "bibo", "carcer", "casso", "celer", //
    "celeritas", "celeriter", "censura", "cito", "clarus", "coerceo", "cogito", "cohibeo", "comminuo", //
    "commisceo", "commodum", "concedo", "condico", "conor", "conservo", "consilium", "conspicio", //
    "constituo", "consulo", "consumo", "contineo", "corrumpo", "crebro", "cresco", "cretum", "cum", //
    "cupido", "cursim", "dare", "datum", "decorus", "decretum", "dedi", "defendo", "defero", "defluo", //
    "deinde", "denuncio", "detrimentum", "dexter", "dico", "dignitas", "dimitto", "directus", "diripio", //
    "discedo", "discessum", "discrepo", "do", "donum", "duco", "effero", "egredior", "egressus", "elatum", //
    "eo", "equitas", "equus", "excellentia", "exhibeo", "exibeo", "exigo", "exitus", "expleo", "expletum", //
    "explevi", "expugno", "extuli", "facina", "facio", "facultas", "facundia", "facunditas", "fateor", //
    "fatigo", "fatum", "faveo", "felicis", "felix", "ferus", "festinatio", "fidelis", "flatus", "fors", //
    "fortis", "fortuna", "fortunatus", "frequento", "gaudium", "gens", "grassor", "gregatim", "haesito", //
    "haud", "hesito", "honor", "iacio", "iam", "impedio", "impetus", "inanis", "inritus", "invado", "ire", //
    "irritus", "itum", "iudicium", "iustus", "jaculum", "judicium", "laedo", "laetificus", "laevus", "lamia", //
    "laqueus", "laudo", "ledo", "letificus", "leviculus", "levis", "levo", "levus", "liber", "libera", //
    "libero", "liberum", "macero", "macto", "malum", "maneo", "mansuetus", "maxime", "mereo", "modo", //
    "moris", "mos", "mundus", "narro", "nequaquam", "nimirum", "non", "nota", "novitas", "novus", "numerus", //
    "nunc", "nusquam", "nutrio", "nutus", "obduro", "ocius", "officium", "ordinatio", "pendo", "penus", //
    "percepi", "perceptum", "percipio", "perdo", "perfectus", "perscitus", "pertinax", "pessum", "placide", //
    "placidus", "placitum", "plenus", "plerusque", "plurimus", "poema", "poematis", "praeclarus", "praeda", //
    "praesto", "preclarus", "preda", "prehendo", "presto", "pretium", "priscus", "pristinus", "probo", //
    "proficio", "progredior", "progressus", "prohibeo", "promptus", "prope", "proventus", "pulcher", //
    "pulchra", "pulchrum", "puto", "qualitas", "qualiter", "quasi", "quies", "quietis", "ratio", "recolo", //
    "recordatio", "rectum", "rectus", "reddo", "regina", "rei", "relaxo", "renuntio", "repens", "reperio", //
    "repeto", "repleo", "reprehendo", "requiro", "res", "retineo", "rideo", "risi", "risum", "rubor", "rumor", //
    "salus", "saluto", "salutor", "sanctimonia", "sane", "sanitas", "sapiens", "sapienter", "sceleris", //
    "scelus", "scisco", "securus", "secus", "seditio", "sentio", "sepelio", "servo", "sicut", "silentium", //
    "similis", "singularis", "sino", "siquidem", "solus", "solvo", "somnium", "speciosus", "strenuus", //
    "subiungo", "subvenio", "succurro", "suffragium", "summopere", "super", "suscipio", "tamen", "tamquam", //
    "tanquam", "tectum", "tego", "tendo", "teneo", "tergum", "terra", "tersus", "texi", "totus", "tribuo", //
    "turpis", "universitas", "universum", "us", "usus", "utilis", "utilitas", "valde", "valens", "velociter", //
    "velox", "velut", "venia", "vergo", "videlicet", "video", "vidi", "vindico", "vires", "vis", "visum", //
    "vocis", "voco", "volo", "voluntas", "vomica", "vox"
    //
    } ;
  private static final String LOOP = "loop";
  private static final String SHOW_RANDOMLY = "show-randomly";
  private static final String REPEAT = "repeat";
  private static final String RANDOM_NUMBER = "random-number";
  private static final String DRAW = "draw";
  private static final String DATE = "date";
  private static final String TIME = "time";
  private static final String GET = "get";
  private static final String SET = "set";
  private static final String PROPERTY = "property";
  private static final String LOREM_IPSUM = "lorem-ipsum";
  private static final String FORMAT = "format";
  private static final String IF = "if";
  private Logger _logger = Logger.getLogger(getClass().getCanonicalName());
  private Pattern _pattern = Pattern.compile(
      //
      "\\{" + LOOP + ":(\\w+)(:\\-?\\d+)?\\}.*?\\{/" + LOOP + ":\\1\\}|" + //
      "\\{" + SHOW_RANDOMLY + ":(\\w+)\\}.*?\\{/" + SHOW_RANDOMLY + ":\\3\\}|" + //
      "\\{" + REPEAT + ":(\\w+):\\d+(:\\d+)?\\}.*?\\{/" + REPEAT + ":\\4\\}|" + //
      "\\{" + RANDOM_NUMBER + ":\\-?\\d+:\\-?\\d+\\}|" + //
      "\\{" + DRAW + ":\\w+:\\w+(:\\w+)*\\}|" + //
      "\\{" + DATE + ":[GyMwWDdFE]+(:\\w{2})?\\}|" + //
      "\\{" + TIME + ":[aHkKhmsSzZ]+(:\\w{2})?\\}|" + //
      "\\{" + GET + ":\\w+((\\[\\d+\\])?(\\.\\w+)?|(\\.\\-offset|\\.\\-length|\\.\\-first|\\.\\-last))?\\}|" + //
      "\\{" + PROPERTY + ":[^}]*\\}|" + //
      "\\{" + LOREM_IPSUM + ":\\d+:\\d+\\}|" + //
      "\\{" + SET + ":(\\w+)\\}.*?\\{/" + SET + ":\\13\\}|" + //
      "\\{" + FORMAT + ":(\\w+)(:\\w{2}:\\w{2})?\\}.*?\\{/" + FORMAT + ":\\14\\}|" + //
      "\\{" + IF + ":(\\w+):((\\w+((\\[\\d+\\])?(\\.\\w+)|(\\.\\-offset|\\.\\-length|\\.\\-first|\\.\\-last))?)|([\'][^\']*[\'])):(equals|not-equals|greater-than|greater-than-or-equals|less-than|less-than-or-equals|empty|not-empty|exists|not-exists|even-number|odd-number)(:((\\w+((\\[\\d+\\])?(\\.\\w+)|(\\.\\-offset|\\.\\-length|\\.\\-first|\\.\\-last))?)|([\'][^\']*[\'])))?\\}.*?\\{/" + IF + ":\\16\\}", //
      Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
  private Locale _locale = Locale.getDefault();
  private Hashtable<String, Object> _valueObjects = new Hashtable<String, Object>();
  private Hashtable<String, SpanFormatter> _spanFormatters = new Hashtable<String, SpanFormatter>();

  public TemplateProcessor()
  {
    super();
    putValueObject("jbrackets_version", VERSION);
    putValueObject("jbrackets_build", BUILD);
  }

  private TemplateProcessor(Locale locale, Hashtable<String, Object> valueObjects)
  {
    this();
    _locale = locale;
    _valueObjects = valueObjects;
  }

  public synchronized void setValueObjects(Hashtable<String, Object> valueObjects)
  {
    _valueObjects = valueObjects;
  }

  public synchronized Hashtable<String, Object> getValueObjects()
  {
    return (_valueObjects);
  }

  public synchronized void clearValueObjects()
  {
    _valueObjects.clear();
  }

  public synchronized Object putValueObject(String name, Object valueObject)
  {
    return (valueObject != null ? _valueObjects.put(name.toLowerCase(), valueObject) : null);
  }

  public synchronized Object getValueObject(String name)
  {
    return (_valueObjects.get(name.toLowerCase()));
  }

  public synchronized Object removeValueObject(String name)
  {
    return (_valueObjects.remove(name.toLowerCase()));
  }

  public synchronized boolean hasAnyValueObjects()
  {
    return (!_valueObjects.isEmpty());
  }

  public synchronized int valueObjectCount()
  {
    return (_valueObjects.size());
  }

  public synchronized boolean containsValueObjectName(String name)
  {
    return (_valueObjects.containsKey(name.toLowerCase()));
  }

  public synchronized boolean containsValueObject(Object valueObject)
  {
    return (_valueObjects.containsValue(valueObject));
  }

  public synchronized Enumeration<String> valueObjectNames()
  {
    return (_valueObjects.keys());
  }

  public synchronized Collection<Object> valueObjects()
  {
    return (_valueObjects.values());
  }

  public synchronized void clearSpanFormatters()
  {
    _spanFormatters.clear();
  }

  public synchronized SpanFormatter putSpanFormatter(String name, SpanFormatter spanFormatter)
  {
    return (_spanFormatters.put(name, spanFormatter));
  }

  public synchronized SpanFormatter getSpanFormatter(String name)
  {
    return (_spanFormatters.get(name));
  }

  public synchronized SpanFormatter removeSpanFormatter(String name)
  {
    return (_spanFormatters.remove(name));
  }

  public synchronized boolean hasAnySpanFormatters()
  {
    return (!_spanFormatters.isEmpty());
  }

  public synchronized int spanFormatterCount()
  {
    return (_spanFormatters.size());
  }

  public synchronized boolean containsSpanFormatterName(String name)
  {
    return (_spanFormatters.containsKey(name));
  }

  public synchronized boolean containsSpanFormatter(SpanFormatter spanFormatter)
  {
    return (_spanFormatters.containsValue(spanFormatter));
  }

  public synchronized Enumeration<String> spanFormatterNames()
  {
    return (_spanFormatters.keys());
  }

  public synchronized Collection<SpanFormatter> spanFormatters()
  {
    return (_spanFormatters.values());
  }

  public void setLocale(Locale locale)
  {
    _locale = locale;
  }

  public Locale getLocale()
  {
    return (_locale);
  }

  public String apply(File templateFile)
    throws IOException, FileNotFoundException
  {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(templateFile));
    StringBuffer stringBuffer = new StringBuffer();
    String lineSeparator = System.getProperty("line.separator");
    try
      {
        String lineString = bufferedReader.readLine();
        while (lineString != null)
          {
            stringBuffer.append(lineString);
            stringBuffer.append(lineSeparator);
            lineString = bufferedReader.readLine();
          }
      }
    finally
      {
        bufferedReader.close();
      }
    return (apply(stringBuffer.toString()));
  }

  public String apply(String templateString)
  {
    Matcher matcher = _pattern.matcher(templateString);
    StringBuffer stringBuffer = new StringBuffer();
    while (matcher.find())
      {
        String matchedString = substring(matcher.group(), "{", "}", false);
        String replacement = null;
        String[] pieces = matchedString.split(":");
        if (pieces[0].equals(RANDOM_NUMBER))
          {
            try
              {
                int startNumber = Integer.parseInt(pieces[1]);
                replacement = "" + ((int) (Math.random() * (Integer.parseInt(pieces[2]) - startNumber + 1)) + startNumber);
              }
            catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
              }
          }
        else if (pieces[0].equals(LOREM_IPSUM))
          {
            int minSentences = 1;
            int maxSentences = 1;
            try
              {
                minSentences = Integer.parseInt(pieces[1]);
              }
            catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
              }
            try
              {
                maxSentences = Integer.parseInt(pieces[2]);
              }
            catch (Exception exception)
              {
                maxSentences = minSentences;
                _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
              }
            int sentences = (int) (Math.random() * (maxSentences - minSentences + 1)) + minSentences;
            StringBuffer stringBufferParagraph = new StringBuffer("Lorem ipsum");
            for (int sentenceIndex = 0; sentenceIndex < sentences; sentenceIndex++)
              {
                int wordCount = (int) (Math.random() * 6) + 3;
                for (int wordIndex = 0; wordIndex < wordCount; wordIndex++)
                  {
                    String word = LATIN_WORDS[(int) (Math.random() * LATIN_WORDS.length)];
                    if (wordIndex == 0 && sentenceIndex != 0)
                      stringBufferParagraph.append(" " + word.substring(0, 1).toUpperCase() + word.substring(1));
                    else
                      stringBufferParagraph.append(" " + word);
                  }
                switch ((int) Math.random() * 10)
                  {
                    case 8:
                      stringBufferParagraph.append("!");
                      break;
                    case 9:
                      stringBufferParagraph.append("?");
                      break;
                    default:
                      stringBufferParagraph.append(".");
                      break;
                  }
              }
            replacement = stringBufferParagraph.toString();
          }
        else if (pieces[0].equals(DATE) || pieces[0].equals(TIME))
          {
            Locale locale = _locale;
            if (pieces.length == 3)
              locale = new Locale(pieces[2]);
            try
              {
                replacement = (new SimpleDateFormat(pieces[1], locale)).format(new Date());
              }
            catch (IllegalArgumentException illegalArgumentException)
              {
                _logger.log(Level.SEVERE, "Invalid date and time format; \"" + pieces[1] + "\".", illegalArgumentException);
              }
            catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
              }
          }
        else if (pieces[0].equals(GET))
          {
            try
              {
                Object object = getObjectValue(pieces[1]);
                replacement = object.toString();
              }
            catch (ObjectNotFoundException objectNotFoundException)
              {
                _logger.log(Level.SEVERE, "Object Not Found", objectNotFoundException);
              }
            catch (AttributeNotFoundException attributeNotFoundException)
              {
                _logger.log(Level.SEVERE, "Attribute Not Found", attributeNotFoundException);
              }
            catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
              {
                _logger.log(Level.SEVERE, "Array Index Out Of Bound", arrayIndexOutOfBoundsException);
              }
            catch (NotArrayException notArrayException)
              {
                _logger.log(Level.SEVERE, "Not An Array", notArrayException);
              }
            catch (ArrayNotFoundException arrayNotFoundException)
              {
                _logger.log(Level.SEVERE, "Array Not Found", arrayNotFoundException);
              }
            catch (NullPointerException nullPointerException)
              {
                _logger.log(Level.SEVERE, "Null Pointer", nullPointerException);
              }
            catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "Exception", exception);
              }
          }
        else if (pieces[0].equals(LOOP))
          {
            try
              {
                int increment = 1;
                if (pieces.length == 3)
                  increment = Integer.parseInt(pieces[2]);
                Object[] object = (Object[]) _valueObjects.get(pieces[1].toLowerCase());
                if (object != null)
                  {
                    String loopTemplate = substring(matcher.group(), "}", "{/");
                    Hashtable<String, Object> foreachValues = (Hashtable<String, Object>) _valueObjects.clone();
                    replacement = "";
                    if (increment > 0)
                      for (int index = 0; index < object.length; index += increment)
                        {
                          TemplateProcessor processor = new TemplateProcessor(_locale, foreachValues);
                          processor.putValueObject(pieces[1] + "-offset", index + 1);
                          processor.putValueObject(pieces[1] + "-first", (index == 0));
                          processor.putValueObject(pieces[1] + "-last", (index + 1 >= object.length));
                          processor.putValueObject(pieces[1], object[index]);
                          replacement += processor.apply(loopTemplate);
                        }
                    else if (increment < 0)
                      for (int index = object.length - 1; index >= 0; index += increment)
                        {
                          TemplateProcessor processor = new TemplateProcessor(_locale, foreachValues);
                          processor.putValueObject(pieces[1] + "-offset", index + 1);
                          processor.putValueObject(pieces[1] + "-first", (index == object.length - 1));
                          processor.putValueObject(pieces[1] + "-last", (index == 0));
                          processor.putValueObject(pieces[1], object[index]);
                          replacement += processor.apply(loopTemplate);
                        }
                  }
                else
                  {
                    _logger.log(Level.SEVERE, "Unable to find array \"" + pieces[1] + "\".");
                  }
              }
            catch (NumberFormatException numberFormatException)
              {
                _logger.log(Level.SEVERE, "Invalid Number", numberFormatException);
              }
            catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "Exception", exception);
              }
          }
        else if (pieces[0].equals(IF))
          {
            boolean condition = false;
            String ifBlock = substring(matcher.group(), "}", "{/");
            if (pieces[3].equals("exists") || pieces[3].equals("not-exists"))
              {
                try
                  {
                    getObjectValue(pieces[2]);
                    condition = pieces[3].equals("exists");
                  }
                catch (Exception exception)
                  {
                    condition = pieces[3].equals("not-exists");
                  }
              }
            else
              {
                try
                  {
                    String leftSideStr = "";
                    if (pieces[2].startsWith("'") && pieces[2].endsWith("'"))
                      {
                        leftSideStr = pieces[2].substring(1).substring(0, pieces[2].length() - 2);
                      }
                    else
                      {
                        Object leftSideObj = getObjectValue(pieces[2]);
                        leftSideStr = leftSideObj == null ? "" : leftSideObj.toString();
                      }
                    String rightSideStr = "";
                    if (pieces.length == 5)
                      if (pieces[4].startsWith("'") && pieces[4].endsWith("'"))
                        {
                          rightSideStr = pieces[4].substring(1).substring(0, pieces[4].length() - 2);
                        }
                      else
                        {
                          Object rightSideObj = getObjectValue(pieces[4]);
                          rightSideStr = rightSideObj == null ? "" : rightSideObj.toString();
                        }
                    if (pieces[3].equals("equals"))
                      {
                        condition = leftSideStr.equals(rightSideStr);
                      }
                    else if (pieces[3].equals("not-equals"))
                      {
                        condition = !leftSideStr.equals(rightSideStr);
                      }
                    else if (pieces[3].equals("empty"))
                      {
                        condition = leftSideStr.equals("");
                      }
                    else if (pieces[3].equals("not-empty"))
                      {
                        condition = !leftSideStr.equals("");
                      }
                    else
                      {
                        int leftSideInt = 0;
                        int rightSideInt = 0;
                        try
                          {
                            if (!leftSideStr.equals(""))
                              leftSideInt = Integer.parseInt(leftSideStr);
                          }
                        catch (NumberFormatException numberFormatException)
                          {
                            _logger.log(Level.SEVERE, "Invalid Number", numberFormatException);
                          }
                        catch (Exception exception)
                          {
                            _logger.log(Level.SEVERE, "Exception", exception);
                          }
                        try
                          {
                            if (!rightSideStr.equals(""))
                              rightSideInt = Integer.parseInt(rightSideStr);
                          }
                        catch (NumberFormatException numberFormatException)
                          {
                            _logger.log(Level.SEVERE, "Invalid Number", numberFormatException);
                          }
                        catch (Exception exception)
                          {
                            _logger.log(Level.SEVERE, "Exception", exception);
                          }
                        if (pieces[3].equals("greater-than"))
                          {
                            condition = leftSideInt > rightSideInt;
                          }
                        else if (pieces[3].equals("greater-than-or-equals"))
                          {
                            condition = leftSideInt >= rightSideInt;
                          }
                        else if (pieces[3].equals("less-than"))
                          {
                            condition = leftSideInt < rightSideInt;
                          }
                        else if (pieces[3].equals("less-than-or-equals"))
                          {
                            condition = leftSideInt <= rightSideInt;
                          }
                        else if (pieces[3].equals("even-number"))
                          {
                            condition = leftSideInt % 2 == 0 && leftSideInt != 0;
                          }
                        else if (pieces[3].equals("odd-number"))
                          {
                            condition = leftSideInt % 2 == 1 && leftSideInt != 0;
                          }
                      }
                  }
                catch (ObjectNotFoundException objectNotFoundException)
                  {
                    _logger.log(Level.SEVERE, "Object Not Found", objectNotFoundException);
                  }
                catch (AttributeNotFoundException attributeNotFoundException)
                  {
                    _logger.log(Level.SEVERE, "Attribute Not Found", attributeNotFoundException);
                  }
                catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
                  {
                    _logger.log(Level.SEVERE, "Array Index Out Of Bound", arrayIndexOutOfBoundsException);
                  }
                catch (NotArrayException notArrayException)
                  {
                    _logger.log(Level.SEVERE, "Not An Array", notArrayException);
                  }
                catch (ArrayNotFoundException arrayNotFoundException)
                  {
                    _logger.log(Level.SEVERE, "Array Not Found", arrayNotFoundException);
                  }
                catch (NullPointerException nullPointerException)
                  {
                    _logger.log(Level.SEVERE, "Null Pointer", nullPointerException);
                  }
                catch (Exception exception)
                  {
                    _logger.log(Level.SEVERE, "Exception", exception);
                  }
              }
            replacement = condition ? apply(ifBlock) : "";
          }
        else if (pieces[0].equals(SHOW_RANDOMLY))
          {
            String randomlyBlock = substring(matcher.group(), "}", "{/");
            replacement = (int) (Math.random() * 2.0) == 1 ? apply(randomlyBlock) : "";
          }
        else if (pieces[0].equals(DRAW))
          {
            replacement = pieces[1 + (int) (Math.random() * pieces.length - 1)];
          }
        else if (pieces[0].equals(REPEAT))
          {
            int repeatTimes = 1;
            try
              {
                if (pieces.length == 4)
                  {
                    int startNumber = repeatTimes = Integer.parseInt(pieces[2]);
                    repeatTimes = ((int) (Math.random() * (Integer.parseInt(pieces[3]) - startNumber + 1)) + startNumber);
                  }
                else
                  {
                    repeatTimes = Integer.parseInt(pieces[2]);
                  }
              }
            catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
              }
            String repeatBlock = substring(matcher.group(), "}", "{/");
            Hashtable<String, Object> repeatValues = (Hashtable<String, Object>) _valueObjects.clone();
            replacement = "";
            repeatValues.put(pieces[1] + "-length", repeatTimes);
            for (int index = 0; index < repeatTimes; index++)
              {
                TemplateProcessor processor = new TemplateProcessor(_locale, repeatValues);
                processor.putValueObject(pieces[1] + "-offset", index + 1);
                processor.putValueObject(pieces[1] + "-first", (index == 0));
                processor.putValueObject(pieces[1] + "-last", (index == repeatTimes - 1));
                processor.putValueObject(pieces[1], index + 1);
                replacement += processor.apply(repeatBlock);
              }
          }
        else if (pieces[0].equals(PROPERTY))
          {
            replacement = System.getProperty(pieces[1], "");
          }
        else if (pieces[0].equals(FORMAT))
          {
            try
              {
                Locale locale = _locale;
                if (pieces.length == 4)
                  locale = new Locale(pieces[2], pieces[3]);
                SpanFormatter spanFormatter = _spanFormatters.get(pieces[1]);
                if (spanFormatter != null)
                  {
                    String spanTemplate = substring(matcher.group(), "}", "{/");
                    replacement = spanFormatter.format(apply(spanTemplate), locale);
                  }
                else
                  {
                    _logger.log(Level.SEVERE, "Unable to find array \"" + pieces[1] + "\".");
                  }
              }
            catch (NumberFormatException numberFormatException)
              {
                _logger.log(Level.SEVERE, "Invalid Number", numberFormatException);
              }
            catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "Exception", exception);
              }
          }
        else if (pieces[0].equals(SET))
          {
            putValueObject(pieces[1], apply(substring(matcher.group(), "}", "{/")));
          }
        matcher.appendReplacement(stringBuffer, replacement == null ? "" : replacement);
        replacement = null;
      }
    matcher.appendTail(stringBuffer);
    matcher = null;
    String output = stringBuffer.toString();
    if (_pattern.matcher(output).find())
      output = apply(output);
    return (output);
  }

  private Object[] getArrayObject(String name)
    throws ArrayNotFoundException, NotArrayException
  {
    try
      {
        Object[] objArray = (Object[]) _valueObjects.get(name.toLowerCase());
        if (objArray == null)
          throw new ArrayNotFoundException(name);
        return (objArray);
      }
    catch (ClassCastException classCastException)
      {
        throw new NotArrayException(name);
      }
  }

  private Object getObjectValue(String name)
    throws ArrayNotFoundException, NotArrayException, ArrayIndexOutOfBoundsException, ObjectNotFoundException, AttributeNotFoundException
  {
    String[] pieces = name.split("[.]");
    Object object = null;
    if (pieces[0].endsWith("]"))
      {
        String arrayName = null;
        int index = 0;
        try
          {
            arrayName = pieces[0].substring(0, pieces[0].indexOf("["));
            index = Integer.parseInt(pieces[0].substring(pieces[0].indexOf("[") + 1, pieces[0].length() - 1)) - 1;
            if (index < 0)
              throw new ArrayIndexOutOfBoundsException("0");
            object = getArrayObject(arrayName)[index];
          }
        catch (IndexOutOfBoundsException indexOutOfBoundsException)
          {
            throw new ArrayIndexOutOfBoundsException("" + index);
          }
      }
    else
      {
        object = _valueObjects.get(pieces[0].toLowerCase());
      }
    if (object == null)
      {
        throw new ObjectNotFoundException(pieces[0]);
      }
    else if (pieces.length == 2)
      {
        if (pieces[1].toLowerCase().equals("-length"))
          {
            Object offsetValue = _valueObjects.get(pieces[0].toLowerCase() + "-length");
            return (offsetValue == null ? getArrayObject(pieces[0]).length : offsetValue);
          }
        else if (pieces[1].toLowerCase().equals("-offset"))
          {
            Object offsetValue = _valueObjects.get(pieces[0].toLowerCase() + "-offset");
            return (offsetValue == null ? 0 : offsetValue);
          }
        else if (pieces[1].toLowerCase().equals("-first"))
          {
            return (((Boolean) _valueObjects.get(pieces[0].toLowerCase() + "-first")).booleanValue());
          }
        else if (pieces[1].toLowerCase().equals("-last"))
          {
            return (((Boolean) _valueObjects.get(pieces[0].toLowerCase() + "-last")).booleanValue());
          }
        else
          {
            boolean found = false;
            try
              {
                if (object instanceof Properties)
                  {
                    Properties properties = (Properties) object;
                    object = properties.getProperty(pieces[1]);
                    found = object != null;
                  }
                else
                  {
                    BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
                    PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
                    for (int index = 0; index < descriptors.length && !found; index++)
                      if (descriptors[index].getName().equals(pieces[1]))
                        {
                          object = descriptors[index].getReadMethod().invoke(object);
                          found = true;
                        }
                  }
              }
            catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "Exception", exception);
              }
            if (!found)
              throw new AttributeNotFoundException("Attribute :" + pieces[1] + " in " + name);
          }
      }
    return (object);
  }

  public void loadAllSpanFormatters()
  {
    loadAllSpanFormatters(null);
  }

  public void loadAllSpanFormatters(String rootPackageName)
  {
    try
      {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (rootPackageName == null)
          rootPackageName = "com.anars.jbrackets.formatters";
        else
          rootPackageName = rootPackageName.trim();
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
            String formatterName = formatterClass.getSimpleName();
            if (formatterName.endsWith("SpanFormatter"))
              formatterName = formatterName.substring(0, formatterName.length() - 13);
            putSpanFormatter(formatterName, (SpanFormatter) formatterClass.newInstance());
          }
      }
    catch (Exception exception)
      {
        _logger.log(Level.SEVERE, "Exception", exception);
      }
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
          if (cls.getSuperclass().getName().equals("com.anars.jbrackets.SpanFormatter"))
            classList.add(cls);
        }
    return (classList);
  }

  private String substring(String string, String start, String end)
  {
    return (substring(string, start, end, true));
  }

  private String substring(String string, String start, String end, boolean lastIndex)
  {
    String substring = string;
    substring = substring.substring(substring.indexOf(start) + 1);
    substring = substring.substring(0, lastIndex ? substring.lastIndexOf(end) : substring.indexOf(end));
    return (substring);
  }
}
