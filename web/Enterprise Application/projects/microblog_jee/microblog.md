**Creating a Mini Blog**

The aim is to design a small web application based on Java EE. This project covers various aspects beyond pure Java, such as version control, integrating layout into the application, basic security, and the freedom to implement solutions.

**Requirements**

Create a blog that allows users to register, publish posts, add relationships, and comment on posts by their connections. Posts, comments, and a user's profile should only be visible to their connections. It should not be possible to view the profile or posts of a user who is not in one's connections. Users should be able to search for other users by their pseudonyms.

**Requirements**

- The project must be managed using Git. It should be possible to synchronize it with GitHub, GitLab, or other similar tools to facilitate collaboration.
- HTML pages should be valid to ensure compatibility with various web browsers. The W3C validator can be used to check your HTML pages and identify any potential errors. See https://validator.w3.org/#validate-by-input
- Basic security vulnerabilities must be avoided (session theft, XSS, SQL injection).
- Data should be stored in a database. To access it, the use of DAO is recommended.
- Some form of layout, even if minimal, must be applied using CSS.
- You should provide a file explaining how to install the project. The SQL script for creating the database should also be included.
- Dependencies like .jar files (MySQL JDBC driver, JSTL, etc.) should be included in the project.

**Options**

- It's not mandatory to use a framework for this project. Using servlets and JSP is perfectly valid.
- It's recommended to design before coding and create diagrams (UML, Merise, etc.). The website https://draw.io can be helpful for this.