# GitHub Login - Manual Test Cases

## Test Case 1: Valid Login

**Test Steps**:
1. Navigate to https://github.com/login
2. Enter valid username/email in the "Username or email address" field
3. Enter valid password in the "Password" field
4. Click "Sign in" button

**Expected Results**:
- User is successfully logged in
- Redirected to GitHub dashboard/home page
- User profile menu is visible in top right corner
- No error messages are displayed

**Test Data**: Use valid GitHub account credentials

---

## Test Case 2: Invalid Username/Password

**Test Steps**:
1. Navigate to https://github.com/login
2. Enter invalid username (e.g., "invaliduser123456")
3. Enter invalid password (e.g., "wrongpassword")
4. Click "Sign in" button

**Expected Results**:
- Login fails
- Error message is displayed: "Incorrect username or password"
- User remains on login page
- Username and password fields are cleared or highlighted

---

## Test Case 3: Empty Username Field

**Test Steps**:
1. Navigate to https://github.com/login
2. Leave username field empty
3. Enter any password in password field
4. Click "Sign in" button

**Expected Results**:
- Login attempt fails
- Appropriate validation message is shown
- Form submission is prevented
- Focus moves to username field

---

## Test Case 4: Empty Password Field

**Test Steps**:
1. Navigate to https://github.com/login
2. Enter valid username
3. Leave password field empty
4. Click "Sign in" button

**Expected Results**:
- Login attempt fails
- Appropriate validation message is shown
- Form submission is prevented
- Focus moves to password field

---

## Test Case 5: Both Fields Empty

**Test Steps**:
1. Navigate to https://github.com/login
2. Leave both username and password fields empty
3. Click "Sign in" button

**Expected Results**:
- Login attempt fails
- Validation messages are shown for both fields
- Form submission is prevented

---

## Test Case 6: Password Visibility Toggle

**Test Steps**:
1. Navigate to https://github.com/login
2. Enter password in password field
3. Look for password visibility toggle icon/button
4. Click the toggle to show password
5. Click the toggle again to hide password

**Expected Results**:
- Password is initially hidden (shown as dots/asterisks)
- When toggle is clicked, password becomes visible as plain text
- When clicked again, password is hidden again
- Toggle icon changes appropriately (eye/eye-slash)

---

## Test Case 7: "Forgot Password" Link

**Test Steps**:
1. Navigate to https://github.com/login
2. Click "Forgot password?" link

**Expected Results**:
- User is redirected to password reset page
- Page displays form to enter email/username for password reset
- URL contains "password_reset" or similar

---

## Test Case 8: "Sign Up" Link Navigation

**Test Steps**:
1. Navigate to https://github.com/login
2. Click "Create an account" or "Sign up" link

**Expected Results**:
- User is redirected to GitHub sign up page
- Sign up form is displayed
- URL contains "join" or "signup"

---

## Test Case 9: Login Form Field Validation

**Test Steps**:
1. Navigate to https://github.com/login
2. Click in username field and then click outside without entering data
3. Click in password field and then click outside without entering data
4. Test tab navigation between fields
5. Test Enter key to submit form

**Expected Results**:
- Fields show appropriate validation states
- Tab navigation works correctly
- Enter key submits the form
- Field labels/placeholders are clear and helpful

---

## Test Case 10: Login with Email vs Username

**Test Steps**:
1. Navigate to https://github.com/login
2. Test 1: Login with username and valid password
3. Logout
4. Test 2: Login with email address and valid password

**Expected Results**:
- Both username and email should work for login
- Login successful in both cases
- No difference in behavior

---

## Test Case 11: Browser Back Button After Login

**Test Steps**:
1. Successfully log in to GitHub
2. Navigate to a different page within GitHub
3. Use browser back button to go to login page

**Expected Results**:
- User should be redirected to dashboard/home page (not login page)
- User should remain logged in
- No re-authentication required

---

## Test Case 12: Login Page Accessibility

**Test Steps**:
1. Navigate to https://github.com/login
2. Test keyboard navigation (Tab, Shift+Tab, Enter)
3. Check form labels and aria attributes
4. Test with screen reader (if available)
5. Verify color contrast and font sizes

**Expected Results**:
- All interactive elements are keyboard accessible
- Form has proper labels and accessibility attributes
- Color contrast meets accessibility standards
- Text is readable and properly structured

---
