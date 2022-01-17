# Tests Description
There are 2 kinds of tests :
* Structure tests that check the classes you create are in the correct package, have the correct methods, attributes, annotations and so on
* Behaviour tests that check if the implementation of your methods is correct

Each test method relies on the pattern **"GIVEN-WHEN-THEN"** which is visible thanks to the comments. Here is an example:
```java
@Test
public void shouldNotHaveDrugsBeforeInit() throws NoSuchFieldException, IllegalAccessException {
    //GIVEN
    Class<DrugsServlet> clazz = DrugsServlet.class;
    Field drugsField = clazz.getDeclaredField("drugs");
    drugsField.setAccessible(true);
    ParameterizedType listType = (ParameterizedType) drugsField.getGenericType();
    DrugsServlet servlet = new DrugsServlet();
    //WHEN
    Object drugs = drugsField.get(servlet);
    //THEN
    assertThat(drugs).isNull();
    assertThat(drugsField.getType()).isEqualTo(List.class);
    assertThat(listType.getActualTypeArguments()[0]).isEqualTo(Drug.class);
}
```
The **GIVEN** block contains the hypothesis and all the code used to configure the test. In our case above, we use Java Reflection to get access to the "drugs" private field. It will be useful for the checks

The **WHEN** block contains the actions. The goal of this test method is to check the result of these actions. In our case, the action is to get the value of the "drugs" field inside the "servlet" object.

The **THEN** block contains the checks, the controls on the results. For the method above, we have 3 checks:
* we want a "null" value for the "drugs" field which means that it is empty
* the field must have a `List` type
* the type of the elements in the list must be `Drug`. Finally, the type of the "drugs" field must be `List<Drug>`.

## Reading tips
### `DrugsServletStructureTestCase`
 * `shouldExtendsHttpServlet`: checks that the class extends the correct superclass
 * `shouldHaveWebServletAnnotation`: checks that the class is correctly annotated
 * `shouldHaveInitMethod`: checks if the method `init` is present

### `DrugsServletBehaviourTestCase`
 * `shouldNotHaveDrugsBeforeInit`: checks the presence of an attribute named `drugs` which is a list of `Drug` objects. Before the call of the `init` method, this list should be null.
 * `shouldHaveDrugsAfterInit`: checks that after the call of the `init` method, the list contains 2 items
 * `shouldSetDrugsInRequestThenDispatch`: checks that with the call of the `doGet` method, the `setAttribute` method is called on the `request` parameter then the `getRequestDispatcher` method is called then `forward` method is called on the dispatcher we just got
 * `shouldSaveDrugThenRedirect`: checks that with the call of the `doPost` method, your implementation fetches the sent parameters, build a new Drug object which is added to the list then redirect the browser to the list of drugs.
 
 You will notice that the path of redirection starts with "contextPath". Please note that this prefix must be retrieved thanks to this call : `req.getServletContext().getContextPath()`
