package org.classmatechen.tencent.client.impl.refresh;

import lombok.Data;

@Data
public class RefreshDep {

    private Long clientId;
    private String clientSecret;
    private String refreshToken;
    private String redirectUri;
}
