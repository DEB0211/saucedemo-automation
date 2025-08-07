# 🐞 Bug Investigation Scenario

## Title
Error message alignment issue on invalid login

## Reproduction Steps (BDD Style)
```gherkin
@bug
Scenario: Invalid user login shows misaligned error
  Given user is on login page
  When user enters username "invalid_user" and password "wrong_pass"
  Then user should see an error message "Username and password do not match"
```

## Debug Steps
1. Open browser and reproduce manually using invalid credentials.
2. Check the error message location using `getCssValue()`.
3. Validate HTML alignment or layout break.
4. Attach screenshot.
5. Log issue in defect tracking system (e.g., JIRA).

## Screenshot
_Attach screenshot taken automatically by Hooks.java on failure._

---