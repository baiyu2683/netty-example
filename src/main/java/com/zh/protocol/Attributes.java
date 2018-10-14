package com.zh.protocol;

import io.netty.util.AttributeKey;

/**
 * @Author zh2683
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
