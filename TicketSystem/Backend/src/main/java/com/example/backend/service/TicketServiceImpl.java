package com.example.backend.service;

import com.example.backend.entity.Ticket;
import com.example.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepo;


    @Override
    public void addTicket(Ticket ticket) {
        ticketRepo.save(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketRepo.save(ticket);
    }

    @Override
    public void resolveTicket(Long id) {
        Ticket temp = ticketRepo.findById(id).get();
        temp.setIsResolved("true");
        ticketRepo.save(temp);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return (List)ticketRepo.findAll();
    }

    @Override
    public List<Ticket> getUnResolvedTickets() {
        return (List)ticketRepo.findUnResolvedTickets();
    }

    @Override
    public List<Ticket> getResolvedTickets() {
        return (List)ticketRepo.findResolvedTickets();
    }

    @Override
    public void deleteTicketById(Long id) {
        ticketRepo.deleteById(id);
    }
}
