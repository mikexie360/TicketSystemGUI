# TicketSystemGUI

The Ticket System GUI allows developers to submit tickets, keep track of current bugs, and keep track of resolved bugs.

The Ticket System GUI uses a simple CRUD application with javafx that uses api calls to the spring boot backend with a MySQL database.

The Ticket System GUI has english and chinese localization that the user can easily switch between and was designed so that future language can be implemented easily.

The Spring Boot backend uses a simple restful api for developers to submit bug reports and resolve bugs.

The Spring Boot backend connects to a mysql database to keep track of all tickets.

Commonly used API calls are the following...

Post Mapping
http://localhost:8080/api/addTicket

Put Mapping
http://localhost:8080/api/updateTicket
http://localhost:8080/api/resolveTicket={id}

Get Mapping
http://localhost:8080/api/getTickets

Delete Mapping
http://localhost:8080/api/deleteTicket=1

The front end handles refresh of the table and the language localization.

While the backend handles the business logic and the connection between the frontend and the database.

