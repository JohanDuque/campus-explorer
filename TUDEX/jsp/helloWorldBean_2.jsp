<%@ page import = "it.polimi.iol.duque.bean.HelloBean" %>

<jsp:useBean id="hello" class="it.polimi.iol.duque.bean.HelloBean" scope="session">
  <jsp:setProperty name="hello" property="name" param="sms"/>
</jsp:useBean>

<!-- SESSION BEAN, if at instantiation time the parameter on the querystring is set, then the param value is kept in session
	Otherwise, it remains with a default value unless an explicit "jsp:setProperty" is used --> 

<HTML>
<HEAD><TITLE>Hello</TITLE></HEAD>
<BODY>
<H1> 
	Hello, <jsp:getProperty name="hello" property="name" />
	<hr/>
	
	<%
		if(request.getParameter("sms") != null && hello.getName().equals("World")){	
			System.out.println(hello.getName());
	%>
		<jsp:setProperty name="hello" property="name" param="sms"/>
	<%
		}
	
	%>
		
</H1>
</BODY>
</HTML>
