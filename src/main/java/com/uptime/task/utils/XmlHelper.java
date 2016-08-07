package com.uptime.task.utils;

import com.uptime.task.XConstants;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by de1mos on 3.08.16.
 */
public class XmlHelper {

    public static String convertXmlToJson(Document doc) {
        String jsonResult = null;

        try {
            JSONObject xmlJSONObj = XML.toJSONObject(XmlDocumentToString(doc));
            jsonResult = xmlJSONObj.toString(XConstants.XML_PRETTY_PRINT_INDENT_FACTOR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return jsonResult;
    }

    public static String XmlDocumentToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    public static Node getNodeFromDocument(Document doc, String nodeName) {
        return doc.getElementsByTagName(nodeName).item(0);
    }
}
