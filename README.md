# Spring Webflux API Authentication

This project is designed to show how to authenticate the API in Spring Webflux security. It is easy to just validate the request body with Converter. However, it is important to cache the request body when the server needs to handle the body after authentication. In this project, you can find how to cache the request and handle it after authentication