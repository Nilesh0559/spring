# Spring Security

## Terminologies

### Authentication

- Authentication is - who you are.

1. Password (Knowledge Based)
2. Text Message, OTP, Access Token Device (Possession Based)
3. Combination of both Knowledge Based & Possession Based (Multi-Factor)

### Authorization:

- Authorization is - what you're allowed to do.

### Principal:

- Currently logged in user/account.

### Granted Authority:

- Permissions allowed for the user/account.
- These are fine-grained.

### Roles:

- Group (Combination) of granted authorities.
- These are coarse-grained.

## Adding Spring Security in application:

- Add spring-boot-starter-security dependency in your application.

## Spring Security Working:

- Spring security works on the **basis of filters**.
- It has lot of filters serving different purpose and they intercept the request before passing it to the controller/servlet.
- When any client sends the request to server, it's first intercepted by spring security filter - `DelegatingFilterProxy`. As the name suggests, this filter doesn't do anything rather it delegates the request to different other filters.
- One of the filter is `AuthenticationFilter`. This filter checks the request URL to see if it matches the `RequestMatcher`. If it doesn't then the request is passed to next filter & if it does then this filter converts the `HttpServletRequest` to `Authentication` object.
- The `Authentication` object is passed to right `AuthenticationManager` (_done by `AuthenticationManagerResolver`_). Since `AuthenticationManager` being an interface, the common implementation of it is `ProviderManager` which has one method `authenticate()` which takes in the `Authentication` object and consults with different different `AuthenticationProvider`. This is done by calling `support()` method of `AuthenticationProvider`.
- When `ProviderManager` finds the right `AuthenticationProvider`, it calls the `authenticate()` of it and passes the same `Authentication` object.
- This `authenticate()` of `AuthenticationProvider` does the actual authentication by using `UserDetailsService` & calling its `loadUserByUserName()` and passing the username as argument.
- If the user is legit user, then it returns the object of `UserDetails` to caller (_after password verification_) which is then converted into `Principal` object and put it in same `Authentication` object.
- Lastly, this `Authentication` object is returned to filter and also added in `SecurityContext` of the application so that the subsequent request from same user/session are permitted.

#### By just adding the dependency, it by default -

1. Adds mandatory authentication for all URLs expect some endpoints like `/error` or `/logout`.
2. Adds sign in form with `/login` endpoint.
3. Perform validations and handles login errors.
4. It configures random generated password with by default user - **user**. This username and password can be changed/overrriden by adding below two properties in **application.properties** file.
   `spring.security.user.name`
   `spring.security.user.password`
