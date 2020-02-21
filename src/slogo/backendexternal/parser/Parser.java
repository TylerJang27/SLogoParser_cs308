package slogo.backendexternal.parser;

import java.io.File;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import slogo.commands.Command;

public class Parser {
  private static final String RESOURCES_PACKAGE = Parser.class.getPackageName() + ".resources.";
  private static final ResourceBundle COMMAND_SYNTAX =
      ResourceBundle.getBundle(Parser.class.getPackageName() + ".resources.Syntax.properties");

  private List<Command> recentHistory;
  private Map<String, Pattern> myCommands;


  public Parser(){ this("English");}

  public Parser(String language){
    setLanguage(language);
  }

  public void parseLine(String line){
  }

  private void setLanguage(String lang){
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + lang);
    for (String key : Collections.list(resources.getKeys())) {
      String regex = resources.getString(key);
      myCommands.put(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
    }
  }

  private boolean match (String text) {
    return myCommands.get(text).matcher(text).matches();
  }

}
