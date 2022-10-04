package ru.razbezhkin.electronicqueue.UI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.razbezhkin.electronicqueue.domain.ReservationData;
import ru.razbezhkin.electronicqueue.domain.RoomDto;
import ru.razbezhkin.electronicqueue.domain.TicketDto;
import ru.razbezhkin.electronicqueue.service.RoomService;
import ru.razbezhkin.electronicqueue.service.TicketService;

import javax.annotation.PostConstruct;
import java.util.Comparator;

@Route("/api/v2/rooms")
@RequiredArgsConstructor
public class RoomUI extends VerticalLayout {

    private final RoomService roomService;
    private final TicketService ticketService;

    private final HeaderLogout headerLogout;

    @PostConstruct
    public void init() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        add(headerLogout, new H1("Take Rooms"));

        roomService.getAllRooms()
                .forEach(this::createRoomsButton);

    }

    private void createRoomsButton(RoomDto room) {
        Button button = new Button(room.getRoomName());
        Dialog dialog = createTicketDialog();
        FormLayout ticketLayout = createTimeLayout(room);

        if (isEmpty(ticketLayout)) {
            return;
        }

        dialog.add(ticketLayout);
        button.addClickListener(event -> {
            if (isEmpty(ticketLayout)) {
                remove(button);
            } else {
                dialog.open();
            }
        });
        add(button);
    }

    private Dialog createTicketDialog() {
        Dialog dialog = new Dialog();
        VerticalLayout header = new VerticalLayout();
        header.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        header.add(new H3("Free times"));
        dialog.add(header);
        return dialog;
    }

    private boolean isEmpty(Component component) {
        return component.getChildren().findAny().isEmpty();
    }

    private FormLayout createTimeLayout(RoomDto room) {
        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 5));

        ticketService.getTodayFreeTicketsByRoomName(room.getRoomName()).stream()
                .sorted(Comparator.comparing(TicketDto::time))
                .forEach(it -> {
                    Button timeButton = createTicket(it);
                    formLayout.add(timeButton);
                    timeButton.addClickListener(event -> {
                        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                        ticketService.saveTicket(
                                new ReservationData(
                                        user.getName(),
                                        timeButton.getText(),
                                        room.getRoomName()
                                )
                        );
                        formLayout.remove(timeButton);
                    });
                });

        return formLayout;
    }

    private Button createTicket(TicketDto ticket) {
        return new Button(ticket.time().toLocalTime().toString());
    }
}
