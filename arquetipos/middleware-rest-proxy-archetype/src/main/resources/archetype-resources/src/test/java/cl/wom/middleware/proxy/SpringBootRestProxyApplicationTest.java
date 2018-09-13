package ${groupId};

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class SpringBootRestProxyApplicationTest extends CamelTestSupport {
 
    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;
 
    @Produce(uri = "restlet:http://0.0.0.0:${portProxy}/${resourceService}")
    protected ProducerTemplate template;
 
    @DirtiesContext
    @Test
    public void testSendMatchingMessage() throws Exception {
        String expectedBody = "<matched/>";
 
        resultEndpoint.expectedBodiesReceived(expectedBody);
 
        template.sendBodyAndHeader(expectedBody, "foo", "bar");
 
        resultEndpoint.assertIsNotSatisfied();
    }
 
//    @DirtiesContext
//    @Test
//    public void testSendNotMatchingMessage() throws Exception {
//        resultEndpoint.expectedMessageCount(0);
// 
//        template.sendBodyAndHeader("<notMatched/>", "foo", "notMatchedHeaderValue");
// 
//        resultEndpoint.assertIsSatisfied();
//    }
}