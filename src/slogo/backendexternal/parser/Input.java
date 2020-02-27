package slogo.backendexternal.parser;


import java.util.regex.Pattern;

public enum Input {
  Comment (Pattern.compile("^#.*", Pattern.CASE_INSENSITIVE)),
  Constant (Pattern.compile("-?[0-9]+\\.?[0-9]*", Pattern.CASE_INSENSITIVE)),
  Variable (Pattern.compile(":[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE)),
  Command (Pattern.compile("[a-zA-Z_]+(\\?)?|[*+-/%~]", Pattern.CASE_INSENSITIVE)),
  ListStart (Pattern.compile("\\[", Pattern.CASE_INSENSITIVE)),
  ListEnd (Pattern.compile("\\]", Pattern.CASE_INSENSITIVE)),
  GroupStart (Pattern.compile("\\(", Pattern.CASE_INSENSITIVE)),
  GroupEnd (Pattern.compile("\\)", Pattern.CASE_INSENSITIVE)),
  Whitespace (Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE)),
  Newline (Pattern.compile("\\n+", Pattern.CASE_INSENSITIVE)),
  Make (Pattern.compile("MAKE", Pattern.CASE_INSENSITIVE)),
  Set (Pattern.compile("SET", Pattern.CASE_INSENSITIVE)),
  TO (Pattern.compile("TO", Pattern.CASE_INSENSITIVE));

  private Pattern regex;
  Input(Pattern regex){ this.regex = regex;}
  public boolean matches(String input){ return this.regex.matcher(input).matches();}
}
