# Generate a key pair and self-signed certificate
keytool -genkeypair -alias myserver -keyalg RSA -keysize 2048 -validity 365 -keystore server.keystore



# Export the server certificate
keytool -export -alias myserver -keystore server.keystore -file server.cer


# Create a client truststore and import the server certificate
keytool -import -alias myserver -file server.cer -keystore client.truststore


keytool -genkeypair -keyalg RSA -keysize 2048 -alias mycert -keystore keystore.jks -validity 3650


