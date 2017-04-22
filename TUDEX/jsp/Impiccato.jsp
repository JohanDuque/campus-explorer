<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="partita" class="it.polimi.iol.duque.bean.Impiccato" scope="session"/>
<jsp:setProperty name="partita" property="*"/>

<head>
<title>Impiccato</title>
</head>
<body>
<h1>Gioco dell'Impiccato</h1>

<%-- Gestione Utente ======================================================--%>
<% 
String username=request.getParameter("username");

if (username == null || username.equals("") )
{
	Cookie cookies [] = request.getCookies();
	if (cookies != null) {
    	for (int i = 0; i < cookies.length; i++) {
        	if (cookies[i].getName().equals("username")) {
            	username = cookies[i].getValue();
            	break;
        	}
    	}
	} 

	if (username == null || username.equals("") )
	{
    	response.sendRedirect("login.jsp");
	}
} else {
	Cookie cookie = new Cookie ("username",username);
	response.addCookie(cookie);
}
%>

Giocatore: <%=username%> (per cambiare nome clicca <a href="login.jsp">qui</a>)

<%-- Vittoria ==============================================================--%>
<% if (partita.getSuccesso()) { %>
	<p>Congratulazioni !</p>
	<p>La parola segreta era <jsp:getProperty name="partita" property="parolaSegreta" />.</p>
	<p>L'hai indovinata impiegando <jsp:getProperty name="partita" property="numTentativi" /> tentativi.</p>
	<% partita.reset(); %>
  	<p>Per giocare ancora clicca <a href="Impiccato.jsp"> qui </a>.</p>
</body>

<%-- Sconfitta =============================================================--%>
<% } else if (partita.getNumTentativi() - 1 == partita.getMaxTentativi()) { %>

   	<p>Hai esaurito tutti i <jsp:getProperty name="partita" property="maxTentativi" /> tentativi a disposizione senza indovinare</p>
	<p>Caratteri inseriti: <jsp:getProperty name="partita" property="lettereInserite" /></p>
	<p><jsp:getProperty name="partita" property="trattini" /></p>
	<p>La parola segreta era <jsp:getProperty name="partita" property="parolaSegreta" />.</p>
	<br>
	<% partita.reset(); %>
  	<p>Per giocare ancora clicca <a href="Impiccato.jsp"> qui </a>.</p>

<%-- Gioco =================================================================--%>
<% } else { %>
	<p>Tentativo in corso numero <jsp:getProperty name="partita" property="numTentativi" /> / <jsp:getProperty name="partita" property="maxTentativi" />. </p>
	<p>Caratteri inseriti: <jsp:getProperty name="partita" property="lettereInserite" /></p>
	<p><jsp:getProperty name="partita" property="trattini" /></p>
    <form method="post">
        <p> Inserisci una nuova lettera oppure l'intera parola da indovinare:
        <input type="text" name="parolaUtente">
        <input type="submit" value="Invia risposta">      
    </form>
<% } %>
</body>
</html>
