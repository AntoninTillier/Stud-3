**Servlet Development**

**Exercise 1: Random Number**

- Create a Java Web project and name it "TPServlet."
- Create a servlet named "MaServlet" that generates and displays a random number between 0 and 100 in response to a GET request. Complete the `doGet()` method to perform the requested processing.
 
**Exercise 2: Connection**

- Create the "ServletConnect" servlet that displays a login form.
- Complete the `doGet()` method that displays the login form.
- Complete the `doPost()` method that handles request validation by first saving the data in a session and then redirecting the request to the "ServletWelcome" servlet.
- Create the "ServletWelcome" servlet that displays the message "Welcome login". To do this, it retrieves the session data to display the message correctly.
- The "ServletWelcome" servlet checks that the session has been created successfully; otherwise, it performs a redirection to "ServletConnect."
**Exercice 3: Guessing Game**

- Create the "ServletDevinette" servlet that displays a form containing a text field, allowing the user to enter a number, and a button to submit.
- Clicking the OK button sends a POST request to the "ServletDevinetteRes" servlet, which compares the entered number and a randomly generated number. It guides the user by displaying a message indicating the result of the comparison. If the user manages to guess the number, a message "Well done" is displayed; otherwise, a redirection to "ServletDevinette" with the comparison message is performed.
- The `doPost()` method retrieves the entered number and the number to guess, then performs the comparison. What mechanism do you use to pass these data?
