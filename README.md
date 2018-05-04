# Table of Contents
1. [Dependencies](#dependencies-(maven))
2. [Bean Scopes](#bean-scopes)
    * [Scope Configuration (Using XML)](#scope-configuration-(using-XML))
    * [Scope Configuration (Using Annotations)](#scope-configuration-(using-annotations))
3. [Bean Lifecycle](#bean-lifecycle)
4. [Expression Language in JSF](#expression-language-in-jsf)
    * [Introspection](#introspection)
    * [Expression Language](#expression-language)
    * [EL Operators](#el-operators)
5. [Components](#components) 
    * [JSF Input Components](#jsf-input-components)
    * [JSF Output Components](#jsf-output-components)
    * [JSF Selection Components](#jsf-selection-components)
6. [Component Tags](#component-tags)
    * [Form Input Component](form-input-component)
    * [InputText Component](#inputtext-component)
    * [InputTextArea Component](#inputtextarea-component)
    * [InputSecret Component](#inputsecret-component)
    * [InputHidden Component](#inputhidden-component)
    * [OutputLabel Component](#outputlabel-component)
    * [OutputLink Component](#outputlink-component)
    * [OutputFormat Component](#outputformat-component)
    * [OutputText Component](#outputtext-component)
    * [Select Option Component](#select-option-component)
    * [Dynamic Selection Tag Usage](#dynamic-selection-tag-usage)
7. [Value Binding](#value-binding)
    * [Input Value Binding](input-value-binding)
    * [Output Value Binding](output-value-binding)
8. [JSF Lifecycle](#jsf-lifecycle)
9. [Phase Listener](#phase-listener)
10. [JSF Validators](#jsf-validators)
    * [Custom Validator](#custom-validator) 
11. [JSF Converters](#jsf-converter)
    * [Custom Converter](#custom-converter)
12. [Internationalization](#internationalization)






# JSF Basics (v2.2)

## Build Tool: Maven

#### Reference Implementations
* jsf-api.jar
* jsf-impl.jar

### Dependencies (Maven)
**pom.xml**
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
>All files with extension *.xhtml* have to have the below mentioned syntax in the starting  
***.xhtml**
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
  xmlns:ui="http://java.sun.com/jsf/facelets"
  xmlns:h="http://java.sun.com/jsf/html"
  xmlns:f="http://java.sun.com/jsf/core">
</html>
```

* JSF allows users to design a complex tree of named POJO Beans. These beans have a predetermined lifespan known as Scope.
* Beans can be defined using: <br>
  > Managed Bean Creation facility (faces-config.xml) <br>
  > Java EE 5 annotations
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
  > @ManagedBean(name="someName")
* If a name is not specified the class name is used as the Bean name, mixed case starting with lower case
* The eager attribute can be used to insure that a bean is loaded in a non-lazy fashion
  > @ManagedBean(name="someName", eager=true)
> Note: Make sure you include the correct package ```import javax.faces.bean. ManagedBean;```

### Bean Scopes
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
  - @ApplicationScoped*
  - @SessionScoped*
  - @ViewScoped*
  - @RequestScoped*
  - @CustomScoped(value="#{someMap}")
  - @NoneScoped
> Note: make sure to import: ```javax.faces.bean.Xscoped;```<br>
**Frequently Used Scope Types*

### Bean Lifecycle
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

### Expression Language in JSF

#### Introspection
* Page markup will want to access managed Bean properties
* JSF uses Java introspection to access Bean properties
  - Consider the Bean instance variable 'name'<br>
    ```java
      private String name
      public String getName(){...}
      public void setName(String name){...}
    ```
* Boolean instance variables are slightly different
  ```java 
    private boolean rendered
    public boolean isRendered (){...}
    public void setRendered(boolean name){...}
  ```
* For introspection to work correctly the class must have a zero argument constructor
  > Alternatively no constructors is valid
* The instance variable isn’t actually needed, set/get or set/is Variable name is all that is needed for introspection to resolve
* Avoid public instance variables
  - Introspection won’t call a setter/getter for a public instance variable
  - This can make debugging more difficult
* Static instance variables cannot be accessed through introspection

#### Expression Language
* Bean properties can be exposed to EL expression language
* Consider the named bean "applicant" with a setter/getter for the property *firstName*
* JSF expression language for accessing the property looks like this
  > ```#{applicant.firstName}```
  
> Note: The framework calls the getter/setter methods as needed
* The EL notation **#{}** defers evaluation to runtime
  - 99% percent of the time this is the syntax you want
* The EL notation **${}** compile-time (immediate) evaluation
  - Can offer some page level optimizations in side of looping structures
* What happens when an expression is evaluated
  - Bean look-up
  - Reflective bean property resolution

#### EL Operators
* Arithmetic
  > +, -, *, / (or 'div'), % (or 'mod')
* Relational
  > == (or 'eq'), != (or 'ne'), < (or 'lt'), > (or 'gt'), <= (or 'le'), >= ('ge')
* Logical
  > && (or 'and'), || (or 'or'), ! (or 'not')
* Conditional:
  > A ? B : C
* Empty
  > empty (true if variable is null, an zero-length string, array, Map, or Collection) 
  <br>```#{not empty (mybean.value) and mybean.rendered}```

### Components 
* Components in JSF are classified into 3 parts:
  - The Component Class
  - The Tag Class
  - The Renderer Class

![JSF Component Architecture](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/Component%20Architecture.JPG)

* The default JSF components render html (h: namespace)
* More information on component development will be covered in later lectures

* JSF contains a handful of basic components that are equivalent to the HTML 5 elements
#### JSF Input Components
  - <h:form />
  - <h:inputText />
  - <h:inputTextarea />
  - <h:inputSecret />
  - <h:inputHidden />    

#### JSF Output Components
  - <h:outputLabel />
  - <h:outputLink />
  - <h:outputFormat />
  - <h:outputText />

#### JSF Selection Components
  - <h:selectBooleanCheckbox>
  - <h:selectOneRadio>
  - <h:selectManyCheckbox>
  - <h:selectOneListbox>
  - <h:selectManyListbox>
  - <h:selectOneMenu>
  - <h:selectManyMenu>

### Component Tags
Component tags are placed on JSF pages/views:<br>
  ```html 
    <h:outputText />
  ```
Tag attributes allow developers to customize the appearance and behavior of components:
  ```html
    <h:outputText value="Hello World" rendered="true" />
  ```
Tags are nested in a parent-child containment format:
  ```html
    <h:form>
      <h:outputLabel for="fullName" />
      <h:inputText id="fullName" />
    </h:form>
  ```

#### Form Input Component
* The form input component is a required parent tag for all input components
* Any input components in the form tag will be submitted to the server when submit occurs
    ```html
        <h:form />
          <h:inputText value="#{bean.userName}"/>
          ...
        </h:form/>
    ```
* Form tags can not be embedded but there can be more than one form per page

#### InputText Component
* Input Text is the same as the *```<input type="text"/>```* in html 4 allowing client side users to input text
* The value binding can be of type String, Number and all number primitives. JSF takes care of conversion
* InputText can be quite powerful when combined with converters, validators and ajax tags which will be explained in more detail later

#### InputTextArea Component
* InputTextArea is the same as the *```<input type="textarea"/>```* in html 4 allowing client side users to input text
* The value binding should be of the type String for the value attribute

#### InputSecret Component
* InputSecret Text is the same as the *```<input type="password"/>```* in HTML 4 allowing client to enter hidden or secret text
* The component attribute autocomplete="off" is handy for suppressing the form auto complete feature of most modern browsers

#### InputHidden Component
* Similar to the *```<input type="hidden"/>```* in HTML 4
* Allows JSF developers to include hidden form data that will be submitted with the other form elements
* Not used as often in JSF as in standard HTML as Bean scopes provide more intuitive state saving

#### OutputLabel Component
* Renders the same output as the HTML 4 *```<label>```* tag
* Generally used in combination with an input component
* When the id attribute of an input component matches the for attribute of an outputLabel a fieldset tag will automatically be inserted by the framework

#### OutputLink Component
* Renders the same output as the HTML 4 *```<a>```* tag
* Not commonly used in JSF as most developers use framework features that aren’t implicitly supported by this component
* JSF 2.0 introduced *```<h:link />```* a component that allows developers to use HTTP GET submits instead of the standard JSF POST submits

#### OutputFormat Component
* Allows developers to use Java i18n message bundles that have specified input parameters
* This component will be covered in later lectures around message bundles
* A simple example of its usage:
    ```html
      <h:outputFormat value="Line number {0} !">
        <f:param value="153,44"/>
      </h:outputFormat>
    ```
#### OutputText Component
* Renders the same output as the HTML 4 *```<span>```* tag
* JSF 2.0 EL notation has somewhat reduced the use of the outputText component <br>
  *```#{myBean.value1}```* is equivalent to *```<h:outputText value="#{myBean.value1}"/>```*
* However it is still used when JSF conversion is needed

#### Select Option Component
* Each selection tag works like a parent-child container, and has two necessary parts
  - The parent tag with the currently selected value: 
      ```html
        <h:selectOneMenu value="#{modelBean.ourColor}">
      ```
  - The child tag(s) listing available items:
      ```html
        <f:selectItem itemLabel="Red" itemValue="red" />
      ```
* A simple example of its usage: 
    ```html
      <h:selectOneMenu value="#{modelBean.ourColor}">
        <f:selectItem itemLabel="Red" itemValue="red" />
        <f:selectItem itemLabel="Green" itemValue="green" />
        <f:selectItem itemLabel="Blue" itemValue="blue" />
      </h:selectOneMenu>
    ```

#### Dynamic Selection Tag Usage
* The available items can also be dynamically pulled from a bean:
    ```html
      <h:selectOneMenu value="#{modelBean.ourColor}">
        <f:selectItems value="#{modelBean.availableColors}" />
      </h:selectOneMenu>
    ```
* The dynamic items could be an Array of Strings:
    ```java
      private String[] availableColors = {"Red", "Blue", "Green"};
      public String[] getAvailableColors() {
        return availableColors;
      }
    ```
* The items can now be modified directly in the backing bean list instead of at the page level
* Combining the two approaches is a common practice
* This is useful for adding a default value without modifying the bean:
    ```html
      <h:selectOneMenu>
        <f:selectItem itemLabel="Please Choose a Color..." noSelectionOption="true" />
        <f:selectItems value="#{modelBean.availableColors}" />
      </h:selectOneMenu>
    ```
* Note that by specifying only itemLabel the f:selectItem will automatically use the same value for itemValue
* Note the noSelectionOption in the default item, which is used to ensure the user cannot use the item as their submitted selection

### Dynamic Service Layer
*  Normally bound item lists would be retrieved from a service layer
*  The alternative would be directly accessing the persistence layer (database, file system, web service, etc.)
*  This service layer allows additional processing and caching of items
*  For example multiple users may call the service layer for a local copy of the item list:
    ```java
      private String[] availableColors = null;
      public String[] getAvailableColors() {
        if (availableColors == null) {
          availableColors = database.retrieveColorList();
        }
        return availableColors;
      }
    ```

### Value Binding
* Bean values are assigned to component attributes using JSF EL syntax
* For example the managed bean myBean’s instance variable 'value1' is assigned to the input component as follows:
    ```html
      <h:inputText value="#{myBean.value1}"/>
      <h:inputText value="#{myBean.value1}" rendered="#{myBean.value2}"/>
    ```

#### Input Value Binding
* Input components in JSF require that the bound bean property is mutable :
    ```java
      public void setValue1(Object value1){...}
      public Object getValue1(){...};
    ```
* If the JSF introspection mechanism can’t find the corresponding setter or getter a run time error will occur
* All other non "value" attribute bindings can be immutable as the setter method is never called by the JSF framework

#### Output Value Binding
* Output components in JSF assume that the associated value bindings are immutable but it is not a requirement
    ```java
      public Object getValue1(){...};
    ```
* If the JSF introspection mechanism can’t find the corresponding getter a run time error will occur
* All non "value" component attribute bindings can be immutable as the setter method is never called by the JSF framework

### JSF Lifecycle
* The framework defines a lifecycle that executes in distinct phases:
  * Restore View
  * Apply Request Values
  * Process Validations
  * Update Model Values
  * Invoke Application
  * Render Response
* Lifecycle has two logical portions Execute and Render
  * Supports AJAX partial processing and partial rendering
  * You can specify components that JSF processes on the server or that JSF renders when an Ajax call returns

#### Phase 1: Restore View
  ![Restore View](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/RestoreView.JPG)
  * The Restore View phase restores an existing view from the previous request, or creates a new view for an initial request
    - The resulting view is put into the current FacesContext
  
#### Phase 2: Apply Request Values
  * The Apply Request Values phase is where submitted request parameters are mapped to their corresponding UIInput components
    ![Request Values](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/ApplyRequestValues.JPG)
  
#### Phase 3: Process Validations
  * The Process Validations phase is where submitted values are processed by JSF converters and validators
    ![Process Validations](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/ProcessValidations.JPG)
  * If any component fails validation then,
    - The component's valid property is set to ***false***  
    - A *FacesMessage* is added to the ***FacesContext***

#### Phase 4: Update Model Values
  * If conversion & validation passes, then the Update Model Values phase is reached
  * If EL value bindings were specified on UIInput components, then those component values are set in the mode
  * For example, the following EL value binding:<br>
    ```*<h:inputText value="#{MyBean.firstName}"/>*```
    … will cause the *MyBean.setFirstName()* method to be called and will pass it the result of *HtmlInputText.getValue()* 
    ![Update Model Values](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/UpdateModelValues.JPG)
  
#### Phase 5: Invoke Application
  * The Invoke Application phase invokes methods in managed-beans that perform some kind of action
  * ActionListener methods are called first, then Actions
  * Markup Examples:
    ```html
      <h:commandButton actionListener="#{MyBean.checkAll}" />
      <h:commandButton action="#{MyBean.submit}" />
    ```
    ![Invoke Application](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/InvokeApplication.JPG)
  
#### Phase 6: Render Response
  * The **Render Response** phase is responsible for
    – Invoking component renderers for the appropriate client device
    – Sending a fully encoded response back to the client
    – The response is typically HTML markup that is written to the *HttpServletResponse OutputStream*
    ![Render Response](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/RenderResponse.JPG)

### HTTP GET
![HTTP GET](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/HTTP%20GET.JPG)


### HTTP POST (Valid Values) 
![HTTP POST](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/HTTP%20POST%20VALID.JPG)


### HTTP POST (Invalid Values) 
![HTTP POST](https://github.com/btgn/images/blob/master/JSF-ICEfaces%20Images/HTTP%20POST%20INVALID.JPG)

