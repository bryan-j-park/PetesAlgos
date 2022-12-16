<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pete's Algos</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<link href="https://fonts.googleapis.com/css2?family=Righteous&family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
    <header>
        <nav>
            <img src="/imgs/pete2.png" width="120px;">
            <h1>Pete's Algos</h1>
        </nav>
        <div class="links">
            <h2><a href="">all algos</a></h2>
            <h2><a href="/logout">logout</a></h2>
        </div>
    </header>
    <main>
        <form:form action = "/testform" method = "POST" modelAttribute ="problem" class="addForm">
		<p class="formSpacing">
			<form:label path = "name">name:</form:label>
			<form:input path = "name"/>
			<form:errors path = "name" class="formErrors"/>
		</p>
		
		<p class="formSpacing">
			<form:label path = "difficulty">difficulty:</form:label>
			<form:input path = "difficulty"/>
			<form:errors path = "difficulty" class="formErrors"/>
		</p>
		
		<p class="formSpacing">
			<form:label path = "datatype">datatype:</form:label>
			<form:input path = "datatype"/>
			<form:errors path = "datatype" class="formErrors"/>
		</p>
		
        <p class="formSpacing">
			<form:label path = "number">number:</form:label>
			<form:input path = "number"/>
			<form:errors path = "number" class="formErrors"/>
		</p>

        <p class="formSpacing">
			<form:label path = "code">code:</form:label>
			<form:textarea path = "code"/>
			<form:errors path = "code" class="formErrors"/>
		</p>
		
		<input type="submit" value = "Add problem" class="formButton"/>
	</form:form>
    </main>
</body>
</html>