package org.classmatechen.oceanengine.client.impl.refresh;

import lombok.Data;

@Data
public class RefreshDep {

    private Long appId;
    private String secret;
    private String refreshToken;
}
