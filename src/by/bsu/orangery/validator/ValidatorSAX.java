package by.bsu.orangery.validator;

/**
 * Created by Михаил on 09.01.2016.
 */
import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;

public class ValidatorSAX {

    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    public static void main(String[ ] args) {
        String fileName = "data/orangery.xml";
        String schemaName = "data/orangery.xsd";
        String logname = "logs/log.txt";
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
// создание схемы
            Schema schema = factory.newSchema(schemaLocation);
// создание валидатора на основе схемы
            Validator validator = schema.newValidator();
// проверка документа
            Source source = new StreamSource(fileName);
            FlowerErrorHandler sh = new FlowerErrorHandler("logs/log.txt");
            validator.setErrorHandler(sh);
            validator.validate(source);
            System.out.println(fileName + " validating is ended.");
            System.out.println(fileName + " is valid.");
        } catch (SAXException e) {
            System.err.print("validation "+ fileName + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            System.err.print(fileName + " is not valid because "
                    + e.getMessage());
        }
    }
}