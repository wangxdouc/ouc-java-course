package ouc.framework.config;

import java.util.Map;

public class ControllerConfig {

	private String controllerName;
	private String controllerClass;
	private Map<String, String> results;

	public ControllerConfig() {

	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(String controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Map<String, String> getResults() {
		return results;
	}

	public void setResults(Map<String, String> results) {
		this.results = results;
	}



}
