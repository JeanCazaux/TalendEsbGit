package org.talend.designer.codegen.translators.common;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.core.ui.branding.AbstractBrandingService;
import org.talend.designer.codegen.i18n.Messages;
import org.talend.designer.codegen.ITalendSynchronizer;
import org.talend.designer.codegen.config.CamelEndpointBuilder;
import org.talend.designer.codegen.config.CamelEndpointBuilder.BuildingValueParamAppender;
import org.talend.designer.codegen.config.CamelEndpointBuilder.ConditionParamAppender;
import org.talend.designer.codegen.config.CamelEndpointBuilder.NodeParamNotDefaultAppender;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.NodeParamsHelper;
import org.talend.designer.runprocess.CodeGeneratorRoutine;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.ProcessUtils;

public class Header_routeJava {

  protected static String nl;
  public static synchronized Header_routeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Header_routeJava result = new Header_routeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "package ";
  protected final String TEXT_3 = ";" + NL;
  protected final String TEXT_4 = NL + "        ";
  protected final String TEXT_5 = " ;";
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * Job: ";
  protected final String TEXT_7 = " Purpose: ";
  protected final String TEXT_8 = "<br>" + NL + " * Description: ";
  protected final String TEXT_9 = " <br>" + NL + " * @author ";
  protected final String TEXT_10 = NL + " * @version ";
  protected final String TEXT_11 = NL + " * @status ";
  protected final String TEXT_12 = NL + " */" + NL;
  protected final String TEXT_13 = NL + NL + NL;
  protected final String TEXT_14 = NL + NL + "/**" + NL + " * Applies configuration properties to a HTTPConduit" + NL + " */" + NL + "class HttpConduitConfigApplier {" + NL + "    private static final String SECURE_HTTP_PREFIX = \"https\";" + NL + "" + NL + "    HttpConduitConfigApplier() {" + NL + "    }" + NL + "" + NL + "    void apply(java.util.Dictionary<String, String> d, org.apache.cxf.transport.http.HTTPConduit c, String address) {" + NL + "        applyClientPolicies(d, c);" + NL + "        applyAuthorization(d, c);" + NL + "        applyProxyAuthorization(d, c);" + NL + "        if (address != null && address.startsWith(SECURE_HTTP_PREFIX)) {" + NL + "            applyTlsClientParameters(d, c);" + NL + "        }" + NL + "    }" + NL + "" + NL + "    private void applyTlsClientParameters(java.util.Dictionary<String, String> d," + NL + "            org.apache.cxf.transport.http.HTTPConduit c) {" + NL + "        java.util.Enumeration<String> keys = d.keys();" + NL + "        org.apache.cxf.configuration.jsse.TLSClientParameters p = c.getTlsClientParameters();" + NL + "        org.apache.cxf.configuration.security.SecureRandomParameters srp = null;" + NL + "        org.apache.cxf.configuration.security.KeyManagersType kmt = null;" + NL + "        org.apache.cxf.configuration.security.TrustManagersType tmt = null;" + NL + "        while (keys.hasMoreElements()) {" + NL + "            String k = keys.nextElement();" + NL + "            if (k.startsWith(\"tlsClientParameters.\")) {" + NL + "                if (p == null) {" + NL + "                    p = new org.apache.cxf.configuration.jsse.TLSClientParameters();" + NL + "                    c.setTlsClientParameters(p);" + NL + "                }" + NL + "                String v = d.get(k);" + NL + "                k = k.substring(\"tlsClientParameters.\".length());" + NL + "" + NL + "                if (\"secureSocketProtocol\".equals(k)) {" + NL + "                    p.setSecureSocketProtocol(v);" + NL + "                } else if (\"sslCacheTimeout\".equals(k)) {" + NL + "                    p.setSslCacheTimeout(Integer.parseInt(v));" + NL + "                } else if (\"jsseProvider\".equals(k)) {" + NL + "                    p.setJsseProvider(v);" + NL + "                } else if (\"disableCNCheck\".equals(k)) {" + NL + "                    p.setDisableCNCheck(Boolean.parseBoolean(v));" + NL + "                } else if (\"useHttpsURLConnectionDefaultHostnameVerifier\".equals(k)) {" + NL + "                    p.setUseHttpsURLConnectionDefaultHostnameVerifier(Boolean.parseBoolean(v));" + NL + "                } else if (\"useHttpsURLConnectionDefaultSslSocketFactory\".equals(k)) {" + NL + "                    p.setUseHttpsURLConnectionDefaultSslSocketFactory(Boolean.parseBoolean(v));" + NL + "                } else if (k.startsWith(\"certConstraints.\")) {" + NL + "                    k = k.substring(\"certConstraints.\".length());" + NL + "                    org.apache.cxf.configuration.security.CertificateConstraintsType cct = p.getCertConstraints();" + NL + "                    if (cct == null) {" + NL + "                        cct = new org.apache.cxf.configuration.security.CertificateConstraintsType();" + NL + "                        p.setCertConstraints(cct);" + NL + "                    }" + NL + "                    org.apache.cxf.configuration.security.DNConstraintsType dnct = null;" + NL + "                    if (k.startsWith(\"SubjectDNConstraints.\")) {" + NL + "                        dnct = cct.getSubjectDNConstraints();" + NL + "                        if (dnct == null) {" + NL + "                            dnct = new org.apache.cxf.configuration.security.DNConstraintsType();" + NL + "                            cct.setSubjectDNConstraints(dnct);" + NL + "                        }" + NL + "                        k = k.substring(\"SubjectDNConstraints.\".length());" + NL + "                    } else if (k.startsWith(\"IssuerDNConstraints.\")) {" + NL + "                        dnct = cct.getIssuerDNConstraints();" + NL + "                        if (dnct == null) {" + NL + "                            dnct = new org.apache.cxf.configuration.security.DNConstraintsType();" + NL + "                            cct.setIssuerDNConstraints(dnct);" + NL + "                        }" + NL + "                        k = k.substring(\"IssuerDNConstraints.\".length());" + NL + "                    }" + NL + "                    if (dnct != null) {" + NL + "                        if (\"combinator\".equals(k)) {" + NL + "                            dnct.setCombinator(org.apache.cxf.configuration.security.CombinatorType.fromValue(v));" + NL + "                        } else if (\"RegularExpression\".equals(k)) {" + NL + "                            dnct.getRegularExpression().add(k);" + NL + "                        }" + NL + "                    }" + NL + "                } else if (k.startsWith(\"secureRandomParameters.\")) {" + NL + "                    k = k.substring(\"secureRandomParameters.\".length());" + NL + "                    if (srp == null) {" + NL + "                        srp = new org.apache.cxf.configuration.security.SecureRandomParameters();" + NL + "                    }" + NL + "                    if (\"algorithm\".equals(k)) {" + NL + "                        srp.setAlgorithm(v);" + NL + "                    } else if (\"provider\".equals(k)) {" + NL + "                        srp.setProvider(v);" + NL + "                    }" + NL + "                } else if (k.startsWith(\"cipherSuitesFilter.\")) {" + NL + "                    k = k.substring(\"cipherSuitesFilter.\".length());" + NL + "                    java.util.StringTokenizer st = new java.util.StringTokenizer(v, \",\");" + NL + "                    org.apache.cxf.configuration.security.FiltersType ft = p.getCipherSuitesFilter();" + NL + "                    if (ft == null) {" + NL + "                        ft = new org.apache.cxf.configuration.security.FiltersType();" + NL + "                        p.setCipherSuitesFilter(ft);" + NL + "                    }" + NL + "                    java.util.List<String> lst = \"include\".equals(k) ? ft.getInclude() : ft.getExclude();" + NL + "                    while (st.hasMoreTokens()) {" + NL + "                        lst.add(st.nextToken());" + NL + "                    }" + NL + "                } else if (k.startsWith(\"cipherSuites\")) {" + NL + "                    java.util.StringTokenizer st = new java.util.StringTokenizer(v, \",\");" + NL + "                    while (st.hasMoreTokens()) {" + NL + "                        p.getCipherSuites().add(st.nextToken());" + NL + "                    }" + NL + "                } else if (k.startsWith(\"trustManagers.\")) {" + NL + "                    tmt = getTrustManagers(tmt, k.substring(\"trustManagers.\".length()), v);" + NL + "                } else if (k.startsWith(\"keyManagers.\")) {" + NL + "                    kmt = getKeyManagers(kmt, k.substring(\"keyManagers.\".length()), v);" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "" + NL + "        try {" + NL + "            if (srp != null) {" + NL + "                p.setSecureRandom(org.apache.cxf.configuration.jsse.TLSParameterJaxBUtils.getSecureRandom(srp));" + NL + "            }" + NL + "            if (kmt != null) {" + NL + "                p.setKeyManagers(org.apache.cxf.configuration.jsse.TLSParameterJaxBUtils.getKeyManagers(kmt));" + NL + "            }" + NL + "            if (tmt != null) {" + NL + "                p.setTrustManagers(org.apache.cxf.configuration.jsse.TLSParameterJaxBUtils.getTrustManagers(tmt));" + NL + "            }" + NL + "        } catch (RuntimeException e) {" + NL + "            throw e;" + NL + "        } catch (Exception e) {" + NL + "            throw new RuntimeException(e);" + NL + "        }" + NL + "    }" + NL + "" + NL + "    private org.apache.cxf.configuration.security.KeyManagersType getKeyManagers(" + NL + "            org.apache.cxf.configuration.security.KeyManagersType keyManagers, String k, String v) {" + NL + "        if (keyManagers == null) {" + NL + "            keyManagers = new org.apache.cxf.configuration.security.KeyManagersType();" + NL + "        }" + NL + "        if (\"factoryAlgorithm\".equals(k)) {" + NL + "            keyManagers.setFactoryAlgorithm(v);" + NL + "        } else if (\"provider\".equals(k)) {" + NL + "            keyManagers.setProvider(v);" + NL + "        } else if (\"keyPassword\".equals(k)) {" + NL + "            keyManagers.setKeyPassword(v);" + NL + "        } else if (k.startsWith(\"keyStore.\")) {" + NL + "            keyManagers.setKeyStore(getKeyStore(keyManagers.getKeyStore(), k.substring(\"keyStore.\".length()), v));" + NL + "        }" + NL + "        return keyManagers;" + NL + "    }" + NL + "" + NL + "    private org.apache.cxf.configuration.security.KeyStoreType getKeyStore(org.apache.cxf.configuration.security.KeyStoreType ks, String k, String v) {" + NL + "        if (ks == null) {" + NL + "            ks = new org.apache.cxf.configuration.security.KeyStoreType();" + NL + "        }" + NL + "        if (\"type\".equals(k)) {" + NL + "            ks.setType(v);" + NL + "        } else if (\"password\".equals(k)) {" + NL + "            ks.setPassword(v);" + NL + "        } else if (\"provider\".equals(k)) {" + NL + "            ks.setProvider(v);" + NL + "        } else if (\"url\".equals(k)) {" + NL + "            ks.setUrl(v);" + NL + "        } else if (\"file\".equals(k)) {" + NL + "            ks.setFile(v);" + NL + "        } else if (\"resource\".equals(k)) {" + NL + "            ks.setResource(v);" + NL + "        }" + NL + "        return ks;" + NL + "    }" + NL + "" + NL + "    private org.apache.cxf.configuration.security.TrustManagersType getTrustManagers(" + NL + "            org.apache.cxf.configuration.security.TrustManagersType tmt, String k, String v) {" + NL + "        if (tmt == null) {" + NL + "            tmt = new org.apache.cxf.configuration.security.TrustManagersType();" + NL + "        }" + NL + "        if (\"provider\".equals(k)) {" + NL + "            tmt.setProvider(v);" + NL + "        } else if (\"factoryAlgorithm\".equals(k)) {" + NL + "            tmt.setFactoryAlgorithm(v);" + NL + "        } else if (k.startsWith(\"keyStore.\")) {" + NL + "            tmt.setKeyStore(getKeyStore(tmt.getKeyStore(), k.substring(\"keyStore.\".length()), v));" + NL + "        } else if (k.startsWith(\"certStore\")) {" + NL + "            tmt.setCertStore(getCertStore(tmt.getCertStore(), k.substring(\"certStore.\".length()), v));" + NL + "        }" + NL + "        return tmt;" + NL + "    }" + NL + "" + NL + "    private org.apache.cxf.configuration.security.CertStoreType getCertStore(" + NL + "            org.apache.cxf.configuration.security.CertStoreType cs, String k, String v) {" + NL + "        if (cs == null) {" + NL + "            cs = new org.apache.cxf.configuration.security.CertStoreType();" + NL + "        }" + NL + "        if (\"file\".equals(k)) {" + NL + "            cs.setFile(v);" + NL + "        } else if (\"url\".equals(k)) {" + NL + "            cs.setUrl(v);" + NL + "        } else if (\"resource\".equals(k)) {" + NL + "            cs.setResource(v);" + NL + "        }" + NL + "        return cs;" + NL + "    }" + NL + "" + NL + "    private void applyProxyAuthorization(java.util.Dictionary<String, String> d," + NL + "            org.apache.cxf.transport.http.HTTPConduit c) {" + NL + "        java.util.Enumeration<String> keys = d.keys();" + NL + "        org.apache.cxf.configuration.security.ProxyAuthorizationPolicy p = c.getProxyAuthorization();" + NL + "        while (keys.hasMoreElements()) {" + NL + "            String k = keys.nextElement();" + NL + "            if (k.startsWith(\"proxyAuthorization.\")) {" + NL + "                if (p == null) {" + NL + "                    p = new org.apache.cxf.configuration.security.ProxyAuthorizationPolicy();" + NL + "                    c.setProxyAuthorization(p);" + NL + "                }" + NL + "                String v = d.get(k);" + NL + "                k = k.substring(\"proxyAuthorization.\".length());" + NL + "" + NL + "                if (\"UserName\".equals(k)) {" + NL + "                    p.setUserName(v);" + NL + "                } else if (\"Password\".equals(k)) {" + NL + "                    p.setPassword(v);" + NL + "                } else if (\"Authorization\".equals(k)) {" + NL + "                    p.setAuthorization(v);" + NL + "                } else if (\"AuthorizationType\".equals(k)) {" + NL + "                    p.setAuthorizationType(v);" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "" + NL + "    private void applyAuthorization(java.util.Dictionary<String, String> d," + NL + "            org.apache.cxf.transport.http.HTTPConduit c) {" + NL + "        java.util.Enumeration<String> keys = d.keys();" + NL + "        org.apache.cxf.configuration.security.AuthorizationPolicy p = c.getAuthorization();" + NL + "        while (keys.hasMoreElements()) {" + NL + "            String k = keys.nextElement();" + NL + "            if (k.startsWith(\"authorization.\")) {" + NL + "                if (p == null) {" + NL + "                    p = new org.apache.cxf.configuration.security.AuthorizationPolicy();" + NL + "                    c.setAuthorization(p);" + NL + "                }" + NL + "                String v = d.get(k);" + NL + "                k = k.substring(\"authorization.\".length());" + NL + "" + NL + "                if (\"UserName\".equals(k)) {" + NL + "                    p.setUserName(v);" + NL + "                } else if (\"Password\".equals(k)) {" + NL + "                    p.setPassword(v);" + NL + "                } else if (\"Authorization\".equals(k)) {" + NL + "                    p.setAuthorization(v);" + NL + "                } else if (\"AuthorizationType\".equals(k)) {" + NL + "                    p.setAuthorizationType(v);" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "" + NL + "    private void applyClientPolicies(java.util.Dictionary<String, String> d," + NL + "            org.apache.cxf.transport.http.HTTPConduit c) {" + NL + "        java.util.Enumeration<String> keys = d.keys();" + NL + "        org.apache.cxf.transports.http.configuration.HTTPClientPolicy p = c.getClient();" + NL + "        while (keys.hasMoreElements()) {" + NL + "            String k = keys.nextElement();" + NL + "            if (k.startsWith(\"client.\")) {" + NL + "                if (p == null) {" + NL + "                    p = new org.apache.cxf.transports.http.configuration.HTTPClientPolicy();" + NL + "                    c.setClient(p);" + NL + "                }" + NL + "                String v = d.get(k);" + NL + "                k = k.substring(\"client.\".length());" + NL + "                if (\"ConnectionTimeout\".equals(k)) {" + NL + "                    p.setConnectionTimeout(Long.parseLong(v.trim()));" + NL + "                } else if (\"ReceiveTimeout\".equals(k)) {" + NL + "                    p.setReceiveTimeout(Long.parseLong(v.trim()));" + NL + "                } else if (\"AsyncExecuteTimeout\".equals(k)) {" + NL + "                    p.setAsyncExecuteTimeout(Long.parseLong(v.trim()));" + NL + "                } else if (\"AsyncExecuteTimeoutRejection\".equals(k)) {" + NL + "                    p.setAsyncExecuteTimeoutRejection(Boolean.parseBoolean(v.trim()));" + NL + "                } else if (\"AutoRedirect\".equals(k)) {" + NL + "                    p.setAutoRedirect(Boolean.parseBoolean(v.trim()));" + NL + "                } else if (\"MaxRetransmits\".equals(k)) {" + NL + "                    p.setMaxRetransmits(Integer.parseInt(v.trim()));" + NL + "                } else if (\"AllowChunking\".equals(k)) {" + NL + "                    p.setAllowChunking(Boolean.parseBoolean(v.trim()));" + NL + "                } else if (\"ChunkingThreshold\".equals(k)) {" + NL + "                    p.setChunkingThreshold(Integer.parseInt(v.trim()));" + NL + "                } else if (\"ChunkLength\".equals(k)) {" + NL + "                    p.setChunkLength(Integer.parseInt(v.trim()));" + NL + "                } else if (\"Connection\".equals(k)) {" + NL + "                    p.setConnection(org.apache.cxf.transports.http.configuration.ConnectionType.valueOf(v));" + NL + "                } else if (\"DecoupledEndpoint\".equals(k)) {" + NL + "                    p.setDecoupledEndpoint(v);" + NL + "                } else if (\"ProxyServer\".equals(k)) {" + NL + "                    p.setProxyServer(v);" + NL + "                } else if (\"ProxyServerPort\".equals(k)) {" + NL + "                    p.setProxyServerPort(Integer.parseInt(v.trim()));" + NL + "                } else if (\"ProxyServerType\".equals(k)) {" + NL + "                    p.setProxyServerType(org.apache.cxf.transports.http.configuration.ProxyServerType.fromValue(v));" + NL + "                } else if (\"NonProxyHosts\".equals(k)) {" + NL + "                    p.setNonProxyHosts(v);" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_15 = NL + NL + "@org.springframework.boot.autoconfigure.SpringBootApplication" + NL + "@org.springframework.boot.context.properties.EnableConfigurationProperties(";
  protected final String TEXT_16 = ".ContextProperties.class)" + NL + "@org.springframework.context.annotation.ImportResource(\"classpath:META-INF/spring/";
  protected final String TEXT_17 = ".xml\")" + NL + "public class ";
  protected final String TEXT_18 = " extends org.apache.camel.builder.RouteBuilder implements TalendESBRoute {" + NL + "" + NL + "    @org.springframework.beans.factory.annotation.Autowired" + NL + "    org.springframework.core.env.Environment env;" + NL;
  protected final String TEXT_19 = NL + NL + "    @org.springframework.context.annotation.Bean" + NL + "    io.prometheus.client.CollectorRegistry metricRegistry() {" + NL + "        try {" + NL + "            new io.prometheus.jmx.BuildInfoCollector().register();" + NL + "            new io.prometheus.jmx.JmxCollector(new org.springframework.core.io.ClassPathResource(\"config/jmx-prometheus.yaml\").getInputStream()).register();" + NL + "        } catch (javax.management.MalformedObjectNameException e) {" + NL + "            e.printStackTrace();" + NL + "        } catch (java.io.IOException e) {" + NL + "            e.printStackTrace();" + NL + "        }" + NL + "" + NL + "        return io.prometheus.client.CollectorRegistry.defaultRegistry;" + NL + "    }" + NL;
  protected final String TEXT_20 = NL;
  protected final String TEXT_21 = NL + "    public class ";
  protected final String TEXT_22 = " extends ";
  protected final String TEXT_23 = " implements TalendESBRoute {";
  protected final String TEXT_24 = NL + NL + "    private boolean inOSGi = routines.system.BundleUtils.inOSGi();" + NL + "" + NL + "    private boolean inMS = ";
  protected final String TEXT_25 = ";" + NL + "" + NL + "    private static Object arguments;" + NL;
  protected final String TEXT_26 = NL + NL + "    private org.apache.activemq.pool.PooledConnectionFactory pooledConnectionFactory_";
  protected final String TEXT_27 = NL + NL + "    public void setArguments(Object arguments) {" + NL + "        this.inOSGi = true;";
  protected final String TEXT_28 = ".arguments = arguments;";
  protected final String TEXT_29 = NL + "        if (null != context && null != context.getProperty(\"context\")) {" + NL + "            contextStr = (String) context.getProperty(\"context\");" + NL + "        }";
  protected final String TEXT_30 = NL + "    }" + NL + "" + NL + "    public <T> Map<String, T> getArguments(Object props,Class c){" + NL + "        if (props != null) {" + NL + "            try {" + NL + "                java.lang.reflect.Field propertiesField = props.getClass().getDeclaredField(\"properties\");" + NL + "                propertiesField.setAccessible(true);" + NL + "                java.util.Dictionary p = (java.util.Dictionary) propertiesField.get(props);" + NL + "                java.util.Map<String,T> result = new java.util.HashMap<>();" + NL + "" + NL + "                if(p !=null ){" + NL + "                    for (java.util.Enumeration<String> keys = p.keys(); keys.hasMoreElements();) {" + NL + "                        String key = keys.nextElement();" + NL + "                        if(c.equals(String.class)){" + NL + "                            result.put(key, (T) String.valueOf(p.get(key)));" + NL + "                        }else{" + NL + "                            result.put(key, (T) p.get(key));" + NL + "                        }" + NL + "" + NL + "                    }" + NL + "                }" + NL + "" + NL + "                return result;" + NL + "" + NL + "            } catch (Exception e) {" + NL + "                e.printStackTrace();" + NL + "            }" + NL + "        }" + NL + "        return null;" + NL + "    }";
  protected final String TEXT_31 = NL + "    private final CorrelationIDCallbackHandler correlationIDCallbackHandler_";
  protected final String TEXT_32 = " = new CorrelationIDCallbackHandler();";
  protected final String TEXT_33 = NL + "    public interface Service_";
  protected final String TEXT_34 = " {";
  protected final String TEXT_35 = NL + "        @javax.ws.rs.Path(";
  protected final String TEXT_36 = ")" + NL + "        @javax.ws.rs.";
  protected final String TEXT_37 = "()";
  protected final String TEXT_38 = NL + "        @javax.ws.rs.Consumes(";
  protected final String TEXT_39 = ")";
  protected final String TEXT_40 = NL + "        @javax.ws.rs.Produces(";
  protected final String TEXT_41 = NL + "        Object ";
  protected final String TEXT_42 = "(";
  protected final String TEXT_43 = ", ";
  protected final String TEXT_44 = NL + "                @javax.ws.rs.PathParam(\"";
  protected final String TEXT_45 = "\") String ";
  protected final String TEXT_46 = NL + "                ";
  protected final String TEXT_47 = " payload";
  protected final String TEXT_48 = NL + "        );";
  protected final String TEXT_49 = NL + "    }" + NL;
  protected final String TEXT_50 = NL + NL + "    //ESB Service Activity Monitor Feature" + NL + "" + NL + "    private org.talend.esb.sam.agent.feature.EventFeature eventFeature;" + NL + "" + NL + "    public void setEventFeature(org.talend.esb.sam.agent.feature.EventFeature eventFeature){" + NL + "        this.eventFeature = eventFeature;" + NL + "    }" + NL + "" + NL + "    public org.talend.esb.sam.agent.feature.EventFeature getEventFeature(org.apache.cxf.Bus bus) {" + NL + "        org.talend.esb.sam.agent.feature.EventFeature eventFeature_;" + NL + "        if(inOSGi){" + NL + "            eventFeature_ = eventFeature;" + NL + "        }else{" + NL + "            eventFeature_ = new org.springframework.context.support.ClassPathXmlApplicationContext(\"META-INF/tesb/agent-context.xml\").getBean(org.talend.esb.sam.agent.feature.EventFeature.class);" + NL + "        }" + NL + "" + NL + "        return eventFeature_;" + NL + "    }" + NL;
  protected final String TEXT_51 = NL + "    //ESB Service Locator Feature" + NL + "" + NL + "    private org.talend.esb.servicelocator.cxf.LocatorFeature locatorFeature;" + NL + "" + NL + "    public org.talend.esb.servicelocator.cxf.LocatorFeature getLocatorFeature(org.apache.cxf.Bus bus) {" + NL + "        org.talend.esb.servicelocator.cxf.LocatorFeature locatorFeature_ = null;" + NL + "        if(inOSGi){" + NL + "            locatorFeature_ = locatorFeature;" + NL + "        }else{" + NL + "            org.springframework.context.support.ClassPathXmlApplicationContext context_ = new org.springframework.context.support.ClassPathXmlApplicationContext(new String[] { \"META-INF/tesb/locator/beans.xml\" });" + NL + "            locatorFeature_ = (org.talend.esb.servicelocator.cxf.LocatorFeature) context_.getBean(org.talend.esb.servicelocator.cxf.LocatorFeature.class);" + NL + "        }" + NL + "        return locatorFeature_;" + NL + "    }" + NL + "" + NL + "    public void setLocatorFeature(org.talend.esb.servicelocator.cxf.LocatorFeature locatorFeature){" + NL + "        this.locatorFeature = locatorFeature;" + NL + "    }" + NL + "" + NL + "    public void initLocator(CamelContext camelContext){" + NL + "        if(camelContext.getComponent(\"locator\") == null){" + NL + "            camelContext.addComponent(\"locator\", new org.apache.camel.support.DefaultComponent(){" + NL + "                @Override" + NL + "                protected org.apache.camel.Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {" + NL + "                    return null;" + NL + "                }" + NL + "            });" + NL + "        }" + NL + "    }" + NL;
  protected final String TEXT_52 = NL + "    static class CorrelationIDCallbackHandler implements org.talend.esb.policy.correlation.CorrelationIDCallbackHandler {" + NL + "        private String correlationId;" + NL + "        public void setCorrelationId(String correlationId) {" + NL + "            this.correlationId = correlationId;" + NL + "        }" + NL + "        public String getCorrelationId() {" + NL + "            return correlationId;" + NL + "        }" + NL + "    }";
  protected final String TEXT_53 = NL + "    public String getCXFRSEndpointAddress(String endpointUrl) {" + NL + "" + NL + "        if (inOSGi || inMS) {" + NL + "" + NL + "            if (endpointUrl != null && !endpointUrl.trim().isEmpty() && !endpointUrl.contains(\"://\")) {" + NL + "" + NL + "                if (endpointUrl.startsWith(\"/services\")) {" + NL + "                    endpointUrl = endpointUrl.substring(\"/services\".length());" + NL + "                }" + NL + "" + NL + "                if (!endpointUrl.startsWith(\"/\")) {" + NL + "                    endpointUrl = '/' + endpointUrl;" + NL + "                }" + NL + "            }" + NL + "            return endpointUrl;" + NL + "        }" + NL + "" + NL + "        String defaultEndpointUrl = \"";
  protected final String TEXT_54 = "\";" + NL + "" + NL + "        if (null == endpointUrl || endpointUrl.trim().isEmpty()) {" + NL + "" + NL + "            endpointUrl = defaultEndpointUrl;" + NL + "" + NL + "        } else if (!endpointUrl.contains(\"://\")) { // relative" + NL + "" + NL + "            if (endpointUrl.startsWith(\"/\")) {" + NL + "                endpointUrl = endpointUrl.substring(1);" + NL + "            }" + NL + "" + NL + "            endpointUrl = defaultEndpointUrl + endpointUrl;" + NL + "        }" + NL + "" + NL + "        return endpointUrl;" + NL + "    }" + NL + "" + NL + "static class CxfPayloadProvider implements javax.ws.rs.ext.MessageBodyWriter<org.apache.camel.component.cxf.common.CxfPayload<javax.xml.transform.Source>> {" + NL + "    public boolean isWriteable(Class<?> cls, java.lang.reflect.Type type, java.lang.annotation.Annotation[] anns, javax.ws.rs.core.MediaType mt) {" + NL + "        return cls == org.apache.camel.component.cxf.common.CxfPayload.class;" + NL + "    }" + NL + "    public long getSize(org.apache.camel.component.cxf.common.CxfPayload<javax.xml.transform.Source> obj, Class<?> cls, java.lang.reflect.Type type," + NL + "            java.lang.annotation.Annotation[] anns, javax.ws.rs.core.MediaType mt) {" + NL + "        return -1;" + NL + "    }" + NL + "    public void writeTo(org.apache.camel.component.cxf.common.CxfPayload<javax.xml.transform.Source> obj, Class<?> cls, java.lang.reflect.Type type," + NL + "            java.lang.annotation.Annotation[] anns, javax.ws.rs.core.MediaType mt," + NL + "            javax.ws.rs.core.MultivaluedMap<String, Object> headers, java.io.OutputStream os)" + NL + "            throws java.io.IOException, javax.ws.rs.WebApplicationException {" + NL + "        java.util.List<javax.xml.transform.Source> bodySource = obj.getBodySources();" + NL + "        if (bodySource == null || bodySource.size() != 1) {" + NL + "            throw new javax.ws.rs.InternalServerErrorException();" + NL + "        }" + NL + "        try {" + NL + "            org.apache.cxf.staxutils.StaxUtils.copy(bodySource.get(0), os);" + NL + "        } catch (javax.xml.stream.XMLStreamException ex) {" + NL + "            throw new javax.ws.rs.InternalServerErrorException(ex);" + NL + "        }" + NL + "" + NL + "    }" + NL + "}";
  protected final String TEXT_55 = NL + NL + "    private String propertyToString(Object obj){" + NL + "        if(obj!=null && obj instanceof java.util.Date){" + NL + "            return String.format(\"yyyy-MM-dd HH:mm:ss;%tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\",obj);" + NL + "        }else{" + NL + "            return String.valueOf(obj);" + NL + "        }" + NL + "    }" + NL;
  protected final String TEXT_56 = NL + "            private final static class CXFConsumerBinding extends org.apache.camel.component.cxf.jaxws.DefaultCxfBinding {" + NL + "                private org.apache.camel.component.cxf.jaxws.CxfEndpoint endpoint;" + NL + "" + NL + "                public CXFConsumerBinding(org.apache.camel.component.cxf.jaxws.CxfEndpoint endpoint){" + NL + "                    this.endpoint = endpoint;" + NL + "                    setHeaderFilterStrategy(consumerSoapHeaderFilter);" + NL + "                }" + NL + "                @Override" + NL + "                protected void propagateHeadersFromCamelToCxf(org.apache.camel.Exchange camelExchange," + NL + "                        Map<String, Object> camelHeaders, org.apache.cxf.message.Exchange cxfExchange," + NL + "                        Map<String, Object> cxfContext) {" + NL + "                    super.propagateHeadersFromCamelToCxf(camelExchange, camelHeaders, cxfExchange, cxfContext);" + NL + "                    final Object samlToken = camelHeaders.get(\"samlToken\");" + NL + "                    if (samlToken != null) {" + NL + "                        final Map<String, Object> properties = endpoint.getProperties();" + NL + "                        if (properties != null){" + NL + "                            Object object = properties.get(org.apache.cxf.ws.security.SecurityConstants.STS_CLIENT);" + NL + "                            if (object != null && object instanceof org.apache.cxf.ws.security.trust.STSClient) {" + NL + "                                ((org.apache.cxf.ws.security.trust.STSClient) object).setOnBehalfOf(samlToken);" + NL + "                                cxfContext.put(org.apache.cxf.ws.security.SecurityConstants.CACHE_ISSUED_TOKEN_IN_ENDPOINT, false);" + NL + "                            }" + NL + "                        }" + NL + "                    }" + NL + "                }" + NL + "            }";
  protected final String TEXT_57 = NL + "                private final static class CXFProviderBinding extends org.apache.camel.component.cxf.jaxws.DefaultCxfBinding{" + NL + "" + NL + "                    @Override" + NL + "                    protected void propagateHeadersFromCxfToCamel( org.apache.cxf.message.Message cxfMessage," + NL + "                            org.apache.camel.Message camelMessage, org.apache.camel.Exchange camelExchange) {" + NL + "                        super.propagateHeadersFromCxfToCamel(cxfMessage, camelMessage, camelExchange);" + NL + "                        camelExchange.getIn().removeHeader(\"samlToken\");" + NL + "                        org.apache.cxf.security.SecurityContext securityContext = cxfMessage.get(org.apache.cxf.security.SecurityContext.class);" + NL + "                        if(securityContext != null && securityContext instanceof org.apache.cxf.rt.security.saml.claims.SAMLSecurityContext){" + NL + "                            org.w3c.dom.Element assertionElement = ((org.apache.cxf.rt.security.saml.claims.SAMLSecurityContext) securityContext).getAssertionElement();" + NL + "                            if(assertionElement != null){" + NL + "                                camelExchange.getIn().setHeader(\"samlToken\", assertionElement);" + NL + "                            }" + NL + "                        }" + NL + "                    }" + NL + "                }";
  protected final String TEXT_58 = NL + "            @org.springframework.context.annotation.Bean" + NL + "            public org.springframework.boot.web.servlet.ServletRegistrationBean servletRegistrationBean(org.springframework.context.ApplicationContext context) {" + NL + "                return new org.springframework.boot.web.servlet.ServletRegistrationBean(new org.apache.cxf.transport.servlet.CXFServlet(), \"/services/*\");" + NL + "            }" + NL + "            @org.springframework.context.annotation.Bean(name = \"cxf\")" + NL + "            public org.apache.cxf.bus.spring.SpringBus springBus() {" + NL;
  protected final String TEXT_59 = NL + "                    return (org.apache.cxf.bus.spring.SpringBus) new org.apache.cxf.bus.spring.SpringBusFactory().createBus(\"META-INF/tesb/locator/beans.xml\");";
  protected final String TEXT_60 = NL + "                    return new org.apache.cxf.bus.spring.SpringBus();";
  protected final String TEXT_61 = NL + NL + "            }";
  protected final String TEXT_62 = NL + NL + "            private org.apache.camel.component.cxf.jaxws.CxfEndpoint getCxfEndpoint(String uri, boolean isProvider, boolean useAuthorization, boolean usePropagateSamlAP, String... token){" + NL + "                final org.apache.camel.component.cxf.jaxws.CxfEndpoint cxfEndpoint = (org.apache.camel.component.cxf.jaxws.CxfEndpoint) endpoint(uri);" + NL;
  protected final String TEXT_63 = NL + "                    cxfEndpoint.setCxfConfigurer(new org.apache.camel.component.cxf.jaxws.CxfConfigurer() {" + NL + "                        public void configure(org.apache.cxf.frontend.AbstractWSDLBasedEndpointFactory factory) {" + NL + "                        }" + NL + "                        public void configureClient(org.apache.cxf.endpoint.Client client) {" + NL + "                            String configName = \"org.apache.cxf.http.conduits-\" + cxfEndpoint.getPortNameAsQName().getLocalPart() + \".cfg\";" + NL + "                            java.util.Dictionary<String, String> dic = loadHttpConduitProperties(configName);" + NL + "" + NL + "                            org.apache.cxf.transport.http.HTTPConduit conduit = (org.apache.cxf.transport.http.HTTPConduit) client.getConduit();" + NL + "                            HttpConduitConfigApplier configApplier = new HttpConduitConfigApplier();" + NL + "                            configApplier.apply(dic, conduit, cxfEndpoint.getAddress());" + NL + "                        }" + NL + "                        public void configureServer(org.apache.cxf.endpoint.Server server) {" + NL + "                        }" + NL + "                    });";
  protected final String TEXT_64 = NL + "                    if (!isProvider&&token!=null &&" + NL + "                            (org.apache.cxf.transport.http.auth.HttpAuthHeader.AUTH_TYPE_BASIC.equals(token[0])" + NL + "                                    || org.apache.cxf.transport.http.auth.HttpAuthHeader.AUTH_TYPE_DIGEST.equals(token[0]))) {" + NL + "                        addHttpAuthConsumer(cxfEndpoint, token[0], token[1], token[2]);" + NL + "                    }";
  protected final String TEXT_65 = NL + "                    if(!isProvider&&token!=null&&token.length>=3&&\"UsernameToken\".equals(token[0])){" + NL + "                        addUsernameTokenConsumer(cxfEndpoint, token[1], token[2]);" + NL + "                    }";
  protected final String TEXT_66 = NL + "                if (inOSGi) {";
  protected final String TEXT_67 = NL + "                        if (isProvider && token!=null && org.apache.cxf.transport.http.auth.HttpAuthHeader.AUTH_TYPE_BASIC.equals(token[0])) {" + NL;
  protected final String TEXT_68 = NL + "                            addHttpAuthProvider(cxfEndpoint);";
  protected final String TEXT_69 = NL + NL + "                        }";
  protected final String TEXT_70 = NL + "                        if(isProvider && token!=null && \"UsernameToken\".equals(token[0])) {" + NL + "                            addUsernameTokenProvider(cxfEndpoint);" + NL + "                        }" + NL;
  protected final String TEXT_71 = NL + "                    if(token!=null&&\"SAMLToken\".equals(token[0])){" + NL + "                        addSAMLToken(cxfEndpoint,isProvider, useAuthorization, usePropagateSamlAP, token);" + NL + "                    }";
  protected final String TEXT_72 = NL + "                    if(token!=null&&\"Registry\".equals(token[0])){" + NL + "                        addRegistry(cxfEndpoint, isProvider, usePropagateSamlAP, token);" + NL + "                    }";
  protected final String TEXT_73 = NL + "                }";
  protected final String TEXT_74 = NL + "                return cxfEndpoint;" + NL + "            }";
  protected final String TEXT_75 = NL + "            private void addHttpAuthConsumer(org.apache.camel.component.cxf.jaxws.CxfEndpoint cxfEndpoint, String type, String username, String password) {" + NL + "                org.apache.cxf.configuration.security.AuthorizationPolicy authorizationPolicy = new org.apache.cxf.configuration.security.AuthorizationPolicy();" + NL + "                authorizationPolicy.setAuthorizationType(type);" + NL + "                authorizationPolicy.setUserName(username);" + NL + "                authorizationPolicy.setPassword(password);" + NL + "                cxfEndpoint.setProperties(java.util.Collections.<String, Object>singletonMap(org.apache.cxf.configuration.security.AuthorizationPolicy.class.getName(), authorizationPolicy));" + NL + "            }";
  protected final String TEXT_76 = NL + "            private void addHttpAuthProvider(org.apache.camel.component.cxf.jaxws.CxfEndpoint cxfEndpoint) {" + NL + "                org.apache.cxf.interceptor.security.JAASLoginInterceptor jaasLoginInterceptor = new org.apache.cxf.interceptor.security.JAASLoginInterceptor();" + NL + "                jaasLoginInterceptor.setContextName(\"karaf\");" + NL + "                cxfEndpoint.getInInterceptors().add(jaasLoginInterceptor);" + NL + "            }";
  protected final String TEXT_77 = NL + NL + "            class PropertyFileValidator implements org.apache.wss4j.dom.validate.Validator {" + NL + "" + NL + "                private final static String USERSFILENAME = \"users.properties\";" + NL + "" + NL + "                @Override" + NL + "                public org.apache.wss4j.dom.validate.Credential validate(org.apache.wss4j.dom.validate.Credential credential, org.apache.wss4j.dom.handler.RequestData data) throws org.apache.wss4j.common.ext.WSSecurityException {" + NL + "                    if (credential == null || credential.getUsernametoken() == null) {" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILURE, \"noCredential\");" + NL + "                    }" + NL + "" + NL + "                    String user = null;" + NL + "                    String password = null;" + NL + "" + NL + "                    org.apache.wss4j.dom.message.token.UsernameToken usernameToken = credential.getUsernametoken();" + NL + "" + NL + "                    user = usernameToken.getName();" + NL + "                    String pwType = usernameToken.getPasswordType();" + NL + "                    if (log.isDebugEnabled()) {" + NL + "                        log.debug(\"UsernameToken user \" + usernameToken.getName());" + NL + "                        log.debug(\"UsernameToken password type \" + pwType);" + NL + "                    }" + NL + "" + NL + "                    if (usernameToken.isHashed()) {" + NL + "                        log.warn(\"Authentication failed as hashed username token not supported\");" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                    }" + NL + "" + NL + "                    password = usernameToken.getPassword();" + NL + "" + NL + "                    if (!org.apache.wss4j.dom.WSConstants.PASSWORD_TEXT.equals(pwType)) {" + NL + "                        log.warn(\"Password type \" + pwType + \" not supported\");" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                    }" + NL + "" + NL + "                    if (!(user != null && user.length() > 0 && password != null && password.length() > 0)) {" + NL + "                        log.warn(\"User or password empty\");" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                    }" + NL + "" + NL + "                    java.io.InputStream stream = getConfigLocation(USERSFILENAME);" + NL + "                    try {" + NL + "                        Properties properties = new Properties();" + NL + "                        properties.load(stream);" + NL + "                        String propertyPwd = (String) properties.get(user);" + NL + "                        if (propertyPwd == null || !propertyPwd.equalsIgnoreCase(password)) {" + NL + "                            log.info(\"Authentication failed\");" + NL + "                            throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                        }" + NL + "                    } catch (Exception ex) {" + NL + "                        log.info(\"Authentication failed\", ex);" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                    } finally {" + NL + "                        try {" + NL + "                            stream.close();" + NL + "                        } catch (java.io.IOException e) {" + NL + "" + NL + "                        }" + NL + "                    }" + NL + "" + NL + "                    return credential;" + NL + "                }" + NL + "" + NL + "            }" + NL + "" + NL + "            private void addUsernameTokenProvider(org.apache.camel.component.cxf.jaxws.CxfEndpoint cxfEndpoint) {" + NL + "            PropertyFileValidator validator = new PropertyFileValidator();" + NL + "" + NL + "            cxfEndpoint" + NL + "                    .setProperties(java.util.Collections" + NL + "                            .<String, Object> singletonMap(" + NL + "                                    org.apache.cxf.ws.security.SecurityConstants.USERNAME_TOKEN_VALIDATOR," + NL + "                                    validator));" + NL + "            java.io.InputStream policyInput = getConfigLocation(\"org.talend.esb.job.token.policy\");" + NL + "            org.apache.neethi.Policy policy;" + NL + "            try {" + NL + "                policy = cxfEndpoint.getBus().getExtension(org.apache.cxf.ws.policy.PolicyBuilder.class).getPolicy(policyInput);" + NL + "            } catch (Exception e) {" + NL + "                throw new RuntimeException(\"Cannot load policy\", e);" + NL + "            }" + NL + "" + NL + "            cxfEndpoint.getFeatures().add(" + NL + "                    new org.apache.cxf.ws.policy.WSPolicyFeature(policy));" + NL + "            }";
  protected final String TEXT_78 = NL + "            private void addUsernameTokenProvider(org.apache.camel.component.cxf.jaxws.CxfEndpoint cxfEndpoint) {" + NL + "                org.apache.wss4j.dom.validate.JAASUsernameTokenValidator validator = new org.apache.wss4j.dom.validate.JAASUsernameTokenValidator();" + NL + "                validator.setContextName(\"karaf\");" + NL + "                cxfEndpoint.setProperties(java.util.Collections.<String, Object>singletonMap(org.apache.cxf.ws.security.SecurityConstants.USERNAME_TOKEN_VALIDATOR, validator));" + NL + "                cxfEndpoint.getFeatures().add(new org.apache.cxf.ws.policy.WSPolicyFeature(policyProvider.getUsernamePolicy(cxfEndpoint.getBus())));" + NL + "            }";
  protected final String TEXT_79 = NL + "            private void addUsernameTokenConsumer(org.apache.camel.component.cxf.jaxws.CxfEndpoint cxfEndpoint," + NL + "                    String username, String password) {" + NL + "                if (null != policyProvider) {" + NL + "                    Map<String, Object> props = new HashMap<String, Object>();" + NL + "                    props.put(org.apache.cxf.rt.security.SecurityConstants.USERNAME, username);" + NL + "                    props.put(org.apache.cxf.rt.security.SecurityConstants.PASSWORD, password);" + NL + "                    cxfEndpoint.setProperties(props);" + NL + "                    cxfEndpoint.getFeatures().add(new org.apache.cxf.ws.policy.WSPolicyFeature(policyProvider.getUsernamePolicy(cxfEndpoint.getBus())));" + NL + "                } else {" + NL + "                    Map<String, Object> props = new HashMap<String, Object>();" + NL + "                    props.put(org.apache.wss4j.dom.handler.WSHandlerConstants.ACTION, org.apache.wss4j.dom.handler.WSHandlerConstants.USERNAME_TOKEN);" + NL + "                    props.put(org.apache.wss4j.dom.handler.WSHandlerConstants.PASSWORD_TYPE, org.apache.wss4j.common.WSS4JConstants.PW_TEXT);" + NL + "                    props.put(org.apache.wss4j.dom.handler.WSHandlerConstants.USER, username);" + NL + "                    props.put(org.apache.wss4j.dom.handler.WSHandlerConstants.PW_CALLBACK_REF," + NL + "                        new org.talend.esb.security.saml.WSPasswordCallbackHandler(username, password));" + NL + "                    cxfEndpoint.getOutInterceptors().add(new org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor(props));" + NL + "                }" + NL + "            }";
  protected final String TEXT_80 = NL + NL + "private Map<String, String> clientProperties;" + NL + "" + NL + "public void setClientProperties(Object clientProperties) {" + NL + "        this.clientProperties = getArguments(clientProperties,String.class);" + NL + "}" + NL;
  protected final String TEXT_81 = NL + "private final static org.apache.camel.component.cxf.transport.header.CxfHeaderFilterStrategy consumerSoapHeaderFilter = new org.apache.camel.component.cxf.transport.header.CxfHeaderFilterStrategy();";
  protected final String TEXT_82 = NL + "private void addRegistry(org.apache.camel.component.cxf.jaxws.CxfEndpoint cxfEndpoint," + NL + "        boolean isProvider, boolean usePropagateSamlAP, final String... token) {";
  protected final String TEXT_83 = NL + NL + "    if(!isProvider){" + NL + "        final org.apache.cxf.Bus bus = cxfEndpoint.getBus();" + NL + "        if(usePropagateSamlAP && (token[4] == null)){" + NL + "            usePropagateSamlAP = false ;" + NL + "        }" + NL;
  protected final String TEXT_84 = NL + "            Properties stsProperties = new Properties();" + NL + "            java.io.InputStream stsCfg= getConfigLocation(\"org.talend.esb.job.client.sts.cfg\");" + NL + "" + NL + "            try {" + NL + "                    stsProperties.load(stsCfg);" + NL + "            } catch (Exception e) {" + NL + "                throw new RuntimeException(\"Cannot load client properties\", e);" + NL + "            }" + NL + "" + NL + "            Map<String, Object> stsProps = new HashMap<String, Object>();" + NL + "            for (Map.Entry<Object, Object> entry : stsProperties.entrySet()) {" + NL + "                    stsProps.put((String)entry.getKey(), entry.getValue());" + NL + "            }" + NL + "            new org.talend.esb.security.saml.STSClientUtils(stsProps);";
  protected final String TEXT_85 = NL + "        final org.apache.cxf.ws.security.trust.STSClient stsClient;" + NL + "        if(usePropagateSamlAP) {" + NL + "            stsClient= org.talend.esb.security.saml.STSClientUtils.createSTSX509Client(bus, token[4]);" + NL + "        }else {" + NL + "            stsClient= org.talend.esb.security.saml.STSClientUtils.createSTSClient(bus, token[1], token[2]);" + NL + "        }" + NL + "        if (token[3]/*Role*/ != null && !(\"\".equals(token[3]))) {" + NL + "            org.talend.esb.security.saml.STSClientUtils.applyAuthorization(stsClient, token[3]);" + NL + "        }" + NL + "" + NL + "        Map<String, Object> clientProps = new HashMap<String, Object>();" + NL + "        clientProps.put(org.apache.cxf.ws.security.SecurityConstants.USERNAME," + NL + "                token[1]);" + NL + "        clientProps.put(org.apache.cxf.ws.security.SecurityConstants.PASSWORD," + NL + "                token[2]);" + NL + "" + NL + "        clientProps.put(" + NL + "                org.apache.cxf.ws.security.SecurityConstants.STS_CLIENT," + NL + "                stsClient);" + NL;
  protected final String TEXT_86 = NL + "            Properties clientPropertiesTmp = new Properties();" + NL + "            java.io.InputStream clientCfg= getConfigLocation(\"org.talend.esb.job.client.cfg\");" + NL + "" + NL + "            try {" + NL + "                    clientPropertiesTmp.load(clientCfg);" + NL + "" + NL + "                    clientProperties = new HashMap<String, String>();" + NL + "                    for (String key : clientPropertiesTmp.stringPropertyNames()) {" + NL + "                        clientProperties.put(key, clientPropertiesTmp.getProperty(key));" + NL + "                    }" + NL + "            } catch (Exception e) {" + NL + "                throw new RuntimeException(\"Cannot load client properties\", e);" + NL + "            }";
  protected final String TEXT_87 = NL + NL + "        for (Map.Entry<String, String> entry : clientProperties.entrySet()) {" + NL + "            if (org.apache.cxf.ws.security.SecurityConstants.ALL_PROPERTIES" + NL + "                    .contains(entry.getKey())) {" + NL + "                clientProps.put(entry.getKey(), entry.getValue());" + NL + "            }" + NL + "        }" + NL + "" + NL + "        if(usePropagateSamlAP) {" + NL + "            clientProps.put(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_USERNAME, token[4]);" + NL + "            clientProps" + NL + "            .put(org.apache.cxf.ws.security.SecurityConstants.CALLBACK_HANDLER," + NL + "                    new org.talend.esb.security.saml.WSPasswordCallbackHandler(token[4], token[2]));" + NL + "        }else {" + NL + "            clientProps" + NL + "            .put(org.apache.cxf.ws.security.SecurityConstants.CALLBACK_HANDLER," + NL + "                    new org.talend.esb.security.saml.WSPasswordCallbackHandler(" + NL + "                            clientProperties" + NL + "                            .get(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_USERNAME)," + NL + "                            clientProperties" + NL + "                            .get(\"security.signature.password\")));" + NL + "        }" + NL + "        clientProps.put(\"soap.no.validate.parts\", Boolean.TRUE);" + NL + "        clientProps.put(\"use.service.registry\", \"true\");" + NL + "        // set the cxfEndpoint Properties" + NL + "        cxfEndpoint.setProperties(clientProps);";
  protected final String TEXT_88 = NL + "        final org.apache.camel.component.cxf.jaxws.CxfConfigurer cxfEndpointConfigurer = cxfEndpoint.getCxfConfigurer();" + NL + "        final org.apache.camel.component.cxf.jaxws.CxfEndpoint cxfEndpointTrans= cxfEndpoint;" + NL + "        cxfEndpoint.setCxfConfigurer(new org.apache.camel.component.cxf.jaxws.CxfConfigurer() {" + NL + "            public void configure(org.apache.cxf.frontend.AbstractWSDLBasedEndpointFactory factory) {" + NL + "                if (cxfEndpointConfigurer != null) {" + NL + "                    cxfEndpointConfigurer.configure(factory);" + NL + "                }" + NL + "                factory.setAddress(null);" + NL + "            }" + NL + "            public void configureClient(org.apache.cxf.endpoint.Client client) {" + NL;
  protected final String TEXT_89 = NL + "                    String configName = \"org.apache.cxf.http.conduits-\" + cxfEndpointTrans.getPortNameAsQName().getLocalPart() + \".cfg\";" + NL + "                    java.util.Dictionary<String, String> dic = loadHttpConduitProperties(configName);" + NL + "" + NL + "                    org.apache.cxf.transport.http.HTTPConduit conduit = (org.apache.cxf.transport.http.HTTPConduit) client.getConduit();" + NL + "                    HttpConduitConfigApplier configApplier = new HttpConduitConfigApplier();" + NL + "                    configApplier.apply(dic, conduit, cxfEndpointTrans.getAddress());";
  protected final String TEXT_90 = NL + "                    if (cxfEndpointConfigurer != null) {" + NL + "                        cxfEndpointConfigurer.configureClient(client);" + NL + "                    }";
  protected final String TEXT_91 = NL + NL + "            }" + NL + "            public void configureServer(org.apache.cxf.endpoint.Server server) {" + NL + "                if (cxfEndpointConfigurer != null) {" + NL + "                    cxfEndpointConfigurer.configureServer(server);" + NL + "                }" + NL + "            }" + NL + "        });";
  protected final String TEXT_92 = NL + "        org.apache.cxf.endpoint.ClientLifeCycleManager cLifeCycleManager = bus.getExtension(org.apache.cxf.endpoint.ClientLifeCycleManager.class);" + NL + "        if (cLifeCycleManager != null) {" + NL + "            cLifeCycleManager.registerListener(new org.apache.cxf.endpoint.ClientLifeCycleListener() {" + NL + "                public void clientCreated(org.apache.cxf.endpoint.Client client) {" + NL + "                    String address = client.getEndpoint().getEndpointInfo().getAddress();" + NL + "                    if ((address != null) && address.startsWith(\"locator://\")) {" + NL + "                        org.talend.esb.servicelocator.cxf.LocatorFeature locatorFeature_ = null;" + NL + "                        if(inOSGi){" + NL + "                            locatorFeature_ = routines.system.BundleUtils.getService(org.talend.esb.servicelocator.cxf.LocatorFeature.class);" + NL + "                        }else{" + NL + "                            org.springframework.context.support.ClassPathXmlApplicationContext context_ = new org.springframework.context.support.ClassPathXmlApplicationContext(new String[] { \"META-INF/tesb/locator/beans.xml\" });" + NL + "                            locatorFeature_ = (org.talend.esb.servicelocator.cxf.LocatorFeature) context_.getBean(org.talend.esb.servicelocator.cxf.LocatorFeature.class);" + NL + "                        }" + NL + "                        locatorFeature_.initialize(client, client.getBus());" + NL + "                    }" + NL + "                }" + NL + "                public void clientDestroyed(org.apache.cxf.endpoint.Client client) {" + NL + "                }" + NL + "            });" + NL + "        }" + NL + "    }";
  protected final String TEXT_93 = NL + "    if (isProvider) {" + NL + "" + NL + "        // set endpoint properties";
  protected final String TEXT_94 = NL + NL + "            class PropertyFileValidator implements org.apache.wss4j.dom.validate.Validator {" + NL + "" + NL + "                private final static String USERSFILENAME = \"users.properties\";" + NL + "" + NL + "                @Override" + NL + "                public org.apache.wss4j.dom.validate.Credential validate(org.apache.wss4j.dom.validate.Credential credential, org.apache.wss4j.dom.handler.RequestData data) throws org.apache.wss4j.common.ext.WSSecurityException {" + NL + "                    if (credential == null || credential.getUsernametoken() == null) {" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILURE, \"noCredential\");" + NL + "                    }" + NL + "" + NL + "                    String user = null;" + NL + "                    String password = null;" + NL + "" + NL + "                    org.apache.wss4j.dom.message.token.UsernameToken usernameToken = credential.getUsernametoken();" + NL + "" + NL + "                    user = usernameToken.getName();" + NL + "                    String pwType = usernameToken.getPasswordType();" + NL + "                    if (log.isDebugEnabled()) {" + NL + "                        log.debug(\"UsernameToken user \" + usernameToken.getName());" + NL + "                        log.debug(\"UsernameToken password type \" + pwType);" + NL + "                    }" + NL + "" + NL + "                    if (usernameToken.isHashed()) {" + NL + "                        log.warn(\"Authentication failed as hashed username token not supported\");" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                    }" + NL + "" + NL + "                    password = usernameToken.getPassword();" + NL + "" + NL + "                    if (!org.apache.wss4j.dom.WSConstants.PASSWORD_TEXT.equals(pwType)) {" + NL + "                        log.warn(\"Password type \" + pwType + \" not supported\");" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                    }" + NL + "" + NL + "                    if (!(user != null && user.length() > 0 && password != null && password.length() > 0)) {" + NL + "                        log.warn(\"User or password empty\");" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                    }" + NL + "" + NL + "                    java.io.InputStream stream = getConfigLocation(USERSFILENAME);" + NL + "                    try {" + NL + "                        Properties properties = new Properties();" + NL + "                        properties.load(stream);" + NL + "                        String propertyPwd = (String) properties.get(user);" + NL + "                        if (propertyPwd == null || !propertyPwd.equalsIgnoreCase(password)) {" + NL + "                            log.info(\"Authentication failed\");" + NL + "                            throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                        }" + NL + "                    } catch (Exception ex) {" + NL + "                        log.info(\"Authentication failed\", ex);" + NL + "                        throw new org.apache.wss4j.common.ext.WSSecurityException(org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.FAILED_AUTHENTICATION);" + NL + "                    } finally {" + NL + "                        try {" + NL + "                            stream.close();" + NL + "                        } catch (java.io.IOException e) {" + NL + "" + NL + "                        }" + NL + "                    }" + NL + "" + NL + "                    return credential;" + NL + "                }" + NL + "" + NL + "            }" + NL + "" + NL + "            PropertyFileValidator jaasUTValidator = new PropertyFileValidator();" + NL + "" + NL + "        Properties serviceProps = new Properties();" + NL + "        java.io.InputStream serviceCfg= getConfigLocation(\"org.talend.esb.job.service.cfg\");" + NL + "" + NL + "        try {" + NL + "                serviceProps.load(serviceCfg);" + NL + "            securityProps = new HashMap<String, Object>();" + NL + "            for (String key : serviceProps.stringPropertyNames()) {" + NL + "                securityProps.put(key, serviceProps.getProperty(key));" + NL + "            }" + NL + "        } catch (Exception e) {" + NL + "            throw new RuntimeException(\"Cannot load service properties\", e);" + NL + "        }" + NL;
  protected final String TEXT_95 = NL + "            org.apache.wss4j.dom.validate.JAASUsernameTokenValidator jaasUTValidator = new org.apache.wss4j.dom.validate.JAASUsernameTokenValidator();" + NL + "            jaasUTValidator.setContextName(\"karaf\");";
  protected final String TEXT_96 = NL + NL + "        final String userName = (String) securityProps.get(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_USERNAME);" + NL + "        final Map<String, Object> endpointProps = new HashMap<String, Object>();" + NL + "        endpointProps.put(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_USERNAME, userName);" + NL + "        endpointProps.put(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_PROPERTIES, securityProps.get(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_PROPERTIES));" + NL + "        endpointProps.put(org.apache.cxf.rt.security.SecurityConstants.CALLBACK_HANDLER, new org.talend.esb.security.saml.WSPasswordCallbackHandler(userName, (String) securityProps.get(\"security.signature.password\")));" + NL + "        endpointProps.put(org.apache.cxf.ws.security.SecurityConstants.USERNAME_TOKEN_VALIDATOR, jaasUTValidator);" + NL + "        endpointProps.put(\"use.service.registry\", \"true\");";
  protected final String TEXT_97 = NL + "            Properties pepProps = new Properties();" + NL + "            java.io.InputStream pepCfg= getConfigLocation(\"org.talend.esb.authorization.pep.cfg\");" + NL + "" + NL + "            try {" + NL + "                pepProps.load(pepCfg);" + NL + "            } catch (Exception e) {" + NL + "                throw new RuntimeException(\"Cannot load pep properties\", e);" + NL + "            }" + NL + "            endpointProps.put(org.talend.esb.authorization.xacml.rt.pep.AuthorizationConstants.PDP_ADDRESS_PROPERTY, pepProps.get(\"tesb.pdp.address\"));";
  protected final String TEXT_98 = NL + NL + "        cxfEndpoint.setProperties(endpointProps);" + NL + "    }";
  protected final String TEXT_99 = NL + "}";
  protected final String TEXT_100 = NL + "private Map<String, Object> securityProps;" + NL + "" + NL + "public void setSecurityProps(Object securityProps) {" + NL + "    this.securityProps = getArguments(securityProps,Object.class);" + NL + "}";
  protected final String TEXT_101 = NL + "private org.apache.cxf.interceptor.Interceptor<? extends org.apache.cxf.message.Message> authorizationInterceptor;" + NL + "" + NL + "public void setAuthorizationInterceptor(org.apache.cxf.interceptor.Interceptor<? extends org.apache.cxf.message.Message> authorizationInterceptor) {" + NL + "    this.authorizationInterceptor = authorizationInterceptor;" + NL + "}";
  protected final String TEXT_102 = NL + NL + "private org.talend.esb.security.policy.PolicyProvider policyProvider;" + NL + "" + NL + "public void setPolicyProvider(org.talend.esb.security.policy.PolicyProvider policyProvider) {" + NL + "    this.policyProvider = policyProvider;" + NL + "}" + NL;
  protected final String TEXT_103 = NL + NL + "private void addSAMLToken(org.apache.camel.component.cxf.jaxws.CxfEndpoint cxfEndpoint," + NL + "        boolean isProvider, boolean useAuthorization, boolean usePropagateSamlAP, String... token) {" + NL + "        final org.apache.cxf.Bus bus = cxfEndpoint.getBus();";
  protected final String TEXT_104 = NL + "    if (!isProvider) {" + NL;
  protected final String TEXT_105 = NL + "            Properties stsProperties = new Properties();" + NL + "            java.io.InputStream stsCfg = getConfigLocation(\"org.talend.esb.job.client.sts.cfg\");" + NL + "" + NL + "            try {" + NL + "                    stsProperties.load(stsCfg);" + NL + "            } catch (Exception e) {" + NL + "                throw new RuntimeException(\"Cannot load client properties\", e);" + NL + "            }" + NL + "" + NL + "            Map<String, Object> stsProps = new HashMap<String, Object>();" + NL + "            for (Map.Entry<Object, Object> entry : stsProperties.entrySet()) {" + NL + "                    stsProps.put((String)entry.getKey(), entry.getValue());" + NL + "            }" + NL + "            new org.talend.esb.security.saml.STSClientUtils(stsProps);";
  protected final String TEXT_106 = NL + NL + "        final org.apache.cxf.ws.security.trust.STSClient stsClient;" + NL + "        if (usePropagateSamlAP) {" + NL + "            stsClient = org.talend.esb.security.saml.STSClientUtils.createSTSX509Client(bus, token[4]);" + NL + "        } else {" + NL + "            stsClient = org.talend.esb.security.saml.STSClientUtils.createSTSClient(bus, token[1], token[2]);" + NL + "        }";
  protected final String TEXT_107 = NL + "        if (token[3] != null && !(\"\".equals(token[3]))) {" + NL + "            org.talend.esb.security.saml.STSClientUtils.applyAuthorization(stsClient, token[3]);" + NL + "        }";
  protected final String TEXT_108 = NL + "        Map<String, Object> clientProps = new HashMap<String, Object>();" + NL + "        clientProps.put(" + NL + "                org.apache.cxf.ws.security.SecurityConstants.STS_CLIENT," + NL + "                stsClient);" + NL;
  protected final String TEXT_109 = NL + "            Properties clientPropertiesTmp = new Properties();" + NL + "            java.io.InputStream clientCfg = getConfigLocation(\"org.talend.esb.job.client.cfg\");" + NL + "" + NL + "            try {" + NL + "                    clientPropertiesTmp.load(clientCfg);" + NL + "" + NL + "                    clientProperties = new HashMap<String, String>();" + NL + "                    for (String key : clientPropertiesTmp.stringPropertyNames()) {" + NL + "                        clientProperties.put(key, clientPropertiesTmp.getProperty(key));" + NL + "                    }" + NL + "            } catch (Exception e) {" + NL + "                throw new RuntimeException(\"Cannot load client properties\", e);" + NL + "            }";
  protected final String TEXT_110 = NL + NL + "        for (Map.Entry<String, String> entry : clientProperties.entrySet()) {" + NL + "            if (org.apache.cxf.ws.security.SecurityConstants.ALL_PROPERTIES" + NL + "                    .contains(entry.getKey())) {" + NL + "                clientProps.put(entry.getKey(), entry.getValue());" + NL + "            }" + NL + "        }" + NL + "        if(usePropagateSamlAP) {" + NL + "            clientProps.put(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_USERNAME, token[4]);" + NL + "            clientProps" + NL + "            .put(org.apache.cxf.ws.security.SecurityConstants.CALLBACK_HANDLER," + NL + "                    new org.talend.esb.security.saml.WSPasswordCallbackHandler(token[4], token[2]));" + NL + "        }else {" + NL + "            clientProps" + NL + "            .put(org.apache.cxf.ws.security.SecurityConstants.CALLBACK_HANDLER," + NL + "                    new org.talend.esb.security.saml.WSPasswordCallbackHandler(" + NL + "                            clientProperties" + NL + "                            .get(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_USERNAME)," + NL + "                            clientProperties" + NL + "                            .get(\"security.signature.password\")));" + NL + "        }" + NL + "        // set the cxfEndpoint Properties" + NL + "        cxfEndpoint.setProperties(clientProps);" + NL + "    }";
  protected final String TEXT_111 = NL + NL + "if (isProvider) {" + NL + "        // set endpoint properties";
  protected final String TEXT_112 = NL + NL + "        Properties serviceProps = new Properties();" + NL + "        java.io.InputStream serviceCfg = getConfigLocation(\"org.talend.esb.job.service.cfg\");" + NL + "" + NL + "        try {" + NL + "            serviceProps.load(serviceCfg);" + NL + "" + NL + "            securityProps = new HashMap<String, Object>();" + NL + "            for (String key : serviceProps.stringPropertyNames()) {" + NL + "                securityProps.put(key, serviceProps.getProperty(key));" + NL + "            }" + NL + "" + NL + "        } catch (Exception e) {" + NL + "            throw new RuntimeException(\"Cannot load service properties\", e);" + NL + "        }";
  protected final String TEXT_113 = NL + "        final String userName = (String) securityProps.get(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_USERNAME);" + NL + "        final Map<String, Object> endpointProps = new HashMap<String, Object>();" + NL + "        endpointProps.put(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_USERNAME, userName);" + NL + "        endpointProps.put(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_PROPERTIES, securityProps.get(org.apache.cxf.ws.security.SecurityConstants.SIGNATURE_PROPERTIES));" + NL + "        endpointProps.put(org.apache.cxf.rt.security.SecurityConstants.CALLBACK_HANDLER, new org.talend.esb.security.saml.WSPasswordCallbackHandler(userName, (String) securityProps.get(\"security.signature.password\")));" + NL;
  protected final String TEXT_114 = NL + "        if(useAuthorization){" + NL + "            Properties pepProps = new Properties();" + NL + "            java.io.InputStream pepCfg = getConfigLocation(\"org.talend.esb.authorization.pep.cfg\");" + NL + "" + NL + "            try {" + NL + "                pepProps.load(pepCfg);" + NL + "            } catch (Exception e) {" + NL + "                throw new RuntimeException(\"Cannot load pep properties\", e);" + NL + "            }" + NL + "            endpointProps.put(org.talend.esb.authorization.xacml.rt.pep.AuthorizationConstants.PDP_ADDRESS_PROPERTY, pepProps.get(\"tesb.pdp.address\"));" + NL + "            org.talend.esb.authorization.xacml.rt.pep.CXFXACMLAuthorizingInterceptor authzInterceptor = new org.talend.esb.authorization.xacml.rt.pep.CXFXACMLAuthorizingInterceptor();" + NL + "            authzInterceptor.setPdpAddress(pepProps.getProperty(\"tesb.pdp.address\"));" + NL + "            cxfEndpoint.getInInterceptors().add(authzInterceptor);" + NL + "        }";
  protected final String TEXT_115 = NL + "        cxfEndpoint.setProperties(endpointProps);" + NL + "}" + NL;
  protected final String TEXT_116 = NL + "        // add policy" + NL + "        org.apache.neethi.Policy policy;" + NL;
  protected final String TEXT_117 = NL + "            java.io.InputStream policyInput = null;" + NL + "            if (useAuthorization) {" + NL + "                policyInput = getConfigLocation(\"org.talend.esb.job.saml.authz.policy\");" + NL + "            } else {" + NL + "                policyInput = getConfigLocation(\"org.talend.esb.job.saml.policy\");" + NL + "            }" + NL + "" + NL + "" + NL + "            try {" + NL + "                policy = cxfEndpoint.getBus().getExtension(org.apache.cxf.ws.policy.PolicyBuilder.class).getPolicy(policyInput);" + NL + "            } catch (Exception e) {" + NL + "                throw new RuntimeException(\"Cannot load policy\", e);" + NL + "            }";
  protected final String TEXT_118 = NL + "            if (useAuthorization) {" + NL + "                policy = policyProvider.getSAMLAuthzPolicy(bus);" + NL + "            } else {" + NL + "                policy = policyProvider.getSAMLPolicy(bus);" + NL + "            }";
  protected final String TEXT_119 = NL + NL + "        cxfEndpoint.getFeatures().add(new org.apache.cxf.ws.policy.WSPolicyFeature(policy));" + NL + "}";
  protected final String TEXT_120 = NL + "            private org.apache.camel.Endpoint endpoint(String uri) {" + NL + "                return context().getEndpoint(uri);" + NL + "            }";
  protected final String TEXT_121 = NL + "    @Override" + NL + "    protected RouteBuilder createRouteBuilder() throws Exception {" + NL + "        return new RouteBuilder() {";
  protected final String TEXT_122 = NL + "    java.util.Map<String, String> getOidcSettings() {" + NL + "        java.util.Map<String, String> settings = new java.util.HashMap<String, String>();";
  protected final String TEXT_123 = NL + "        if(!inOSGi){" + NL + "            org.springframework.context.support.ClassPathXmlApplicationContext context =" + NL + "                new org.springframework.context.support.ClassPathXmlApplicationContext(\"META-INF/tesb/oidc-context.xml\");" + NL + "            org.talend.esb.security.oidc.OidcConfiguration oidcConfiguration = context.getBean(org.talend.esb.security.oidc.OidcConfiguration.class);" + NL + "            return oidcConfiguration.getOidcProperties();" + NL + "        }";
  protected final String TEXT_124 = NL + "        String tokenEndpoint = org.talend.esb.security.oidc.OidcClientUtils.getTokenEndpoint();" + NL + "        String validationEndpoint = org.talend.esb.security.oidc.OidcClientUtils.getValidationEndpoint();" + NL + "        String clientId = org.talend.esb.security.oidc.OidcClientUtils.getPublicClientID();" + NL + "        String scope = org.talend.esb.security.oidc.OidcClientUtils.getScope();" + NL + "" + NL + "        if (tokenEndpoint == null && validationEndpoint == null) {" + NL + "" + NL + "            String fileName = \"org.talend.esb.job.oidc.cfg\";" + NL + "            java.io.InputStream stream = null;" + NL + "" + NL + "            String configFile = \"config/\" + fileName;" + NL + "" + NL + "            String file = \"\";" + NL + "            // by default from current \"config\" directory" + NL + "            file = System.getProperty(\"user.dir\") + java.io.File.separator + configFile;" + NL + "" + NL + "            java.io.File usersfile = new java.io.File(file);" + NL + "            if (usersfile.exists()) {" + NL + "                try{" + NL + "                    stream = new java.io.FileInputStream(file);" + NL + "                }catch(Exception e){" + NL + "                    stream = this.getClass().getClassLoader().getResourceAsStream(configFile);" + NL + "                }" + NL + "            } else {" + NL + "                stream = this.getClass().getClassLoader().getResourceAsStream(configFile);" + NL + "            }" + NL + "" + NL + "            if (stream != null) {" + NL + "                Properties oidcProperties = new Properties();" + NL + "                try {" + NL + "                    oidcProperties.load(stream);" + NL + "                } catch (Exception e) {" + NL + "                    throw new RuntimeException(\"Cannot load OpenID Connect properties\", e);" + NL + "                } finally {" + NL + "                    try {" + NL + "                        stream.close();" + NL + "                    } catch (Exception e) {}" + NL + "                }" + NL + "" + NL + "                tokenEndpoint = oidcProperties.getProperty(org.talend.esb.security.oidc.OidcConfiguration.OIDC_TOKEN_ENDPOINT_LOCATION);" + NL + "                validationEndpoint = oidcProperties.getProperty(org.talend.esb.security.oidc.OidcConfiguration.OIDC_VALIDATION_ENDPOINT_LOCATION);" + NL + "                clientId = oidcProperties.getProperty(org.talend.esb.security.oidc.OidcConfiguration.OIDC_PUBLIC_CLIENT_ID);" + NL + "                scope = oidcProperties.getProperty(org.talend.esb.security.oidc.OidcConfiguration.OIDC_SCOPE);" + NL + "            }" + NL + "        }" + NL + "" + NL + "        settings.put(org.talend.esb.security.oidc.OidcConfiguration.OIDC_TOKEN_ENDPOINT_LOCATION, tokenEndpoint);" + NL + "        settings.put(org.talend.esb.security.oidc.OidcConfiguration.OIDC_VALIDATION_ENDPOINT_LOCATION, validationEndpoint);" + NL + "        settings.put(org.talend.esb.security.oidc.OidcConfiguration.OIDC_PUBLIC_CLIENT_ID, clientId);" + NL + "        settings.put(org.talend.esb.security.oidc.OidcConfiguration.OIDC_SCOPE, scope);" + NL + "" + NL + "        return settings;" + NL + "    }";
  protected final String TEXT_125 = NL + NL + "    private String oidcClientBearer(String oidcUsername, String oidcPassword) throws java.lang.Exception {" + NL + "        return org.talend.esb.security.oidc.OidcClientUtils.oidcClientBearer(oidcUsername, oidcPassword, getOidcSettings());" + NL + "    }";
  protected final String TEXT_126 = NL + "            @Override" + NL + "            public void configure() throws java.lang.Exception {" + NL + "                doConfig();" + NL + "            }" + NL + "" + NL + "            class DefaultRegistry extends org.apache.camel.support.DefaultRegistry{" + NL + "            " + NL + "                private org.apache.camel.spi.Registry registry = null;" + NL + "                " + NL + "                public DefaultRegistry(org.apache.camel.spi.Registry registry){" + NL + "                    this.registry = registry;" + NL + "                }" + NL + "        " + NL + "                public void put(String k, Object v) {" + NL + "                    registry.bind(k, (v == null) ? Object.class: v.getClass(), v);" + NL + "                }" + NL + "                " + NL + "                public Object get(String k) {" + NL + "                    return registry.lookupByName(k);" + NL + "                }" + NL + "        " + NL + "            }" + NL + "" + NL + "            public void destroy() {";
  protected final String TEXT_127 = NL + NL + "                if (null != pooledConnectionFactory_";
  protected final String TEXT_128 = ") {" + NL + "                    pooledConnectionFactory_";
  protected final String TEXT_129 = ".clear();" + NL + "                }";
  protected final String TEXT_130 = NL + NL + "            }" + NL + "" + NL + "            public void doConfig() throws java.lang.Exception {" + NL + "                final /*org.apache.camel.model.Model*/CamelContext camelContext = getContext();" + NL + "                " + NL + "                camelContext.setDebugging(true);" + NL + "                camelContext.setStreamCaching(false);" + NL + "                org.apache.camel.spi.Registry realRegistry = camelContext.getRegistry();" + NL + "                " + NL + "                if (realRegistry == null) {" + NL + "                    realRegistry = new org.apache.camel.support.DefaultRegistry();" + NL + "                }" + NL + "                " + NL + "                DefaultRegistry registry = new DefaultRegistry(realRegistry);" + NL + "                " + NL + "                ((org.apache.camel.impl.DefaultCamelContext) camelContext).setRegistry(realRegistry);" + NL + "                " + NL + "                // add notifier" + NL + "                java.util.Collection<org.apache.camel.management.JmxNotificationEventNotifier> jmxEventNotifiers = camelContext" + NL + "                    .getRegistry().findByType(" + NL + "                        org.apache.camel.management.JmxNotificationEventNotifier.class);" + NL + "                if (jmxEventNotifiers != null && !jmxEventNotifiers.isEmpty()) {" + NL + "                    camelContext.getManagementStrategy().addEventNotifier(" + NL + "                        jmxEventNotifiers.iterator().next());" + NL + "                }" + NL + "                " + NL;
  protected final String TEXT_131 = NL + "                        String keyStore = System.getProperty(\"javax.net.ssl.keyStore\");" + NL + "                        String keyStorePassword = System.getProperty(\"javax.net.ssl.keyStorePassword\");" + NL + "" + NL + "                        if (keyStore == null) {" + NL + "                            System.setProperty(\"javax.net.ssl.keyStore\", \"config/clientstore-conduits.jks\");" + NL + "                        }" + NL + "                        if (keyStorePassword == null) {" + NL + "                            System.setProperty(\"javax.net.ssl.keyStorePassword\", \"cspass\");" + NL + "                        }";
  protected final String TEXT_132 = NL + NL + "                    java.util.List<Object> providers = new java.util.ArrayList<Object>();" + NL + "                    providers.add(new com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider());" + NL + "                    providers.add(new com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider());" + NL + "                    registry.put(\"providers\", providers);" + NL;
  protected final String TEXT_133 = NL + "                        ";
  protected final String TEXT_134 = NL + "                    //read context values" + NL + "                    readContextValues(contextStr);";
  protected final String TEXT_135 = NL + "                    if(inOSGi){";
  protected final String TEXT_136 = NL + "                    }";
  protected final String TEXT_137 = NL + "                    //read context values" + NL + "                    readContextValues(contextStr, camelContext);";
  protected final String TEXT_138 = NL + "                    camelContext.setUseMDCLogging(true);";
  protected final String TEXT_139 = NL + "                    consumerSoapHeaderFilter.setRelayHeaders(false);" + NL + "                    registry.put(\"CXF_PAYLOAD_HEADER_FILTER\", consumerSoapHeaderFilter);";
  protected final String TEXT_140 = NL + "                    registry.put(\"DEFAULT_CXF_BINDING\", new CXFProviderBinding());";
  protected final String TEXT_141 = NL + "                org.apache.cxf.jaxrs.AbstractJAXRSFactoryBean factory_";
  protected final String TEXT_142 = ";";
  protected final String TEXT_143 = NL + "                    org.apache.cxf.jaxrs.JAXRSServerFactoryBean sf_";
  protected final String TEXT_144 = " = new org.apache.cxf.jaxrs.JAXRSServerFactoryBean();" + NL + "                    factory_";
  protected final String TEXT_145 = " = sf_";
  protected final String TEXT_146 = ";" + NL + "                    sf_";
  protected final String TEXT_147 = ".setServiceClass(";
  protected final String TEXT_148 = ".class);" + NL + "                    sf_";
  protected final String TEXT_149 = ".setResourceProvider(";
  protected final String TEXT_150 = ".class, new org.apache.camel.component.cxf.jaxrs.CamelResourceProvider(";
  protected final String TEXT_151 = ".class));" + NL + "                    sf_";
  protected final String TEXT_152 = ".setProvider(new CxfPayloadProvider());" + NL + "                    // avoid JAXBException in runtime" + NL + "                    sf_";
  protected final String TEXT_153 = ".setProvider(new org.apache.cxf.jaxrs.provider.SourceProvider());" + NL;
  protected final String TEXT_154 = NL + "                                org.apache.cxf.jaxrs.security.JAASAuthenticationFilter jaas_";
  protected final String TEXT_155 = " = new org.apache.cxf.jaxrs.security.JAASAuthenticationFilter();" + NL + "                                jaas_";
  protected final String TEXT_156 = ".setContextName(\"karaf\");" + NL + "                                sf_";
  protected final String TEXT_157 = ".setProvider(jaas_";
  protected final String TEXT_158 = ");";
  protected final String TEXT_159 = NL + NL + "                            Properties pepProps = new Properties();" + NL + "                            java.io.InputStream pepCfg = getConfigLocation(\"org.talend.esb.authorization.pep.cfg\");" + NL + "" + NL + "                            try {" + NL + "                                pepProps.load(pepCfg);";
  protected final String TEXT_160 = NL + "                                org.talend.esb.authorization.xacml.rt.pep.CXFXACMLAuthorizingInterceptor _authorizationInterceptor =" + NL + "                                        new org.talend.esb.authorization.xacml.rt.pep.CXFXACMLAuthorizingInterceptor();" + NL + "                                _authorizationInterceptor.setPdpAddress(pepProps.getProperty(\"tesb.pdp.address\"));" + NL + "                                authorizationInterceptor = (org.apache.cxf.interceptor.Interceptor) _authorizationInterceptor;" + NL;
  protected final String TEXT_161 = NL + "                            } catch (Exception e) {" + NL + "                                throw new RuntimeException(\"Cannot load pep properties\", e);" + NL + "                            }" + NL + "" + NL + "                            Properties serviceProps = new Properties();" + NL + "                            java.io.InputStream serviceCfg = getConfigLocation(\"org.talend.esb.job.service.cfg\");" + NL + "" + NL + "                            try {" + NL + "                                    serviceProps.load(serviceCfg);" + NL + "                                securityProps = new HashMap<String, Object>();" + NL + "                                for (String key : serviceProps.stringPropertyNames()) {" + NL + "                                    securityProps.put(key, serviceProps.getProperty(key));" + NL + "                                }" + NL + "                            } catch (Exception e) {" + NL + "                                throw new RuntimeException(\"Cannot load service properties\", e);" + NL + "                            }";
  protected final String TEXT_162 = NL + NL + "                            if (null != securityProps) {" + NL + "                                org.talend.esb.security.saml.SAMLRESTUtils.configureServer(sf_";
  protected final String TEXT_163 = ", securityProps);";
  protected final String TEXT_164 = NL + "                                sf_";
  protected final String TEXT_165 = ".getInInterceptors().add(authorizationInterceptor);";
  protected final String TEXT_166 = NL + "                            }";
  protected final String TEXT_167 = NL + "                                // OpenID authentication" + NL + "                                new org.talend.esb.security.oidc.OidcClientUtils(getOidcSettings()); // get settings to use them by access token validator" + NL + "                                sf_";
  protected final String TEXT_168 = ".setProvider(new org.talend.esb.security.oidc.OidcAccessTokenValidator());";
  protected final String TEXT_169 = NL + "                    org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean cf_";
  protected final String TEXT_170 = " = new org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean() {" + NL + "                        // https://issues.apache.org/jira/browse/CAMEL-7229" + NL + "                        public void setBus(org.apache.cxf.Bus bus) {" + NL + "                            if (null == this.bus) {" + NL + "                                super.setBus(bus);" + NL + "                            }" + NL + "                        };" + NL;
  protected final String TEXT_171 = NL + "                            public org.apache.cxf.jaxrs.client.WebClient createWebClient(){" + NL + "                                org.apache.cxf.jaxrs.client.WebClient ws = super.createWebClient();" + NL + "                                org.apache.cxf.jaxrs.client.ClientConfiguration config = org.apache.cxf.jaxrs.client.WebClient" + NL + "                                        .getConfig(ws);" + NL + "                                org.apache.cxf.transport.http.HTTPConduit conduit = (org.apache.cxf.transport.http.HTTPConduit) config" + NL + "                                        .getConduit();" + NL + "" + NL + "                                String urlString = \"";
  protected final String TEXT_172 = "\";" + NL + "" + NL + "                                String configName = \"org.apache.cxf.http.conduits-\" + urlString + \".cfg\";" + NL + "                                java.util.Dictionary<String, String> dic = loadHttpConduitProperties(configName);" + NL + "                                HttpConduitConfigApplier configApplier = new HttpConduitConfigApplier();" + NL + "                                configApplier.apply(dic, conduit, ";
  protected final String TEXT_173 = ");" + NL + "                                return ws;" + NL + "                            }";
  protected final String TEXT_174 = NL + "                    };" + NL + "                    factory_";
  protected final String TEXT_175 = " = cf_";
  protected final String TEXT_176 = NL + "                    cf_";
  protected final String TEXT_177 = ".class);";
  protected final String TEXT_178 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_179 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_180 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_181 = " = ";
  protected final String TEXT_182 = "; ";
  protected final String TEXT_183 = NL + "                            cf_";
  protected final String TEXT_184 = ".setUsername(";
  protected final String TEXT_185 = ");" + NL + "                            cf_";
  protected final String TEXT_186 = ".setPassword(routines.system.PasswordEncryptUtil.decryptPassword(decryptedPassword_";
  protected final String TEXT_187 = "));";
  protected final String TEXT_188 = NL + "                                Properties stsProperties = new Properties();" + NL + "                                java.io.InputStream stsCfg = getConfigLocation(\"org.talend.esb.job.client.sts.cfg\");" + NL + "" + NL + "                                try {" + NL + "                                    stsProperties.load(stsCfg);" + NL + "                                } catch (Exception e) {" + NL + "                                    throw new RuntimeException(\"Cannot load client properties\", e);" + NL + "                                }" + NL + "" + NL + "                                Map<String, Object> stsProps = new HashMap<String, Object>();" + NL + "                                for (Map.Entry<Object, Object> entry : stsProperties.entrySet()) {" + NL + "                                    stsProps.put((String)entry.getKey(), entry.getValue());" + NL + "                                }" + NL + "" + NL + "                                new org.talend.esb.security.saml.STSClientUtils(stsProps);";
  protected final String TEXT_189 = NL + "                                if (!inOSGi) {" + NL + "                                    throw new IllegalArgumentException(\"SAML based security scenarios are not supported in Studio (standalone)\");" + NL + "                                }";
  protected final String TEXT_190 = NL + "                            org.apache.cxf.ws.security.trust.STSClient stsClient_";
  protected final String TEXT_191 = " =" + NL + "                                org.talend.esb.security.saml.STSClientUtils.createSTSClient(cf_";
  protected final String TEXT_192 = ".getBus(),";
  protected final String TEXT_193 = NL + "                                    ";
  protected final String TEXT_194 = ", decryptedPassword_";
  protected final String TEXT_195 = NL + "                                org.talend.esb.security.saml.STSClientUtils.applyAuthorization(stsClient_";
  protected final String TEXT_196 = NL + "                            org.talend.esb.security.saml.SAMLRESTUtils.configureClient(cf_";
  protected final String TEXT_197 = ", stsClient_";
  protected final String TEXT_198 = NL + "                            org.talend.esb.security.oidc.OidcClientUtils.configureClient(cf_";
  protected final String TEXT_199 = ", getOidcSettings());";
  protected final String TEXT_200 = NL + "                    factory_";
  protected final String TEXT_201 = ".setAddress(";
  protected final String TEXT_202 = ".setAddress(getCXFRSEndpointAddress(";
  protected final String TEXT_203 = NL + "                factory_";
  protected final String TEXT_204 = NL + "                    if(inMS || inOSGi) {";
  protected final String TEXT_205 = ".setServiceName(new javax.xml.namespace.QName(";
  protected final String TEXT_206 = ",";
  protected final String TEXT_207 = "));" + NL + NL;
  protected final String TEXT_208 = NL + "                            initLocator(camelContext);" + NL + "" + NL + "                            factory_";
  protected final String TEXT_209 = ".setAddress(\"locator://rest\");" + NL + "" + NL + "                            org.talend.esb.servicelocator.cxf.LocatorFeature locatorFeature_";
  protected final String TEXT_210 = " = getLocatorFeature(cf_";
  protected final String TEXT_211 = ".getBus());";
  protected final String TEXT_212 = NL + "                            org.talend.esb.servicelocator.cxf.LocatorFeature locatorFeature_";
  protected final String TEXT_213 = " = getLocatorFeature(sf_";
  protected final String TEXT_214 = NL + NL;
  protected final String TEXT_215 = NL + "                    java.util.Map<String, String> slCustomProps_";
  protected final String TEXT_216 = " = new java.util.HashMap<String, String>();" + NL;
  protected final String TEXT_217 = NL + "                        slCustomProps_";
  protected final String TEXT_218 = ".put(";
  protected final String TEXT_219 = NL + "                        if(cf_";
  protected final String TEXT_220 = ".getProperties() == null){" + NL + "                            cf_";
  protected final String TEXT_221 = ".setProperties(new java.util.HashMap<String, Object>());" + NL + "                        }" + NL + "" + NL + "                        cf_";
  protected final String TEXT_222 = ".getProperties().put(org.talend.esb.servicelocator.cxf.LocatorFeature.LOCATOR_PROPERTIES, slCustomProps_";
  protected final String TEXT_223 = NL + "                        if(sf_";
  protected final String TEXT_224 = ".getProperties() == null){" + NL + "                            sf_";
  protected final String TEXT_225 = ".setProperties(new java.util.HashMap<String, Object>());" + NL + "                        }" + NL + "" + NL + "                        sf_";
  protected final String TEXT_226 = NL + "                        factory_";
  protected final String TEXT_227 = ".getFeatures().add(locatorFeature_";
  protected final String TEXT_228 = NL + "                    // {baseUri}cREST" + NL + "                    factory_";
  protected final String TEXT_229 = ", \"cREST\"));";
  protected final String TEXT_230 = NL + "                            org.talend.esb.sam.agent.feature.EventFeature eventFeature_";
  protected final String TEXT_231 = " = getEventFeature(cf_";
  protected final String TEXT_232 = " = getEventFeature(sf_";
  protected final String TEXT_233 = NL + NL + NL + "                    if (eventFeature_";
  protected final String TEXT_234 = " != null) {" + NL + "                        factory_";
  protected final String TEXT_235 = ".getFeatures().add(eventFeature_";
  protected final String TEXT_236 = ");" + NL + "                    }";
  protected final String TEXT_237 = NL + "                    final String correlationId_";
  protected final String TEXT_238 = ";" + NL + "                    if (null != correlationId_";
  protected final String TEXT_239 = " && correlationId_";
  protected final String TEXT_240 = ".length() > 0) {" + NL + "                        correlationIDCallbackHandler_";
  protected final String TEXT_241 = ".setCorrelationId(correlationId_";
  protected final String TEXT_242 = ");" + NL + "                        factory_";
  protected final String TEXT_243 = ".getProperties(true).put(org.talend.esb.policy.correlation.feature.CorrelationIDFeature.CORRELATION_ID_CALLBACK_HANDLER," + NL + "                            correlationIDCallbackHandler_";
  protected final String TEXT_244 = ");" + NL + "                    }" + NL + "                    factory_";
  protected final String TEXT_245 = ".getFeatures().add(new org.talend.esb.policy.correlation.feature.CorrelationIDFeature());";
  protected final String TEXT_246 = NL + "                registry.put(\"";
  protected final String TEXT_247 = "\", factory_";
  protected final String TEXT_248 = NL + "                java.util.List<Object> providers_";
  protected final String TEXT_249 = " = (java.util.List<Object>)registry.get(\"providers\");" + NL + "                if (providers_";
  protected final String TEXT_250 = " == null) {" + NL + "                    providers_";
  protected final String TEXT_251 = " = new java.util.ArrayList<Object>();" + NL + "                }" + NL + "                providers_";
  protected final String TEXT_252 = ".addAll(";
  protected final String TEXT_253 = ".getProviders());" + NL + "                registry.put(\"providers\", providers_";
  protected final String TEXT_254 = ");" + NL + "" + NL + "                java.util.List<org.apache.cxf.feature.Feature> fts_";
  protected final String TEXT_255 = ".getFeatures();" + NL + "                if (fts_";
  protected final String TEXT_256 = " == null) {" + NL + "                    fts_";
  protected final String TEXT_257 = " = new java.util.ArrayList<>();" + NL + "                }" + NL + "                java.util.List<org.apache.cxf.interceptor.Interceptor<?extends org.apache.cxf.message.Message>> icsIn_";
  protected final String TEXT_258 = " =";
  protected final String TEXT_259 = NL + "                    ";
  protected final String TEXT_260 = ".getInInterceptors();" + NL + "                if (icsIn_";
  protected final String TEXT_261 = " == null) {" + NL + "                    icsIn_";
  protected final String TEXT_262 = " = new java.util.ArrayList<>();" + NL + "                }" + NL + "                java.util.List<org.apache.cxf.interceptor.Interceptor<?extends org.apache.cxf.message.Message>> icsOut_";
  protected final String TEXT_263 = ".getOutInterceptors();" + NL + "                if (icsOut_";
  protected final String TEXT_264 = " == null) {" + NL + "                    icsOut_";
  protected final String TEXT_265 = " = new java.util.ArrayList<>();" + NL + "                }" + NL + "" + NL + "                org.apache.cxf.feature.Feature ft_";
  protected final String TEXT_266 = " = new  org.apache.cxf.feature.AbstractFeature() {" + NL + "                    @Override" + NL + "                    protected void initializeProvider(org.apache.cxf.interceptor.InterceptorProvider provider, org.apache.cxf.Bus bus) {" + NL + "                        org.apache.cxf.interceptor.Interceptor<org.apache.cxf.message.Message> icIn =" + NL + "                                new org.apache.cxf.phase.AbstractPhaseInterceptor<org.apache.cxf.message.Message>(org.apache.cxf.phase.Phase.RECEIVE) {" + NL + "                            @Override" + NL + "                            public void handleMessage(org.apache.cxf.message.Message msg) {" + NL + "                                org.slf4j.MDC.put(\"org.talend.esb.jobclass.name\", \"";
  protected final String TEXT_267 = ".";
  protected final String TEXT_268 = "\");" + NL + "                            }" + NL + "                        };" + NL + "                        org.apache.cxf.interceptor.Interceptor<org.apache.cxf.message.Message> icOut =" + NL + "                                new org.apache.cxf.phase.AbstractPhaseInterceptor<org.apache.cxf.message.Message>(org.apache.cxf.phase.Phase.SETUP_ENDING) {" + NL + "                            @Override" + NL + "                            public void handleMessage(org.apache.cxf.message.Message msg) {" + NL + "                                org.slf4j.MDC.remove(\"org.talend.esb.jobclass.name\");" + NL + "                            }" + NL + "                        };" + NL + "                        provider.getInInterceptors().add(icIn);" + NL + "                        provider.getInFaultInterceptors().add(icIn);" + NL + "                        provider.getOutInterceptors().add(icOut);" + NL + "                        provider.getOutFaultInterceptors().add(icOut);" + NL + "                    }" + NL + "                };" + NL + "" + NL + "                fts_";
  protected final String TEXT_269 = ".add(ft_";
  protected final String TEXT_270 = ");" + NL + "" + NL + "                registry.put(\"features_";
  protected final String TEXT_271 = "\", fts_";
  protected final String TEXT_272 = ");" + NL + "                registry.put(\"inInterceptors_";
  protected final String TEXT_273 = "\", icsIn_";
  protected final String TEXT_274 = ");" + NL + "                registry.put(\"outInterceptors_";
  protected final String TEXT_275 = "\", icsOut_";
  protected final String TEXT_276 = ");" + NL + "                registry.put(\"properties_";
  protected final String TEXT_277 = "\", ";
  protected final String TEXT_278 = ".getProperties() != null ? ";
  protected final String TEXT_279 = ".getProperties() : new java.util.HashMap<String, Object>());";
  protected final String TEXT_280 = NL + "                registry.put(\"endpointConfigurer_";
  protected final String TEXT_281 = "\", new org.apache.camel.component.cxf.jaxrs.";
  protected final String TEXT_282 = "() {" + NL + "                        @Override" + NL + "                        public void configure(org.apache.cxf.jaxrs.AbstractJAXRSFactoryBean factoryBean) {";
  protected final String TEXT_283 = NL + "                                    if(inMS || inOSGi) {";
  protected final String TEXT_284 = NL + "                                    // SL" + NL + "                                    factoryBean.setServiceName(new javax.xml.namespace.QName(";
  protected final String TEXT_285 = NL + "                                                                    ";
  protected final String TEXT_286 = NL + "                                    factoryBean.setAddress(\"locator://rest\"); // is ignored by Camel";
  protected final String TEXT_287 = NL + "                                    }";
  protected final String TEXT_288 = NL + "                        }" + NL + "" + NL + "                        @Override" + NL + "                        public void configureClient(org.apache.cxf.jaxrs.client.Client client) {";
  protected final String TEXT_289 = NL + "                                org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean cfb = (org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean)";
  protected final String TEXT_290 = ";" + NL + "                                String authHeader = cfb.getUsername() + \":\" + cfb.getPassword();" + NL + "                                authHeader = \"Basic \" + java.util.Base64.getEncoder().encodeToString(authHeader.getBytes());" + NL + "                                client.header(\"Authorization\", authHeader);";
  protected final String TEXT_291 = NL + "                        }" + NL + "" + NL + "                        @Override" + NL + "                        public void configureServer(org.apache.cxf.endpoint.Server server) {" + NL + "" + NL + "                        }" + NL + "                    }" + NL + "                );";
  protected final String TEXT_292 = NL + "                if(camelContext.getRegistry().lookupByName(\"";
  protected final String TEXT_293 = "\") == null) {" + NL + "                    if (";
  protected final String TEXT_294 = " && inOSGi) {" + NL + "                        javax.sql.DataSource ds_";
  protected final String TEXT_295 = " = routines.system.BundleUtils.getService(javax.sql.DataSource.class, \"(osgi.jndi.service.name=\" + ";
  protected final String TEXT_296 = " + \")\");" + NL + "                        if (ds_";
  protected final String TEXT_297 = " != null) {" + NL + "                            log.info(\"Using container managed datasource: \" + ";
  protected final String TEXT_298 = " + \" : \" + ds_";
  protected final String TEXT_299 = ".toString());" + NL + "                            camelContext.getRegistry().bind(\"";
  protected final String TEXT_300 = "\", ds_";
  protected final String TEXT_301 = ");" + NL + "                        } else {" + NL + "                            log.error(\"Datasource reference could not be found for: \" + ";
  protected final String TEXT_302 = ");" + NL + "                        }" + NL + "" + NL + "                    } else {" + NL + "                        org.apache.commons.dbcp2.BasicDataSource ds_";
  protected final String TEXT_303 = " = new org.apache.commons.dbcp2.BasicDataSource();" + NL + "                        ds_";
  protected final String TEXT_304 = ".setDriverClassName(";
  protected final String TEXT_305 = ");" + NL + "                        ds_";
  protected final String TEXT_306 = ".setUrl(";
  protected final String TEXT_307 = NL + "                        ds_";
  protected final String TEXT_308 = ".setPassword(decryptedPassword_";
  protected final String TEXT_309 = NL + "                        ds_";
  protected final String TEXT_310 = ".setInitialSize(";
  protected final String TEXT_311 = ".setMaxTotal(";
  protected final String TEXT_312 = ".setMinIdle(";
  protected final String TEXT_313 = ".setMaxIdle(";
  protected final String TEXT_314 = ".setMaxConn(";
  protected final String TEXT_315 = ".setDefaultQueryTimeout(";
  protected final String TEXT_316 = ".setValidationQueryTimeout(java.time.Duration.ofSeconds(";
  protected final String TEXT_317 = ".setValidationQuery(";
  protected final String TEXT_318 = ".setConnectionInitSqls(java.util.Arrays.asList(";
  protected final String TEXT_319 = NL + "                        camelContext.getRegistry().bind(\"";
  protected final String TEXT_320 = ");" + NL + "                    }" + NL + "                }" + NL;
  protected final String TEXT_321 = NL + "                    registry.put(";
  protected final String TEXT_322 = ", new ";
  protected final String TEXT_323 = NL + "                {" + NL + "                    Object beanInstance = null;";
  protected final String TEXT_324 = NL + "                    if(beanInstance != null){" + NL + "                        registry.put(";
  protected final String TEXT_325 = ", beanInstance);" + NL + "                    }" + NL + "                }";
  protected final String TEXT_326 = NL + "                registry.put(";
  protected final String TEXT_327 = "));" + NL;
  protected final String TEXT_328 = NL + "                        javax.jms.ConnectionFactory jmsConnectionFactory_";
  protected final String TEXT_329 = " =" + NL + "                            new org.apache.activemq.ActiveMQConnectionFactory( ";
  protected final String TEXT_330 = " ,";
  protected final String TEXT_331 = NL + "                                org.apache.activemq.ActiveMQConnectionFactory jmsConnectionFactory_";
  protected final String TEXT_332 = " =" + NL + "                                    new org.apache.activemq.ActiveMQConnectionFactory(";
  protected final String TEXT_333 = ");" + NL + "                                jmsConnectionFactory_";
  protected final String TEXT_334 = ".setUseRetroactiveConsumer(true);";
  protected final String TEXT_335 = NL + "                                javax.jms.ConnectionFactory jmsConnectionFactory_";
  protected final String TEXT_336 = NL + "                        pooledConnectionFactory_";
  protected final String TEXT_337 = NL + "                            = new org.apache.activemq.pool.PooledConnectionFactory();" + NL + "                        pooledConnectionFactory_";
  protected final String TEXT_338 = ".setConnectionFactory(jmsConnectionFactory_";
  protected final String TEXT_339 = ");" + NL + "                        pooledConnectionFactory_";
  protected final String TEXT_340 = ".setExpiryTimeout(Long.valueOf(";
  protected final String TEXT_341 = "));" + NL + "                        pooledConnectionFactory_";
  protected final String TEXT_342 = ".setIdleTimeout(";
  protected final String TEXT_343 = ".setMaxConnections(";
  protected final String TEXT_344 = ".setMaximumActiveSessionPerConnection(";
  protected final String TEXT_345 = ");" + NL + "" + NL + "                        if(camelContext.getComponent(";
  protected final String TEXT_346 = ")==null){" + NL + "                                                    camelContext.addComponent(";
  protected final String TEXT_347 = "," + NL + "                            org.apache.camel.component.jms.JmsComponent.jmsComponent";
  protected final String TEXT_348 = "(pooledConnectionFactory_";
  protected final String TEXT_349 = "));" + NL + "                        }" + NL;
  protected final String TEXT_350 = NL + "                        if(camelContext.getComponent(";
  protected final String TEXT_351 = ")==null){" + NL + "                            camelContext.addComponent(";
  protected final String TEXT_352 = "," + NL + "                                org.apache.camel.component.jms.JmsComponent.jmsComponent";
  protected final String TEXT_353 = "(jmsConnectionFactory_";
  protected final String TEXT_354 = "));" + NL + "                            }";
  protected final String TEXT_355 = NL + "                    {" + NL + "                    javax.jms.ConnectionFactory jmsConnectionFactory = new com.ibm.mq.jms.MQConnectionFactory();" + NL + "                    ((com.ibm.mq.jms.MQConnectionFactory) jmsConnectionFactory).setHostName(";
  protected final String TEXT_356 = ");" + NL + "                    try {" + NL + "                        ((com.ibm.mq.jms.MQConnectionFactory) jmsConnectionFactory).setPort(";
  protected final String TEXT_357 = ");" + NL + "                        // ((com.ibm.mq.jms.MQConnectionFactory) jmsConnectionFactory).setCCSID(866);" + NL + "                        ((com.ibm.mq.jms.MQConnectionFactory) jmsConnectionFactory).setTransportType(";
  protected final String TEXT_358 = ");" + NL + "                        ((com.ibm.mq.jms.MQConnectionFactory) jmsConnectionFactory).setQueueManager(";
  protected final String TEXT_359 = NL + "                            ((com.ibm.mq.jms.MQConnectionFactory) jmsConnectionFactory).setChannel(";
  protected final String TEXT_360 = NL + NL + "                    } catch (javax.jms.JMSException e) {" + NL + "                        e.printStackTrace();" + NL + "                    }";
  protected final String TEXT_361 = NL + "                    if(camelContext.getComponent(";
  protected final String TEXT_362 = ")==null){" + NL + "                        camelContext.addComponent(";
  protected final String TEXT_363 = "(jmsConnectionFactory));" + NL + "                    }";
  protected final String TEXT_364 = NL + "                    org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter connectionFactoryAdapter";
  protected final String TEXT_365 = NL + "                            = new org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter();" + NL + "                    connectionFactoryAdapter";
  protected final String TEXT_366 = ");" + NL;
  protected final String TEXT_367 = NL + NL + "                    connectionFactoryAdapter";
  protected final String TEXT_368 = ");" + NL + "                    connectionFactoryAdapter";
  protected final String TEXT_369 = ".setTargetConnectionFactory(jmsConnectionFactory);" + NL + "" + NL + "                    if(camelContext.getComponent(";
  protected final String TEXT_370 = "(connectionFactoryAdapter";
  protected final String TEXT_371 = "));" + NL + "                    }";
  protected final String TEXT_372 = NL + "                    StringBuilder hostValue_";
  protected final String TEXT_373 = " = new StringBuilder();";
  protected final String TEXT_374 = NL + "                        String cMQConnectionFactoryHost =(String) context.getProperty(\"";
  protected final String TEXT_375 = "\");" + NL + "" + NL + "                        if (!cMQConnectionFactoryHost.isEmpty() && (cMQConnectionFactoryHost.startsWith(\"\\\"failover\") || cMQConnectionFactoryHost.startsWith(\"failover\"))) {" + NL + "                            hostValue_";
  protected final String TEXT_376 = ".append(cMQConnectionFactoryHost);" + NL + "                        } else {                         " + NL + "                            hostValue_";
  protected final String TEXT_377 = ".append(";
  protected final String TEXT_378 = "?\"amqps\":\"amqp\");" + NL + "                            hostValue_";
  protected final String TEXT_379 = ".append(\"://\");" + NL + "                            hostValue_";
  protected final String TEXT_380 = ".append(cMQConnectionFactoryHost);" + NL + "                            if (!";
  protected final String TEXT_381 = ".isEmpty()) {" + NL + "                                hostValue_";
  protected final String TEXT_382 = ".append(\":\");" + NL + "                                hostValue_";
  protected final String TEXT_383 = ");" + NL + "                            }" + NL + "                        }";
  protected final String TEXT_384 = NL + "                    hostValue_";
  protected final String TEXT_385 = NL + "                        org.apache.qpid.jms.JmsConnectionFactory jmsConnFactory";
  protected final String TEXT_386 = " = new org.apache.qpid.jms.JmsConnectionFactory(";
  protected final String TEXT_387 = ", hostValue_";
  protected final String TEXT_388 = ".toString());";
  protected final String TEXT_389 = " = new org.apache.qpid.jms.JmsConnectionFactory(hostValue_";
  protected final String TEXT_390 = NL + NL + "                    if(camelContext.getComponent(";
  protected final String TEXT_391 = "," + NL + "                                new org.apache.camel.component.amqp.AMQPComponent(jmsConnFactory";
  protected final String TEXT_392 = "));" + NL + "" + NL + "                    }" + NL + "                    //((org.apache.camel.component.amqp.AMQPComponent) camelContext.getComponent(";
  protected final String TEXT_393 = ")).getConfiguration().setClientId(\"";
  protected final String TEXT_394 = "\" + java.util.UUID.randomUUID().toString());";
  protected final String TEXT_395 = NL + "                     org.apache.camel.component.paho.PahoComponent mqttComponent_";
  protected final String TEXT_396 = " = new org.apache.camel.component.paho.PahoComponent(){" + NL + "                         @Override" + NL + "                            protected org.apache.camel.Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {" + NL + "                             org.apache.camel.component.paho.PahoEndpoint endpoint = new org.apache.camel.component.paho.PahoEndpoint(uri, remaining, this, getConfiguration().copy()) {" + NL + "                                @Override" + NL + "                                public boolean isLenientProperties() {" + NL + "                                    return true;" + NL + "                                }" + NL + "                             };" + NL + "                             this.setProperties(endpoint, parameters);" + NL + "                             org.apache.camel.component.paho.PahoConfiguration config = endpoint.getConfiguration();";
  protected final String TEXT_397 = NL + "                    {";
  protected final String TEXT_398 = NL + "                    config.setPassword(decryptedPassword_";
  protected final String TEXT_399 = ");" + NL + "                    }" + NL + "                    config.setUserName(";
  protected final String TEXT_400 = NL + "                            config.setBrokerUrl(";
  protected final String TEXT_401 = ");" + NL + "                            config.setAutomaticReconnect(";
  protected final String TEXT_402 = ");" + NL + "                            config.setConnectionTimeout(";
  protected final String TEXT_403 = ");" + NL + "                            config.setMaxReconnectDelay(";
  protected final String TEXT_404 = ");" + NL + "                            config.setMqttVersion(";
  protected final String TEXT_405 = ");" + NL + "                            config.setQos(";
  protected final String TEXT_406 = NL + "                                java.util.Properties sslProps = new java.util.Properties();" + NL + "                                " + NL + "                                sslProps.setProperty(\"com.ibm.ssl.keyStore\", ";
  protected final String TEXT_407 = NL + "                                    String kDecryptedPassword_";
  protected final String TEXT_408 = NL + "                                sslProps.setProperty(\"com.ibm.ssl.keyStorePassword\", kDecryptedPassword_";
  protected final String TEXT_409 = ");" + NL + "                                " + NL + "                                sslProps.setProperty(\"com.ibm.ssl.trustStore\", ";
  protected final String TEXT_410 = NL + "                                sslProps.setProperty(\"com.ibm.ssl.trustStorePassword\", decryptedPassword_";
  protected final String TEXT_411 = ");" + NL + "" + NL + "                                config.setSslClientProps(sslProps);";
  protected final String TEXT_412 = NL + "                            return endpoint;" + NL + "                        }" + NL + "                     };" + NL + "                    if(camelContext.getComponent(";
  protected final String TEXT_413 = ", mqttComponent_";
  protected final String TEXT_414 = NL + "                    {" + NL + "                        javax.jms.ConnectionFactory jmsConnectionFactory;";
  protected final String TEXT_415 = "(jmsConnectionFactory));" + NL + "                        }" + NL + "                    }";
  protected final String TEXT_416 = NL + "            // CXF endpoint for ";
  protected final String TEXT_417 = NL + "            org.apache.camel.component.cxf.jaxws.CxfEndpoint ";
  protected final String TEXT_418 = " = getCxfEndpoint(";
  protected final String TEXT_419 = NL + "                        ,";
  protected final String TEXT_420 = ",decryptedPassword_";
  protected final String TEXT_421 = NL + "            );" + NL;
  protected final String TEXT_422 = ".getFeatures().add(new org.apache.cxf.transport.common.gzip.GZIPFeature());";
  protected final String TEXT_423 = NL + "            " + NL + "            java.util.List<org.apache.cxf.feature.Feature> fts_";
  protected final String TEXT_424 = " = endpoint_";
  protected final String TEXT_425 = ".getFeatures(); " + NL + "            java.util.List<org.apache.cxf.interceptor.Interceptor<? extends org.apache.cxf.message.Message>> icsIn_";
  protected final String TEXT_426 = NL + "                    .getInInterceptors();" + NL + "            if (icsIn_";
  protected final String TEXT_427 = " == null) {" + NL + "                icsIn_";
  protected final String TEXT_428 = " = new java.util.ArrayList<>();" + NL + "            }" + NL + "            java.util.List<org.apache.cxf.interceptor.Interceptor<? extends org.apache.cxf.message.Message>> icsOut_";
  protected final String TEXT_429 = NL + "                    .getOutInterceptors();" + NL + "            if (icsOut_";
  protected final String TEXT_430 = " == null) {" + NL + "                icsOut_";
  protected final String TEXT_431 = " = new java.util.ArrayList<>();" + NL + "            }" + NL + "" + NL + "            org.apache.cxf.feature.Feature ft_";
  protected final String TEXT_432 = " = new org.apache.cxf.feature.AbstractFeature() {" + NL + "                @Override" + NL + "                protected void initializeProvider(org.apache.cxf.interceptor.InterceptorProvider provider," + NL + "                        org.apache.cxf.Bus bus) {" + NL + "                    org.apache.cxf.interceptor.Interceptor<org.apache.cxf.message.Message> icIn = new org.apache.cxf.phase.AbstractPhaseInterceptor<org.apache.cxf.message.Message>(" + NL + "                            org.apache.cxf.phase.Phase.RECEIVE) {" + NL + "                        @Override" + NL + "                        public void handleMessage(org.apache.cxf.message.Message msg) {" + NL + "                            org.slf4j.MDC.put(\"org.talend.esb.jobclass.name\", \"";
  protected final String TEXT_433 = "\");                                   " + NL + "                        }" + NL + "                    };" + NL + "                    org.apache.cxf.interceptor.Interceptor<org.apache.cxf.message.Message> icOut = new org.apache.cxf.phase.AbstractPhaseInterceptor<org.apache.cxf.message.Message>(" + NL + "                            org.apache.cxf.phase.Phase.SETUP_ENDING) {" + NL + "                        @Override" + NL + "                        public void handleMessage(org.apache.cxf.message.Message msg) {" + NL + "                            org.slf4j.MDC.remove(\"org.talend.esb.jobclass.name\");" + NL + "                        }" + NL + "                    };" + NL + "                    provider.getInInterceptors().add(icIn);" + NL + "                    provider.getInFaultInterceptors().add(icIn);" + NL + "                    provider.getOutInterceptors().add(icOut);" + NL + "                    provider.getOutFaultInterceptors().add(icOut);" + NL + "                }" + NL + "            };" + NL + "" + NL + "            fts_";
  protected final String TEXT_434 = ");" + NL + "" + NL + "            registry.put(\"features_";
  protected final String TEXT_435 = ");" + NL + "            registry.put(\"inInterceptors_";
  protected final String TEXT_436 = ");" + NL + "            registry.put(\"outInterceptors_";
  protected final String TEXT_437 = ".setCxfBinding(new CXFConsumerBinding(";
  protected final String TEXT_438 = NL + "                final String correlationId_";
  protected final String TEXT_439 = ";" + NL + "                if (null != correlationId_";
  protected final String TEXT_440 = ".length() > 0) {" + NL + "                    correlationIDCallbackHandler_";
  protected final String TEXT_441 = ");" + NL + "                    java.util.Map<String, Object> properties = new java.util.HashMap<String, Object>();" + NL + "                    properties.put(org.talend.esb.policy.correlation.feature.CorrelationIDFeature.CORRELATION_ID_CALLBACK_HANDLER," + NL + "                        correlationIDCallbackHandler_";
  protected final String TEXT_442 = ".setProperties(properties);" + NL + "                }";
  protected final String TEXT_443 = NL + "                    initLocator(camelContext);";
  protected final String TEXT_444 = ".setAddress(\"locator://cSOAP\");";
  protected final String TEXT_445 = NL + "                // Add Service Locator Service to ";
  protected final String TEXT_446 = ".setBus(springBus());";
  protected final String TEXT_447 = ".setBus(new org.apache.cxf.bus.spring.SpringBusFactory().createBus(\"META-INF/tesb/locator/beans.xml\"));";
  protected final String TEXT_448 = NL + "                    if (!inOSGi) {";
  protected final String TEXT_449 = ".setBus(new org.apache.cxf.bus.spring.SpringBusFactory().createBus(\"META-INF/tesb/locator/beans.xml\"));" + NL + "                    }";
  protected final String TEXT_450 = NL + NL + "                org.talend.esb.servicelocator.cxf.LocatorFeature locatorFeature_";
  protected final String TEXT_451 = " = getLocatorFeature(";
  protected final String TEXT_452 = ".getBus());" + NL;
  protected final String TEXT_453 = " = new java.util.HashMap<String, String>();";
  protected final String TEXT_454 = NL + NL + "                    java.util.Map<String, Object> endpointProps_";
  protected final String TEXT_455 = ".getProperties();" + NL + "                    if (endpointProps_";
  protected final String TEXT_456 = " == null) {" + NL + "                        endpointProps_";
  protected final String TEXT_457 = " = new java.util.HashMap<String, Object>();" + NL + "                    }" + NL + "                    endpointProps_";
  protected final String TEXT_458 = ".put(org.talend.esb.servicelocator.cxf.LocatorFeature.LOCATOR_PROPERTIES, slCustomProps_";
  protected final String TEXT_459 = ".setProperties(endpointProps_";
  protected final String TEXT_460 = NL + "            //http://jira.talendforge.org/browse/TESB-3850" + NL + "            // !\"true\".equals(useRegistry) - https://jira.talendforge.org/browse/TESB-10725";
  protected final String TEXT_461 = NL + "                // Add Service Activity Monitor Service to ";
  protected final String TEXT_462 = NL + NL + "                org.talend.esb.sam.agent.feature.EventFeature eventFeature_";
  protected final String TEXT_463 = " = getEventFeature(";
  protected final String TEXT_464 = ".getBus());" + NL + "" + NL + "                if (eventFeature_";
  protected final String TEXT_465 = " != null) {";
  protected final String TEXT_466 = ");" + NL + "                }" + NL;
  protected final String TEXT_467 = NL + "                // Avoid using Service Registry for Service Consumer (TESB-19181)" + NL + "                if (";
  protected final String TEXT_468 = ".getProperties() == null) {";
  protected final String TEXT_469 = ".setProperties(new java.util.HashMap<String, Object>());" + NL + "                }";
  protected final String TEXT_470 = ".getProperties().put(\"use.service.registry\", \"false\");";
  protected final String TEXT_471 = NL;
  protected final String TEXT_472 = NL + "            if(inOSGi){" + NL + "           " + NL + "                // add handlers for main MIME types" + NL + "                javax.activation.MailcapCommandMap mc_";
  protected final String TEXT_473 = " = ( javax.activation.MailcapCommandMap)javax.activation.CommandMap.getDefaultCommandMap();" + NL + "                mc_";
  protected final String TEXT_474 = ".addMailcap(\"text/html;; x-java-content-handler=com.sun.mail.handlers.text_html\");" + NL + "                mc_";
  protected final String TEXT_475 = ".addMailcap(\"text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml\");" + NL + "                mc_";
  protected final String TEXT_476 = ".addMailcap(\"text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain\");" + NL + "                mc_";
  protected final String TEXT_477 = ".addMailcap(\"multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed\");" + NL + "                mc_";
  protected final String TEXT_478 = ".addMailcap(\"message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822\");" + NL + "                javax.activation.CommandMap.setDefaultCommandMap(mc_";
  protected final String TEXT_479 = ");" + NL + "                // add com.sun.mail.handlers to job imports" + NL + "                com.sun.mail.handlers.text_plain text_plain_h_";
  protected final String TEXT_480 = " = null;" + NL + "           }" + NL;
  protected final String TEXT_481 = NL + "                        org.apache.camel.spi.AggregationRepository repo_";
  protected final String TEXT_482 = " = new ";
  protected final String TEXT_483 = NL + "                        org.apache.camel.spi.RecoverableAggregationRepository repo_";
  protected final String TEXT_484 = NL + "                            repo_";
  protected final String TEXT_485 = ".setUseRecovery(true);" + NL + "                            repo_";
  protected final String TEXT_486 = ".setMaximumRedeliveries(";
  protected final String TEXT_487 = ");" + NL + "                            repo_";
  protected final String TEXT_488 = ".setDeadLetterUri(";
  protected final String TEXT_489 = ".setRecoveryInterval(";
  protected final String TEXT_490 = NL + "                            org.apache.camel.component.leveldb.LevelDBAggregationRepository repo_";
  protected final String TEXT_491 = " = new org.apache.camel.component.leveldb.LevelDBAggregationRepository(\"";
  protected final String TEXT_492 = "\");";
  protected final String TEXT_493 = NL + "                Map<String, String> ";
  protected final String TEXT_494 = "NSMap = new HashMap<String, String>();" + NL + "                {";
  protected final String TEXT_495 = "NSMap.put(";
  protected final String TEXT_496 = NL + "        Map<String, String> ";
  protected final String TEXT_497 = "NSMap = new HashMap<String, String>();" + NL + "        {";
  protected final String TEXT_498 = NL + "        }";
  protected final String TEXT_499 = NL + "            Map<String,String> ";
  protected final String TEXT_500 = "userDefinedHeaders = new HashMap<String,String>();";
  protected final String TEXT_501 = "userDefinedHeaders.put(";
  protected final String TEXT_502 = NL + "        }" + NL;
  protected final String TEXT_503 = NL + NL + "        org.apache.camel.language.xpath.XPathBuilder xpathBuilder_";
  protected final String TEXT_504 = " = new org.apache.camel.language.xpath.XPathBuilder(";
  protected final String TEXT_505 = ");" + NL + "        xpathBuilder_";
  protected final String TEXT_506 = ".setNamespaces(";
  protected final String TEXT_507 = "NSMap);" + NL + "        xpathBuilder_";
  protected final String TEXT_508 = ".threadSafety(true);" + NL;
  protected final String TEXT_509 = NL + "            camelContext.setStreamCaching(true);";
  protected final String TEXT_510 = " ";
  protected final String TEXT_511 = "ns =";
  protected final String TEXT_512 = NL + "        org.talend.camel.TalendEndpoint endpoint_";
  protected final String TEXT_513 = " =" + NL + "            (org.talend.camel.TalendEndpoint) endpoint(\"talend:\" + ";
  protected final String TEXT_514 = " + \"?context=\" + ";
  protected final String TEXT_515 = " + \"&propagateHeader=";
  protected final String TEXT_516 = "&sticky=";
  protected final String TEXT_517 = "&componentId=";
  protected final String TEXT_518 = NL + "            org.talend.camel.TalendEndpoint endpoint_";
  protected final String TEXT_519 = " =" + NL + "                (org.talend.camel.TalendEndpoint) endpoint(\"talend:";
  protected final String TEXT_520 = "/";
  protected final String TEXT_521 = "?context=\" + ";
  protected final String TEXT_522 = "?context=";
  protected final String TEXT_523 = "&propagateHeader=";
  protected final String TEXT_524 = NL + "{" + NL + "        Map <String, String> propertiesMap = new HashMap<String, String>();";
  protected final String TEXT_525 = NL + "                propertiesMap.put(";
  protected final String TEXT_526 = ", propertyToString(";
  protected final String TEXT_527 = NL + "        endpoint_";
  protected final String TEXT_528 = ".setEndpointProperties(propertiesMap);" + NL + "}";
  protected final String TEXT_529 = NL + "        log.info(\"";
  protected final String TEXT_530 = " - Creating new connection.\");";
  protected final String TEXT_531 = NL + "        software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider credentials_";
  protected final String TEXT_532 = " = software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider.create();";
  protected final String TEXT_533 = NL + "        software.amazon.awssdk.auth.credentials.AwsBasicCredentials credentials_";
  protected final String TEXT_534 = " = software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create(";
  protected final String TEXT_535 = NL + "                int socketSendBufferSizeHints_";
  protected final String TEXT_536 = " = 0;" + NL + "                int socketReceiveBufferSizeHints_";
  protected final String TEXT_537 = " = 0;";
  protected final String TEXT_538 = NL + "                socketSendBufferSizeHints_";
  protected final String TEXT_539 = NL + "                socketReceiveBufferSizeHints_";
  protected final String TEXT_540 = NL + "                    software.amazon.awssdk.http.SdkHttpClient  httpClient_";
  protected final String TEXT_541 = " = software.amazon.awssdk.http.apache.ApacheHttpClient.builder()";
  protected final String TEXT_542 = NL + "                    .socketTimeout(java.time.Duration.ofMillis(";
  protected final String TEXT_543 = "))";
  protected final String TEXT_544 = NL + "                    .connectionTimeout(java.time.Duration.ofMillis(";
  protected final String TEXT_545 = NL + "                    .proxyConfiguration(software.amazon.awssdk.http.apache.ProxyConfiguration.builder()" + NL + "\t\t\t        .useSystemPropertyValues(false)" + NL + "\t\t\t        .endpoint(java.net.URI.create(\"";
  protected final String TEXT_546 = "://";
  protected final String TEXT_547 = ":";
  protected final String TEXT_548 = "\"))" + NL + "\t\t\t        .username(\"";
  protected final String TEXT_549 = "\")" + NL + "\t\t\t        .password(\"";
  protected final String TEXT_550 = "\")" + NL + "\t\t\t        .build())";
  protected final String TEXT_551 = NL + "                    .build();";
  protected final String TEXT_552 = NL + "                    ";
  protected final String TEXT_553 = ".builder().credentialsProvider(software.amazon.awssdk.auth.credentials.StaticCredentialsProvider.create(credentials_";
  protected final String TEXT_554 = "))" + NL + "                    .httpClient(httpClient_";
  protected final String TEXT_555 = ".builder().credentialsProvider(";
  protected final String TEXT_556 = NL + "                     credentials_";
  protected final String TEXT_557 = " " + NL + "                    software.amazon.awssdk.auth.credentials.StaticCredentialsProvider.create(credentials_";
  protected final String TEXT_558 = NL + "                    .httpClient(software.amazon.awssdk.http.apache.ApacheHttpClient.builder().build())";
  protected final String TEXT_559 = NL + "                    .region(software.amazon.awssdk.regions.Region.";
  protected final String TEXT_560 = NL + "\t\t\t\t.build();" + NL + "\t\t\t\t";
  protected final String TEXT_561 = " = com.amazonaws.services.mq.AmazonMQClientBuilder.standard()" + NL + "                        .withCredentials(new software.amazon.awssdk.auth.credentials.AWSCredentialsProvider() {" + NL + "\t\t\t\t\t\t\t@Override" + NL + "\t\t\t\t\t\t\tpublic AwsCredentials resolveCredentials() {" + NL + "\t\t\t\t\t\t\t\treturn credentials_";
  protected final String TEXT_562 = ";" + NL + "\t\t\t\t\t\t\t}" + NL + "                        })";
  protected final String TEXT_563 = NL + "                        .withRegion(";
  protected final String TEXT_564 = NL + "                        .withClientConfiguration(clientConfig_";
  protected final String TEXT_565 = ")   ";
  protected final String TEXT_566 = "\t" + NL + "                        .build();";
  protected final String TEXT_567 = NL + "                    registry.put(\"";
  protected final String TEXT_568 = "\t" + NL + "                    log.info(\"";
  protected final String TEXT_569 = " - Creating new connection successfully.\");";
  protected final String TEXT_570 = NL + "        log.info(\"";
  protected final String TEXT_571 = NL + "\t" + NL + "\t";
  protected final String TEXT_572 = "\t   ";
  protected final String TEXT_573 = NL + "\t   " + NL + "       com.microsoft.azure.storage.StorageCredentialsSharedAccessSignature credentials_";
  protected final String TEXT_574 = " = new com.microsoft.azure.storage.StorageCredentialsSharedAccessSignature(decryptedPassword_";
  protected final String TEXT_575 = ");\t";
  protected final String TEXT_576 = NL + "\t" + NL + "       com.microsoft.azure.storage.StorageCredentialsAccountAndKey credentials_";
  protected final String TEXT_577 = " = new com.microsoft.azure.storage.StorageCredentialsAccountAndKey(";
  protected final String TEXT_578 = ");" + NL + "\t";
  protected final String TEXT_579 = NL + "    " + NL + "\t" + NL + "    registry.put(\"credentials_";
  protected final String TEXT_580 = "\", credentials_";
  protected final String TEXT_581 = ");" + NL + "" + NL + "        registry.put(\"blobType_";
  protected final String TEXT_582 = "\", org.apache.camel.component.azure.blob.BlobType.";
  protected final String TEXT_583 = NL + "            java.util.Map<String, String> blobMetadataList = new java.util.HashMap<String, String>();";
  protected final String TEXT_584 = NL + "                blobMetadataList.put(\"";
  protected final String TEXT_585 = NL + "            registry.put(\"blobMetadata_";
  protected final String TEXT_586 = "\", blobMetadataList);";

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

    String version = (String) v.get(1);

    boolean isStudioEEVersion = org.talend.core.PluginChecker.isTIS();

    List<? extends INode> graphicalNodes = process.getGraphicalNodes();
    //boolean stats = codeGenArgument.isStatistics();
    //boolean trace = codeGenArgument.isTrace();
    //boolean isRunInMultiThread = codeGenArgument.getIsRunInMultiThread();
    //List<IContextParameter> params = new ArrayList<IContextParameter>();
    //params=process.getContextManager().getDefaultContext().getContextParameterList();
    boolean isTestContainer = ProcessUtils.isTestContainer(process);
    String className = isTestContainer ? process.getName() + "Test" : process.getName();
    String extendsClassName = isTestContainer ? "org.apache.camel.test.junit4.CamelTestSupport" : "org.apache.camel.builder.RouteBuilder";

    
IBrandingService service = (IBrandingService) GlobalServiceRegister.getDefault().getService(IBrandingService.class);
if (service instanceof AbstractBrandingService) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(((AbstractBrandingService) service).getJobLicenseHeader(version));
    
}

    String routeFolderName = "";
    IProcess baseProcess = ProcessUtils.getTestContainerBaseProcess(process);
    if (baseProcess != null) {
        routeFolderName = JavaResourcesHelper.getJobFolderName(baseProcess.getName(), baseProcess.getVersion()) + ".";
    }
    routeFolderName = routeFolderName + JavaResourcesHelper.getJobFolderName(process.getName(), process.getVersion());
    String packageName = codeGenArgument.getCurrentProjectName().toLowerCase() + "." + routeFolderName;


    stringBuffer.append(TEXT_2);
    stringBuffer.append( packageName );
    stringBuffer.append(TEXT_3);
    
    java.util.Set<String> importsSet = new java.util.TreeSet<String>();
/*  for (String routine : CodeGeneratorRoutine.getRequiredRoutineName(process)) {
        if (!routine.equals(ITalendSynchronizer.TEMPLATE)) {
            importsSet.add("import routines."+routine);
        }
    }
*/

    for (String codesJar : CodeGeneratorRoutine.getRequiredCodesJarName(process)) {
        importsSet.add("import "+ codesJar);
    }

    importsSet.add("import java.util.HashMap");
    importsSet.add("import java.util.Map");
    importsSet.add("import routines.TalendString");
    importsSet.add("import routines.system.api.TalendESBRoute");
    importsSet.add("import routines.*");
    importsSet.add("import routines.system.*");
    importsSet.add("import org.apache.camel.CamelContext");
    importsSet.add("import java.util.Properties");

    String headerImports = ElementParameterParser.getValue(process, "__HEADER_IMPORT__");
    if(headerImports!=null && !"".equals(headerImports.trim())){
        String[] imports = headerImports.split(";");
        for(String s: imports){
            if(s!=null && !"".equals(s.trim())){
                importsSet.add(s.trim());
            }
        }
    }
    String footerImports = ElementParameterParser.getValue(process, "__FOOTER_IMPORT__");
    if(footerImports!=null && !"".equals(footerImports.trim())){
        String[] imports = footerImports.split(";");
        for(String s: imports){
            if(s!=null && !"".equals(s.trim())){
                importsSet.add(s.trim());
            }
        }
    }

    List<? extends INode> generatingNodes = process.getGeneratingNodes();
    for(INode n: generatingNodes){
        List<? extends IElementParameter> elementParameters = n.getElementParameters();
        for(IElementParameter p: elementParameters){
            if(p.getShowIf() != null && !p.isShow(elementParameters)){
                continue;
            }
            if(EParameterFieldType.MEMO_IMPORT == p.getFieldType()){
                Object value = p.getValue();
                if(value == null || !(value instanceof String) || "".equals(((String)value).trim())){
                    continue;
                }
                String[] imports = ((String)value).trim().split(";");
                for(String s: imports){
                    if(s!=null && !"".equals(s.trim())){
                        importsSet.add(s.trim());
                    }
                }
            }
        }

    }

    if(isTestContainer){
        importsSet.add("import org.apache.camel.builder.RouteBuilder");
        importsSet.add("import org.junit.Test");
        importsSet.add("import org.apache.camel.component.mock.MockEndpoint");
    }

    for(String s: importsSet){

    stringBuffer.append(TEXT_4);
    stringBuffer.append(s);
    stringBuffer.append(TEXT_5);
    
    }

    stringBuffer.append(TEXT_6);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(ElementParameterParser.getValue(process, "__PURPOSE__") );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(ElementParameterParser.getValue(process, "__DESCRIPTION__") );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(ElementParameterParser.getValue(process, "__AUTHOR__") );
    stringBuffer.append(TEXT_10);
    stringBuffer.append(version );
    stringBuffer.append(TEXT_11);
    stringBuffer.append(ElementParameterParser.getValue(process, "__STATUS__") );
    stringBuffer.append(TEXT_12);
    
        boolean exportTypeSpringBoot = process.getClass().getName().endsWith("MicroServiceProcess");
        boolean isRoutelet = ((IProcess2) process).getProperty().getItem().getClass().getName().endsWith("RouteletProcessItemImpl");

        boolean hasCSoapOrCRest = false;

        boolean isEnableMetrics = false; 

        for (INode node : graphicalNodes) {
            if ("cSOAP".equals(node.getComponent().getName()) || "cREST".equals(node.getComponent().getName())) {
                hasCSoapOrCRest = true;
            }
        }

        if(exportTypeSpringBoot){
            try{
                isEnableMetrics = Boolean.valueOf(process.getClass().getDeclaredMethod("isEnableMetrics").invoke(process).toString());
            }catch(Exception e){

            }

 
    stringBuffer.append(TEXT_13);
     if(hasCSoapOrCRest) { 
    stringBuffer.append(TEXT_14);
     } 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(className.toLowerCase());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_18);
     if(isEnableMetrics) { 
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
    
    }else {

    stringBuffer.append(TEXT_21);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(extendsClassName );
    stringBuffer.append(TEXT_23);
    
   }

    stringBuffer.append(TEXT_24);
    stringBuffer.append(exportTypeSpringBoot);
    stringBuffer.append(TEXT_25);
    
    for (INode node : graphicalNodes) {
        if ("cMQConnectionFactory".equals(node.getComponent().getName())) {
            boolean hasPooledConnectionFactory = Boolean.parseBoolean(ElementParameterParser.getValue(node, "__AMQ_POOL__").trim());
            boolean isActiveMQ = "ActiveMQ".equals(ElementParameterParser.getValue(node, "__MQ_TYPE__"));
            if (hasPooledConnectionFactory && isActiveMQ) {
                String cid = node.getUniqueName();

    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    
            }
        }
    }

    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_28);
     if(!exportTypeSpringBoot){ 
    stringBuffer.append(TEXT_29);
     } 
    stringBuffer.append(TEXT_30);
    

    boolean hasSam = false;
    boolean hasCorrelationID = false;
    boolean hasCXFRSProvider = false;
    boolean hasCXFRSServiceLocator = false;
    boolean hasCXFServiceLocator = false;
    boolean hasOidcAuthentication = false;
    boolean hasOidcClientAuthentication = false;
    for (INode node : graphicalNodes) {
        if ("cSOAP".equals(node.getComponent().getName())) {
            if(!hasCXFServiceLocator){
                hasCXFServiceLocator = Boolean.parseBoolean(ElementParameterParser.getValue(node, "__ENABLE_SL__"));
            }
            boolean useRegistry = false;
            String dataFormat = ElementParameterParser.getValue(node, "__DATAFORMAT__");
            if (isStudioEEVersion && !"RAW".equals(dataFormat) && !"CXF_MESSAGE".equals(dataFormat)) {
                useRegistry = "true".equals(ElementParameterParser.getValue(node, "__ENABLE_REGISTRY__"));
            }
            if ("true".equals(ElementParameterParser.getValue(node, "__ENABLE_SAM__"))
                    && !"RAW".equals(dataFormat) && !useRegistry) {
                hasSam = true;
            }
            if ("true".equals(ElementParameterParser.getValue(node, "__ENABLE_CORRELATION__")) || useRegistry) {
                hasCorrelationID = true;
                String cid = node.getUniqueName();

    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    
            } // CorrelationID
        } else if ("cREST".equals(node.getComponent().getName())) {
            if(!hasCXFRSServiceLocator){
                hasCXFRSServiceLocator = Boolean.parseBoolean(ElementParameterParser.getValue(node, "__SERVICE_LOCATOR__"));
            }
            String cid = node.getUniqueName();
            if ("true".equals(ElementParameterParser.getValue(node, "__SERVICE_ACTIVITY_MONITOR__"))) {
                hasSam = true;
            }

            if (!hasOidcClientAuthentication) {
                hasOidcClientAuthentication = "true".equals(ElementParameterParser.getValue(node, "__ENABLE_SECURITY__"))
                                                  && "OIDC".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__"))
                                                  && !node.getIncomingConnections().isEmpty();
            }

            if (!hasOidcAuthentication) {
                hasOidcAuthentication = "true".equals(ElementParameterParser.getValue(node, "__ENABLE_SECURITY__"))
                                            && "OIDC".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__"));
            }

            if (node.getIncomingConnections().isEmpty()) {
                hasCXFRSProvider = true;

                if ("MANUAL".equals(ElementParameterParser.getValue(node, "__SERVICE_TYPE__"))) {
final Map<String, String> contentTypes = new java.util.HashMap<String, String>() {{
        put("XML", "{ \"application/xml\", \"text/xml\" }");
        put("JSON", "{ \"application/json\" }");
        put("XML-JSON", "{ \"application/xml\", \"text/xml\", \"application/json\" }");
        put("FORM", "{ \"application/x-www-form-urlencoded\" }");
        put("MULTIPART", "{ \"multipart/form-data\", \"multipart/mixed\", \"multipart/related\" }");
        put("HTML", "{ \"text/html\" }");
        put("ANY", "{ \"*/*\" }");
    }};

    

    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    
                List<Map<String, String>> mappings = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__SCHEMAS__");
                for (Map<String, String> mapping : mappings) {
                    String outputConn = mapping.get("SCHEMA");

                    String httpVerb = mapping.get("HTTP_VERB");
                    String uriPattern = mapping.get("URI_PATTERN");
                    String consumes = mapping.get("CONSUMES");
                    String produces = mapping.get("PRODUCES");
                    String beanClass = mapping.get("BEAN");

    stringBuffer.append(TEXT_35);
    stringBuffer.append(uriPattern);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(httpVerb);
    stringBuffer.append(TEXT_37);
    
            String consumesContentTypes = null;
            if (contentTypes.containsKey(consumes)) {
                consumesContentTypes = contentTypes.get(consumes);

    stringBuffer.append(TEXT_38);
    stringBuffer.append(consumesContentTypes);
    stringBuffer.append(TEXT_39);
    
            }

             //if (null != produces) {
            String producesContentTypes = null;
            if (contentTypes.containsKey(produces)) {
                producesContentTypes = contentTypes.get(produces);

    stringBuffer.append(TEXT_40);
    stringBuffer.append(producesContentTypes);
    stringBuffer.append(TEXT_39);
    
            }            

    stringBuffer.append(TEXT_41);
    stringBuffer.append(outputConn);
    stringBuffer.append(TEXT_42);
    
    final class URITemplateUtils {

        public static final String TEMPLATE_PARAMETERS = "jaxrs.template.parameters";
        public static final String LIMITED_REGEX_SUFFIX = "(/.*)?";
        public static final String FINAL_MATCH_GROUP = "FINAL_MATCH_GROUP";
        private static final String DEFAULT_PATH_VARIABLE_REGEX = "([^/]+?)";
        private static final String CHARACTERS_TO_ESCAPE = ".*+";

        private final String template;
        private final List<String> variables = new ArrayList<String>();
        private final List<String> customVariables = new ArrayList<String>();
        private final Pattern templateRegexPattern;
        private final String literals;
        private final List<UriChunk> uriChunks;

        public URITemplateUtils(String theTemplate) {
            template = theTemplate;
            StringBuilder literalChars = new StringBuilder();
            StringBuilder patternBuilder = new StringBuilder();
            CurlyBraceTokenizer tok = new CurlyBraceTokenizer(template);
            uriChunks = new ArrayList<UriChunk>();
            while (tok.hasNext()) {
                String templatePart = tok.next();
                UriChunk chunk = createUriChunk(templatePart);
                uriChunks.add(chunk);
                if (chunk instanceof Literal) {
                    String encodedValue = encodePartiallyEncoded(chunk.getValue(), false);
                    String substr = escapeCharacters(encodedValue);
                    literalChars.append(substr);
                    patternBuilder.append(substr);
                } else if (chunk instanceof Variable) {
                    Variable var = (Variable)chunk;
                    variables.add(var.getName());
                    if (var.getPattern() != null) {
                        customVariables.add(var.getName());
                        patternBuilder.append('(');
                        patternBuilder.append(var.getPattern());
                        patternBuilder.append(')');
                    } else {
                        patternBuilder.append(DEFAULT_PATH_VARIABLE_REGEX);
                    }
                }
            }
            literals = literalChars.toString();

            int endPos = patternBuilder.length() - 1;
            boolean endsWithSlash = (endPos >= 0) ? patternBuilder.charAt(endPos) == '/' : false;
            if (endsWithSlash) {
                patternBuilder.deleteCharAt(endPos);
            }
            patternBuilder.append(LIMITED_REGEX_SUFFIX);

            templateRegexPattern = Pattern.compile(patternBuilder.toString());
        }



        public String getLiteralChars() {
            return literals;
        }

        public String getValue() {
            return template;
        }

        /**
         * List of all variables in order of appearance in template.
         *
         * @return unmodifiable list of variable names w/o patterns,
         * e.g. for "/foo/{v1:\\d}/{v2}" returned list is ["v1","v2"].
         */
        public List<String> getVariables() {
            return Collections.unmodifiableList(variables);
        }

        /**
         * List of variables with patterns (regexps). List is subset of elements from {@link #getVariables()}.
         *
         * @return unmodifiable list of variables names w/o patterns.
         */
        public List<String> getCustomVariables() {
            return Collections.unmodifiableList(customVariables);
        }

        private String escapeCharacters(String expression) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);
                sb.append(isReservedCharacter(ch) ? "\\" + ch : ch);
            }
            return sb.toString();
        }

        private boolean isReservedCharacter(char ch) {
            return CHARACTERS_TO_ESCAPE.indexOf(ch) != -1;
        }




        /**
         * Creates object form string.
         *
         * @param uriChunk stringified uri chunk
         * @return If param has variable form then {@link Variable} instance is created,
         * otherwise chunk is treated as {@link Literal}.
         */
        public UriChunk createUriChunk(String uriChunk) {
            if (uriChunk == null || "".equals(uriChunk)) {
                throw new IllegalArgumentException("uriChunk is empty");
            }
            UriChunk uriChunkRepresentation = new Variable().create(uriChunk);
            if (uriChunkRepresentation == null) {
                uriChunkRepresentation = new Literal().create(uriChunk);
            }
            return uriChunkRepresentation;
        }

        /**
         * Stringified part of URI. Chunk is not URI segment; chunk can span over multiple URI segments or one URI
         * segments can have multiple chunks. Chunk is used to decompose URI of {@link URITemplate} into literals
         * and variables. Example: "foo/bar/{baz}{blah}" is decomposed into chunks: "foo/bar", "{baz}" and
         * "{blah}".
         */
        abstract class UriChunk {

            public abstract String getValue();

            @Override
            public String toString() {
                return getValue();
            }
        }

        final class Literal extends UriChunk {
            private String value;

            private Literal() {
                // empty constructor
            }

            public Literal create(String uriChunk) {
                if (uriChunk == null || "".equals(uriChunk)) {
                    throw new IllegalArgumentException("uriChunk is empty");
                }
                Literal literal = new Literal();
                literal.value = uriChunk;
                return literal;
            }

            @Override
            public String getValue() {
                return value;
            }

        }

        /**
         * Variable of URITemplate. Variable has either "{varname:pattern}" syntax or "{varname}".
         */
        final class Variable extends UriChunk {
            private final Pattern VARIABLE_PATTERN =
                    Pattern.compile("(\\w[-\\w\\.]*[ ]*)(\\:(.+))?");
            private String name;
            private Pattern pattern;

            private Variable() {
                // empty constructor
            }

            /**
             * Creates variable from stringified part of URI.
             *
             * @param uriChunk uriChunk chunk that depicts variable
             * @return Variable if variable was successfully created; null if uriChunk was not a variable
             */
            public Variable create(String uriChunk) {
                Variable newVariable = new Variable();
                if (uriChunk == null || "".equals(uriChunk)) {
                    return null;
                }
                if (insideBraces(uriChunk)) {
                    uriChunk = stripBraces(uriChunk).trim();
                    Matcher matcher = VARIABLE_PATTERN.matcher(uriChunk);
                    if (matcher.matches()) {
                        newVariable.name = matcher.group(1).trim();
                        if (matcher.group(2) != null && matcher.group(3) != null) {
                            String patternExpression = matcher.group(3).trim();
                            newVariable.pattern = Pattern.compile(patternExpression);
                        }
                        return newVariable;
                    }
                }
                return null;
            }

            public String getName() {
                return name;
            }

            public String getPattern() {
                return pattern != null ? pattern.pattern() : null;
            }

            @Override
            public String getValue() {
                if (pattern != null) {
                    return "{" + name + ":" + pattern + "}";
                } else {
                    return "{" + name + "}";
                }
            }
        }

        /**
         * Splits string into parts inside and outside curly braces. Nested curly braces are ignored and treated
         * as part inside top-level curly braces. Example: string "foo{bar{baz}}blah" is split into three tokens,
         * "foo","{bar{baz}}" and "blah". When closed bracket is missing, whole unclosed part is returned as one
         * token, e.g.: "foo{bar" is split into "foo" and "{bar". When opening bracket is missing, closing
         * bracket is ignored and taken as part of current token e.g.: "foo{bar}baz}blah" is split into "foo",
         * "{bar}" and "baz}blah".
         * <p>
         * This is helper class for {@link URITemplate} that enables recurring literals appearing next to regular
         * expressions e.g. "/foo/{zipcode:[0-9]{5}}/". Nested expressions with closed sections, like open-closed
         * brackets causes expression to be out of regular grammar (is context-free grammar) which are not
         * supported by Java regexp version.
         */
        class CurlyBraceTokenizer {

            private List<String> tokens = new ArrayList<String>();
            private int tokenIdx;

            public CurlyBraceTokenizer(String string) {
                boolean outside = true;
                int level = 0;
                int lastIdx = 0;
                int idx;
                for (idx = 0; idx < string.length(); idx++) {
                    if (string.charAt(idx) == '{') {
                        if (outside) {
                            if (lastIdx < idx) {
                                tokens.add(string.substring(lastIdx, idx));
                            }
                            lastIdx = idx;
                            outside = false;
                        } else {
                            level++;
                        }
                    } else if (string.charAt(idx) == '}' && !outside) {
                        if (level > 0) {
                            level--;
                        } else {
                            if (lastIdx < idx) {
                                tokens.add(string.substring(lastIdx, idx + 1));
                            }
                            lastIdx = idx + 1;
                            outside = true;
                        }
                    }
                }
                if (lastIdx < idx) {
                    tokens.add(string.substring(lastIdx, idx));
                }
            }

            public boolean hasNext() {
                return tokens.size() > tokenIdx;
            }

            public String next() {
                if (hasNext()) {
                    return tokens.get(tokenIdx++);
                } else {
                    throw new IllegalStateException("no more elements");
                }
            }
        }

        /**
         * Token is enclosed by curly braces.
         *
         * @param token text to verify
         * @return true if enclosed, false otherwise.
         */
        public boolean insideBraces(String token) {
            return token.charAt(0) == '{' && token.charAt(token.length() - 1) == '}';
        }

        /**
         * Strips token from enclosed curly braces. If token is not enclosed method
         * has no side effect.
         *
         * @param token text to verify
         * @return text stripped from curly brace begin-end pair.
         */
        public String stripBraces(String token) {
            if (insideBraces(token)) {
                return token.substring(1, token.length() - 1);
            } else {
                return token;
            }
        }




        // HttpUtils()

        private final Pattern ENCODE_PATTERN =
                Pattern.compile("%[0-9a-fA-F][0-9a-fA-F]");

        // there are more of such characters, ex, '*' but '*' is not affected by UrlEncode
        private static final String PATH_RESERVED_CHARACTERS = "=@/:";
        private static final String QUERY_RESERVED_CHARACTERS = "?/";

        private String componentEncode(String reservedChars, String value) {

            StringBuilder buffer = new StringBuilder();
            StringBuilder bufferToEncode = new StringBuilder();

            for (int i = 0; i < value.length(); i++) {
                char currentChar = value.charAt(i);
                if (reservedChars.indexOf(currentChar) != -1) {
                    if (bufferToEncode.length() > 0) {
                        buffer.append(urlEncode(bufferToEncode.toString()));
                        bufferToEncode.setLength(0);
                    }
                    buffer.append(currentChar);
                } else {
                    bufferToEncode.append(currentChar);
                }
            }

            if (bufferToEncode.length() > 0) {
                buffer.append(urlEncode(bufferToEncode.toString()));
            }

            return buffer.toString();
        }

        public String queryEncode(String value) {

            return componentEncode(QUERY_RESERVED_CHARACTERS, value);
        }

        public String urlEncode(String value) {

            try {
                value = java.net.URLEncoder.encode(value, "UTF-8");
            } catch (java.io.UnsupportedEncodingException ex) {
                // unlikely to happen
            }

            return value;
        }

        public String pathEncode(String value) {

            String result = componentEncode(PATH_RESERVED_CHARACTERS, value);
            // URLEncoder will encode '+' to %2B but will turn ' ' into '+'
            // We need to retain '+' and encode ' ' as %20
            if (result.indexOf('+') != -1) {
                result = result.replace("+", "%20");
            }
            if (result.indexOf("%2B") != -1) {
                result = result.replace("%2B", "+");
            }

            return result;
        }

        public boolean isPartiallyEncoded(String value) {
            return ENCODE_PATTERN.matcher(value).find();
        }

        /**
         * Encodes partially encoded string. Encode all values but those matching pattern
         * "percent char followed by two hexadecimal digits".
         *
         * @param encoded fully or partially encoded string.
         * @return fully encoded string
         */
        public String encodePartiallyEncoded(String encoded, boolean query) {
            if (encoded.length() == 0) {
                return encoded;
            }
            Matcher m = ENCODE_PATTERN.matcher(encoded);
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (m.find()) {
                String before = encoded.substring(i, m.start());
                sb.append(query ? queryEncode(before) : pathEncode(before));
                sb.append(m.group());
                i = m.end();
            }
            String tail = encoded.substring(i, encoded.length());
            sb.append(query ? queryEncode(tail) : pathEncode(tail));
            return sb.toString();
        }

    }

            boolean paramCommaWritten = false;

            URITemplateUtils uriTemplate = new URITemplateUtils(uriPattern);
            List<String> uriVariables = uriTemplate.getVariables();
            // we are going to declare all uri template variables ("Path" parameter type by REST)
            for (String uriVariable : uriVariables) {
                if (!paramCommaWritten) {
                    paramCommaWritten = true;
                } else {
                    
    stringBuffer.append(TEXT_43);
    
                }

    stringBuffer.append(TEXT_44);
    stringBuffer.append(uriVariable);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(uriVariable);
    
            }
            if (contentTypes.containsKey(consumes)) {
                if (!paramCommaWritten) {
                    paramCommaWritten = true;
                } else {
                    
    stringBuffer.append(TEXT_43);
    
                }
                String contentClass = null;
                 if ("XML".equals(consumes) || "JSON".equals(consumes) || "XML-JSON".equals(consumes))
                 {
                     contentClass = beanClass == null || beanClass.isEmpty() ? "org.w3c.dom.Document" : beanClass;
                 }
                 else
                 {
                     contentClass = "ANY".equals(consumes) ? "String" : "org.w3c.dom.Document";
                 }

    stringBuffer.append(TEXT_46);
    stringBuffer.append(contentClass);
    stringBuffer.append(TEXT_47);
    
            }

    stringBuffer.append(TEXT_48);
    
                } // mappings

    stringBuffer.append(TEXT_49);
    
                } // "MANUAL"
            } // service

            if ("true".equals(ElementParameterParser.getValue(node, "__ENABLE_CORRELATION__"))) {
                hasCorrelationID = true;

    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    
            } // CorrelationID

        } // "cREST"
    } // for

    if (hasSam) {

    stringBuffer.append(TEXT_50);
    
    }

    if (hasCXFServiceLocator || hasCXFRSServiceLocator) {

    stringBuffer.append(TEXT_51);
    
    }

    if (hasCorrelationID) {

    stringBuffer.append(TEXT_52);
    
    }

    if (hasCXFRSProvider) {
        String defaultUri = (String) System.getProperties().get("restServiceDefaultUri");
        if (null == defaultUri || defaultUri.trim().isEmpty() || !defaultUri.contains("://")) {
            defaultUri = "http://127.0.0.1:8090/";
        } else if (!defaultUri.endsWith("/")) {
            defaultUri = defaultUri + "/";
        }

    stringBuffer.append(TEXT_53);
    stringBuffer.append(defaultUri);
    stringBuffer.append(TEXT_54);
    
    }


    stringBuffer.append(TEXT_55);
    
            boolean hasCXFSamlTokenProvider = false;
            boolean hasCXFSamlTokenConsumer = false;
            boolean hasCXFSamlTokenAuthorizationConsumer = false;
            boolean hasCXFSamlTokenAuthorizationProvider = false;
            boolean hasCXFUsernameTokenProvider = false;
            boolean hasCXFUsernameTokenConsumer = false;
            boolean hasCXFHTTPAuthConsumer = false;
            boolean hasCXFHTTPAuthProvider = false;
            boolean hasCXFComponentConsumer = false;
            boolean hasCXFRegistryProvider = false;
            boolean hasCXFRegistryConsumer = false;
            boolean hasCXFSAMLPropagateConsumer = false;

            List<INode> allCxfNodes = new ArrayList<INode>();
            allCxfNodes.addAll(process.getNodesOfType("cSOAP"));
            boolean hasCXFComponent = !allCxfNodes.isEmpty();
            allCxfNodes.addAll(process.getNodesOfType("cREST"));
            boolean hasCXF = !allCxfNodes.isEmpty();
            for(INode n: allCxfNodes) {
                boolean isProvider = n.getIncomingConnections().isEmpty();
                hasCXFComponentConsumer |= !isProvider;

                String useRegistry = "false";
                if(isStudioEEVersion){
                    useRegistry = ElementParameterParser.getValue(n, "__ENABLE_REGISTRY__");
                }
                String useSecurity = ElementParameterParser.getValue(n, "__ENABLE_SECURITY__");
                String formatType = ElementParameterParser.getValue(n, "__DATAFORMAT__");
                if("RAW".equals(formatType) || "CXF_MESSAGE".equals(formatType)){
                    useRegistry = "false";
                    useSecurity = "false";
                }
                String securityType = ElementParameterParser.getValue(n, "__SECURITY_TYPE__");
                if(!hasCXFServiceLocator){
                    hasCXFServiceLocator = Boolean.parseBoolean(ElementParameterParser.getValue(n, "__ENABLE_SL__"));
                }

                if("true".equals(useRegistry)){
                    useSecurity = "false";
                    if (isProvider) {
                        hasCXFRegistryProvider = true;
                    } else {
                        hasCXFRegistryConsumer = true;
                        if(!hasCXFSAMLPropagateConsumer && Boolean.parseBoolean(ElementParameterParser.getValue(n, "__USE_PROPAGATE_CREDENTIAL__"))){
                            hasCXFSAMLPropagateConsumer = true;
                        }
                    }
                }
                if("true".equals(useSecurity)){
                    if("SAML".equals(securityType) && (!hasCXFSamlTokenProvider || !hasCXFSamlTokenConsumer || !hasCXFSAMLPropagateConsumer)){
                        if (isProvider) {
                            hasCXFSamlTokenProvider = true;
                            if(!hasCXFSamlTokenAuthorizationProvider && isStudioEEVersion){
                                if("true".equals(ElementParameterParser.getValue(n, "__USE_AUTHORIZATION__"))){
                                    hasCXFSamlTokenAuthorizationProvider = true;
                                }
                            }
                        } else {
                            hasCXFSamlTokenConsumer = true;
                            if(!hasCXFSamlTokenAuthorizationConsumer && isStudioEEVersion){
                                if("true".equals(ElementParameterParser.getValue(n, "__USE_AUTHORIZATION__"))){
                                    hasCXFSamlTokenAuthorizationConsumer = true;
                                }
                            }
                            if(!hasCXFSAMLPropagateConsumer){
                                String samlSecurityType = ElementParameterParser.getValue(n, "__SAML_SECURITY_TYPES__");
                                if("PROPAGATE_UP".equals(samlSecurityType) || "PROPAGATE_AP".equals(samlSecurityType)){
                                    hasCXFSAMLPropagateConsumer = true;
                                }
                            }
                        }
                    }else if("USER".equals(securityType) && (!hasCXFUsernameTokenProvider || !hasCXFUsernameTokenConsumer)){
                        if(isProvider){
                            hasCXFUsernameTokenProvider = true;
                        }else{
                            hasCXFUsernameTokenConsumer = true;
                        }
                    }else if(("BASIC".equals(securityType)||"DIGEST".equals(securityType)) && (!hasCXFHTTPAuthProvider || !hasCXFHTTPAuthConsumer)){
                        if(isProvider){
                            hasCXFHTTPAuthProvider = true;
                        }else{
                            hasCXFHTTPAuthConsumer = true;
                        }
                    }
                }
            }

        if(hasCXFSAMLPropagateConsumer){

    stringBuffer.append(TEXT_56);
          }
        if(hasCXFRegistryProvider || hasCXFSamlTokenProvider){

    stringBuffer.append(TEXT_57);
    
            } if(hasCXF && exportTypeSpringBoot){

    stringBuffer.append(TEXT_58);
    
                if(hasCXFServiceLocator || hasCXFRSServiceLocator){
                
    stringBuffer.append(TEXT_59);
    
                   } else {
                
    stringBuffer.append(TEXT_60);
    }
    stringBuffer.append(TEXT_61);
    
            } if(hasCXFComponent){

    stringBuffer.append(TEXT_62);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_63);
    }
    
                if(hasCXFHTTPAuthConsumer){

    stringBuffer.append(TEXT_64);
    
                }
                if(hasCXFUsernameTokenConsumer){

    stringBuffer.append(TEXT_65);
    
                }
if(hasCXFUsernameTokenProvider || hasCXFSamlTokenProvider || hasCXFSamlTokenConsumer || hasCXFHTTPAuthProvider || hasCXFRegistryProvider || hasCXFRegistryConsumer){
    
                if(!exportTypeSpringBoot){
               
    stringBuffer.append(TEXT_66);
    }
    
                    if(hasCXFHTTPAuthProvider){

    stringBuffer.append(TEXT_67);
    if(!exportTypeSpringBoot){
    stringBuffer.append(TEXT_68);
    }
    stringBuffer.append(TEXT_69);
    
                    }
                    if(hasCXFUsernameTokenProvider){

    stringBuffer.append(TEXT_70);
    
                    }
                    if(hasCXFSamlTokenProvider || hasCXFSamlTokenConsumer){


    stringBuffer.append(TEXT_71);
                      }
                    if(hasCXFRegistryProvider || hasCXFRegistryConsumer){

    stringBuffer.append(TEXT_72);
    }
    if(!exportTypeSpringBoot){
    stringBuffer.append(TEXT_73);
    }
    
}

    stringBuffer.append(TEXT_74);
    
            if (hasCXFHTTPAuthConsumer) {

    stringBuffer.append(TEXT_75);
    
            }
            if (hasCXFHTTPAuthProvider) {

    stringBuffer.append(TEXT_76);
    
            }

            if (hasCXFUsernameTokenProvider) {

    
            if(exportTypeSpringBoot){
           
    stringBuffer.append(TEXT_77);
    }else{
    stringBuffer.append(TEXT_78);
    }
    stringBuffer.append(TEXT_20);
    
            }
            if (hasCXFUsernameTokenConsumer) {

    stringBuffer.append(TEXT_79);
              }
        }
if(hasCXFSamlTokenConsumer || hasCXFRegistryConsumer){

    stringBuffer.append(TEXT_80);
    
}

if(hasCXFComponentConsumer){

    stringBuffer.append(TEXT_81);
    
}
if(hasCXFRegistryProvider || hasCXFRegistryConsumer){

    stringBuffer.append(TEXT_82);
    if(hasCXFRegistryConsumer){

    stringBuffer.append(TEXT_83);
     if(exportTypeSpringBoot) {
    stringBuffer.append(TEXT_84);
    }
    stringBuffer.append(TEXT_85);
     if(exportTypeSpringBoot){ 
    stringBuffer.append(TEXT_86);
    }
    stringBuffer.append(TEXT_87);
    //for fix [TESB-12172], set default address to null to avoid override after wsdl from registry initialized.
        
    stringBuffer.append(TEXT_88);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_89);
    }else{
    stringBuffer.append(TEXT_90);
    }
    stringBuffer.append(TEXT_91);
    //for fix [TESB-12172], add locator feature when necessary.
        
    stringBuffer.append(TEXT_92);
    
}
if(hasCXFRegistryProvider){

    stringBuffer.append(TEXT_93);
     if(exportTypeSpringBoot) {
    stringBuffer.append(TEXT_94);
    } else {
    stringBuffer.append(TEXT_95);
    }
    stringBuffer.append(TEXT_96);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_97);
    }
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_99);
    
}

if (hasCXFSamlTokenProvider || hasCXFRegistryProvider) {

    stringBuffer.append(TEXT_100);
    
}

if (hasCXFSamlTokenAuthorizationProvider && !hasCXFComponent) {

    stringBuffer.append(TEXT_101);
    
}

if(hasCXFComponent && (hasCXFUsernameTokenProvider || hasCXFUsernameTokenConsumer || hasCXFSamlTokenProvider || hasCXFSamlTokenConsumer
    ||hasCXFRegistryProvider || hasCXFRegistryConsumer)){

    stringBuffer.append(TEXT_102);
    
if(hasCXFSamlTokenConsumer || hasCXFSamlTokenProvider){

    stringBuffer.append(TEXT_103);
    
if(hasCXFSamlTokenConsumer){

    stringBuffer.append(TEXT_104);
     if(exportTypeSpringBoot) {
    stringBuffer.append(TEXT_105);
    }
    stringBuffer.append(TEXT_106);
    if(hasCXFSamlTokenAuthorizationConsumer){
    stringBuffer.append(TEXT_107);
    }
    stringBuffer.append(TEXT_108);
     if(exportTypeSpringBoot){ 
    stringBuffer.append(TEXT_109);
    }
    stringBuffer.append(TEXT_110);
    
}
if(hasCXFSamlTokenProvider){

    stringBuffer.append(TEXT_111);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_112);
    }
    stringBuffer.append(TEXT_113);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_114);
    }
    stringBuffer.append(TEXT_115);
    
}//end if(hasCXFSamlTokenProvider)

    stringBuffer.append(TEXT_116);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_117);
    } else {
    stringBuffer.append(TEXT_118);
    }
    stringBuffer.append(TEXT_119);
    
}//end if(hasCXFSamlTokenConsumer || hasCXFSamlTokenProvider)
}//end if(hasCXFSamlTokenProvider || hasCXFSamlTokenConsumer || hasCXFRegistryProvider || hasCXFRegistryConsumer)

    stringBuffer.append(TEXT_20);
    
    if(isTestContainer){

        if(hasCXFComponent){

    stringBuffer.append(TEXT_120);
    
        }// end if cCxf

    stringBuffer.append(TEXT_121);
    
    }//end if(isTestContainer)

    if (hasOidcAuthentication) {

    
    stringBuffer.append(TEXT_122);
     if(!exportTypeSpringBoot) {
    stringBuffer.append(TEXT_123);
     } 
    stringBuffer.append(TEXT_124);
    
    } // end if hasOidcAuthentication

    if (hasOidcClientAuthentication) {
        
    stringBuffer.append(TEXT_125);
    
    }

    stringBuffer.append(TEXT_126);
    
    for (INode node : graphicalNodes) {
        if ("cMQConnectionFactory".equals(node.getComponent().getName())) {
            boolean hasPooledConnectionFactory = Boolean.parseBoolean(ElementParameterParser.getValue(node, "__AMQ_POOL__").trim());
            boolean isActiveMQ = "ActiveMQ".equals(ElementParameterParser.getValue(node, "__MQ_TYPE__"));
            if (hasPooledConnectionFactory && isActiveMQ) {
                String cid = node.getUniqueName();

    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    
            }
        }
    }

    stringBuffer.append(TEXT_130);
    if(exportTypeSpringBoot){
    
                        if(process.getNodesOfType("cSOAP").size() > 0){
                    
    stringBuffer.append(TEXT_131);
    }
    }
    stringBuffer.append(TEXT_20);
    
                    if(process.getNodesOfType("cREST").size() > 0){
                
    stringBuffer.append(TEXT_132);
    }
    stringBuffer.append(TEXT_20);
    if(exportTypeSpringBoot){
    if(!isRoutelet){
    stringBuffer.append(TEXT_133);
    stringBuffer.append( generateAddRoutesMethod(process));
    }
    stringBuffer.append(TEXT_134);
    }else{
    if(!isRoutelet) {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(TEXT_133);
    stringBuffer.append( generateAddRoutesMethod(process));
    stringBuffer.append(TEXT_136);
    }
    stringBuffer.append(TEXT_137);
    }
    stringBuffer.append(TEXT_20);
    
            //process cConfig components first
            List<? extends INode> camelContextNodes = process.getNodesOfType("cConfig");
            for(INode node: camelContextNodes){
                boolean useMdcLogging = Boolean.parseBoolean(ElementParameterParser.getValue(node, "__USE_MDC_LOGGING__"));
                if(useMdcLogging){

    stringBuffer.append(TEXT_138);
    
                }

    stringBuffer.append(TEXT_46);
    stringBuffer.append(ElementParameterParser.getValue(node, "__CODE__"));
    
            }

    stringBuffer.append(TEXT_20);
    
            List<? extends INode> cxfNodes = process.getNodesOfType("cSOAP");
            if (null != cxfNodes && !cxfNodes.isEmpty()){
                boolean hasCXFConsumer = false;
                boolean hasCXFSAMLProvider = false;
                for(INode n: cxfNodes){
                    int incomingConnections = n.getIncomingConnections().size();
                    if(incomingConnections > 0){
                        hasCXFConsumer = true;
                    }else{
                        String dataformat = ElementParameterParser.getValue(n, "__DATAFORMAT__");

                        String useServiceRegistry = ElementParameterParser.getValue(n, "__ENABLE_REGISTRY__");
                        if(!isStudioEEVersion || "RAW".equals(dataformat) || "CXF_MESSAGE".equals(dataformat)){
                            useServiceRegistry = "false";
                        }

                        String useSecurity = ElementParameterParser.getValue(n, "__ENABLE_SECURITY__");
                        if("true".equals(useServiceRegistry) || "RAW".equals(dataformat) || "CXF_MESSAGE".equals(dataformat)){
                            useSecurity = "false";
                        }

                        if("true".equals(useServiceRegistry) || ("true".equals(useSecurity) && "SAML".equals(ElementParameterParser.getValue(n, "__SECURITY_TYPE__")))){
                            hasCXFSAMLProvider = true;
                        }
                        if(hasCXFConsumer && hasCXFSAMLProvider){
                            break;
                        }
                    }
                }
                if(hasCXFConsumer){

    stringBuffer.append(TEXT_139);
    
                }
                if(hasCXFSAMLProvider){

    stringBuffer.append(TEXT_140);
                  }
            }

            for (INode node : process.getNodesOfType("cREST")) {
                String cid = node.getUniqueName();

    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    
                boolean isProvider = node.getIncomingConnections().isEmpty();

                if (isProvider) {
                    String serviceClass = ("MANUAL".equals(ElementParameterParser.getValue(node, "__SERVICE_TYPE__")))
                        ? ("Service_" + cid) : ElementParameterParser.getValue(node, "__RESOURCE_CLASS__");

    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(serviceClass);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(serviceClass);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(serviceClass);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_153);
    
                    if ("true".equals(ElementParameterParser.getValue(node, "__ENABLE_SECURITY__"))) {
                        if("BASIC".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__"))) {

    if(!exportTypeSpringBoot){
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    }
    
                        } else if ("SAML".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__"))) {

    stringBuffer.append(TEXT_20);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_159);
    
                                    if (hasCXFSamlTokenAuthorizationProvider && !hasCXFComponent) {
                                
    stringBuffer.append(TEXT_160);
    }
    stringBuffer.append(TEXT_161);
    }
    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
     if ("true".equals(ElementParameterParser.getValue(node, "__USE_AUTHORIZATION__"))) { 
    stringBuffer.append(TEXT_164);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_165);
     } 
    stringBuffer.append(TEXT_166);
    
                        } else if ("OIDC".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__"))) {
                            
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_168);
    
                        }
                    }

                } else {

    stringBuffer.append(TEXT_169);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_170);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_171);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(ElementParameterParser.getValue(node, "__URL__"));
    stringBuffer.append(TEXT_173);
    }
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    
                    if ("RESOURCECLASS".equals(ElementParameterParser.getValue(node, "__SERVICE_TYPE__"))) {

    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(ElementParameterParser.getValue(node, "__RESOURCE_CLASS__"));
    stringBuffer.append(TEXT_177);
    
                    }
                    if ("true".equals(ElementParameterParser.getValue(node, "__ENABLE_SECURITY__"))) {
                        String passwordFieldName = "__PASSWORD__";

    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    
                        if ("BASIC".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__"))) {

    stringBuffer.append(TEXT_183);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_184);
    stringBuffer.append(ElementParameterParser.getValue(node, "__USERNAME__"));
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_186);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_187);
    
                        } else if ("SAML".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__"))) {

    stringBuffer.append(TEXT_20);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_188);
    }else{
    stringBuffer.append(TEXT_189);
    }
    stringBuffer.append(TEXT_190);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_192);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(ElementParameterParser.getValue(node, "__USERNAME__"));
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
     if ("true".equals(ElementParameterParser.getValue(node, "__USE_AUTHORIZATION__"))) { 
    stringBuffer.append(TEXT_195);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(ElementParameterParser.getValue(node, "__ROLE_NAME__"));
    stringBuffer.append(TEXT_158);
     } 
    stringBuffer.append(TEXT_196);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    
                        }else if ("OIDC".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__"))) {
                            if(hasOidcClientAuthentication){

    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(ElementParameterParser.getValue(node, "__USERNAME__"));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(ElementParameterParser.getValue(node, "__PASSWORD__"));
    stringBuffer.append(TEXT_199);
    
                            }
                        }
                    }
                }

    
                if (isProvider) {

    stringBuffer.append(TEXT_20);
    if(exportTypeSpringBoot){
    stringBuffer.append(TEXT_200);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_201);
    stringBuffer.append(ElementParameterParser.getValue(node, "__URL__"));
    stringBuffer.append(TEXT_158);
    }else{
    stringBuffer.append(TEXT_200);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(ElementParameterParser.getValue(node, "__URL__"));
    stringBuffer.append(TEXT_187);
    }
                  } else {

    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_201);
    stringBuffer.append(ElementParameterParser.getValue(node, "__URL__"));
    stringBuffer.append(TEXT_158);
    
                }

    
                boolean ignoreSLStudio = !Boolean.valueOf(System.getProperty("ignoreSLStudio"));
                if ("true".equals(ElementParameterParser.getValue(node, "__SERVICE_LOCATOR__"))) {
                    if(ignoreSLStudio) {

    stringBuffer.append(TEXT_204);
    
                    }

    stringBuffer.append(TEXT_200);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(ElementParameterParser.getValue(node, "__SERVICE_NAMESPACE__"));
    stringBuffer.append(TEXT_206);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(ElementParameterParser.getValue(node, "__SERVICE_NAME__"));
    stringBuffer.append(TEXT_207);
    
                        if (!isProvider) {

    stringBuffer.append(TEXT_208);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_209);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_211);
    
                        }else{

    stringBuffer.append(TEXT_212);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_211);
    
                        }

    stringBuffer.append(TEXT_214);
    
                    List<Map<String, String>> customProperties = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node, "__SL_META_DATA__");
                    if (!customProperties.isEmpty())
                    {

    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_216);
     for (Map<String, String> custProp : customProperties) { 
    stringBuffer.append(TEXT_217);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(custProp.get("NAME"));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(custProp.get("VALUE"));
    stringBuffer.append(TEXT_158);
     } 
    stringBuffer.append(TEXT_20);
    if(!isProvider){
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_221);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    }else{
    stringBuffer.append(TEXT_223);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    }
    
                    }

    stringBuffer.append(TEXT_226);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    
                    if(ignoreSLStudio) {

    stringBuffer.append(TEXT_136);
    
                    }
                } else if (!isProvider) {

    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(ElementParameterParser.getValue(node, "__URL__"));
    stringBuffer.append(TEXT_229);
    
                }

                if ("true".equals(ElementParameterParser.getValue(node, "__SERVICE_ACTIVITY_MONITOR__"))) {


    
                        if (!isProvider) {

    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_231);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_211);
    
                        }else{

    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_211);
    
                        }

    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_234);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_236);
    
                }

                if ("true".equals(ElementParameterParser.getValue(node, "__ENABLE_CORRELATION__"))) {

    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(ElementParameterParser.getValue(node, "__CORRELATION_VALUE__"));
    stringBuffer.append(TEXT_238);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_243);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_244);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_245);
    
                }

    stringBuffer.append(TEXT_246);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_247);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    
                String fbn = isProvider ? "sf_" + cid : "cf_" + cid;

    stringBuffer.append(TEXT_248);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_249);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(fbn);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_254);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(fbn);
    stringBuffer.append(TEXT_255);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(fbn);
    stringBuffer.append(TEXT_260);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(fbn);
    stringBuffer.append(TEXT_263);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_264);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_266);
    stringBuffer.append(packageName);
    stringBuffer.append(TEXT_267);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_270);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_271);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_274);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_275);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_276);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(fbn);
    stringBuffer.append(TEXT_278);
    stringBuffer.append(fbn);
    stringBuffer.append(TEXT_279);
    
                    String endpointConfigurerClassName = "CxfRsConfigurer";
                
    stringBuffer.append(TEXT_280);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(endpointConfigurerClassName);
    stringBuffer.append(TEXT_282);
    
                                if ("true".equals(ElementParameterParser.getValue(node, "__SERVICE_LOCATOR__"))) {
                                    if(ignoreSLStudio) {
                            
    stringBuffer.append(TEXT_283);
    
                                    } //end ignoreSLStudio
                            
    stringBuffer.append(TEXT_284);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(ElementParameterParser.getValue(node, "__SERVICE_NAMESPACE__"));
    stringBuffer.append(TEXT_206);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(ElementParameterParser.getValue(node, "__SERVICE_NAME__"));
    stringBuffer.append(TEXT_187);
    
                                    if (!isProvider)  {
                            
    stringBuffer.append(TEXT_286);
    
                                    }
                                    if(ignoreSLStudio) {
                            
    stringBuffer.append(TEXT_287);
    
                                    }  //end if(ignoreSLStudio)
                                }
                            
    stringBuffer.append(TEXT_288);
    
                               if (!isProvider &&
                                    "true".equals(ElementParameterParser.getValue(node, "__ENABLE_SECURITY__")) &&
                                    "BASIC".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__"))) {
                            
    stringBuffer.append(TEXT_289);
    stringBuffer.append(fbn);
    stringBuffer.append(TEXT_290);
    
                                }
                            
    stringBuffer.append(TEXT_291);
    
            }
            for (INode node: process.getNodesOfType("cSQLConnection")) {
                final String cid = node.getUniqueName();
                NodeParamsHelper helper = new NodeParamsHelper(node);

                String driverClassName = helper.getStringParam("__DRIVER_CLASS__");
                String jdbcURL = helper.getStringParam("__URL__");
                boolean useDsAlias = helper.getBoolParam("__USE_DATA_SOURCE_ALIAS__");
                boolean authRequired = helper.getBoolParam("__AUTH_REQUIRED__");
                String dsAlias = helper.getStringParam("__DATA_SOURCE_ALIAS__");

    stringBuffer.append(TEXT_292);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(useDsAlias);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_295);
    stringBuffer.append(dsAlias);
    stringBuffer.append(TEXT_296);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_297);
    stringBuffer.append(dsAlias);
    stringBuffer.append(TEXT_298);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_299);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_300);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(dsAlias);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(driverClassName);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(jdbcURL);
    stringBuffer.append(TEXT_158);
    
                        if (authRequired) {
                            String username = helper.getStringParam("__USER__");
                            String passwordFieldName = "__PASS__";

    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_307);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_184);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    
                        }
                        String initSize = helper.getStringParam("__INITIAL_SIZE__");
                        String maxCon = helper.getStringParam("__MAX_TOTAL__");
                        String minIdle = helper.getStringParam("__MIN_IDLE__");
                        String maxIdle = helper.getStringParam("__MAX_IDLE__");
                        boolean setTimeouts = helper.getBoolParam("__SET_TIMEOUTS__");
                        boolean useValidationQuery = helper.getBoolParam("__USE_VALIDATION_QUERY__");
                        boolean useInitSQL = helper.getBoolParam("__USE_INIT_SQL__");

    stringBuffer.append(TEXT_309);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_310);
    stringBuffer.append(initSize);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_311);
    stringBuffer.append(maxCon);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_312);
    stringBuffer.append(minIdle);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_313);
    stringBuffer.append(maxIdle);
    stringBuffer.append(TEXT_158);
    
                        if (setTimeouts) {
                            String queryTimeout = helper.getStringParam("__QUERY_TIMEOUT__");
                            String conTimeout = helper.getStringParam("__MAX_CONNECTION_DURATION__");
                            String vaildationTimeout = helper.getStringParam("__VALIDATION_QUERY_TIMEOUT__");

    stringBuffer.append(TEXT_309);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(conTimeout);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(queryTimeout);
    stringBuffer.append(TEXT_158);
    
                            if (useValidationQuery) {
                                String validationQueryTimeout = helper.getStringParam("__VALIDATION_QUERY_TIMEOUT__");

    stringBuffer.append(TEXT_309);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(validationQueryTimeout);
    stringBuffer.append(TEXT_187);
    
                            }
                        }
                        if (useValidationQuery) {
                            String validationQuery = helper.getStringParam("__VALIDATION_QUERY__");

    stringBuffer.append(TEXT_309);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_317);
    stringBuffer.append(validationQuery);
    stringBuffer.append(TEXT_158);
    
                        }
                        if (useInitSQL) {
                            String initSQL = helper.getStringParam("__INIT_QUERY__");
                            initSQL = org.talend.core.model.utils.NodeUtil.replaceCRLFInMEMO_SQL(initSQL);

                            initSQL = initSQL.replace("\\n", "");

    stringBuffer.append(TEXT_309);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_318);
    stringBuffer.append(initSQL);
    stringBuffer.append(TEXT_187);
    
                        }

    stringBuffer.append(TEXT_319);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_300);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_320);
    
            }

            //then process cBeanRegister components
            List<? extends INode> beanRegisterNodes = process.getNodesOfType("cBeanRegister");
            if(beanRegisterNodes != null && beanRegisterNodes.size()>0){
            for(INode node: beanRegisterNodes){
                String id = ElementParameterParser.getValue(node, "__ID__");
                String isSimple = ElementParameterParser.getValue(node, "__SIMPLE_CONFIG__");
                String classQualifiedName = ElementParameterParser.getValue(node, "__CLASS_QUALIFIED_NAME__");
                String hasArguments = ElementParameterParser.getValue(node, "__SPECIFY_ARGUMENTS__");
                List<Map<String, String>> arguments = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__ARGUMENTS__");

                String code = ElementParameterParser.getValue(node, "__CODE__");

                if("true".equals(isSimple)){
                    StringBuilder sb = new StringBuilder();
                    if("true".equals(hasArguments)){
                        for(Map<String, String> s: arguments){
                            if(sb.length() == 0){
                                sb.append(s.get("VALUE"));
                            }else{
                                sb.append(",");
                                sb.append(s.get("VALUE"));
                            }
                        }
                    }

    stringBuffer.append(TEXT_321);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_322);
    stringBuffer.append(classQualifiedName);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(sb.toString());
    stringBuffer.append(TEXT_187);
    
                }else{

    stringBuffer.append(TEXT_323);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(code);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_325);
    
                }
            }
            }

            //after, process cDataset
            List<? extends INode> datasetNodes = process.getNodesOfType("cDataset");
            for(INode n: datasetNodes){
                String beanID = ElementParameterParser.getValue(n, "__DATASET_NAME__");
                String registerNewBean = ElementParameterParser.getValue(n, "__REGISTER_DATASET_BEAN__");
                if(!"true".equals(registerNewBean)){
                    continue;
                }
                String beanClass = ElementParameterParser.getValue(n, "__BEAN_CLASS__");
                List<Map<String, String>> beanArguments = (List<Map<String, String>>) ElementParameterParser.getObjectValue(n, "__BEAN_ARGUMENTS__");
                StringBuilder sb = new StringBuilder();
                if(beanArguments != null){
                    for(Map<String,String> map: beanArguments){
                        if(sb.length() == 0){
                            sb.append(map.get("VALUE"));
                        }else{
                            sb.append(",");
                            sb.append(map.get("VALUE"));
                        }
                    }
                }

    stringBuffer.append(TEXT_326);
    stringBuffer.append(beanID);
    stringBuffer.append(TEXT_322);
    stringBuffer.append(beanClass);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(sb.toString());
    stringBuffer.append(TEXT_327);
    
            }
            //cMQConnectionFactory http://jira.talendforge.org/browse/TESB-4860
            for (INode node: process.getNodesOfType("cMQConnectionFactory")) {
                NodeParamsHelper helper = new NodeParamsHelper(node);
                //Use transaction http://jira.talendforge.org/browse/TESB-5160 By LiXiaopeng
                String useTransaction = ElementParameterParser.getValue(node, "__USE_TRANSACTION__");
                String transactedMethodStr = "";
                if("true".equals(useTransaction)){
                    transactedMethodStr = "Transacted";
                }
                final String cid = node.getUniqueName();
                //Component Name
                String name = cid.replace("_", "");
                name = "\"" + name + "\"";

                //ActiveMQ
                String mqType = ElementParameterParser.getValue(node, "__MQ_TYPE__");
                String amqUseRetroactiveConsumer = ElementParameterParser.getValue(node, "__AMQ_USE_RETROACTIVE_CONSUMER__").trim();
                if("ActiveMQ".equals(mqType)){
                    String amqUri = ElementParameterParser.getValue(node, "__AMQ_BROKER_URI__").trim();
                    String useActiveMQAuth = ElementParameterParser.getValue(node, "__AMQ_AUTH__");
                    if ("true".equals(useActiveMQAuth)) {
                        String username = ElementParameterParser.getValue(node, "__AMQ_USERNAME__");
                        String passwordFieldName = "__AMQ_PASSWORD__";

    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_328);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_329);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_330);
    stringBuffer.append(amqUri);
    stringBuffer.append(TEXT_158);
    
                    } else {

    
                                if("true".equals(amqUseRetroactiveConsumer)){
                            
    stringBuffer.append(TEXT_331);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_332);
    stringBuffer.append(amqUri);
    stringBuffer.append(TEXT_333);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_334);
    
                                } else {
                            
    stringBuffer.append(TEXT_335);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_332);
    stringBuffer.append(amqUri);
    stringBuffer.append(TEXT_158);
    
                                }
                            
    stringBuffer.append(TEXT_20);
    
                    }
                    String amqPool = ElementParameterParser.getValue(node, "__AMQ_POOL__").trim();

                    if("true".equals(amqPool)){
                         String amq_pool_max_connections = ElementParameterParser.getValue(node, "__AMQ_POOL_MAX_CONNECTIONS__").trim();
                         String amq_pool_max_active = ElementParameterParser.getValue(node, "__AMQ_POOL_MAX_ACTIVE__").trim();
                         String amq_pool_idle_timeout = ElementParameterParser.getValue(node, "__AMQ_POOL_IDLE_TIMEOUT__").trim();
                         String amq_pool_expiry_timeout = ElementParameterParser.getValue(node, "__AMQ_POOL_EXPIRY_TIMEOUT__").trim();

                         if(amq_pool_max_connections.startsWith("\"")){
                            amq_pool_max_connections = amq_pool_max_connections.substring(1);
                        }
                        if(amq_pool_max_connections.endsWith("\"")){
                            amq_pool_max_connections = amq_pool_max_connections.substring(0, amq_pool_max_connections.length() - 1);
                        }
                        if(amq_pool_max_active.startsWith("\"")){
                            amq_pool_max_active = amq_pool_max_active.substring(1);
                        }
                        if(amq_pool_max_active.endsWith("\"")){
                            amq_pool_max_active = amq_pool_max_active.substring(0, amq_pool_max_active.length() - 1);
                        }
                        if(amq_pool_idle_timeout.startsWith("\"")){
                            amq_pool_idle_timeout = amq_pool_idle_timeout.substring(1);
                        }
                        if(amq_pool_idle_timeout.endsWith("\"")){
                            amq_pool_idle_timeout = amq_pool_idle_timeout.substring(0, amq_pool_idle_timeout.length() - 1);
                        }
                        if(amq_pool_expiry_timeout.startsWith("\"")){
                             amq_pool_expiry_timeout = amq_pool_expiry_timeout.substring(1);
                        }
                        if(amq_pool_expiry_timeout.endsWith("\"")){
                            amq_pool_expiry_timeout = amq_pool_expiry_timeout.substring(0, amq_pool_expiry_timeout.length() - 1);
                        }

    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_339);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_340);
    stringBuffer.append(amq_pool_expiry_timeout);
    stringBuffer.append(TEXT_341);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_342);
    stringBuffer.append(amq_pool_idle_timeout);
    stringBuffer.append(TEXT_339);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_343);
    stringBuffer.append(amq_pool_max_connections);
    stringBuffer.append(TEXT_339);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_344);
    stringBuffer.append(amq_pool_max_active);
    stringBuffer.append(TEXT_345);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_347);
    stringBuffer.append(transactedMethodStr);
    stringBuffer.append(TEXT_348);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_349);
    
                    } else {

    stringBuffer.append(TEXT_350);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_351);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(transactedMethodStr);
    stringBuffer.append(TEXT_353);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_354);
    
                    }
                } else if ("WebSphere MQ Jms".equals(mqType)) {
                    String wmqServer = ElementParameterParser.getValue(node, "__WQM_SEVER__");
                    String wmqPort = ElementParameterParser.getValue(node, "__WMQ_PORT__");
                    String wmqTransportType = ElementParameterParser.getValue(node, "__WMQ_TRANSPORT_TYPE__");
                    String wmqUM = ElementParameterParser.getValue(node, "__WMQ_QUEUE_MANAGER__");
                    String wmqChannel = ElementParameterParser.getValue(node, "__WMQ_CHANNEL__");

                    //Username and password, http://jira.talendforge.org/browse/TESB-4073
                    String username = ElementParameterParser.getValue(node, "__WMQ_USERNAME__");
                    String useAuth = ElementParameterParser.getValue(node, "__WMQ_AUTH__");

                    if(wmqPort.startsWith("\"")){
                        wmqPort = wmqPort.substring(1);
                    }
                    if(wmqPort.endsWith("\"")){
                        wmqPort = wmqPort.substring(0, wmqPort.length() - 1);
                    }
                    if(wmqTransportType.startsWith("\"")){
                        wmqTransportType = wmqTransportType.substring(1);
                    }
                    if(wmqTransportType.endsWith("\"")){
                        wmqTransportType = wmqTransportType.substring(0, wmqTransportType.length() - 1);
                    }


    stringBuffer.append(TEXT_355);
    stringBuffer.append(wmqServer);
    stringBuffer.append(TEXT_356);
    stringBuffer.append(wmqPort);
    stringBuffer.append(TEXT_357);
    stringBuffer.append(wmqTransportType);
    stringBuffer.append(TEXT_358);
    stringBuffer.append(wmqUM);
    stringBuffer.append(TEXT_158);
    
                        if (wmqTransportType.equals("1") && (wmqChannel != null) && !wmqChannel.equals("")){

    stringBuffer.append(TEXT_359);
    stringBuffer.append(wmqChannel);
    stringBuffer.append(TEXT_158);
    
                        }

    stringBuffer.append(TEXT_360);
    
                    if("false".equals(useAuth)){

    stringBuffer.append(TEXT_361);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_362);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_347);
    stringBuffer.append(transactedMethodStr);
    stringBuffer.append(TEXT_363);
    
                    }else{

    stringBuffer.append(TEXT_364);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_365);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_184);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_366);
    
                    String passwordFieldName = "__WMQ_PASSWORD__";
                    
    stringBuffer.append(TEXT_20);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_367);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_368);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_369);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_362);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_347);
    stringBuffer.append(transactedMethodStr);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_371);
    
                    }

    stringBuffer.append(TEXT_136);
    
                }else if("AMQP".equals(mqType)){
                    String host = helper.getStringParam("__AMQP_BROKER_URI__");
                    boolean useSSL = helper.getBoolParam("__AMQP_SSL__");
                    boolean useAuth = helper.getBoolParam("__AMQP_USE_AUTH__");
                    String port = helper.getStringParam("__AMQP_PORT__");

                    StringBuilder ep = new StringBuilder();
                    String username = helper.getStringParam("__AMQP_USERNAME__");

    stringBuffer.append(TEXT_372);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_373);
    
                   
                    if (host.startsWith("context.")) {
                        String contextKey4host = host.substring(8);

    stringBuffer.append(TEXT_374);
    stringBuffer.append(contextKey4host);
    stringBuffer.append(TEXT_375);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_376);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(useSSL);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_380);
    stringBuffer.append(port);
    stringBuffer.append(TEXT_381);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_382);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(port);
    stringBuffer.append(TEXT_383);
                          
                    }else if (host.startsWith("\"failover") || host.startsWith("failover")) {
                        ep.append(host);
                    } else {
                        ep.append("\"");
                        ep.append(useSSL ? "amqps" : "amqp");
                        ep.append("://\" + ");
                        ep.append(host);
                        if (!port.isEmpty()) {
                            ep.append("+ \":\" + ");
                            ep.append(port);
                        }
                    }

                    // Add amqp.* params from cAMQP components which use the cMqConnectionFactory
                    java.util.Map<String, String> amqpParamsMap = new java.util.HashMap();
                    for (INode node_camqp: process.getNodesOfType("cAMQP")) {
                        if (node.getUniqueName().equals(ElementParameterParser.getValue(node_camqp, "__CONNECTION_FACOTRY_CONFIGURATION__"))) {
                            java.util.List<java.util.Map<String, String>> tableValues = (java.util.List<java.util.Map<String, String>>) ElementParameterParser.getObjectValue(node_camqp, "__URI_OPTIONS__");
                            for (java.util.Map<String, String> map : tableValues) {
                                String argName = map.get("NAME").trim();
                                if(argName.startsWith("\"") && argName.endsWith("\"") && argName.length() >= 2) {
                                    argName = argName.substring(1, argName.length() - 1);
                                }
                                if (argName.startsWith("amqp.")) {
                                    String argValue = map.get("VALUE").trim();
                                    if(argValue.startsWith("\"") && argValue.endsWith("\"") && argValue.length() >= 2) {
                                        argValue = argValue.substring(1, argValue.length() - 1);
                                    }
                                    amqpParamsMap.put(argName, argValue);
                                }
                            }
                        }
                    }
                    if (!amqpParamsMap.isEmpty()) {
                        String amqpParamString = "\"\"?";
                        for (String argName : amqpParamsMap.keySet()) {
                            String argValue = amqpParamsMap.get(argName);
                            amqpParamString = amqpParamString + argName + "=" + argValue + "&";
                        }
                        amqpParamString = amqpParamString.substring(1, amqpParamString.length()-1);  // remove the "&" in the end
                        amqpParamString = amqpParamString + "\"";
                        if (ep.length() >= 1) {
                            ep.append(" + ");
                        } 
                        ep.append(amqpParamString);
                    }
                    if (ep.length() >= 1) {

    stringBuffer.append(TEXT_384);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(ep.toString());
    stringBuffer.append(TEXT_158);
                      
                    }
                    if(useAuth) {

                        String passwordFieldName = "__AMQP_PASSWORD__";

                        
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    
                    }


    stringBuffer.append(TEXT_20);
     if (useAuth) {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_386);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_387);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_388);
    } else {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_389);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_388);
    }
    stringBuffer.append(TEXT_390);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_362);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_393);
    stringBuffer.append(process.getName().toLowerCase());
    stringBuffer.append(TEXT_394);
    
                } else if("MQTT".equals(mqType)) {
                    String host = helper.getStringParam("__MQTT_HOST__");
                    String port = helper.getStringParam("__MQTT_PORT__");
                    boolean useSSL = helper.getBoolParam("__MQTT_SSL__");
                    String brokerUri = "\""+(useSSL?"ssl":"tcp") + "://\"+" + host + "+\":\"+" + port;

    stringBuffer.append(TEXT_395);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_396);
    
                String useActiveMQTTAuth = ElementParameterParser.getValue(node, "__MQTT_AUTH__");
                if ("true".equals(useActiveMQTTAuth)){
                    String username = ElementParameterParser.getValue(node, "__MQTT_USERNAME__");
                    String passwordFieldName = "__MQTT_PASSWORD__";

    stringBuffer.append(TEXT_397);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_398);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_399);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_158);
    
                }

    stringBuffer.append(TEXT_400);
    stringBuffer.append(brokerUri);
    stringBuffer.append(TEXT_401);
    stringBuffer.append(helper.getBoolParam("__MQTT_AUTO_RECONNECT__"));
    stringBuffer.append(TEXT_402);
    stringBuffer.append(helper.getStringParam("__MQTT_CONNECTION_TIMEOUT__"));
    stringBuffer.append(TEXT_403);
    stringBuffer.append(helper.getIntParam("__MQTT_RECONNECT_DELAY__"));
    stringBuffer.append(TEXT_404);
    stringBuffer.append(helper.getIntParam("__MQTT_VERSION__"));
    stringBuffer.append(TEXT_405);
    stringBuffer.append(helper.getIntParam("__MQTT_QOS__"));
    stringBuffer.append(TEXT_158);
    if("true".equals(helper.getStringParam("__MQTT_SSL__"))){
    stringBuffer.append(TEXT_406);
    stringBuffer.append(helper.getStringParam("__MQTT_SSL_KEY_STORE__"));
    stringBuffer.append(TEXT_158);
    String passwordFieldName = "__MQTT_SSL_KEY_STORE_PASSWORD__";
     if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_408);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_409);
    stringBuffer.append(helper.getStringParam("__MQTT_SSL_TRUST_STORE__"));
    stringBuffer.append(TEXT_158);
    passwordFieldName = "__MQTT_SSL_TRUST_STORE_PASSWORD__";
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_410);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_411);
    }
    stringBuffer.append(TEXT_412);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_362);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_413);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_236);
    
                } else if ("Other".equals(mqType)) {

    stringBuffer.append(TEXT_414);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(ElementParameterParser.getValue(node, "__OTHER_CODE__"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_351);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(transactedMethodStr);
    stringBuffer.append(TEXT_415);
    
                }

            }

    List< ? extends INode> processNodes = (List< ? extends INode>)process.getGraphicalNodes();
    for (INode node : processNodes) {
            if (!node.isActivate()) {
                continue;
            }
            String nodeComponentName = node.getComponent().getName();
    if("cSOAP".equals(nodeComponentName)){
            IElementParameter param = node.getElementParameter("LABEL");
            String cid = node.getUniqueName();
            NodeParamsHelper helper = new NodeParamsHelper(node);
            String uri = "";
            String statements = null;
            // http://jira.talendforge.org/browse/TESB-5241
            /*
             * change to use label + unique to make it unique but readable
             */
//          if(param != null && !"__UNIQUE_NAME__".equals(param.getValue())){
//              cid = (String)param.getValue()+"_"+cid;
//          }
            String endpointVar = "endpoint_" + node.getUniqueName();
            
    
// included in header_route.javajet
// available variables :
// INode node;
// String cid;
// String componentName;
// NodeParamsHelper helper;
// return values:
// String uri = ""; generate the endpoint uri.
// String statements = null; generate the statements before wrapping endpoint uri.

String url = ElementParameterParser.getValue(node, "__ADDRESS__").trim();

String dataformat = ElementParameterParser.getValue(node, "__DATAFORMAT__");

boolean useServiceRegistry = helper.getVisibleBoolParam("__ENABLE_REGISTRY__");
if(!isStudioEEVersion || "RAW".equals(dataformat) || "CXF_MESSAGE".equals(dataformat)){
	useServiceRegistry = false;
}

boolean useSecurity = helper.getVisibleBoolParam("__ENABLE_SECURITY__");
if(useServiceRegistry || "RAW".equals(dataformat) || "CXF_MESSAGE".equals(dataformat)){
	useSecurity = false;
}

List<? extends IConnection> conns = node.getIncomingConnections();
if ("true".equals(ElementParameterParser.getValue(node, "__ENABLE_SL__")) 
		&&  (!useServiceRegistry)) {
	if (!conns.isEmpty()) {
		// consumer role
		url = "\"locator://locator/\"";
	}
}

String serviceType = ElementParameterParser.getValue(node, "__SERVICE_TYPE__");

StringBuilder sb = new StringBuilder();
sb.append("\"cxf://\"+").append(url);
sb.append("+\"?dataFormat=" + dataformat + "\"");
if("PAYLOAD".equals(dataformat)){
	sb.append("+\"&allowStreaming="+ElementParameterParser.getValue(node, "__ALLOW_STREAMING__")+"\"");
}
if ("true".equals(ElementParameterParser.getValue(node, "__LOG_MESSAGES__"))) {
	sb.append("+\"&loggingFeatureEnabled=true\"");
}

if ("wsdlURL".equals(serviceType)) {
	if(!useServiceRegistry){
		sb.append("+\"&" + serviceType + "=\"");
		//http://jira.talendforge.org/browse/TESB-6226
        final String filePath;
		if("file".equals(ElementParameterParser.getValue(node, "__WSDL_TYPE__"))){
		    filePath =  ElementParameterParser.getValue(node, "__WSDL_FILE__");
		}else{
			filePath = "getClass().getResource(\"/"
			    + org.talend.core.model.utils.JavaResourcesHelper.getResouceClasspath(
			        ElementParameterParser.getValue(node, "__WSDL_FILE_REPO__"),
			        ElementParameterParser.getValue(node, "__ROUTE_RESOURCE_TYPE_VERSION__"))
			    + "\").toString()";
		}
		sb.append("+").append(filePath.replaceAll("\\\\","/"));
	} else {
		sb.append("+\"&wsdlURL=\"");
	}
} else {
	sb.append("+\"&" + serviceType + "=\"");
	sb.append("+" + ElementParameterParser.getValue(node, "__SERVICE_CLASS__"));
}




String serviceName = helper.getVisibleStringParam("__SERVICE_NAME__").trim();
if(!serviceName.isEmpty() && !serviceName.equals("\"\"")) {
	sb.append("+\"&serviceName=\"+").append(serviceName);
}
String portName = helper.getVisibleStringParam("__PORT_NAME__").trim();
if(!portName.isEmpty() && !portName.equals("\"\"")) {
	sb.append("+\"&endpointName=\"+").append(portName);
}
String operationName = helper.getVisibleStringParam("__OPERATION_NAME__").trim();
if(!operationName.isEmpty() && !operationName.equals("") && !operationName.equals("\"\"") ) {
	sb.append("+\"&defaultOperationNamespace=\"+javax.xml.namespace.QName.valueOf(").append(operationName).append(").getNamespaceURI()")
	.append("+\"&defaultOperationName=\"+javax.xml.namespace.QName.valueOf(").append(operationName).append(").getLocalPart()");
}

List<Map<String, String>> tableValues = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__ADVARGUMENTS__");
for (Map<String, String> map : tableValues) {
	String argName = map.get("NAME").trim();
	String argValue = map.get("VALUE").trim();
	sb.append("+\"&\"+" + argName + "+\"=\"+" + argValue);
}

if(!conns.isEmpty()){
	sb.append("+\"&headerFilterStrategy=#CXF_PAYLOAD_HEADER_FILTER\"");
	sb.append("+\"&properties.id=\"+\"");
	sb.append(node.getUniqueName());
	sb.append("\"");
}else if(useServiceRegistry || (useSecurity && "SAML".equals(ElementParameterParser.getValue(node, "__SECURITY_TYPE__")))) {
	sb.append("+\"&cxfBinding=#DEFAULT_CXF_BINDING\"");
}
uri = sb.toString();

    
            String formatType = ElementParameterParser.getValue(node, "__DATAFORMAT__");

            boolean useRegistry = false;
            useSecurity = false;
            if(!"RAW".equals(formatType) && !"CXF_MESSAGE".equals(formatType)){
                if(isStudioEEVersion){
                    useRegistry = "true".equals(ElementParameterParser.getValue(node, "__ENABLE_REGISTRY__"));
                }
                if(!useRegistry){
                    useSecurity = "true".equals(ElementParameterParser.getValue(node, "__ENABLE_SECURITY__"));
                }
            }

            String securityType = ElementParameterParser.getValue(node, "__SECURITY_TYPE__");
            String username = ElementParameterParser.getValue(node, "__USERNAME__");
            String alias = ElementParameterParser.getValue(node, "__ALIASNAME__").trim();
            boolean isProvider = node.getIncomingConnections().isEmpty();
            boolean isUseAuthorization = false;
            String securityToken = "(String[])null";
            boolean isUsePropagateSamlUP = false;
            boolean isUsePropagateSamlAP = false;
            if(useRegistry){
                securityToken = "\"Registry\"";
                    String usePropagate = ElementParameterParser.getValue(node, "__USE_PROPAGATE_CREDENTIAL__");
                    if("true".equals(usePropagate)){
                        if(alias == null || alias.matches("\"?\\s*\"?")) {
                            //no alias.
                            isUsePropagateSamlUP = true;
                        }else {
                            isUsePropagateSamlUP = false;
                        }
                        isUsePropagateSamlAP = !isUsePropagateSamlUP;
                    }
            }else if(useSecurity){
                if("USER".equals(securityType)){
                    securityToken = "\"UsernameToken\"";
                }else if("SAML".equals(securityType)){
                    securityToken = "\"SAMLToken\"";
                    String samlSecurityType = ElementParameterParser.getValue(node, "__SAML_SECURITY_TYPES__");
                    if("PROPAGATE_UP".equals(samlSecurityType)) {
                            isUsePropagateSamlUP = true;
                    }else if ("PROPAGATE_AP".equals(samlSecurityType)) {
                        isUsePropagateSamlAP = true;
                        username = "null";
                    }
                    if(isStudioEEVersion){
                        isUseAuthorization = "true".equals(ElementParameterParser.getValue(node, "__USE_AUTHORIZATION__"));
                    }
                }else if("BASIC".equals(securityType)){
                    securityToken = "org.apache.cxf.transport.http.auth.HttpAuthHeader.AUTH_TYPE_BASIC";
                }else if("DIGEST".equals(securityType)){
                    securityToken = "org.apache.cxf.transport.http.auth.HttpAuthHeader.AUTH_TYPE_DIGEST";
                }
            }
            String roleName = "\"\"";
            if(!isProvider && (useRegistry || isUseAuthorization)) {
                roleName = ElementParameterParser.getValue(node, "__ROLE_NAME__");
                if(null == roleName || "".equals(roleName)){
                    roleName = "\"\"";
                }
            }

            if((useSecurity || useRegistry) && !isProvider) {
                String passwordFieldName = "__PASSWORD__";

    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    
            }

    stringBuffer.append(TEXT_416);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_417);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_418);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(isProvider);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(isUseAuthorization);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(isUsePropagateSamlAP);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(securityToken);
    
            if((useSecurity || useRegistry) && !isProvider){

    stringBuffer.append(TEXT_419);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_420);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(roleName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(alias);
    
            }

    stringBuffer.append(TEXT_421);
    if("true".equals(ElementParameterParser.getValue(node, "__ENABLE_CXF_MESSAGE_GZIP__"))){
    stringBuffer.append(TEXT_259);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_422);
    }
    stringBuffer.append(TEXT_423);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_424);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_425);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_426);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_427);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_428);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_429);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_430);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_431);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_432);
    stringBuffer.append(packageName);
    stringBuffer.append(TEXT_267);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_433);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_434);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_271);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_435);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_436);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_275);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    

            if((isUsePropagateSamlUP || isUsePropagateSamlAP) && !isProvider){

    stringBuffer.append(TEXT_46);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_437);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_187);
    
            }
            String useSL = ElementParameterParser.getValue(node, "__ENABLE_SL__");
            String useSAM = ElementParameterParser.getValue(node, "__ENABLE_SAM__");
            String useCorrelation = ElementParameterParser.getValue(node, "__ENABLE_CORRELATION__");
            if("RAW".equals(formatType)){
                useCorrelation = "false";
            }

            if ("true".equals(useCorrelation) || useRegistry) {

    stringBuffer.append(TEXT_438);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(ElementParameterParser.getValue(node, "__CORRELATION_VALUE__"));
    stringBuffer.append(TEXT_439);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_440);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_441);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_442);
     if (!useRegistry) { 
    stringBuffer.append(TEXT_259);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_245);
     } 
              }
            if ("true".equals(useSL) && (isProvider || !useRegistry)) {
                if (!isProvider) {

    stringBuffer.append(TEXT_443);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_444);
    
                }
                List<Map<String, String>> customProperties = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node, "__SL_META_DATA__");

    stringBuffer.append(TEXT_445);
    stringBuffer.append(endpointVar);
    if(exportTypeSpringBoot){
    if(isProvider){
    stringBuffer.append(TEXT_133);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_446);
     }else {
    stringBuffer.append(TEXT_133);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_447);
    }
    }else{
    stringBuffer.append(TEXT_448);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_449);
    }
    stringBuffer.append(TEXT_450);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_451);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_452);
     if (!customProperties.isEmpty()) { 
    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_453);
     for (Map<String, String> custProp : customProperties) { 
    stringBuffer.append(TEXT_217);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(custProp.get("NAME"));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(custProp.get("VALUE"));
    stringBuffer.append(TEXT_158);
     } 
    stringBuffer.append(TEXT_454);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_455);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_456);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_457);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_458);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_459);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
     } 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    
            } else if (useRegistry && !isProvider) {
                List<Map<String, String>> customProperties = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node, "__SL_META_DATA__");
                if (!customProperties.isEmpty()) {

    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_453);
     for (Map<String, String> custProp : customProperties) { 
    stringBuffer.append(TEXT_217);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(custProp.get("NAME"));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(custProp.get("VALUE"));
    stringBuffer.append(TEXT_158);
     } 
    stringBuffer.append(TEXT_454);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_455);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_456);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_457);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_458);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_459);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    
                }
            }

    stringBuffer.append(TEXT_460);
    
            if ("true".equals(useSAM) && !"RAW".equals(formatType) && !useRegistry) {

    stringBuffer.append(TEXT_461);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_462);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_463);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_464);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_465);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_466);
    
            }
            if (!useRegistry && !isProvider) {

    stringBuffer.append(TEXT_467);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_468);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_469);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_470);
    
            }

    }else if("cFtp".equals(nodeComponentName)){
            String cid = node.getUniqueName();
            String passwordFieldName = "__PASSWORD__";

    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_471);
    

    }else if("cMail".equals(nodeComponentName)){
            String cid = node.getUniqueName();
            String passwordFieldName = "__PASSWORD__";

    stringBuffer.append(TEXT_472);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_473);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_474);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_475);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_476);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_477);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_478);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_479);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_480);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_471);
      }else if("cAggregate".equals(nodeComponentName)){
                boolean usePersistence = "true".equals(ElementParameterParser.getValue(node, "__USE_PERSISTENCE__"));
                String repository = ElementParameterParser.getValue(node, "__REPOSITORY__");
                if (usePersistence) {
                    boolean useRecovery = "true".equals(ElementParameterParser.getValue(node, "__USE_RECOVERY__"));
                    String recoveryInterval = ElementParameterParser.getValue(node, "__RECOVERY_INTERVAL__");
                    String deadLetterUri = ElementParameterParser.getValue(node, "__DEAD_LETTER_URI__");
                    String maximumRedeliveries = ElementParameterParser.getValue(node, "__MAXIMUM_REDELIVERIES__");

                    if ("AGGREGATION".equals(repository)) {

    stringBuffer.append(TEXT_481);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_482);
    stringBuffer.append(ElementParameterParser.getValue(node, "__CUSTOM_REPOSITORY__") );
    stringBuffer.append(TEXT_142);
    
                    } else if ("RECOVERABLE".equals(repository)) {

    stringBuffer.append(TEXT_483);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_482);
    stringBuffer.append(ElementParameterParser.getValue(node, "__CUSTOM_REPOSITORY__") );
    stringBuffer.append(TEXT_142);
    
                        if (useRecovery) {

    stringBuffer.append(TEXT_484);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_485);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_486);
    stringBuffer.append(maximumRedeliveries);
    stringBuffer.append(TEXT_487);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_488);
    stringBuffer.append(deadLetterUri);
    stringBuffer.append(TEXT_487);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_489);
    stringBuffer.append(recoveryInterval);
    stringBuffer.append(TEXT_158);
    
                        }
                    } else if ("LEVELDB".equals(repository)) {
                        boolean usePersistentFile = "true".equals(ElementParameterParser.getValue(node, "__USE_PERSISTENT_FILE__"));
                        String persistentFile = ElementParameterParser.getValue(node, "__PERSISTENT_FILENAME__");
                        if (usePersistentFile) {

    stringBuffer.append(TEXT_490);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_491);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_277);
    stringBuffer.append(persistentFile);
    stringBuffer.append(TEXT_158);
    
                        } else {

    stringBuffer.append(TEXT_490);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_491);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_492);
    
                        }
                        if (useRecovery) {

    stringBuffer.append(TEXT_484);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_485);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_486);
    stringBuffer.append(maximumRedeliveries);
    stringBuffer.append(TEXT_487);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_488);
    stringBuffer.append(deadLetterUri);
    stringBuffer.append(TEXT_487);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_489);
    stringBuffer.append(recoveryInterval);
    stringBuffer.append(TEXT_158);
    
                        }
                    }
                }

                String language = ElementParameterParser.getValue(node, "__LANGUAGES__");
                String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");
                List<Map<String, String>> namespaceValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__NAMESPACES__");
                if("xpath".equals(language)&&"true".equals(useNamespaces)){
                    String cid = node.getUniqueName();


    stringBuffer.append(TEXT_493);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_494);
    
                    for(Map<String, String> m: namespaceValues){
                        String pre = m.get("PREFIX");
                        String uri = m.get("URI");

    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_495);
    stringBuffer.append(pre);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_158);
    
                    }

    stringBuffer.append(TEXT_73);
    
                }
    }else if("cLoop".equals(nodeComponentName)){
        String type = ElementParameterParser.getValue(node, "__LOOP_TYPE__");
        if(!"EXPRESSION_TYPE".equals(type)){
            continue;
        }
        String language = ElementParameterParser.getValue(node, "__LANGUAGES__");
        if(!"xpath".equals(language)){
            continue;
        }
        String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");
        if(!"true".equals(useNamespaces)){
            continue;
        }
        String cid = node.getUniqueName();
        List<Map<String, String>> namespaceValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__NAMESPACES__");

    stringBuffer.append(TEXT_496);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_497);
    
            for(Map<String, String> m: namespaceValues){
                String pre = m.get("PREFIX");
                String uri = m.get("URI");

    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_495);
    stringBuffer.append(pre);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_158);
    
            }

    stringBuffer.append(TEXT_498);
      } else if("cAWSS3".equals(nodeComponentName)){
       String cid = node.getUniqueName();
       List<Map<String, String>> userDefinedHeaders = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__MESSAGE_HEADERS_USER_DEFINED__");
       String useMessageHeadersUserDefined = ElementParameterParser.getValue(node, "__USE_MESSAGE_HEADERS_USER_DEFINED__");

    if(("true".equals(useMessageHeadersUserDefined)) && (userDefinedHeaders.size()>0)){
    stringBuffer.append(TEXT_499);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_500);
    
                for (Map<String, String> map : userDefinedHeaders) {
            
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_501);
    stringBuffer.append(map.get("MESSAGE_HEADER_USER_DEFINED"));
    stringBuffer.append(TEXT_206);
    stringBuffer.append(map.get("MESSAGE_HEADER_VALUE_USER_DEFINED"));
    stringBuffer.append(TEXT_158);
    }
    }
    
    } else if("cMessageRouter".equals(nodeComponentName)){
        for (IConnection param : node.getOutgoingConnections()) {
            String ct = param.getRouteConnectionType();
            if ("xpath".equals(ct)) {
                String language = ElementParameterParser.getValue(param, "__ROUTETYPE__");
                String useNamespaces = ElementParameterParser.getValue(param, "__USE_NAMESPACES__");
                if(!"true".equals(useNamespaces)){
                    continue;
                }
                String cid = param.getUniqueName();
                List<Map<String, String>> namespaceValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(param, "__NAMESPACES__");

    stringBuffer.append(TEXT_493);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_494);
    
            for(Map<String, String> m: namespaceValues){
                String pre = m.get("PREFIX");
                String uri = m.get("URI");

    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_495);
    stringBuffer.append(pre);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_158);
    
            }

    stringBuffer.append(TEXT_73);
    
            }
        }
    } else if("cRecipientList".equals(nodeComponentName)||"cSplitter".equals(nodeComponentName)
            || "cSetBody".equals(nodeComponentName) || "cMessageFilter".equals(nodeComponentName)) {
        String language = ElementParameterParser.getValue(node, "__LANGUAGES__");
        if(!"xpath".equals(language)){
            continue;
        }
        String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");

        String cid = node.getUniqueName();
        List<Map<String, String>> namespaceValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__NAMESPACES__");

    stringBuffer.append(TEXT_496);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_497);
    
            for(Map<String, String> m: namespaceValues){
                String pre = m.get("PREFIX");
                String uri = m.get("URI");

    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_495);
    stringBuffer.append(pre);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_158);
    
            }

    stringBuffer.append(TEXT_502);
            if ("cSplitter".equals(nodeComponentName)) {
            String expression = ElementParameterParser.getValue(node, "__EXPRESSION__"); 

    stringBuffer.append(TEXT_503);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_504);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_505);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_506);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_507);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_508);
            }    
    stringBuffer.append(TEXT_20);
    
    }else if("cSetHeader".equals(nodeComponentName) || "cSetProperty".equals(nodeComponentName)){
        String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");
        if(!"true".equals(useNamespaces)){
            continue;
        }
        List<Map<String, String>> tableValues =
            (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__VALUES__");
        boolean hasXPath = false;
        for(Map<String, String> propertiesMap: tableValues){
            String propertyLanguage = propertiesMap.get("LANGUAGE");
            if ("xpath".equals(propertyLanguage)) {
                hasXPath = true;
                break;
            }
        }
        if (hasXPath) {
            String cid = node.getUniqueName();
            List<Map<String, String>> namespaceValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__NAMESPACES__");

    stringBuffer.append(TEXT_496);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_497);
    
            for(Map<String, String> m: namespaceValues){
                String pre = m.get("PREFIX");
                String uri = m.get("URI");

    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_495);
    stringBuffer.append(pre);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_158);
    
            }

    stringBuffer.append(TEXT_498);
          }
    } else if ("cWireTap".equals(nodeComponentName) || "cLoadBalancer".equals(nodeComponentName) || "cIdempotentConsumer".equals(nodeComponentName)) {
        if("cWireTap".equals(nodeComponentName) && "true".equals(ElementParameterParser.getValue(node, "__COPY_ORIGINAL_MESSAGE__"))){

    stringBuffer.append(TEXT_509);
    
        }
        String language = ElementParameterParser.getValue(node, "__LANGUAGES__");
        if(!"xpath".equals(language)){
            continue;
        }
        String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");
        if(!"true".equals(useNamespaces)){
            continue;
        }
        String cid = node.getUniqueName();
        List<Map<String, String>> namespaceValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__NAMESPACES__");
        if (!namespaceValues.isEmpty()) {

    stringBuffer.append(TEXT_4);
    stringBuffer.append("org.apache.camel.support.builder.Namespaces");
    stringBuffer.append(TEXT_510);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_511);
    

            String ns = "new org.apache.camel.support.builder.Namespaces";
            for(Map<String, String> m: namespaceValues){
                String pre = m.get("PREFIX");
                String uri = m.get("URI");

    stringBuffer.append(TEXT_46);
    stringBuffer.append(ns);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(pre);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_158);
    
                ns = cid + "ns.add";
            }
        }

      } else if("cTalendJob".equals(nodeComponentName)){
    if (!node.getIncomingConnections().isEmpty()) {
        String ownerName = process.getName();
        String cid = node.getUniqueName();
        List<Map<String, String>> contextParams = null;
        String useJar = ElementParameterParser.getValue(node, "__FROM_EXTERNAL_JAR__");
        // when propagateHeader parameter will be removed the https://github.com/Talend/tesb-rt-se/blob/master/camel-talendjob/src/main/java/org/talend/camel/TalendProducer.java
        // have to be changed - remove populateTalendContextParamsWithCamelHeaders(exchange, args);
        String propagateHeader = ElementParameterParser.getValue(node, "__PROPAGATE_HEADER__");
        String stickyJob = ElementParameterParser.getValue(node, "__STICKY_JOB__");
        if("true".equals(useJar)){
            String typeName = ElementParameterParser.getValue(node, "__JOB__");
            String context = ElementParameterParser.getValue(node, "__CONTEXT__");
            contextParams = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__EXTERNAL_JAR_CONTEXTPARAMS__");

    stringBuffer.append(TEXT_512);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_513);
    stringBuffer.append(typeName);
    stringBuffer.append(TEXT_514);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_515);
    stringBuffer.append(propagateHeader);
    stringBuffer.append(TEXT_516);
    stringBuffer.append(stickyJob);
    stringBuffer.append(TEXT_517);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_492);
    
        }else{
            String typeName = "";
            contextParams = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__CONTEXTPARAMS__");
            String context = (String) node.getElementParameter("PROCESS_TYPE_CONTEXT").getValue();
            String id = (String) node.getElementParameter("PROCESS_TYPE_PROCESS").getValue();
            if(id != null){
                String useSelectedContext = ElementParameterParser.getValue(node, "__USE_SELECTED_CONTEXT__");
                String useRouteContext = ElementParameterParser.getValue(node, "__USE_ROUTE_CONTEXT__");
                String useJobContext = ElementParameterParser.getValue(node, "__USE_JOB_CONTEXT__");
                if("true".equals(useRouteContext)){
                    // TESB-13614
                    // we can't define context during codegeneration in this case, because
                    // context will provided to the job before execution
                    context = "NOT_DEFINED";

                }else if("true".equals(useJobContext)){
                    context = ElementParameterParser.getProcessSelectedContext(id);
                }

                String jobName = ElementParameterParser.getStringElementParameterValue(node.getElementParameter("SELECTED_JOB_NAME"));
//              String jobName = (String) node.getElementParameter("SELECTED_JOB_NAME").getValue();
                String jobVersion = ElementParameterParser.getStringElementParameterValue(node.getElementParameter("PROCESS_TYPE_VERSION"));
                String jobFolderName = JavaResourcesHelper.getJobFolderName(jobName, jobVersion);

                try{
                    String selectedId = (String) node.getElementParameter("PROCESS_TYPE_PROCESS").getValue();
                    String selectedContext = (String) node.getElementParameter("PROCESS_TYPE_CONTEXT").getValue();
                    String selectedVersion = (String) node.getElementParameter("PROCESS_TYPE_VERSION").getValue();

                    typeName = org.talend.designer.runprocess.ProcessorUtilities.getMainCommand(selectedId,selectedVersion, selectedContext,org.talend.designer.runprocess.IProcessor.NO_STATISTICS,org.talend.designer.runprocess.IProcessor.NO_TRACES, null)[0];
                }catch(Exception e){
                    typeName = codeGenArgument.getCurrentProjectName().toLowerCase() + "." + jobFolderName + "." + jobName;
                }
            }

            if("NOT_DEFINED".equals(context)){
                String executionContextVariable = "contextStr";

    stringBuffer.append(TEXT_518);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_519);
    stringBuffer.append(ownerName);
    stringBuffer.append(TEXT_520);
    stringBuffer.append(typeName);
    stringBuffer.append(TEXT_521);
    stringBuffer.append(executionContextVariable);
    stringBuffer.append(TEXT_515);
    stringBuffer.append(propagateHeader);
    stringBuffer.append(TEXT_516);
    stringBuffer.append(stickyJob);
    stringBuffer.append(TEXT_517);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_492);
    
            } else {

    stringBuffer.append(TEXT_518);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_519);
    stringBuffer.append(ownerName);
    stringBuffer.append(TEXT_520);
    stringBuffer.append(typeName);
    stringBuffer.append(TEXT_522);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_523);
    stringBuffer.append(propagateHeader);
    stringBuffer.append(TEXT_516);
    stringBuffer.append(stickyJob);
    stringBuffer.append(TEXT_517);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_492);
    
            }
        }
        int contextParamsSize = contextParams.size();
        if(contextParamsSize > 0){

    stringBuffer.append(TEXT_524);
    
        for (int i=0; i<contextParamsSize; i++) {
                Map<String, String> contextParam = contextParams.get(i);
                String paramName;
                String paramValue;
                if("true".equals(useJar)){
                    paramName = contextParam.get("EXTERNAL_JAR_PARAM_NAME_COLUMN");
                    paramValue = contextParam.get("EXTERNAL_JAR_PARAM_VALUE_COLUMN");

                } else {
                    paramName = "\"" + contextParam.get("PARAM_NAME_COLUMN") + "\"";
                    paramValue = contextParam.get("PARAM_VALUE_COLUMN");
                }

    stringBuffer.append(TEXT_525);
    stringBuffer.append(paramName);
    stringBuffer.append(TEXT_526);
    stringBuffer.append(paramValue);
    stringBuffer.append(TEXT_187);
    
        }

    stringBuffer.append(TEXT_527);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_528);
    
    }
    }
    } else if("cAWSConnection".equals(nodeComponentName)){
        String cid = node.getUniqueName();

    
    boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
    boolean hasCredentials = false;
    boolean hasClientConfiguration = false;

    Map<String, String> awsTypeMap = new java.util.HashMap<String, String>() {
        {
            put("cAWSS3",  "software.amazon.awssdk.services.s3.S3Client");
            put("cAWSSES", "software.amazon.awssdk.services.ses.SesClient");
            put("cAWSSQS", "software.amazon.awssdk.services.sqs.SqsClient");
            put("cAWSSNS", "software.amazon.awssdk.services.sns.SnsClient");
            put("cAWSMQBroker", "software.amazon.awssdk.services.mq.MQClient");
        }
    };

    boolean configClient = "true".equals(ElementParameterParser.getValue(node, "__CONFIG_CLIENT__"));

    List<Map<String,String>> clientConfiguration = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__CLIENT_CONFIGURATION__");
    String region = ElementParameterParser.getValue(node,"__REGION__");
    if (region !=null) region = region.toUpperCase().replaceAll("\"","").replaceAll("-","_").replaceAll(" ","");

    if (isLog4jEnabled) {
    
    stringBuffer.append(TEXT_529);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_530);
    
    }

    boolean inheritCredentials = "true".equals(ElementParameterParser.getValue(node, "__INHERIT_CREDENTIALS__"));
    if (inheritCredentials) {
        
    stringBuffer.append(TEXT_531);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_532);
    
    } else {
        String accessKey = ElementParameterParser.getValue(node, "__ACCESS_KEY__");
        String passwordFieldName = "__SECRET_KEY__";
        
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_533);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_534);
    stringBuffer.append(accessKey);
    stringBuffer.append(TEXT_420);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    
    }

        org.talend.designer.codegen.config.AWSClientConfiguration clientConfig = new org.talend.designer.codegen.config.AWSClientConfiguration();

        boolean setBuffer = false;
        boolean useProxy = false;
        
        boolean useConnectionTimeout=false;
        boolean useMaxConnections=false;
        boolean useMaxErrorRetry=false;

        boolean useProtocol=false;
        
        boolean useProxyDomain=false;
        boolean useProxyHost=false;
        boolean useProxyPort=false;
        boolean useProxyUsername=false;
        boolean useProxyPassword=false;
        
        boolean useSocketTimeout=false;
        boolean useUserAgent=false;
        
        boolean useSocketReceiveBufferSizeHints = false;
        boolean useSocketSendBufferSizeHints = false;

    if (configClient && clientConfiguration.size()>0) {

        for (Map<String,String> map :clientConfiguration) {
            String client_parameter = map.get("CLIENT_PARAMETER");
            String value = map.get("VALUE");
            if (!setBuffer && ("SocketSendBufferSizeHints".equals(client_parameter) || "SocketReceiveBufferSizeHints".equals(client_parameter))) {
                setBuffer=true;
                
    stringBuffer.append(TEXT_535);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_536);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_537);
    
            }
            if ("ProxyDomain".equals(client_parameter) 
                    || "ProxyHost".equals(client_parameter) 
                    || "ProxyPort".equals(client_parameter)
                    || "ProxyUsername".equals(client_parameter)
                    || "ProxyPassword".equals(client_parameter)) {
                useProxy=true;
            }
            if("SocketSendBufferSizeHints".equals(client_parameter)){
                useSocketReceiveBufferSizeHints=true;
            
    stringBuffer.append(TEXT_538);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_142);
    
            }else if("SocketReceiveBufferSizeHints".equals(client_parameter)){
                useSocketReceiveBufferSizeHints=true;
            
    stringBuffer.append(TEXT_539);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_142);
    
            }else if("Protocol".equals(client_parameter)){
               useProtocol=true;
               clientConfig.setProtocol(value.replaceAll("\"",""));
            }else if("ProxyDomain".equals(client_parameter)){
               useProxyDomain=true;
               clientConfig.setProxyDomain(value.replaceAll("\"",""));
            }else if("ProxyHost".equals(client_parameter)){
               useProxyHost=true;
               clientConfig.setProxyHost(value.replaceAll("\"",""));
            }else if("ProxyPort".equals(client_parameter)){
               useProxyPort=true;
               clientConfig.setProxyPort(value);
            }else if("ProxyUsername".equals(client_parameter)){
               useProxyUsername=true;
               clientConfig.setProxyUsername(value.replaceAll("\"",""));
            }else if("ProxyPassword".equals(client_parameter)){
               useProxyPassword=true;
               clientConfig.setProxyPassword(value.replaceAll("\"",""));
            }else if("SocketTimeout".equals(client_parameter)){
               useSocketTimeout=true;
               clientConfig.setSocketTimeout(value);
            }else if("UserAgent".equals(client_parameter)){
               useUserAgent=true;
               clientConfig.setUserAgent(value.replaceAll("\"",""));
            }else if("ConnectionTimeout".equals(client_parameter)){
               useConnectionTimeout=true;
               clientConfig.setConnectionTimeout(value);
            }else if("MaxConnections".equals(client_parameter)){
               useMaxConnections=true;
               clientConfig.setMaxConnections(value);
            }else if("MaxErrorRetry".equals(client_parameter)){
               useMaxErrorRetry=true;
               clientConfig.setMaxErrorRetry(value);
            }else if("SocketReceiveBufferSizeHints".equals(client_parameter)){
               useSocketReceiveBufferSizeHints=true;
               clientConfig.setSocketReceiveBufferSizeHints(value);
            }else if("SocketSendBufferSizeHints".equals(client_parameter)){
               useSocketSendBufferSizeHints=true;
               clientConfig.setSocketSendBufferSizeHints(value);
            }
        }
        hasClientConfiguration = true;
    }

    //generate different aws client for different aws components
    for (INode processNode : process.getGeneratingNodes()) {
        if (processNode.isActivate() && awsTypeMap.containsKey(processNode.getComponent().getName())) {
            String client = ElementParameterParser.getValue(processNode, "__AWS_CONNECTION_CLIENT__");
            if (cid.equals(client)) {
                String clientClass = awsTypeMap.get(processNode.getComponent().getName());
                String clientName = processNode.getComponent().getName() + "_" + cid;
                boolean isAWSMQBroker = processNode.getComponent().getName().equalsIgnoreCase("cAWSMQBroker");
                if(hasClientConfiguration && !isAWSMQBroker){
                
    stringBuffer.append(TEXT_540);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_541);
     if(useSocketTimeout) { 
    stringBuffer.append(TEXT_542);
    stringBuffer.append(clientConfig.getSocketTimeout());
    stringBuffer.append(TEXT_543);
     } 
     if(useConnectionTimeout) { 
    stringBuffer.append(TEXT_544);
    stringBuffer.append(clientConfig.getConnectionTimeout());
    stringBuffer.append(TEXT_543);
     } 
     if(useProxy) { 
    stringBuffer.append(TEXT_545);
    stringBuffer.append(clientConfig.getProxyProtocol());
    stringBuffer.append(TEXT_546);
    stringBuffer.append(clientConfig.getProxyHost());
    stringBuffer.append(TEXT_547);
    stringBuffer.append(clientConfig.getProxyPort());
    stringBuffer.append(TEXT_548);
    stringBuffer.append(clientConfig.getProxyUsername());
    stringBuffer.append(TEXT_549);
    stringBuffer.append(clientConfig.getProxyPassword());
    stringBuffer.append(TEXT_550);
     } 
    stringBuffer.append(TEXT_551);
    stringBuffer.append(TEXT_552);
    stringBuffer.append(clientClass);
    stringBuffer.append(TEXT_510);
    stringBuffer.append(clientName);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(clientClass);
    stringBuffer.append(TEXT_553);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_554);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    
                }else if(!isAWSMQBroker){
                
    stringBuffer.append(TEXT_552);
    stringBuffer.append(clientClass);
    stringBuffer.append(TEXT_510);
    stringBuffer.append(clientName);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(clientClass);
    stringBuffer.append(TEXT_555);
     if(inheritCredentials) { 
    stringBuffer.append(TEXT_556);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
     } else { 
    stringBuffer.append(TEXT_557);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_543);
     } 
    stringBuffer.append(TEXT_558);
                          
                }
                if(region!=null && !region.isEmpty() && !"DEFAULT".equalsIgnoreCase(region) && !isAWSMQBroker){
                
    stringBuffer.append(TEXT_559);
    stringBuffer.append(region);
    stringBuffer.append(TEXT_39);
    
                }
                
    stringBuffer.append(TEXT_560);
    
                if (isAWSMQBroker){
                
    stringBuffer.append(TEXT_552);
    stringBuffer.append(clientClass);
    stringBuffer.append(TEXT_510);
    stringBuffer.append(clientName);
    stringBuffer.append(TEXT_561);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_562);
     if(region!=null && !region.isEmpty() && !"DEFAULT".equalsIgnoreCase(region)){ 
    stringBuffer.append(TEXT_563);
    stringBuffer.append(region);
    stringBuffer.append(TEXT_39);
     } 
     if(hasClientConfiguration){ 
    stringBuffer.append(TEXT_564);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_565);
     } 
    stringBuffer.append(TEXT_566);
    }
                
    stringBuffer.append(TEXT_567);
    stringBuffer.append(clientName);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(clientName);
    stringBuffer.append(TEXT_158);
           
                if(isLog4jEnabled){
                
    stringBuffer.append(TEXT_568);
    stringBuffer.append(clientName);
    stringBuffer.append(TEXT_569);
    
                }          
                awsTypeMap.remove(processNode.getComponent().getName());//generate once
            }
        }
    }


    
    }//end cAWSConnection
    else if("cAzureStorageBlob".equals(nodeComponentName)){
        String cid = node.getUniqueName();

    
    boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    String storageAccountName = ElementParameterParser.getValue(node, "__STORAGE_ACCOUNT_NAME__");
	
    if (isLog4jEnabled) {
    
    stringBuffer.append(TEXT_570);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_530);
    
    }
    
    stringBuffer.append(TEXT_571);
    
	boolean useSAS = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__USE_SAS__"));
    if (useSAS) {
	   String passwordFieldName = "__SAS_KEY__";
    
    stringBuffer.append(TEXT_572);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_573);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_574);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_575);
    
    } else {
	   String passwordFieldName = "__STORAGE_ACCESS_KEY__";
	
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_576);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_577);
    stringBuffer.append(storageAccountName);
    stringBuffer.append(TEXT_420);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_578);
    }
    stringBuffer.append(TEXT_579);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_580);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_581);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_582);
    stringBuffer.append(ElementParameterParser.getValue(node, "__BLOB_TYPE__"));
    stringBuffer.append(TEXT_158);
    
        List<Map<String, String>> blobMetadataList = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__BLOB_METADATA__");
        if (blobMetadataList != null && !blobMetadataList.isEmpty()){

    stringBuffer.append(TEXT_583);
    
            for (Map<String, String> map : blobMetadataList) {
                String argName = map.get("BLOB_METADATA_KEY").trim();
                String argValue = map.get("BLOB_METADATA_VALUE").trim();
                if(argName.startsWith("\"") && argName.endsWith("\"") && argName.length() >= 2) {
                    argName = argName.substring(1, argName.length() - 1);
                }

    stringBuffer.append(TEXT_584);
    stringBuffer.append(argName);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(argValue);
    stringBuffer.append(TEXT_158);
    
            }

    stringBuffer.append(TEXT_585);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_586);
    
        }
    }// end cAzureStorageBlob
}

    stringBuffer.append(TEXT_20);
    return stringBuffer.toString();
  }
}
