package co.adun.mvnejb3jpa.web.transform;

import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import co.adun.mvnejb3jpa.persistence.Persistable;

abstract class BaseTransformer {

    private static final Logger logger = Logger.getLogger(BaseTransformer.class.getName());

    abstract String transform(Persistable entity) throws TransformerException;

    protected String transform(org.w3c.dom.Document doc) throws TransformerException {
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	Writer sw = new StringWriter();
	StreamResult result = new StreamResult(sw);
	DOMSource xmlSource = new DOMSource(doc);
	transformer.transform(xmlSource, result);

	return sw.toString();
    }
}
