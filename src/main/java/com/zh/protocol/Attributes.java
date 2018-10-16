package com.zh.protocol;

import com.zh.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Author zh2683
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
