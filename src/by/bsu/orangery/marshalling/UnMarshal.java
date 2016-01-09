package by.bsu.orangery.marshalling;

/**
 * Created by Михаил on 09.01.2016.
 */
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import by.bsu.orangery.entity.*;
import org.xml.sax.SAXException;
public class UnMarshal {
    public static void main(String[ ] args) {
        JAXBContext jc = null;
        Flowers fl = null;
        try {
            jc = JAXBContext.newInstance("by.bsu.orangery.entity");
            Unmarshaller um = jc.createUnmarshaller();
            String schemaName = "data/orangery.xsd";
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaName);
// создание схемы и перадача ее демарашаллизатору
            Schema schema = factory.newSchema(schemaLocation);
            um.setSchema(schema);
            fl =
                    (Flowers) um.unmarshal(new File("data/orangery.xml"));
            System.out.println(fl);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        for (JAXBElement<? extends Flower> flower:fl.getFlower()) {
            System.out.println(flower.getValue().toString());
        }
    }
}
