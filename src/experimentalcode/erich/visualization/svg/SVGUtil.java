package experimentalcode.erich.visualization.svg;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class SVGUtil {

  /**
   * Formatter to output numbers in a valid SVG number format.
   */
  public static final NumberFormat FMT = NumberFormat.getInstance(Locale.ROOT);

  static {
    FMT.setMaximumFractionDigits(8);
  }

  /**
   * Format a double according to the SVG specs.
   * 
   * @param x number to format
   * @return String representation
   */
  public static String fmt(double x) {
    return FMT.format(x);
  }

  /**
   * Create a SVG element in appropriate namespace
   * 
   * @param document containing document
   * @param parent parent element
   * @param name node name
   * @return new SVG element.
   */
  // Deprecated: Elements on their own often aren't useful.
  // If possible, they should be set up completely, then added
  // (since there might be an update manager tracking changes,
  // or the interim DOM tree not valid!)
  @Deprecated
  public static Element svgElement(Document document, Element parent, String name) {
    Element neu = svgElement(document, name);
    if(parent != null) {
      parent.appendChild(neu);
    }
    return neu;
  }

  /**
   * Create a SVG element in appropriate namespace
   * 
   * @param document containing document
   * @param name node name
   * @return new SVG element.
   */
  public static Element svgElement(Document document, String name) {
    return document.createElementNS(SVGConstants.SVG_NAMESPACE_URI, name);
  }

  /**
   * Set a SVG attribute
   * 
   * @param el element
   * @param name attribute name
   * @param d double value
   */
  public static void setAtt(Element el, String name, double d) {
    el.setAttribute(name, fmt(d));
  }
  
  /**
   * Set a SVG attribute
   * 
   * @param el element
   * @param name attribute name
   * @param d integer value
   */
  public static void setAtt(Element el, String name, int d) {
    el.setAttribute(name, Integer.toString(d));
  }
  
  /**
   * Set a SVG attribute
   * 
   * @param el element
   * @param name attribute name
   * @param d string value
   */
  public static void setAtt(Element el, String name, String d) {
    el.setAttribute(name, d);
  }

  /**
   * Add a CSS class to an Element.
   * 
   * @param e Element
   * @param cssclass class to add.
   */
  public static void addCSSClass(Element e, String cssclass) {
    String oldval = e.getAttribute("class");
    if (oldval == null) {
      setAtt(e, "class", cssclass);
      return;
    }
    String[] classes = oldval.split(" ");
    for (String c : classes) {
      if (c.equals(cssclass)) {
        return;
      }
    }
    setAtt(e, "class", oldval+" "+cssclass);
  }
  
  /**
   * Remove a CSS class from an Element.
   * 
   * @param e Element
   * @param cssclass class to remove.
   */
  public static void removeCSSClass(Element e, String cssclass) {
    String oldval = e.getAttribute("class");
    if (oldval == null) {
      return;
    }
    String[] classes = oldval.split(" ");
    String joined = "";
    for (String c : classes) {
      if (! c.equals(cssclass)) {
        if (joined.length() > 0) {
          joined = joined + " " + c;
        } else {
          joined = c;
        }
      }
    }
    SVGUtil.setAtt(e, "class", joined);
  }
}
