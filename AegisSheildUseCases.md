**Aegis Shield Use Cases**

# Introduction #
This page contains the use cases for the application. The database tables will have a technical id.

# Use Cases #
### 1. Add a new user account. ###
The user can add a new account and password to the database. Required information is:
  * account user name
  * account password
  * account description
  * account name

### 2. Edit user account information. ###
The user can edit information about an account. The editable information is:
  * account name
  * account description
  * account password

### 3. Delete user account information. ###
The user can delete any account.

### 4. View account information. ###
User can see the list of all accounts and then can see the details for each account. On the details screen, a reveal password button should appear which when it is clicked, it will reveal the clear password of the respective account.

### 5. Add a new repeating scheduler. ###
The user can add a reminder scheduler for any account to remind him about changing his password for that account. This can be accessed from the account information view screen.
Scheduler information:
  * short description
  * date created
  * recurrence period (should be able to choose among several standard frequencies)

### 6. Edit scheduler information. ###
The user should be able to edit a scheduler's information by pressing a button in the account details view screen.

### 7. Delete an account scheduler. ###
The user can delete the scheduler for an account, by pressing a button in the account details view screen.

### 8. Generate a random password. ###
The user can generate a random password for an account when creating or editing an account or when a scheduler is triggered.

### 9. Verify password strength. ###
The user can verify the strength of a password when creating or editing an account.

### 10. Enter master password. ###
The user can enter the application master password the first time the Aegis Shield application runs.

### 11. Change master password. ###
Can be done at any moment from the application menu.