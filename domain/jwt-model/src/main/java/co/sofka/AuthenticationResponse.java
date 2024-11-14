package co.sofka;

public class AuthenticationResponse {

    private final String token;
    private final String id;

    private AuthenticationResponse(Builder builder) {
        this.token = builder.token;
        this.id = builder.id;
    }

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }

    public static class Builder {
        private String token;
        private String id;

        public Builder() {
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
