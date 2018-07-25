spring-admin: http://127.0.0.1:8086/#/
config-server: http://localhost:8889/service-discovery-client/region1
               http://localhost:8889/service-discovery-client-region2.yaml
               http://localhost:8889/service-discovery-client-region2.properties
               http://127.0.0.1:8889/logging/production/master/shop-logback-spring.xml
discovery-server: http://localhost:8761
discovery-client: http://localhost:8081/ping
api-gateway: http://localhost:8765/api/client/ping
discovery-server: http://localhost:8761
discovery-client: http://localhost:8081/ping
kotlin-client: http://localhost:8083/ping
go-client: http://localhost:8085/ping
api-gateway: http://localhost:8765/api/client/ping
             http://localhost:8765/api/kotlin-client/ping
kibana: 
hystrix dashboard: http://127.0.0.1:9000/hystrix.stream and add for example localhost:8090/hystrix.stream
