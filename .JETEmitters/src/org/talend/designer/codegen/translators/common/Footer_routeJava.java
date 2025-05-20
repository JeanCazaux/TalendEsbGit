package org.talend.designer.codegen.translators.common;

import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.utils.JavaResourcesHelper;
import java.util.Map;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.ProcessUtils;

public class Footer_routeJava {

  protected static String nl;
  public static synchronized Footer_routeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Footer_routeJava result = new Footer_routeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t}";
  protected final String TEXT_2 = NL + "\t\t};" + NL + "\t}";
  protected final String TEXT_3 = NL + "\tprivate org.apache.camel.main.Main main;" + NL + "" + NL + "    private void run() throws java.lang.Exception {" + NL + "        main = new org.apache.camel.main.Main() {" + NL + "" + NL + "            protected CamelContext createCamelContext() {" + NL + "                final org.apache.camel.impl.DefaultCamelContext camelContext =";
  protected final String TEXT_4 = NL + "                    new org.apache.camel.spring.SpringCamelContext(" + NL + "                        new org.springframework.context.support.ClassPathXmlApplicationContext(\"META-INF/spring/";
  protected final String TEXT_5 = ".xml\"));" + NL + "                        camelContext.setName(\"";
  protected final String TEXT_6 = "\");" + NL + "                        camelContext.getRegistry().bind(\"";
  protected final String TEXT_7 = "\", registry.lookupByName(\"";
  protected final String TEXT_8 = "\"));" + NL + "" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_9 = NL + "\t\t\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\t\t\ttry {" + NL + "\t\t\t\t\t\t\t\t\t\taddRoutelets(camelContext);" + NL + "\t\t\t\t\t\t\t\t\t} catch (Exception ee) {" + NL + "\t\t\t\t\t\t\t\t\t\tee.printStackTrace();" + NL + "\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_10 = NL + "                    new org.apache.camel.impl.DefaultCamelContext();" + NL + "                    camelContext.getRegistry().bind(\"";
  protected final String TEXT_11 = "\"));";
  protected final String TEXT_12 = NL + "                // add statistics which shows on the connection" + NL + "                final routines.system.RunStat runStat = new routines.system.RunStat();" + NL + "                runStat.openSocket(true);" + NL + "                runStat.setAllPID(pid, pid, pid, \"";
  protected final String TEXT_13 = "\");" + NL + "                try {" + NL + "                    runStat.startThreadStat(clientHost, portStats);" + NL + "                } catch (Exception e) {" + NL + "                    throw new RuntimeException(e);" + NL + "                }" + NL + "                runStat.updateStatOnJob(routines.system.RunStat.JOBSTART, null);" + NL + "" + NL + "                final Map<String, String> targetNodeToConnectionMap = new HashMap<String, String>();";
  protected final String TEXT_14 = NL + "                targetNodeToConnectionMap.put(\"";
  protected final String TEXT_15 = "_";
  protected final String TEXT_16 = "\", \"";
  protected final String TEXT_17 = "\");";
  protected final String TEXT_18 = NL + "                for (String connection : targetNodeToConnectionMap.values()) {" + NL + "                    runStat.updateStatOnConnection(connection, routines.system.RunStat.BEGIN, 0);" + NL + "                }" + NL + "                " + NL + "\t\t\t\tcamelContext.addInterceptStrategy(new org.apache.camel.spi.InterceptStrategy() {" + NL + "\t\t\t\t\t@Override" + NL + "\t\t\t\t\tpublic org.apache.camel.Processor wrapProcessorInInterceptors(CamelContext context, org.apache.camel.NamedNode definition, org.apache.camel.Processor target, org.apache.camel.Processor nextTarget) throws Exception {" + NL + "\t\t\t\t\t\treturn new org.apache.camel.support.processor.DelegateAsyncProcessor(target) {" + NL + "\t\t\t\t\t\t\tpublic boolean process(org.apache.camel.Exchange exchange, org.apache.camel.AsyncCallback callback) {" + NL + "\t\t\t\t\t\t\t\tfinal String connection = targetNodeToConnectionMap.get(definition.getId());" + NL + "\t\t\t\t\t\t\t\tif (null != connection) {" + NL + "\t\t\t\t\t\t\t\t\trunStat.updateStatOnConnection(targetNodeToConnectionMap.get(definition.getId()),routines.system.RunStat.RUNNING, 1);" + NL + "\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\treturn super.process(exchange, callback);" + NL + "\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t};" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t});" + NL + "                ";
  protected final String TEXT_19 = NL + "                return camelContext;" + NL + "            }" + NL + "        };" + NL + "" + NL + "        //add route" + NL + "        " + NL + "\t\tmain.bind(\"";
  protected final String TEXT_20 = "\", this);" + NL + "\t\t" + NL + "        main.run();" + NL + "    }" + NL + "" + NL + "\tpublic void stop() throws java.lang.Exception {" + NL + "\t\tif(main != null) {" + NL + "\t\t\tmain.stop();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic void shutdown() throws java.lang.Exception {" + NL + "\t\tif(main != null) {" + NL + "\t\t\tmain.shutdown();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t";
  protected final String TEXT_21 = NL;
  protected final String TEXT_22 = NL + "    " + NL + "        private java.util.Dictionary<String, String> loadHttpConduitProperties(String configName) {" + NL + "            Properties properties = new Properties();" + NL + "            try (java.io.InputStream is = getConfigLocation(configName)) {" + NL + "                properties.load(is);" + NL + "            } catch (Exception e) {" + NL + "                throw new RuntimeException(\"Cannot load the properties\", e);" + NL + "            }" + NL + "            java.util.Dictionary<String, String> dic = new java.util.Hashtable<String, String>();" + NL + "            for (Object key : properties.keySet()) {" + NL + "                dic.put((String) key, properties.getProperty((String) key));" + NL + "            }" + NL + "            return dic;" + NL + "        }" + NL + "        " + NL + "        public java.io.InputStream getConfigLocation(String fileName){" + NL + "        " + NL + "            java.io.InputStream stream = null;" + NL + "            " + NL + "            String configFile = \"config/\" + fileName;" + NL + "            " + NL + "            String configPath = env.getProperty(\"spring.config.additional-location\");" + NL + "            " + NL + "            String file = \"\";" + NL + "            if (configPath != null) {" + NL + "                // get spring.config.additional-location from env" + NL + "                file = configPath +java.io.File.separator+ fileName;" + NL + "            } else {" + NL + "                // by default from current \"config\" directory" + NL + "                file = System.getProperty(\"user.dir\") + java.io.File.separator + configFile;" + NL + "            }" + NL + "" + NL + "            java.io.File usersfile = new java.io.File(file);" + NL + "            " + NL + "            if (usersfile.exists()) {" + NL + "                try{" + NL + "                    stream = new java.io.FileInputStream(file);" + NL + "                }catch(Exception e){" + NL + "                    stream = this.getClass().getClassLoader().getResourceAsStream(configFile);" + NL + "                }" + NL + "            } else {" + NL + "                stream = this.getClass().getClassLoader().getResourceAsStream(configFile);" + NL + "            }" + NL + "            " + NL + "            return stream;" + NL + "        }" + NL + "" + NL + "        private static void loadConfig(Map<String,String> argsMap,String configName,String configValue){" + NL + "            String configFileValue = \"classpath:config/\" + configValue;" + NL + "" + NL + "            if (argsMap.get(configName) == null) {" + NL + "                if (argsMap.get(\"--spring.config.additional-location\") == null) {" + NL + "                    argsMap.put(configName, configFileValue);" + NL + "                } else {" + NL + "                    String value = argsMap.get(\"--spring.config.additional-location\") + java.io.File.separator + configValue;" + NL + "    " + NL + "                    if(new java.io.File(value).exists()){" + NL + "                        argsMap.put(configName, \"file:\" + value);" + NL + "                    }else{" + NL + "                        if (argsMap.get(\"--spring.config.additional-location\").contains(\":\")) {" + NL + "                            try {" + NL + "                                if (new java.io.File(new java.net.URI(value)).exists()) {" + NL + "                                    argsMap.put(configName, value);" + NL + "                                } else {" + NL + "                                    argsMap.put(configName, configFileValue);" + NL + "                                }" + NL + "                            } catch (Exception e) {" + NL + "                                argsMap.put(configName, configFileValue);" + NL + "                            }" + NL + "                        } else {" + NL + "                            argsMap.put(configName, configFileValue);" + NL + "                        }" + NL + "                    }" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "" + NL + "        private static String[] resetArgs(String... args){" + NL + "            Map<String,String> argsMap = new HashMap<String,String>();" + NL + "" + NL + "            for(int i=0;i<args.length;i++){" + NL + "                String[] kv = args[i].split(\"=\");" + NL + "                if(kv.length > 1) {" + NL + "                \targsMap.put(kv[0], kv[1]);" + NL + "                }" + NL + "            }" + NL + "" + NL + "            if (argsMap.get(\"--spring.config.additional-location\") != null) {" + NL + "                System.setProperty(\"spring.config.additional-location\",argsMap.get(\"--spring.config.additional-location\"));" + NL + "            }            " + NL + "            loadConfig(argsMap,\"--spring.banner.location\",\"banner.txt\");" + NL + "            " + NL + "            if (argsMap.get(\"--camel.springboot.typeConversion\") == null) {" + NL + "                argsMap.put(\"--camel.springboot.typeConversion\", \"false\");" + NL + "            }" + NL + "" + NL + "            String[] resetArgs = new String[argsMap.size()];" + NL + "            " + NL + "            java.util.Set<String> keySet = argsMap.keySet();" + NL + "" + NL + "            int idx = 0;" + NL + "            " + NL + "            for(String key:keySet){" + NL + "                resetArgs[idx] =  key+\"=\"+argsMap.get(key);" + NL + "                idx++;" + NL + "            }" + NL + "" + NL + "            return resetArgs;" + NL + "        }" + NL + "" + NL + "" + NL + "        @org.springframework.beans.factory.annotation.Autowired" + NL + "        private ContextProperties context;" + NL + "" + NL + "        @org.springframework.boot.context.properties.ConfigurationProperties" + NL + "        public static class ContextProperties {" + NL;
  protected final String TEXT_23 = NL + "        private final ContextProperties context = new ContextProperties();" + NL + "" + NL + "        public static class ContextProperties extends Properties {" + NL + "        " + NL + "\t\tpublic String getProperty(String key) {" + NL + "" + NL + "\t\t\tString rs = null;" + NL + "" + NL + "\t\t\tif (arguments != null) {" + NL + "\t\t\t\ttry {" + NL + "\t\t\t\t" + NL + "\t\t\t\t\tif(arguments instanceof java.util.HashMap) {" + NL + "\t\t\t\t\t\trs = (String)((java.util.HashMap)arguments).get(key);" + NL + "\t\t\t\t\t}else {" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\t\tjava.lang.reflect.Method getProperty = arguments.getClass()" + NL + "\t\t\t\t\t\t\t\t.getDeclaredMethod(\"getProperty\"," + NL + "\t\t\t\t\t\t\t\t\t\tnew Class[] { String.class });" + NL + "\t\t\t\t\t\tgetProperty.setAccessible(true);" + NL + "\t\t\t\t\t\t" + NL + "\t\t\t\t\t\trs = (String) getProperty.invoke(arguments, key);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t} catch (Exception e) {" + NL + "\t\t\t\t\te.printStackTrace();" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\tif (rs == null) {" + NL + "\t\t\t\trs = super.getProperty(key);" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\treturn rs;" + NL + "\t\t}" + NL + "        " + NL + "            public void synchronizeContext() {";
  protected final String TEXT_24 = NL + "                    if(";
  protected final String TEXT_25 = " != null){";
  protected final String TEXT_26 = NL + "                        String pattern_";
  protected final String TEXT_27 = " = \"yyyy-MM-dd HH:mm:ss\";" + NL + "                        String value_";
  protected final String TEXT_28 = " = \"";
  protected final String TEXT_29 = "\";" + NL + "                        String[] parts_";
  protected final String TEXT_30 = " = value_";
  protected final String TEXT_31 = ".split(\";\");" + NL + "                        if (parts_";
  protected final String TEXT_32 = ".length > 1) {" + NL + "                            pattern_";
  protected final String TEXT_33 = " = parts_";
  protected final String TEXT_34 = "[0];" + NL + "                            this.setProperty(\"";
  protected final String TEXT_35 = "\", pattern_";
  protected final String TEXT_36 = " + \";\" + FormatterUtils.format_Date(";
  protected final String TEXT_37 = ", pattern_";
  protected final String TEXT_38 = "));" + NL + "                        } else {" + NL + "                            this.setProperty(\"";
  protected final String TEXT_39 = "\", FormatterUtils.format_Date(";
  protected final String TEXT_40 = "));" + NL + "                        }";
  protected final String TEXT_41 = NL + "                        this.setProperty(\"";
  protected final String TEXT_42 = "\", ";
  protected final String TEXT_43 = ".toString());";
  protected final String TEXT_44 = NL + "                    }";
  protected final String TEXT_45 = NL + "            }" + NL + "        ";
  protected final String TEXT_46 = NL + "\t\tpublic String ";
  protected final String TEXT_47 = ";" + NL + "\t\tpublic String get";
  protected final String TEXT_48 = "() {" + NL + "\t\t\treturn this.";
  protected final String TEXT_49 = ";" + NL + "\t\t}";
  protected final String TEXT_50 = NL + "\t\tpublic ";
  protected final String TEXT_51 = " ";
  protected final String TEXT_52 = ";" + NL + "\t\tpublic ";
  protected final String TEXT_53 = " get";
  protected final String TEXT_54 = ";" + NL + "\t\t}" + NL + "" + NL + "\t\t";
  protected final String TEXT_55 = NL + "\t\t    public void set";
  protected final String TEXT_56 = "(";
  protected final String TEXT_57 = ") {" + NL + "                this.";
  protected final String TEXT_58 = " = ";
  protected final String TEXT_59 = ";" + NL + "            }" + NL + "\t\t";
  protected final String TEXT_60 = NL + "                public String getProperty(String key) {" + NL + "                    return getProperty(key, null);" + NL + "                }" + NL + "\t\t" + NL + "                public String getProperty(String key, String defaultValue) {" + NL + "                    if (key != null && !key.isEmpty()) {" + NL + "                        try {" + NL + "                            java.lang.reflect.Method m = this.getClass()" + NL + "                                .getDeclaredMethod(\"get\" + Character.toUpperCase(key.charAt(0)) + key.substring(1));" + NL + "                            Object o = m.invoke(this);" + NL + "                            if(o != null){" + NL + "                                return String.valueOf(o);" + NL + "                            }else {" + NL + "                                return defaultValue;" + NL + "                            }" + NL + "                        } catch (Exception e) {" + NL + "                            return defaultValue;" + NL + "                        }" + NL + "                    }" + NL + "                    return null;" + NL + "                }";
  protected final String TEXT_61 = NL + "\t}" + NL + "" + NL + "\tprivate static String contextStr = \"";
  protected final String TEXT_62 = "\";" + NL + "" + NL + "\t" + NL + "\tpublic void setContextName(String contextName){" + NL + "\t\tcontextStr = contextName;" + NL + "\t}" + NL;
  protected final String TEXT_63 = NL + "\tprivate int portStats = -1;" + NL + "\tprivate String clientHost = \"localhost\";" + NL + "\tprivate String pid;";
  protected final String TEXT_64 = NL + NL + "\tprivate final Properties context_param = new Properties();" + NL;
  protected final String TEXT_65 = NL + "    " + NL + "        public static void main(String... args){" + NL + "            String[] resetArgs = resetArgs(args);" + NL + "            org.springframework.context.ApplicationContext applicationContext = new org.springframework.boot.SpringApplication(";
  protected final String TEXT_66 = ".class).run(resetArgs);" + NL + "            org.apache.camel.spring.boot.CamelSpringBootApplicationController applicationController = applicationContext.getBean(org.apache.camel.spring.boot.CamelSpringBootApplicationController.class);" + NL + "            applicationController.blockMainThread();" + NL + "        }" + NL + "    ";
  protected final String TEXT_67 = NL + NL + "        public static void main(String[] args){" + NL + "            int exitCode = new ";
  protected final String TEXT_68 = "().runJobInTOS(args);" + NL + "            if(exitCode != 0) {" + NL + "                System.exit(exitCode);" + NL + "            }" + NL + "        }" + NL;
  protected final String TEXT_69 = NL + "\t@org.junit.Test" + NL + "\tpublic void test";
  protected final String TEXT_70 = "() throws java.lang.Exception {" + NL + "\tsetUp();";
  protected final String TEXT_71 = NL + "\t\t// get the mock endpoint" + NL + "\t\tMockEndpoint mock_";
  protected final String TEXT_72 = " = getMockEndpoint(\"mock:";
  protected final String TEXT_73 = NL + "\t\t\t\tjava.util.List headers = new org.apache.camel.component.dataset.FileDataSet(";
  protected final String TEXT_74 = ").getDefaultBodies();" + NL + "\t\t\t\tfor (Object header : headers) {" + NL + "\t\t\t\t\tmock_";
  protected final String TEXT_75 = ".expectedHeaderValuesReceivedInAnyOrder(" + NL + "\t\t\t\t\theader.toString().split(\";\")[0]," + NL + "\t\t\t\t\theader.toString().split(\";\")[1]);" + NL + "\t\t\t\t}";
  protected final String TEXT_76 = NL + "\t\t\t\t\tmock_";
  protected final String TEXT_77 = ".expectedHeaderValuesReceivedInAnyOrder(";
  protected final String TEXT_78 = ", ";
  protected final String TEXT_79 = ");";
  protected final String TEXT_80 = NL + "\t\t\t\torg.apache.camel.component.dataset.FileDataSet fileDataSet_";
  protected final String TEXT_81 = " = new org.apache.camel.component.dataset.FileDataSet(";
  protected final String TEXT_82 = ");" + NL + "\t\t\t\tmock_";
  protected final String TEXT_83 = ".expectedBodiesReceived(fileDataSet_";
  protected final String TEXT_84 = ".getDefaultBodies());";
  protected final String TEXT_85 = ".expectedBodiesReceived(";
  protected final String TEXT_86 = NL + "\t\t\tmock_";
  protected final String TEXT_87 = ".expectedMessageCount(";
  protected final String TEXT_88 = NL + "\t\t\t\t\t\tmock_";
  protected final String TEXT_89 = ".whenAnyExchangeReceived(new org.apache.camel.Processor(){" + NL + "\t\t\t\t\t\t\tpublic void process(org.apache.camel.Exchange exchange) throws Exception{" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_90 = NL + "\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t});";
  protected final String TEXT_91 = NL + "\t\t\t// send to endpoint" + NL + "\t\t\t";
  protected final String TEXT_92 = NL + "\t\t";
  protected final String TEXT_93 = NL + NL + "    }// end of test";
  protected final String TEXT_94 = NL + NL + "    @Override" + NL + "    public String[][] runJob(String[] args) {" + NL + "\t\tint exitCode = runJobInTOS(args);" + NL + "\t\treturn new String[][] { { Integer.toString(exitCode) } };" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public int runJobInTOS(String[] args) {" + NL + "\t\tString lastStr = \"\";" + NL + "        for (String arg : args) {" + NL + "        \tif (arg.equalsIgnoreCase(\"--context_param\")) {" + NL + "                lastStr = arg;" + NL + "            } else if (lastStr.equals(\"\")) {" + NL + "                evalParam(arg);" + NL + "            } else {" + NL + "                evalParam(lastStr + \" \" + arg);" + NL + "                lastStr = \"\";" + NL + "            }" + NL + "        }" + NL;
  protected final String TEXT_95 = NL + "    \tif(pid == null) {" + NL + "\t    \tpid = TalendString.getAsciiRandomString(6);" + NL + "\t    }";
  protected final String TEXT_96 = NL + "\t\ttry {" + NL + "\t\t\trun();" + NL + "\t\t} catch (java.lang.Exception e) {" + NL + "\t\t\tSystem.err.println(e.getMessage());" + NL + "\t\t\te.printStackTrace();" + NL + "\t\t\treturn 1;" + NL + "\t\t}" + NL + "\t\treturn 0;" + NL + "\t}" + NL;
  protected final String TEXT_97 = NL + "    \t//for the issue APPINT-34429" + NL + "\t\t/**" + NL + "\t\t * read context values from specified context" + NL + "\t\t * @parameter contextName : the name of context while will be used" + NL + "\t\t */" + NL + "\t\tprivate void readContextValues(String contextName){" + NL + "\t\t\t";
  protected final String TEXT_98 = NL + "\t\t\t\t\tString pwd_";
  protected final String TEXT_99 = "_value = context.getProperty(\"";
  protected final String TEXT_100 = "\");" + NL + "\t\t\t\t\tif(pwd_";
  protected final String TEXT_101 = "_value!=null) {" + NL + "\t\t\t\t\t\tif(context_param.containsKey(\"";
  protected final String TEXT_102 = "\")) {//no need to decrypt if it come from program argument or parent job runtime" + NL + "\t\t\t\t\t\t\tcontext.set";
  protected final String TEXT_103 = "(pwd_";
  protected final String TEXT_104 = "_value);" + NL + "\t\t\t\t\t\t} else if (!pwd_";
  protected final String TEXT_105 = "_value.isEmpty()) {" + NL + "\t\t\t\t\t\t\ttry {" + NL + "\t\t\t\t\t\t\t\tcontext.set";
  protected final String TEXT_106 = "(routines.system.PasswordEncryptUtil.decryptPassword(pwd_";
  protected final String TEXT_107 = "_value));" + NL + "\t\t\t\t\t\t\t} catch (java.lang.RuntimeException e) {" + NL + "\t\t\t\t\t\t\t\t//do nothing" + NL + "\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_108 = NL + "\t\t}";
  protected final String TEXT_109 = NL + "            private boolean contextValuesRead = false;" + NL + "" + NL + "            /**" + NL + "             * read context values from specified context" + NL + "             * @parameter contextName : the name of context while will be used" + NL + "             */" + NL + "            private void readContextValues(String contextName, org.apache.camel.CamelContext camelContext){" + NL + "                if (contextValuesRead) {" + NL + "                    return;" + NL + "                }" + NL + "                contextValuesRead = true;" + NL + "                try {" + NL + "                    java.io.InputStream inContext = ";
  protected final String TEXT_110 = ".class.getClassLoader().getResourceAsStream(\"";
  protected final String TEXT_111 = "/contexts/\"+contextName+\".properties\");" + NL + "" + NL + "                    if (inContext!=null) {" + NL + "                        try {" + NL + "                            context.load(inContext);" + NL + "\t\t\t\tif (null != ";
  protected final String TEXT_112 = NL + "\t\t\t\t\t\t\t\tcontext()" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_113 = NL + "\t\t\t\t\t\t\t\tcamelContext" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_114 = NL + "\t\t\t\t.getPropertiesComponent()) {" + NL + "\t\t\t\t" + NL + "\t\t\t\t\t";
  protected final String TEXT_115 = NL + "\t\t\t\t\t\torg.apache.camel.component.properties.PropertiesComponent props = (org.apache.camel.component.properties.PropertiesComponent) context().getComponent(\"TestR_properties\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_116 = NL + "\t\t\t\t\t\torg.apache.camel.component.properties.PropertiesComponent props = (org.apache.camel.component.properties.PropertiesComponent) camelContext.getPropertiesComponent();" + NL + "\t\t\t\t\t";
  protected final String TEXT_117 = NL + "\t                " + NL + "\t                \tcontext.putAll(props.getOverrideProperties());" + NL + "\t\t\t\t}" + NL + "                        } finally {" + NL + "                            inContext.close();" + NL + "                        }" + NL + "                    }else{" + NL + "                        //print info and job continue to run, for case: context_param is not empty." + NL + "                        System.err.println(\"Could not find the context \" + contextName);" + NL + "                    }" + NL + "" + NL + "                    if(!context_param.isEmpty()) {" + NL + "                        context.putAll(context_param);" + NL + "                    }";
  protected final String TEXT_118 = NL + "                            String pwd_";
  protected final String TEXT_119 = "\");" + NL + "                            context.";
  protected final String TEXT_120 = " = null;" + NL + "                            if(pwd_";
  protected final String TEXT_121 = "_value!=null) {" + NL + "                                if(context_param.containsKey(\"";
  protected final String TEXT_122 = "\")) {//no need to decrypt if it come from program argument or parent job runtime" + NL + "                                    context.";
  protected final String TEXT_123 = " = pwd_";
  protected final String TEXT_124 = "_value;" + NL + "                                } else if (!pwd_";
  protected final String TEXT_125 = "_value.isEmpty()) {" + NL + "                                    try {" + NL + "                                        context.";
  protected final String TEXT_126 = " = routines.system.PasswordEncryptUtil.decryptPassword(pwd_";
  protected final String TEXT_127 = "_value);" + NL + "                                        context.put(\"";
  protected final String TEXT_128 = "\",context.";
  protected final String TEXT_129 = ");" + NL + "                                    } catch (java.lang.RuntimeException e) {" + NL + "                                        //do nothing" + NL + "                                    }" + NL + "                                } else {" + NL + "                                    context.";
  protected final String TEXT_130 = "_value;" + NL + "                                    context.put(\"";
  protected final String TEXT_131 = ");" + NL + "                                }" + NL + "                            }";
  protected final String TEXT_132 = NL + "                        String context_";
  protected final String TEXT_133 = "\");" + NL + "                        if (context_";
  protected final String TEXT_134 = "_value == null){" + NL + "                            context_";
  protected final String TEXT_135 = "_value = \"\";" + NL + "                        }" + NL + "                        int context_";
  protected final String TEXT_136 = "_pos = context_";
  protected final String TEXT_137 = "_value.indexOf(\";\");" + NL + "                        String context_";
  protected final String TEXT_138 = "_pattern =  \"yyyy-MM-dd HH:mm:ss\";" + NL + "                        if(context_";
  protected final String TEXT_139 = "_pos > -1){" + NL + "                            context_";
  protected final String TEXT_140 = "_pattern = context_";
  protected final String TEXT_141 = "_value.substring(0, context_";
  protected final String TEXT_142 = "_pos);" + NL + "                            context_";
  protected final String TEXT_143 = "_value = context_";
  protected final String TEXT_144 = "_value.substring(context_";
  protected final String TEXT_145 = "_pos + 1);" + NL + "                        }" + NL + "                        " + NL + "                        context.";
  protected final String TEXT_146 = " = RuntimeUtils.getDate(context_";
  protected final String TEXT_147 = "_pattern, context_";
  protected final String TEXT_148 = "_value);";
  protected final String TEXT_149 = NL + "                            context.";
  protected final String TEXT_150 = "=(";
  protected final String TEXT_151 = ") context.getProperty(\"";
  protected final String TEXT_152 = NL + "                        context.";
  protected final String TEXT_153 = "= new java.text.StringCharacterIterator(context.getProperty(\"";
  protected final String TEXT_154 = "\")).first();";
  protected final String TEXT_155 = " " + NL + "                            try{" + NL + "                                context.";
  protected final String TEXT_156 = "=routines.system.ParserUtils.parseTo_";
  protected final String TEXT_157 = " (context.getProperty(\"";
  protected final String TEXT_158 = "\"));" + NL + "                            }catch(NumberFormatException e){" + NL + "                                context.";
  protected final String TEXT_159 = "=null;" + NL + "                            }";
  protected final String TEXT_160 = NL + "\t\t\t\t\t\t\tif (\"test";
  protected final String TEXT_161 = "\".equals(getTestMethodName())) {" + NL + "\t\t";
  protected final String TEXT_162 = NL + "\t\t\t\t\t\t\t\t\t\t\tjava.net.URL url_";
  protected final String TEXT_163 = " = getClass().getResource(\"";
  protected final String TEXT_164 = "\");" + NL + "\t\t\t\t\t\t\t\t\t\t\tcontext.";
  protected final String TEXT_165 = "=java.net.URLDecoder.decode(url_";
  protected final String TEXT_166 = ".getPath(), \"";
  protected final String TEXT_167 = "\");" + NL + "\t\t";
  protected final String TEXT_168 = NL + "\t\t\t\t\t\t\t}" + NL + "\t\t";
  protected final String TEXT_169 = NL + "                } catch (java.io.IOException ie) {" + NL + "                    System.err.println(\"Could not load context \"+contextName);" + NL + "                    ie.printStackTrace();" + NL + "                }" + NL + "            }";
  protected final String TEXT_170 = NL + "    " + NL + "" + NL + "\tprivate void evalParam(String arg) {" + NL + "        if (arg.startsWith(\"--context=\")) {" + NL + "            contextStr = arg.substring(10);" + NL + "        }else if (arg.startsWith(\"--context_param\")) {" + NL + "            String keyValue = arg.substring(16);" + NL + "            int index = -1;" + NL + "            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {" + NL + "                context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));" + NL + "            }" + NL + "        }";
  protected final String TEXT_171 = NL + "        else if (arg.startsWith(\"--stat_port=\")) {" + NL + "\t\t\tString portStatsStr = arg.substring(12);" + NL + "\t\t\tif (portStatsStr != null && !portStatsStr.equals(\"null\")) {" + NL + "\t\t\t\tportStats = Integer.parseInt(portStatsStr);" + NL + "\t\t\t}" + NL + "    \t} else if (arg.startsWith(\"--client_host=\")) {" + NL + "    \t\tclientHost = arg.substring(14);" + NL + "    \t} else if (arg.startsWith(\"--pid=\")) {" + NL + "    \t\tpid = arg.substring(6);" + NL + "    \t}";
  protected final String TEXT_172 = NL + "\t}" + NL + "}" + NL;

    public static java.util.Collection<String> getRoutelets(final org.talend.core.model.process.IProcess process) {
        java.util.Collection<String> routelets = new java.util.HashSet<String>();
        addRoutelets(routelets, process);
        return routelets;
    }

    private static void addRoutelets(final java.util.Collection<String> routelets, final org.talend.core.model.process.IProcess process) {
        for (org.talend.core.model.process.INode node : process.getGeneratingNodes()) {
            if ("Routelets".equals(node.getComponent().getOriginalFamilyName())) {
                org.talend.core.model.process.IProcess2 routelet = (org.talend.core.model.process.IProcess2) node.getComponent().getProcess();
                final String clazz = org.talend.core.model.utils.RouteUtil.resolveClassName(routelet);
                if (routelets.add(clazz)) {
                    addRoutelets(routelets, routelet);
                }
            }
        }
    }

	static boolean routeContextCodeGenerated = false;
	
	public static boolean hasRouteletComponent(final org.talend.core.model.process.IProcess process){
		for (org.talend.core.model.process.INode node : process.getGeneratingNodes()) {
            if ("Routelets".equals(node.getComponent().getOriginalFamilyName())) {
            	return true;
            }
        }
		return false;
	}

	public static String generateAddRouteletMethod(final org.talend.core.model.process.IProcess process){
		if(hasRouteletComponent(process)){
			routeContextCodeGenerated = false;
			StringBuilder code = new StringBuilder("private void addRoutelets() throws Exception{\n");
			addRouteletsWithContext(process, code);
			return code.append("}\n").toString();
		}else{
			return "";
		}
	}
	
	public static String generateAddRouteletMethodWithCamelContext(final org.talend.core.model.process.IProcess process){
		if(hasRouteletComponent(process)){
			routeContextCodeGenerated = false;
			StringBuilder code = new StringBuilder("private void addRoutelets(org.apache.camel.CamelContext camelContext) throws Exception{\n");
			addRouteletsWithCamelContext(process, code);
			return code.append("}\n").toString();
		}else{
			return "";
		}
	}
	
	public static String generateAddRoutesMethod(final org.talend.core.model.process.IProcess process){
		StringBuilder code = new StringBuilder();
		boolean exportTypeSpringBoot = process.getClass().getName().endsWith("MicroServiceProcess");
        for (org.talend.core.model.process.INode node : process.getGeneratingNodes()) {
            if ("Routelets".equals(node.getComponent().getOriginalFamilyName())) {
	            boolean useSelected = "true".equals(ElementParameterParser.getValue(node, "__SELECTED_CONTEXT__"));
	            boolean useRoute    = "true".equals(ElementParameterParser.getValue(node, "__ROUTE_CONTEXT__"));
	            boolean useRoutelet    = "true".equals(ElementParameterParser.getValue(node, "__ROUTELET_CONTEXT__"));
	            List<Map<String,String>> userDefinedParams = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__CONTEXT_PARAM__");
	            org.talend.core.model.process.IProcess2 routelet = (org.talend.core.model.process.IProcess2) node.getComponent().getProcess();
	            final String routeletClass = org.talend.core.model.utils.RouteUtil.resolveClassName(routelet);
	            final String instanceName = routeletClass.substring(routeletClass.lastIndexOf('.')+1);
	            code.append(routeletClass +" "+ instanceName +"=new "+ routeletClass+ "();\n");
		    	code.append("org.apache.camel.component.properties.PropertiesComponent "+ instanceName +"_props = new org.apache.camel.component.properties.PropertiesComponent();\n");
	            if(useRoute){
	            	if(!routeContextCodeGenerated && !exportTypeSpringBoot){
	            		routeContextCodeGenerated=true;
				        code.append("final Map<String, String> routeContext = new HashMap<String, String>();\n" + 
			                "readContextValues(contextStr);\n" + 
			                "for (java.util.Map.Entry<Object, Object> entry : context.entrySet()) {\n" + 
			                "    routeContext.put((String) entry.getKey(), (String) entry.getValue());\n" + 
			                "}\n");
			        	code.append(instanceName +".setArguments(routeContext);\n");
	            	}
	            	code.append(instanceName+".setContextName(contextStr);\n");
	            }else if(useSelected){
	            	code.append(instanceName+".setContextName(\"" + ElementParameterParser.getValue(node, "__PROCESS_TYPE_CONTEXT__") + "\");\n");
	            }else if(useRoutelet){
	            	code.append(instanceName+".setContextName(\""+node.getComponent().getProcess().getContextManager().getDefaultContext().getName()+"\");\n");
	            }	            
	            if(userDefinedParams!=null && userDefinedParams.size()>0){
	            	code.append("final Properties overwriteContext_"+instanceName+" = new Properties();\n");
	            	if (!exportTypeSpringBoot) {
	                    code.append("readContextValues(contextStr, camelContext);\n");
	            	} 
	            	for(Map<String,String> map :userDefinedParams){
	            		code.append("overwriteContext_"+instanceName+".put(\""+map.get("Parameters")+"\", "+map.get("Values")+");\n"); 
	            	}
	            	code.append(instanceName +".setArguments(overwriteContext_"+instanceName+");\n");
					code.append(instanceName +"_props.setOverrideProperties(overwriteContext_" + instanceName + ");\n");
					code.append("camelContext.setPropertiesComponent(" + instanceName +"_props);\n\n");
	            }

	            if (exportTypeSpringBoot) code.append("camelContext.addRoutes("+instanceName+");");
            }
        }
        
        return code.toString();
    }
    
    private static void addRouteletsWithCamelContext(final org.talend.core.model.process.IProcess process, StringBuilder code){
		boolean exportTypeSpringBoot = process.getClass().getName().endsWith("MicroServiceProcess");
        for (org.talend.core.model.process.INode node : process.getGeneratingNodes()) {
            if ("Routelets".equals(node.getComponent().getOriginalFamilyName())) {
	            boolean useSelected = "true".equals(ElementParameterParser.getValue(node, "__SELECTED_CONTEXT__"));
	            boolean useRoute    = "true".equals(ElementParameterParser.getValue(node, "__ROUTE_CONTEXT__"));
	            boolean useRoutelet = "true".equals(ElementParameterParser.getValue(node, "__ROUTELET_CONTEXT__"));
	            List<Map<String,String>> userDefinedParams = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__CONTEXT_PARAM__");
	            org.talend.core.model.process.IProcess2 routelet = (org.talend.core.model.process.IProcess2) node.getComponent().getProcess();
	            final String routeletClass = org.talend.core.model.utils.RouteUtil.resolveClassName(routelet);
	            final String instanceName = routeletClass.substring(routeletClass.lastIndexOf('.')+1);
	            code.append(routeletClass +" "+ instanceName +"=new "+ routeletClass+ "();\n");
	            code.append("camelContext.getRegistry().bind(\"" + instanceName +"\", new "+ routeletClass+ "());\n");
	            
	            if(useRoute){
	            	code.append(instanceName+".setContextName(contextStr);\n");	            	
	            }else if(useSelected){
	            	code.append(instanceName+".setContextName(\"" + ElementParameterParser.getValue(node, "__PROCESS_TYPE_CONTEXT__") + "\");\n");
	            }else if(useRoutelet) {
	            	code.append(instanceName+".setContextName(\""+node.getComponent().getProcess().getContextManager().getDefaultContext().getName()+"\");\n");
	            }
	            if(userDefinedParams!=null && userDefinedParams.size()>0){
	                if (!exportTypeSpringBoot) {
	                    code.append("readContextValues(contextStr, camelContext);\n");
	                }
	            	code.append("final Map<String, String> overwriteContext_"+instanceName+" = new HashMap<String, String>();\n"); 
	            	for(Map<String,String> map :userDefinedParams){
	            		code.append("overwriteContext_"+instanceName+".put(\""+map.get("Parameters")+"\", "+map.get("Values")+");\n"); 
	            	}
	            	code.append(instanceName +".setArguments(overwriteContext_"+instanceName+");\n");
	            }
            }
        }
    }

    private static void addRouteletsWithContext(final org.talend.core.model.process.IProcess process, StringBuilder code){
		boolean exportTypeSpringBoot = process.getClass().getName().endsWith("MicroServiceProcess");
        for (org.talend.core.model.process.INode node : process.getGeneratingNodes()) {
            if ("Routelets".equals(node.getComponent().getOriginalFamilyName())) {
	            boolean useSelected = "true".equals(ElementParameterParser.getValue(node, "__SELECTED_CONTEXT__"));
	            boolean useRoute    = "true".equals(ElementParameterParser.getValue(node, "__ROUTE_CONTEXT__"));
	            boolean useRoutelet = "true".equals(ElementParameterParser.getValue(node, "__ROUTELET_CONTEXT__"));
	            List<Map<String,String>> userDefinedParams = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__CONTEXT_PARAM__");
	            org.talend.core.model.process.IProcess2 routelet = (org.talend.core.model.process.IProcess2) node.getComponent().getProcess();
	            final String routeletClass = org.talend.core.model.utils.RouteUtil.resolveClassName(routelet);
	            final String instanceName = routeletClass.substring(routeletClass.lastIndexOf('.')+1);
	            code.append(routeletClass +" "+ instanceName +"=new "+ routeletClass+ "(){@Override\n public void configure() throws java.lang.Exception {this.doConfig();}};\n");
	            if(useRoute){
	            	code.append(instanceName+".setContextName(contextStr);\n");	            	
	            }else if(useSelected){
	            	//do nothing
	            }else if(useRoutelet) {
	            	code.append(instanceName+".setContextName(\""+node.getComponent().getProcess().getContextManager().getDefaultContext().getName()+"\");\n");
	            }
	            if(userDefinedParams!=null && userDefinedParams.size()>0){
	                if (!exportTypeSpringBoot) {
	                    code.append("readContextValues(contextStr, camelContext);\n");
	                }
	            	code.append("final Map<String, String> overwriteContext_"+instanceName+" = new HashMap<String, String>();\n"); 
	            	for(Map<String,String> map :userDefinedParams){
	            		code.append("overwriteContext_"+instanceName+".put(\""+map.get("Parameters")+"\", "+map.get("Values")+");\n"); 
	            	}
	            	code.append(instanceName +".setArguments(overwriteContext_"+instanceName+");\n");
	            }
            }
        }
    }

    public String generate(CodeGeneratorArgument argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    Vector v = (Vector) codeGenArgument.getArgument();
    IProcess process = (IProcess) v.get(0);
    Boolean hasSpring = (Boolean) v.get(1);

	List<? extends INode> processNodes = (List<? extends INode>) process.getGeneratingNodes();
	List<IContextParameter> params = process.getContextManager().getDefaultContext().getContextParameterList();

	final boolean stats = codeGenArgument.isStatistics();
	
    boolean isTestContainer=ProcessUtils.isTestContainer(process);
	String className = isTestContainer ? process.getName() + "Test" : process.getName();
	
    boolean exportTypeSpringBoot = false;
    boolean isHttpBasic=false;
    if(process.getClass().getName().endsWith("MicroServiceProcess")){
        exportTypeSpringBoot = true;
    }


    stringBuffer.append(TEXT_1);
    
	if(isTestContainer){

    stringBuffer.append(TEXT_2);
    
	}//end if(isTestContainer)

    stringBuffer.append(TEXT_3);
    
                if (hasSpring) {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(className.toLowerCase());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_8);
    
								if(hasRouteletComponent(process)){
						
    stringBuffer.append(TEXT_9);
    
								}
						
    
                } else { // Routelet

    stringBuffer.append(TEXT_10);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_11);
    
                }
    if (stats) {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(codeGenArgument.getJobName() );
    stringBuffer.append(TEXT_13);
    
                for (INode node : processNodes) {
                    if (node.isActivate()) {
                        for (org.talend.core.model.process.IConnection conn : node.getIncomingConnections()) {
                            String label = "";
                            String labelValue = String.valueOf(node.getElementParameter("LABEL").getValue());
                            String labelValueEscaped = ProcessUtils.escapeJava(labelValue);
                            if (!"__UNIQUE_NAME__".equals(labelValueEscaped)) {
                                label = '_' + labelValueEscaped;
                            }

    stringBuffer.append(TEXT_14);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_15);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(label);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(conn.getUniqueName());
    stringBuffer.append(TEXT_17);
    
                        }
                    }
                }

    stringBuffer.append(TEXT_18);
    
} //if stats

    stringBuffer.append(TEXT_19);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_20);
    stringBuffer.append( generateAddRouteletMethodWithCamelContext(process));
    stringBuffer.append(TEXT_21);
    
        if(exportTypeSpringBoot){
    
    stringBuffer.append(TEXT_22);
    } else { 
    stringBuffer.append(TEXT_23);
     for (IContextParameter ctxParam : params){
                    String cParaName = ctxParam.getName(); 
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_25);
     if(ctxParam.getType().equals("id_Date")){ 
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(ctxParam.getValue() );
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_40);
     } else { 
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_43);
     } 
    stringBuffer.append(TEXT_44);
     } 
    stringBuffer.append(TEXT_45);
     } 
    stringBuffer.append(TEXT_21);
    
for (IContextParameter ctxParam : params) {
	if(ctxParam.getType().equals("id_List Of Value") || ctxParam.getType().equals("id_File") || ctxParam.getType().equals("id_Directory")) { 
    stringBuffer.append(TEXT_46);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(Character.toUpperCase(ctxParam.getName().charAt(0)) + ctxParam.getName().substring(1));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_49);
    	} else { 
    stringBuffer.append(TEXT_50);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true));
    stringBuffer.append(TEXT_51);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(Character.toUpperCase(ctxParam.getName().charAt(0)) + ctxParam.getName().substring(1));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_54);
     if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_55);
    stringBuffer.append(Character.toUpperCase(ctxParam.getName().charAt(0)) + ctxParam.getName().substring(1));
    stringBuffer.append(TEXT_56);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true));
    stringBuffer.append(TEXT_51);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_59);
    
		  }
		
    	}
}

    
	if(exportTypeSpringBoot){

    stringBuffer.append(TEXT_60);
    	
}

    stringBuffer.append(TEXT_61);
    stringBuffer.append(codeGenArgument.getContextName() );
    stringBuffer.append(TEXT_62);
    
    if(stats) {

    stringBuffer.append(TEXT_63);
    
    }

    stringBuffer.append(TEXT_64);
     
        if(exportTypeSpringBoot){
    
    stringBuffer.append(TEXT_65);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_66);
     } else { 
    stringBuffer.append(TEXT_67);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_68);
     }
 
if (isTestContainer) {

	List<String> instanceList =  ProcessUtils.getTestInstances(process);
	for(String instance : instanceList)
	{

    stringBuffer.append(TEXT_69);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_70);
    
	String assertPart = "";

	for (INode node: process.getNodesOfType("cMock")) {
		String cid = node.getUniqueName();
		boolean needCreatePart = false;

    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    
		String testMsgCount = ElementParameterParser.getValue(node, "__TEST_COUNT__");
		String testHeader = ElementParameterParser.getValue(node, "__TEST_HEADER__");
		String testHeaderWithContext = ElementParameterParser.getValue(node, "__TEST_HEADER_CONTEXT__");
		String testHeaderWithTable = ElementParameterParser.getValue(node, "__TEST_HEADER_TABLE__");
		String testBody = ElementParameterParser.getValue(node, "__TEST_BODY__");
		String testBodyWithContext = ElementParameterParser.getValue(node, "__TEST_BODY_CONTEXT__");
		String testBodyWithTable = ElementParameterParser.getValue(node, "__TEST_BODY_TABLE__");
		String testSimulate = ElementParameterParser.getValue(node, "__TEST_SIMULATE__");
		String testWaitTime = ElementParameterParser.getValue(node, "__TEST_WAIT_TIME__");
		if("true".equals(testHeader)){
			needCreatePart = true;
			if("true".equals(testHeaderWithContext)){
				String headerContext = ElementParameterParser.getValue(node, "__TEST_HEADER_USE_CONTEXT__");

    stringBuffer.append(TEXT_73);
    stringBuffer.append(headerContext);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    
			}else if("true".equals(testHeaderWithTable)){
				String headerContext = ElementParameterParser.getValue(node, "__TEST_HEADER_USE_TABLE__");
				//mCode += headerContext;
				List<Map<String, String>> tableValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__TEST_HEADER_USE_TABLE__");
				for(Map<String, String> headersMap: tableValues){
		 			String headerName = headersMap.get("HEADER_NAMES");
		 			String headerValue = headersMap.get("HEADER_VALUES");

    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(headerName);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(headerValue);
    stringBuffer.append(TEXT_79);
    
				}
			}
		}
		if("true".equals(testBody)){
			needCreatePart = true;
			if("true".equals(testBodyWithContext)){
				String bodyContext = ElementParameterParser.getValue(node, "__TEST_BODY_USE_CONTEXT__");

    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(bodyContext);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    				
			}else if("true".equals(testBodyWithTable)){
				String bodyContext = ElementParameterParser.getValue(node, "__TEST_BODY_USE_TABLE__");
				List<Map<String, String>> bodyValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__TEST_BODY_USE_TABLE__");
				String list = "";				
				for(Map<String, String> valuesMap: bodyValues){
		 			String bodyValue = valuesMap.get("BODY_VALUES");
		 			list += bodyValue + ",";
		 		}
		        if(list.length() > 1 &&  list.endsWith(",")){
		           list = list.substring(0, list.length() - 1);

    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(list);
    stringBuffer.append(TEXT_79);
    
		        }
			}
		}
		if("true".equals(testMsgCount)){
			needCreatePart = true;
			String msgCount = ElementParameterParser.getValue(node, "__MESSAGE_COUNT__");

    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(msgCount);
    stringBuffer.append(TEXT_79);
    
		}
		if("true".equals(testSimulate)){
			String processorName = ElementParameterParser.getValue(node, "__PROCESSOR_ENDPOINT__");
			if(!"".equals(processorName)){
		    List<? extends INode> cProcessorNodes = process.getNodesOfType("cProcessor");
				for(INode n: cProcessorNodes){
					if(n.getUniqueName().equals(processorName)){
						String code = ElementParameterParser.getValue(n, "__CODE__");

    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(code);
    stringBuffer.append(TEXT_90);
    
						break;
					}
				}
			}
		}
		if(needCreatePart){
			assertPart += "\nmock_" + cid + ".setResultWaitTime(" + testWaitTime + "L);";
			assertPart += "\nmock_"+cid+".assertIsSatisfied();";
		}
	}

	for (INode node: process.getNodesOfType("OUTPUT")) {
		String tCode = ElementParameterParser.getValue(node, "__INFORMATION__");
		if(!"".equals(tCode)){

    stringBuffer.append(TEXT_91);
    stringBuffer.append(tCode);
    		
	        break;
		}
    }// end of for INPUT

    stringBuffer.append(TEXT_92);
    stringBuffer.append(assertPart);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(instance);
    
	}// end pf for
}// end of if

    stringBuffer.append(TEXT_94);
    
    if (stats) {

    stringBuffer.append(TEXT_95);
    
    }

    stringBuffer.append(TEXT_96);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_97);
    
			for (IContextParameter ctxParam :params){ //start for
				if (ctxParam.getType().equals("id_Password")) {
			
    stringBuffer.append(TEXT_98);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(Character.toUpperCase(ctxParam.getName().charAt(0)) + ctxParam.getName().substring(1));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(Character.toUpperCase(ctxParam.getName().charAt(0)) + ctxParam.getName().substring(1));
    stringBuffer.append(TEXT_106);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_107);
    
					continue;
				}
			}
    stringBuffer.append(TEXT_108);
     }else{ 
    stringBuffer.append(TEXT_109);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_110);
    stringBuffer.append(JavaResourcesHelper.getJobClassPackageFolder(process) );
    stringBuffer.append(TEXT_111);
     if(isTestContainer){ 
    stringBuffer.append(TEXT_112);
     } else {
    stringBuffer.append(TEXT_113);
     } 
    stringBuffer.append(TEXT_114);
     if(isTestContainer){ 
    stringBuffer.append(TEXT_115);
     } else {
    stringBuffer.append(TEXT_116);
     } 
    stringBuffer.append(TEXT_117);
     
                    //for bug TDI-22398
                    for (IContextParameter ctxParam :params){ //start for

                        if (ctxParam.getType().equals("id_Password")) {
                    
    stringBuffer.append(TEXT_118);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_131);
    
                            continue;
                        }

                        String typeToGenerate ="String";
                        if( !(ctxParam.getType().equals("id_File") || ctxParam.getType().equals("id_Directory") ||ctxParam.getType().equals("id_List Of Value")))
                        {
                           typeToGenerate=JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true);
                        }
                        if(typeToGenerate.equals("java.util.Date")){ // start if

        
    stringBuffer.append(TEXT_132);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_148);
    
                        }else if(typeToGenerate.equals("Object")||typeToGenerate.equals("String")||typeToGenerate.equals("java.lang.String") ){
        
    stringBuffer.append(TEXT_149);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_17);
    
                        }else if(typeToGenerate.equals("Character")&&ctxParam.getName()!=null){
        
    stringBuffer.append(TEXT_152);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_154);
    
                        }else{
        
    stringBuffer.append(TEXT_155);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_159);
    
                        } //end if
                    } //end for
					
					//reload contexts for each test case instances
					if (isTestContainer) {
						List<String> instanceList =  ProcessUtils.getTestInstances(process);
						String encoding = System.getProperty("file.encoding");
						for(String instance : instanceList)
						{
		
    stringBuffer.append(TEXT_160);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_161);
    
								//for test container
								for(String testData : ProcessUtils.getTestData(process,instance)){
									if(null!=process.getContextManager().getDefaultContext().getContextParameter(testData)){
										String testDataPath =  ProcessUtils.getTestDataValue(process, instance, testData);
										if(testDataPath != null && testDataPath.length() > 0){
		
    stringBuffer.append(TEXT_162);
    stringBuffer.append(testData);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(testDataPath);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(testData);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(testData);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_167);
    
										}//end if
									}//end if
								}//for testData end
		
    stringBuffer.append(TEXT_168);
    
						}
					} //end if
        
    stringBuffer.append(TEXT_169);
    }
    stringBuffer.append(TEXT_170);
    
    if (stats) {

    stringBuffer.append(TEXT_171);
    
    }

    stringBuffer.append(TEXT_172);
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
