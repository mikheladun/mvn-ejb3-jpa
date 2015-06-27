package co.adun.mvnejb3jpa.web.transform;

import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.persistence.Persistable;

@Component
public class EntityTransformer extends BaseTransformer {
	private static final Logger logger = Logger.getLogger(EntityTransformer.class.getName());

	@Override
	public String transform(Persistable entity) throws TransformerException {
		String xml = null;
		// create JAXB context and initializing Marshaller
		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(entity.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// for getting nice formatted output
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			Writer sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			jaxbMarshaller.marshal(entity, result);

			xml = sw.toString();

		}
		catch (JAXBException e) {
			throw new TransformerException(e.getMessage());
		}
		catch (Exception e) {
			throw new TransformerException(e.getMessage());
		}

		return xml;
	}

	public String transform(Persistable entity, boolean clean) throws TransformerException {
		String xml = this.transform(entity);
		xml = xml.replaceAll("\\<\\?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"\\?\\>", "");
		xml = xml.replaceAll("(\\>)+(\\s)+(\\<)+", "><");

		return xml.trim();
	}
}
