package org.classmatechen.tencent.client.impl.init;

import lombok.Data;

@Data
public class Tencent {

    private Long clientId;
    private String clientSecret;
    private String authorizationCode;
    private String redirectUri;
}
