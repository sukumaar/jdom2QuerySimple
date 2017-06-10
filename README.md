# jdom2QuerySimple

Simple Library to extract nodes from xml with dot notation.

### Libraries Used
- jdom2 library to extract attributes from particular node
- javaslang for functional taste
- java 8 [not because number 8 is lucky for me]

### When you can use this?
- If you have xml which has default namespace and your xpath is suffering from nausea.
- If you have problem for xml element extraction like below:
    * [Xml Namespace breaking my xpath!]
    * [XML element has namespace, my XPATH does not work] 

### Compilation
`this is not any alien library so installation is same as any other maven code available on github`
```sh
$ mvn clean compile package
```
### Installation
- You would find these jars under "target/" after compilation 
    - simple_query-0.0.1-SNAPSHOT.jar           
    - simple_query-0.0.1-SNAPSHOT-jar-with-dependencies.jar
- import one of the jar to your project 

### Sample Input
This xml is aligned with problem which we can solve with this library
```xml
<?xml version="1.0"?>
<company xmlns="http://www.justAnotherSammpleNamespace.com">
	<staff>
		<personalInfo>
			<name>
				<firstname id="001" code="qwerty1">elon</firstname>
				<lastname>musk</lastname>
			</name>
			<phone>11111111</phone>
			<phone>22222222</phone>
		</personalInfo>
		<company>tesla</company>
		<salary>100000</salary>
		<isGood>yes</isGood>
	</staff>
	<staff>
		<personalInfo>
			<name>
				<firstname id="002" code="qwerty2">mark</firstname>
				<lastname>zukerberg</lastname>
			</name>
			<phone>33333333</phone>
			<phone>44444444</phone>
		</personalInfo>
		<company>facebook</company>
		<salary>100000</salary>
	</staff>
</company>
```
### Usage


```java
import java.io.File;
import java.util.List;
import org.jdom2.Element;
import com.sukumaar.jdom2.SimpleQuery.SimpleRead;

SimpleRead obj = new SimpleRead();
File xmlFile = new File("src/main/resources/xml/sample.xml");
String query1 = "company.staff.personalInfo.name.firstname";
List<Element> receiveData1 = obj.getData(xmlFile, query1);
System.out.println(receiveData1.size());
System.out.println(receiveData1);

String query2 = "company.staff.personalInfo.phone";
List<Element> receiveData2 = obj.getData(xmlFile, query2);
System.out.println(receiveData2.size());
//iterating over received data
receiveData2.forEach(x -> {
	System.out.println(x);
});
```
### License
Apache 2.0


yess read it right , open and free


### Thanks To:
http://www.vavr.io

https://www.mkyong.com/java/how-to-read-xml-file-in-java-jdom-example/

http://dillinger.io


[Xml Namespace breaking my xpath!]: <https://stackoverflow.com/questions/5239685/xml-namespace-breaking-my-xpath>
[XML element has namespace, my XPATH does not work
]:<https://stackoverflow.com/questions/10981312/xml-element-has-namespace-my-xpath-does-not-work>

