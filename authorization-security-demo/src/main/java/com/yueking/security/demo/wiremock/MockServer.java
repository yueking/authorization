package com.yueking.security.demo.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServer {
    public static void main(String[] args) {
        configureFor(8088);
        removeAllMappings();
        stubFor(get(urlPathEqualTo("/order/1")).willReturn(aResponse().withBody("{\"id\":1,\"name:\":\"手柄\"}").withStatus(200)));
    }

    private static void mock(String url,String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("/");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }
}
