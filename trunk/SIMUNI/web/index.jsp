<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='pages/templates/masterpage.jsp'>
        <decorator:content placeholder='title'>Insert title here</decorator:content>
        <decorator:content placeholder='body'>Hello</decorator:content>
</decorator:decorate>