### How to generate the server stub

#### Server
```shell script

java -jar ./openapi/openapi-generator-cli-4.3.1.jar generate \
     --generator-name spring \
     --verbose \
     --additional-properties=useTags=true,library=spring-boot,modelPackage=com.visa.cloud.storage.dto.nas,apiPackage=com.visa.cloud.storage.api,basePackage=com.visa.cloud.storage,artifactId=nas-service,artifactVersion=3.0-SNAPSHOT,useOptional=true,dateLibrary=legacy,invokerPackage=com.visa.cloud.storage,java8=true,performBeanValidation=true,hideGenerationTimestamp=true,developerEmail=vgajjala@visa.com,developerOrganization=visa,useBeanValidation=true,skipDefaultInterface=true,interfaceOnly=true \
     --group-id=com.visa.cloud.storage \
     --artifact-id=nas-service \
     --artifact-version=3.0-SNAPSHOT \
     --input-spec ~/work/source/storage-automation/storage-services/nas-service/src/main/resources/swagger-api/nas-api-v2.yaml \
     --instantiation-types=array=ArrayList,map=HashMap \
     --type-mappings=array=List,map=Map,string=String,DateTime=java.time.LocalDateTime\
     --output ./generated-code-openapi/nas-service
```

#### Client

```shell script
java -jar ./openapi/openapi-generator-cli-4.3.1.jar generate \
     --generator-name java \
     --verbose \
     --additional-properties=useTags=true,apiPackage=com.vinay.spring.boot.template,artifactId=storage-nas-isilon-client,artifactVersion=3.0-SNAPSHOT,booleanGetterPrefix=is,dateLibrary=legacy,developerEmail=vgajjala@visa.com,developerOrganization=visa,disableHtmlEscaping=true,groupId=com.visa.cloud.storage.nas,hideGenerationTimestamp=true,invokerPackage=com.visa.cloud.nas.isilon.array.client,java8=true,library=resttemplate,modelPackage=com.visa.cloud.nas.isilon.array.client.model,performBeanValidation=true,serializableModel=true,serializationLibrary=jackson,snapshotVersion=true,useBeanValidation=true,useOptional=true,parcelableModel=false \
     --instantiation-types=array=ArrayList,map=HashMap \
     --type-mappings=array=List,map=Map,string=String,DateTime=java.time.LocalDateTime \
     --input-spec <project-path>/src/main/resources/openapi/vinay-openapi-template.yaml \
     --output ./generated-code-openapi/storage-nas-isilon-client
```
