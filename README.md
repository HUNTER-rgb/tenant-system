# Lab 6 - Tenant Management: Lifecycles and Fragments

Kotlin Android implementation of **Lab 6 / Navigation Practical 2 — Lifecycles and Fragments**, continuing the Lab 5 Intent navigation exercise.

Open this folder in Android Studio, let Gradle sync, then run the `app` configuration.

Implemented:
- Login is the launcher; explicit Intents open Register and Add Tenant.
- Register passes `EMAIL` back to Login with `putExtra` and `FLAG_ACTIVITY_CLEAR_TOP`.
- Login passes the email to Add Tenant for a welcome Toast, then closes itself.
- Help opens the Strathmore website using `ACTION_VIEW`.
- Save validates all tenant fields and displays details using Data Binding.
- Call Tenant opens the saved number using `ACTION_DIAL`; no call permission is needed.
- Share Tenant uses `ACTION_SEND` and a chooser to share the saved summary.
- Saved tenant details survive Activity recreation; forms scroll on small screens.

This is a navigation exercise: any non-empty email and password can log in. Registration does not store accounts, and tenant details are not saved to a database.

## Manual lab checks

1. Launch the app: Login appears. Empty login fields show a message.
2. Tap Help: the browser opens the Strathmore website; return to the app.
3. Open Register: empty fields are rejected. Fill all fields and register; Login shows the email.
4. Enter a password and log in: Add Tenant appears with the welcome Toast.
5. Before saving, Call Tenant and Share Tenant ask you to save a tenant first.
6. Leave each tenant field empty in turn: saving identifies the required field.
7. Save a complete tenant: the name and summary appear, and the input fields clear.
8. Call Tenant: the dialer contains the saved phone number. Return and share: a chooser offers compatible apps.
9. Rotate the screen: the saved tenant remains available for calling and sharing.
10. Press Back from Add Tenant: Login does not reappear.

## Build checks

```sh
./gradlew assembleDebug lintDebug testDebugUnitTest
```

Debug APK: `app/build/outputs/apk/debug/app-debug.apk`.

## Lab 6 additions

- MainActivity logs all seven lifecycle callbacks and saved state with `TENANT_LIFECYCLE`.
- Tenant implements Serializable; TenantRepository shares an in-memory list and total rent.
- Dashboard, Add Tenant and View Tenants have layouts and Fragment classes that clear bindings in onDestroyView.
- Navigation and Safe Args use version 2.9.8. The graph starts at Dashboard, has three actions, and accepts an optional `newTenant` argument at View Tenants.
- As required at the end of Practical 2, MainActivity still displays the original form. The NavHost, App Bar, Fragment button handlers and argument handling belong to Practical 3.

Check Logcat with `tag:TENANT_LIFECYCLE` while logging in, pressing Home, returning, rotating and pressing Back. Save a tenant before rotating and confirm its summary and Call Tenant still work. Open `app/src/main/res/navigation/nav_graph.xml` in Android Studio's Design view to inspect the three destinations and arrows.
