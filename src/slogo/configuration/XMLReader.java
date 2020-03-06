package slogo.configuration;

import java.io.File;
import java.io.IOException;
import javafx.scene.paint.Color;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import slogo.view.MainView;

/**
 * This class handles parsing XML files and returning a completed object.
 *
 * @author Rhondu Smithwick
 * @author Robert C. Duvall
 * extended by Shruthi Kumar
 */
public class XMLReader {

  public static final String ERROR_MESSAGE = "XML file does not represent %s";
  public static final String CORRUPTED_FIELD = "XML file has corrupted/missing fields";
  public static final String INCORRECT_DATATYPE = "XML file has incorrect data type";

  private final String TYPE_ATTRIBUTE;
  private final DocumentBuilder DOCUMENT_BUILDER;

  private Color backgroundColor, penColor;
  private int numTurtles;
  private String turtleImageName;
  private String codeFileName;


  public XMLReader(String type) {
    DOCUMENT_BUILDER = getDocumentBuilder();
    TYPE_ATTRIBUTE = type;
  }

  public MainView getMainView(String fname) {
    File dataFile = new File(fname);
    Element root = getRootElement(dataFile);
    if(! isValidFile(root, MainView.DATA_TYPE)) {
      throw new XMLException(ERROR_MESSAGE, MainView.DATA_TYPE);
    }
    readBasic(root);
    return new MainView(backgroundColor, penColor, numTurtles, turtleImageName, codeFileName);
  }

  private void readBasic(Element root) {
    try {
      backgroundColor = Color.web(getTextValue(root, "background"));
      penColor = Color.web(getTextValue(root, "pen"));
      numTurtles = Integer.parseInt(getTextValue(root, "numTurtles"));
      turtleImageName = getTextValue(root, "turtleName") + ".png";
      codeFileName = getTextValue(root, "fileName");
    }
    catch (NumberFormatException e) {
      throw new XMLException(INCORRECT_DATATYPE, MainView.DATA_TYPE);
    }
  }

  private Element getRootElement (File xmlFile) {
    try {
      DOCUMENT_BUILDER.reset();
      Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
      return xmlDocument.getDocumentElement();
    }
    catch (SAXException | IOException e) {
      throw new XMLException(e);
    }
  }

  private String getTextValue (Element e, String tagName) {
    NodeList nodeList = e.getElementsByTagName(tagName);
    if (nodeList != null && nodeList.getLength() > 0 && !nodeList.item(0).getTextContent().equals("")) {
      return nodeList.item(0).getTextContent();
    }
    else {
      throw new XMLException(CORRUPTED_FIELD + ": " + tagName, MainView.DATA_TYPE);
    }
  }

  private String getAttribute (Element e, String attributeName) {
    return e.getAttribute(attributeName);
  }

  private boolean isValidFile (Element root, String type) {
    return getAttribute(root, TYPE_ATTRIBUTE).equals(type);
  }

  // boilerplate code needed to make a documentBuilder
  private DocumentBuilder getDocumentBuilder()  {
    try {
      return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    catch (ParserConfigurationException e) {
      throw new XMLException(e);
    }
  }
}
