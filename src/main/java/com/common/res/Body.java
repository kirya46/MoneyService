package com.common.res;

/**
 * Created by Kirill Stoianov on 06/09/17.
 */
public class Body {
    private String body;

    public Body(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Body{" +
                "body='" + body + '\'' +
                '}';
    }
}
