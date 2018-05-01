# JSF Basics (v2.2)

### Build Tool: Maven
#### Reference Implementations
* jsf-api.jar
* jsf-impl.jar

#### Dependencies
```xml
<dependencies>
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>3.8.1</version>
    <scope>test</scope>
  </dependency>

  <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
  </dependency>

  <!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-api -->
  <dependency>
    <groupId>com.sun.faces</groupId>
    <artifactId>jsf-api</artifactId>
    <version>2.2.17</version>
  </dependency>

  <!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-impl -->
  <dependency>
    <groupId>com.sun.faces</groupId>
    <artifactId>jsf-impl</artifactId>
    <version>2.2.17</version>
  </dependency>

</dependencies>
 ```
* JSF allows users to design a complex tree of named POJO Beans. These beans have a predetermined lifespan known as Scope.
* Beans can be defined using: <br>
  – Managed Bean Creation facility (faces-config.xml) <br>
  – Java EE 5 annotations
* Manage Beans also have a lifecycle which depends on their speciefied scope

#### JSF 2 XML based Configuration 
* Bean names uniquely identify the Bean within the context of the application
* Follow the same naming conventions as Java classes,
mixed case starting with lower case
* Bean names can be defined in faces-config.xml
```xml
<managed-bean>
  <managed-bean-name>
    myBeanName
  </managed-bean-name>
  <managed-bean-class>
    org.mycompany.package.ClassName
  </managed-bean-class>
</managed-bean>
```

#### JSF 2 @ManagedBean Annotation based Configuration
* Names can be specified with the name attribute
  > @ManagedBean(name=“someName”)
* If a name is not specified the class name is used as the Bean name, mixed case starting with lower case
* The eager attribute can be used to insure that a bean is loaded in a non-lazy fashion
  > @ManagedBean(name=“someName”, eager=true)
> Note: Make sure you include the correct package ```import javax.faces.bean. ManagedBean;```

#### Bean Scopes
* **Application**
  - Lifespan continues as long as the web application is deployed
* **Session**
  - Lifespan of the HttpSession, destroyed by session timeout or manual invalidation
  - Unique to each user but share across multiple browser tabs
* **Request**
  - Lifespan duration of an HTTP request received by the server and response sent to client
* **No Scope**
  - Bean isn’t placed into scope
* **View**
  - Bean lasts the duration of the view
  - Page navigation or page refreshes cause the Bean to be destroyed and reinitialized
* **Flash**
  - Short conversation-style scope that exists for a single view transition including reloads
* **Custom**
  - Allows developers to implement their own custom scope behavior
 
#### Scope Configuration (Using XML)
* Once a Bean is defined in the faces-config.xml or via an Annotation it has a default of request scope
* Bean scope can be defined explicitly in the faces-config.xml
```xml
<managed-bean>
  <managed-bean-name>
    myBeanName
  </managed-bean-name>
  <managed-bean-class>
    org.mycompany.package.ClassName
  </managed-bean-class>
  
  <mangaged-bean-scope>
    application|session|view|request|flash
  </managed-bean-scope>

</managed-bean>
```

#### Scope Configuration (Using Annotations)
* Scopes can also be defined with annotations
  - @ApplicationScoped
  - @SessionScoped
  - @ViewScoped
  - @RequestScoped
  - @CustomScoped(value="#{someMap}")
  - @NoneScoped
> Note: make sure to import: ```javax.faces.bean.Xscoped;```

#### Bean Lifecycle
JSF implementations running in a Java EE 5 compliant container have access to two other annotations:
  - @PostConstruct
  - @PreDestroy
* Methods on managed beans can be annotated as follows:
```java
  @PostConstruct
  public void myMethod(){
    // initialization logic.
  }
```

* When the bean is initialized the method binding for *@PostConstruct* is called immediately after the Class is initialized
* Convenient method for initialization data at construction time
* *@PreDestroy* is called just before the Bean is removed from the container management
* Bean scope plays an important role as to when this method is called
* Can be handy for proactively cleaning up a memory foot print or un-registering a class from a listener
