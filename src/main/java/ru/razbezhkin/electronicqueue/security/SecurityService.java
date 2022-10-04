package ru.razbezhkin.electronicqueue.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.razbezhkin.electronicqueue.UI.RoomUI;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationManager authenticationManager;

    public void authenticate(String login, String password) throws AuthenticationException {
        final Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UI.getCurrent().navigate(RoomUI.class);
    }

    public void logout() {
        VaadinSession.getCurrent().getSession().invalidate();
    }
}