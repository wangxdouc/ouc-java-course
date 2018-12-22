package ouc.framework.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 用DOM4J方法读取Monkey框架配置文件 monkey.xml
 * 
 * @author xiaodong
 *
 */
public class FrameworkConfigParser {


	public static Map<String, ControllerConfig> getControllerConfigs(File file) {
	
		Map<String, ControllerConfig> controllerConfigs = new HashMap<String, ControllerConfig>();
		ControllerConfig controllerConfig = null;
		SAXReader reader = new SAXReader();
		
		try {
			Document document = reader.read(file);
			Element controllers = document.getRootElement();
			Iterator<Element> controllersIt = controllers.elementIterator();
			
			while (controllersIt.hasNext()) {
				controllerConfig = new ControllerConfig();
				Element controllerConfigElement = controllersIt.next();
				List<Attribute> attributes = controllerConfigElement.attributes();

				for (Attribute attribute : attributes) {
					if (attribute.getName().equals("name")) {
						controllerConfig.setControllerName(attribute.getValue());
					}

					if (attribute.getName().equals("class")) {
						controllerConfig.setControllerClass(attribute.getValue());
					}
				}

				Iterator<Element> resultsIt = controllerConfigElement.elementIterator();

				Map<String, String> results = new HashMap<String, String>();

				while (resultsIt.hasNext()) {
					Element resultElement = (Element) resultsIt.next();
					results.put(resultElement.attribute("name").getValue(), resultElement.getStringValue());
				}

				controllerConfig.setResults(results);
				controllerConfigs.put(controllerConfig.getControllerName(), controllerConfig);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return controllerConfigs;
	}

	public static void main(String[] args) {
		File file = new File("src/main/java/monkey.xml");
		Map<String, ControllerConfig> controllerConfigs = FrameworkConfigParser.getControllerConfigs(file);
		
		Iterator<String> controllerConfigKeys =  controllerConfigs.keySet().iterator();
		
		while (controllerConfigKeys.hasNext()) {
		
			String ckey = controllerConfigKeys.next();
			ControllerConfig config = controllerConfigs.get(ckey);
			System.out.println(config.getControllerName());
			System.out.println(config.getControllerClass());
			
			Iterator<String> keysIt = config.getResults().keySet().iterator();
			
			while (keysIt.hasNext()) {
				String key = keysIt.next();
				System.out.println(key + " : " + config.getResults().get(key));
			}
		}
	}

}