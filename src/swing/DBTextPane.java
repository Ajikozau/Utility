package swing;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

public class DBTextPane
  extends JTextPane
{
  protected SimpleAttributeSet attributes;
  protected HashMap<Integer, String> buffMessages;
  
  public HashMap<Integer, String> buffMessages()
  {
    return this.buffMessages;
  }
  
  private boolean onGlass = false;
  
  public DBTextPane(SimpleAttributeSet attributes)
  {
    this.attributes = attributes;
    this.buffMessages = new HashMap();
    setFocusable(false);
    setEditable(false);
    setOpaque(false);
  }
  
  public void changeAttSet(SimpleAttributeSet attributes)
  {
    this.attributes = attributes;
  }
  
  public void placeMultipleStyledStrings(int l, String... strings)
  {
    int i = l;
    for (String s : strings)
    {
      placeStyledString(s, i);
      i++;
    }
    makeDoc(0);
  }
  
  public void placeStyledString(String text, int line)
  {
    int i;
    if (line == 99) {
      i = this.buffMessages.size() + 1;
    } else {
      i = line;
    }
    this.buffMessages.remove(Integer.valueOf(i));
    this.buffMessages.put(Integer.valueOf(i), text);
  }
  
  public void removeString(int line)
  {
    this.buffMessages.remove(Integer.valueOf(line));
  }
  
  public void placeFinalString(String text, int line)
  {
    placeStyledString(text, line);
    makeDoc(0);
  }
  
  public void makeDoc(int startLine)
  {
    setText(null);
    StyledDocument doc = getStyledDocument();
    int maxLength = 0;
    int maxHeight = 0;
    List<Map.Entry<Integer, String>> entryList = new ArrayList(this.buffMessages.entrySet());
    for (Map.Entry<Integer, String> entry : entryList)
    {
      int x = ((String)entry.getValue()).length();
      int y = ((Integer)entry.getKey()).intValue();
      if (x > maxLength) {
        maxLength = Math.max(maxLength, x);
      }
      if (y > maxHeight) {
        maxHeight = Math.max(maxHeight, y);
      }
    }
    int offset = 0;
    for (int i = 0; i < maxHeight + 1; i++)
    {
      int n = i + startLine;
      String string = (String)this.buffMessages.get(Integer.valueOf(n));
      try
      {
        if (string != null)
        {
          doc.setParagraphAttributes(i, 0, this.attributes, false);
          if (i != maxHeight) {
            doc.insertString(offset, string + "\n", this.attributes);
          } else {
            doc.insertString(offset, string, this.attributes);
          }
          int length = string.length();
          offset = offset + length + 1;
        }
        else
        {
          doc.insertString(offset, "\n", null);
          offset++;
        }
      }
      catch (BadLocationException ex)
      {
        Logger.getLogger(DBTextPane.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public void resetText()
  {
    setText("");
    this.buffMessages.clear();
  }
  
  public void makeListDialog()
  {
    setOpaque(true);
    setPreferredSize(null);
    setSize(new Dimension(getWidth(), 10));
    this.onGlass = true;
  }
  
  public void restoreSize()
  {
    if (this.onGlass)
    {
      setPreferredSize(new Dimension(getWidth(), 30));
      setOpaque(false);
      this.onGlass = false;
    }
  }
  
  public void centerText(SimpleAttributeSet attSet, int line, String text)
  {
    int lineHeight = getFontMetrics(getStyledDocument().getFont(attSet)).getHeight();
    Component c = findComponentAt(0, line * lineHeight);
    if (c != null) {
      remove(c);
    }
    DBTextPane newLine = new DBTextPane(attSet);
    add(newLine);
    newLine.setBorder(null);
    newLine.setBounds(0, line * lineHeight, getWidth(), lineHeight);
    newLine.placeFinalString(text, 0);
  }
}
