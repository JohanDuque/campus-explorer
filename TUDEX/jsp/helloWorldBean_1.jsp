<%@ page import = "it.polimi.iol.duque.bean.HelloBean" %>

<jsp:useBean id="hello" class="it.polimi.iol.duque.bean.HelloBean" scope="request" >
	<jsp:setProperty name="hello" property="name" value="giuseppe"/>
</jsp:useBean>

<jsp:setProperty name="hello" property="name" param="sms"/>

<HTML>
<HEAD><TITLE>Hello</TITLE></HEAD>
<BODY>
<H1> 
	Hello, <jsp:getProperty name="hello" property="name" />
	<hr/>
	
	<!--  SHOW JAVA SERVLET -->		
</H1>
</BODY>
</HTML>