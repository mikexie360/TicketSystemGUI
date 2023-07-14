package com.example.backend.service;

import com.example.backend.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    public void addTicket(Ticket ticket);
    public void updateTicket(Ticket ticket);
    public void resolveTicket(Long id);
    public List<Ticket> getAllTickets();
    public List<Ticket> getUnResolvedTickets();
    public List<Ticket> getResolvedTickets();

    public void deleteTicketById(Long id);
}
