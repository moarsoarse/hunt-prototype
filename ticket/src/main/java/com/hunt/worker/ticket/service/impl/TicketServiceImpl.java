package com.hunt.worker.ticket.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hunt.worker.ticket.model.Ticket;
import com.hunt.worker.mapper.TicketMapper;
import com.hunt.worker.ticket.service.TicketService;
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService{

}
