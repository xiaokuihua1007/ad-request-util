package org.classmatechen.baidu.client.impl.refresh;

import lombok.Data;

@Data
public class RefreshDep {

   private String appId;
   private String refreshToken;
   private String secretKey;
   private Long userId;
}
