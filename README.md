# Pyxis

Open-source android spyware

The application is installed on the child's device as well as on the parent's device, in the login view the type of user is chosen.

# Features

- Multiple Child clients
- SMS: received/sent.
- Environment recording.
- Take pictures.
- Keylogger.
- Phishing social network.
- Notifications received: Whatsapp, Instagram, Messenger.

# Build this project

the application work with the api of firebase with which you will have to create a project in firebase and synchronize the application with such project.
[Firebase API](https://firebase.google.com/)

Enable the following development platforms on firebase:
`Authentication`, `realtime database` and `storage`.

- in authentication/sign-in method enable the `email` access provider

- in firebase real-time database assign the following rules:

```java
{
  "rules": {
    ".read": "auth != null",
      ".write": "auth != null"
  }
}
```

- in firebase storage assign the following rules:

```java
service firebase.storage {
  match /b/{bucket}/o {
    match /{allPaths=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

- In the `build.gradle` assign the social network package of your preference.
  also you will have to recreate the view in xml of the social network

```java
ext {
       PACKAGE_CHECK_SOCIAL = "\"PHISHING-SOCIAL_NETWORK\""
}
```

note: it is very important that accept all the necessary permissions for the application to work properly

# Disclaimer

The Pyxis application is intended for legal and educational purposes ONLY. It is a violation of the law to install surveillance software on a mobile phone that you have no right to monitor.

Pyxis is not responsible if the user does not follow the laws of the country and goes against it. If it is found that the user violates any law or spy in secret, he will be subject to sanctions that govern the legislation of the country.

# License

```java
Copyright [2023] [Viraj Bhartiya]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
