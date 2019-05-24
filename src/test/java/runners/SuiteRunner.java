package runners;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class SuiteRunner {
    public static void main(String[] args) {
        TestNG tng = new TestNG();

        XmlSuite suite = new XmlSuite();
        suite.setName("AT_Email:Suite");

        List<String> files = new ArrayList<>();
        files.addAll(new ArrayList<String>() {{
            add("./src/main/resources/xmlRunner.xml");
        }});
        suite.setSuiteFiles(files);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        tng.setXmlSuites(suites);

        tng.run();
    }

}
