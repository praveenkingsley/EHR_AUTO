package driver;
import com.healthgraph.SeleniumFramework.Util.Common.Cls_Generic_Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.TestNG;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class JenkinsSetUp {

    public static String env = "";
    public static String testType = "";
    public static String modules = "";
    private static final Logger logger = LogManager.getLogger();

    @Test
    public static void setUp() {

        try {

            logger.info("");
            logger.info("<<<<<<<<<<>>>>>>>>>>");
            logger.info("STARTING EXECUTION");
            logger.info("<<<<<<<<<<>>>>>>>>>>");

            testType=Cls_Generic_Methods.getJenkinsConfig("testType").toUpperCase();
            logger.info("SELECTED TEST TYPE : "+testType);

            modules=Cls_Generic_Methods.getJenkinsConfig("testModule").toUpperCase();
            logger.info("SELECTED TEST MODULE : "+modules);

            if(env.isEmpty()){
                env=Cls_Generic_Methods.getJenkinsConfig("environment").toUpperCase();
                Cls_Generic_Methods.environment=env;
            }

            logger.info("Environment selected = " + Cls_Generic_Methods.environment);

            List<String> xmlFiles=new ArrayList<>();


            if(testType.equalsIgnoreCase("SMOKE")){
                if(modules.contains("OPD")){
                    xmlFiles.add("./XMLFiles/TC_01_OPD_SMOKE.xml");
                }
                if(modules.contains("INVENTORY")){
                    xmlFiles.add("./XMLFiles/TC_01_OPD_SMOKE.xml");
                }
            }

            //ADD TC LATER
            if(testType.equalsIgnoreCase("REGRESSION")){
                if(modules.contains("OPD")){
                    xmlFiles.add("./XMLFiles/TC_01_OPD_SMOKE.xml");
                }
                if(modules.contains("INVENTORY")){
                    xmlFiles.add("./XMLFiles/TC_01_OPD_SMOKE.xml");
                }
            }

            TestNG testNG=new TestNG();
            testNG.setTestSuites(xmlFiles);
            testNG.run();

            logger.info("<<<<<<<<<<>>>>>>>>>>");
            logger.info("ENDING EXECUTION");
            logger.info("<<<<<<<<<<>>>>>>>>>>");
            logger.info("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
