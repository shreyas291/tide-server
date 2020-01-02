package com.sensei.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;
/**
 * Properties specific to JHipster.
 *
 * <p>
 *     Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private Files files = new Files();

    public Files getFiles(){
        return files;
    }

    public void setFiles(Files files){
        this.files = files;
    }

    public List<String> getStoragePaths() {
            return storagePaths;
    }

    public void setStoragePaths(List<String> storagePaths) {
            this.storagePaths = storagePaths;
    }

    private List<String> storagePaths;
    
    class Files{
        private String storageType = "local";

        public String getStorageType(){
            return storageType;
        }

        public void setStorageType(String storageType) {
            this.storageType = storageType;
        }
    }
    
    private String clientProject;

	public String getClientProject() {
		return clientProject;
	}

	public void setClientProject(String clientProject) {
		this.clientProject = clientProject;
	}
	
	private long tokenValidityInSecondsForRefreshToken;
	
	public long getTokenValidityInSecondsForRefreshToken() {
		return tokenValidityInSecondsForRefreshToken;
	}

	public void setTokenValidityInSecondsForRefreshToken(long tokenValidityInSecondsForRefreshToken) {
		this.tokenValidityInSecondsForRefreshToken = tokenValidityInSecondsForRefreshToken;
	}

	private Mail mail = new Mail();

    public Mail getMail(){
        return mail;
    }

    public void setMail(Mail mail){
        this.mail = mail;
    }

    public static class Mail {

        private String host = "localhost";

        private int port = 25;

        private String username;

        private String password;

        private String protocol = "smtp";

        private boolean tls = false;

        private boolean auth = false;

        private boolean debug = false;

        private String from = "application@localhost";

        private String to = null;

        private int retries = 5;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public boolean isTls() {
            return tls;
        }

        public void setTls(boolean tls) {
            this.tls = tls;
        }

        public boolean isAuth() {
            return auth;
        }

        public void setAuth(boolean auth) {
            this.auth = auth;
        }

        public boolean isDebug() {
            return debug;
        }

        public void setDebug(boolean debug) {
            this.debug = debug;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public int getRetries() {
            return retries;
        }

        public void setRetries(int retries) {
            this.retries = retries;
        }
    }
    
	private List<String> allowedFileContentTypes;

	public List<String> getAllowedFileContentTypes() {
		return allowedFileContentTypes;
	}

	public void setAllowedFileContentTypes(List<String> allowedFileContentTypes) {
		this.allowedFileContentTypes = allowedFileContentTypes;
	}
	
	private String resourcePath;

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
}