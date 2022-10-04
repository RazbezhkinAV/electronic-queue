package ru.razbezhkin.electronicqueue.UI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import ru.razbezhkin.electronicqueue.domain.RoomDto;
import ru.razbezhkin.electronicqueue.domain.TicketDto;
import ru.razbezhkin.electronicqueue.service.RoomService;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;

@Route("/api/v2/rooms")
@RequiredArgsConstructor
public class RoomUI extends VerticalLayout {

    private final RoomService roomService;

    @PostConstruct
    public void init() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();

        roomService.getAllRooms()
                .forEach(this::createRoomsButton);

    }

    private void createRoomsButton(RoomDto room) {
        Button button = new Button(room.getRoomName());
        Dialog dialog = new Dialog();
//        FormLayout timeLayout = createTimeLayout(ticketDto);
//
//        if (isEmpty(timeLayout)) {
//            return;
//        }
//
//        dialog.add(timeLayout);
//        button.addClickListener(event -> {
//            if (isEmpty(timeLayout)) {
//                remove(button);
//            } else {
//                dialog.open();
//            }
//        });
        add(button);
    }

    private boolean isEmpty(Component component) {
        return component.getChildren().findAny().isEmpty();
    }

    private FormLayout createTimeLayout(List<TicketDto> ticketDto) {
        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 5));

        ticketDto.stream()
                .sorted(Comparator.comparing(TicketDto::time))
                .forEach(it -> {
                    Button timeButton = createTicket(it);
                    formLayout.add(timeButton);
                    timeButton.addClickListener(event -> {
                        System.out.println("Time is busy " + event.getSource().getText());
                        formLayout.remove(timeButton);
                    });
                });

        return formLayout;
    }

    private Button createTicket(TicketDto ticket) {
        return new Button(ticket.time().toLocalTime().toString());
    }
}
